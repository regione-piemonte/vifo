/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/components/common/selectitem';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ComuneModel } from 'src/app/shared/models/comune.model';
import { SpecieRichiesta } from 'src/app/shared/models/specie-richiesta.model';
import { DizionariService } from '../../services/dizionari.service';
import { CONST } from 'src/app/shared/constants';
import { VivaiService } from '../../services/vivai.service';
import { Content, InstanceDomandaTable } from 'src/app/shared/models/view-instance';
import { TableHeader } from 'src/app/shared/models/table-header';
import { SavedSearchModel } from 'src/app/shared/models/savedSearchModel';
import { DdtRiepilogoModel } from 'src/app/shared/models/ddt.model';
import { ConfigService } from 'src/app/shared/services/config.service';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';
import { UtilService } from '../../services/util.service';

@Component({
  selector: 'app-ricerca-archivio',
  templateUrl: './ricerca-archivio.component.html',
  styleUrls: ['./ricerca-archivio.component.css']
})
export class RicercaArchivioComponent implements OnInit {

  ricercaType:string='domande';
  ricercaDomandeForm:FormGroup;
  ricercaDdtForm:FormGroup;
  listVivai: SelectItem[]=[];
  listTipoDomanda: SelectItem[]=[];
  listStatoDomanda: SelectItem[]=[];
  listSpecieRichieste: SpecieRichiesta[]=[];
  selectedSpecie:SpecieRichiesta[]=[];

  listAllCognomeRichiedente: SelectItem[];
  listFilteredCognomeRichiedente: SelectItem[];

  listAllDenominazioneRichiedente: SelectItem[];
  listFilteredDenominazioneRichiedente: SelectItem[];

  listAllComuni: ComuneModel[];
  listFilteredComuni: ComuneModel[];

  unsubscribe$ = new Subject<void>();
  currentYear: number = new Date().getFullYear();
  it: any = CONST.IT;

  domandeTableData: InstanceDomandaTable[];
  domandeTableHeaders: TableHeader[];

  ddtTableData: DdtRiepilogoModel[];
  ddtTableHeaders: TableHeader[] = [
    {field: 'numeroDdt', header: 'Numero DDT'},
    {field: 'dataCreazione', header: 'Data creazione'},
    {field: 'idDomanda', header: 'Numero domanda'}
  ];

  paging: any;
  totalCountDomande: number;
  totalCountDdt: number;
  savedSearch: SavedSearchModel;
  userProfile:string;

  first:number=0;

  constructor(
    private router: Router,
    private dizionariService: DizionariService,
    private vivaiService: VivaiService,
    private configService: ConfigService,
    private dialogService: DialogService, 
    private utilService: UtilService,
    private fb: FormBuilder) { }

  ngOnInit() {

    this.userProfile = this.utilService.getUserProfile();
    this.domandeTableHeaders = this.configService.getDomandeTableHeaders();
    // problemi con questa sezione:
    this.savedSearch = JSON.parse(sessionStorage.getItem("search"));

    if(this.savedSearch){
      this.first = this.savedSearch.paging.first;
      this.paging = this.savedSearch.paging;
    }
    if(this.savedSearch && this.savedSearch.type == 'ddt'){
      this.ricercaType = 'ddt';
      this.initRicercaDdtForm(this.savedSearch.values);
    }else{
      this.initRicercaDomandeForm(this.savedSearch?this.savedSearch.values:{});
    }
    sessionStorage.setItem('searchType',this.ricercaType);

    let allValuesItem = {label:'Tutti', value:''}
    this.dizionariService.getListVivaio().subscribe((response: SelectItem[]) => {
      this.listVivai = response;
      this.listVivai.unshift(allValuesItem);
    });

    this.dizionariService.getListStatoDomanda().subscribe((response: SelectItem[]) => {
      this.listStatoDomanda = response;
      this.listStatoDomanda.unshift(allValuesItem);
    });

    this.dizionariService.getListTipoDomanda().subscribe((response: SelectItem[]) => {
      this.listTipoDomanda = response;
      this.listTipoDomanda.unshift(allValuesItem);
    });

    this.dizionariService.getListSpecie().subscribe((response: SpecieRichiesta[]) => {
      this.listSpecieRichieste = response;
    });

  }

  clearValue(field){
    this.ricercaDomandeForm.get(field).patchValue('');
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  ricercaDomande(){
    this.domandeTableData = [];
    this.initPaging();
    this.ricerca();
  }

  ricercaDdt(){
    this.ddtTableData = [];
    this.initPaging();
    this.ricerca();
  }

  initPaging(){
    this.first = 0;
    this.paging = {
      page: 1,
      limit: 5,
      first: 0
    };
  }

  ricerca(){
    if(!this.paging){
      this.initPaging();
    }
    
    if(this.ricercaType == 'domande'){
      this.searchDomandeInArchivio(this.paging);
    }else{
      this.searchDdtInArchivio(this.paging);
    }
  }

  getUpdatedTable(event) {
    if(this.savedSearch){
      this.paging = this.savedSearch.paging;
      this.savedSearch = null;
    }else{
      this.paging = event;
    }
    if(this.ricercaType == 'domande'){
      this.searchDomandeInArchivio(this.paging);
    }else{
      this.searchDdtInArchivio(this.paging);
    }
  }

  searchDdtInArchivio(paging: any){
    // this.ddtTableData.length = 0;
    let values = this.ricercaDdtForm.value;
    sessionStorage.setItem('search', JSON.stringify({type:'ddt',paging:paging,values:values}));
    this.vivaiService.searchDdt(paging, this.getSearchValues(values))
    .subscribe((res: Content<DdtRiepilogoModel[]>) => {
      if (res.totalElements !== 0) {
        res.content.forEach((element, index) => {
        });
        this.ddtTableData = res.content;
      }else{
        this.ddtTableData = [];
      }
      this.totalCountDdt = res.totalElements;
    })
  }

  searchDomandeInArchivio(paging: any){
    // this.domandeTableData.length = 0;
    let values = this.ricercaDomandeForm.value;
    sessionStorage.setItem('search', JSON.stringify({type:'domande',paging:paging,values:values}));
    let biValue = this.ricercaDomandeForm.controls['tipo'].value;
    biValue = biValue?biValue.split('-'):'';
    if(biValue.length > 0){
      values['tipo'] = biValue[0]; 
    }else{
      delete values['tipo'];
    }
    if(biValue.length > 1){
      values['scopo'] = biValue[1];
    }
    this.vivaiService.searchDomanda(paging,this.getSearchValues(values)).pipe(takeUntil(this.unsubscribe$))
    .subscribe((res: Content<InstanceDomandaTable[]>) => {
      if (res.totalElements !== 0) {
        res.content.forEach((element, index) => {
          if (element.tipoDomanda == '2') {
            element.tipoDomanda = 'VENDITA';
          }else{
            element.tipoDomanda = 'GRATUITA';
          }
        });
        this.domandeTableData = res.content;
      }else{
        this.domandeTableData = [];
      }
      this.totalCountDomande = res.totalElements;
      
    })
  }

  resetFormDomande(){
    this.ricercaDomandeForm.reset();
  }
  resetFormDdt(){
    this.ricercaDdtForm.reset();
  }

  initRicercaDomandeForm(values){
    this.ricercaDomandeForm = this.fb.group({
      idDomanda: [values.idDomanda?values.idDomanda:'',
        [Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      idVivaio: [values.idVivaio?values.idVivaio:''],
      tipo: [values.tipo?values.tipo:''],
      dataInvioDa: [values.dataInvioDa?new Date(Date.parse(values.dataInvioDa)):null],
      dataInvioA: [values.dataInvioA?new Date(Date.parse(values.dataInvioA)):null],
      stato: [values.stato?values.stato:''],
      cognome: [values.cognome?values.cognome:''],
      denominazione: [values.denominazione?values.denominazione:''],
      comune: [values.comune?values.comune:''],
      specieRichieste: [values.specieRichieste?values.specieRichieste:''],
      domandaVivaio: [values.specieRichieste?values.domandaVivaio:''],
      escludiDomandeChiuse: [values.escludiDomandeChiuse?values.escludiDomandeChiuse:'']
    });
    this.ricerca();
  }

  initRicercaDdtForm(values){
    this.ricercaDdtForm = this.fb.group({
      numeroDdt: [values.numeroDdt?values.numeroDdt:'', 
        [Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      annoDdt: [values.annoDdt?values.annoDdt:''],
      idDomanda: [values.idDomanda?values.idDomanda:''],
      cognome: [values.cognomeR?values.cognome:''],
      denominazione: [values.denominazione?values.denominazione:''],
    });  
    this.ricerca();
  }

  changeRicercaType(ricercaType:string){
    this.initPaging();
    this.ricercaType = ricercaType
    sessionStorage.setItem('searchType',this.ricercaType);
    if(ricercaType == 'ddt' && !this.ricercaDdtForm){
      
      this.initRicercaDdtForm({});
    }
    if(ricercaType == 'domande' && !this.ricercaDomandeForm){
      this.initRicercaDomandeForm({});
      }
  }

  filterComune(event) {
    if(this.listAllComuni){
      this.listFilteredComuni = this.dizionariService.autocompleteFilter(event, this.listAllComuni, 'Comune');
    }else{
      this.dizionariService.getAllComuni().pipe(takeUntil(this.unsubscribe$)).subscribe((res: ComuneModel[]) => {
        this.listAllComuni = res;
        this.listFilteredComuni = this.dizionariService.autocompleteFilter(event, res, 'Comune');
      });
    }
  }

  filterCognomeRichiedente(event) {
    if(this.listAllCognomeRichiedente){
      this.listFilteredCognomeRichiedente = this.dizionariService.autocompleteFilter(event, this.listAllCognomeRichiedente, 'SelectItem');
    }else{
      this.dizionariService.getListCognomeRichiedente().pipe(takeUntil(this.unsubscribe$))
        .subscribe((res: SelectItem[]) => {
        this.listAllCognomeRichiedente = res;
        this.listFilteredCognomeRichiedente = this.dizionariService.autocompleteFilter(event, res, 'SelectItem');
      });
    }
  }

  filterDenominazioneRichiedente(event) {
    if(this.listAllDenominazioneRichiedente){
      this.listFilteredDenominazioneRichiedente = this.dizionariService.autocompleteFilter(event, this.listAllDenominazioneRichiedente, 'SelectItem');
    }else{
      this.dizionariService.getListDenominazioneRichiedente().pipe(takeUntil(this.unsubscribe$))
        .subscribe((res: SelectItem[]) => {
        this.listAllDenominazioneRichiedente = res;
        this.listFilteredDenominazioneRichiedente = this.dizionariService.autocompleteFilter(event, res, 'SelectItem');
      });
    }
  }

  resetAutocomplete(fieldName){
    if(this.ricercaType == 'domande'){
      this.ricercaDomandeForm.get(fieldName).patchValue('');
    }else if(this.ricercaType == 'ddt'){
      this.ricercaDdtForm.get(fieldName).patchValue('');
    }
    
  }

  getSearchValues(values){
    let retValues = {};
    let keys = Object.keys(values);
    let value;
    for(let i in keys){
      value = values[keys[i]];
      console.log(keys[i]);
      if(Number.isInteger(value) || (value && value.length > 0)){
        retValues[keys[i]] = value;
      }else if(value instanceof Date){
        retValues[keys[i]] = this.formatDate(value);
      }else if(value == true){
        retValues[keys[i]] = value;
      }else if(value && value.value){
        retValues[keys[i]] = value.value;
      }else if(value && value.codIstat){
        retValues[keys[i]] = value.codIstat;
      }
    }
    return retValues;
  }

  formatDate(date) {
    if (date === null || date === "" || date === undefined) {
      return "";
    } else {
      var d = new Date(date),
        month = "" + (d.getMonth() + 1),
        day = "" + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = "0" + month;
      if (day.length < 2) day = "0" + day;
      return [year, month, day].join("");
    }
  }

  viewDdt(event){
    this.router.navigate(['/content/dettaglio-ddt/' + event.numeroDdt + '/' + event.annoDdt]);
  }

  viewDomanda(event){
    const viewMode : boolean = true;
    this.router.navigate([ this.configService.getPathByStato(event.stato) + event.idDomanda]);
  }

  deleteRow(event){
    this.dialogService.showDialog(true, 'Confermare la cancellazione della domanda?', 'Attenzione', DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) {
        this.deleteConfirm(event.idDomanda);
      }
    });
  }

  deleteConfirm(event) {
    this.vivaiService.deleteDomanda(event).subscribe(res => {
      console.log('delete done');
      this.ricerca();
    })
  }

  editRow(event) {
    if(event.stato == 'BOZZA'){
       this.router.navigate(['content/modifica/' + event.idDomanda]);
    }
  }
}
