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
import { CONST } from 'src/app/shared/constants';
import { ComuneModel } from 'src/app/shared/models/comune.model';
import { SavedSearchModel } from 'src/app/shared/models/savedSearchModel';
import { TableHeader } from 'src/app/shared/models/table-header';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { DizionariService } from '../../services/dizionari.service';
import { VivaiService } from '../../services/vivai.service';
import { UtilService } from '../../services/util.service';
import { UserModel } from 'src/app/shared/models/user.model';

@Component({
  selector: 'app-anagrafica-soggetti',
  templateUrl: './anagrafica-soggetti.component.html',
  styleUrls: ['./anagrafica-soggetti.component.css']
})
export class AnagraficaSoggettiComponent implements OnInit {

  ricercaForm: FormGroup;
  listProvince: SelectItem[]=[];
  comune: ComuneModel[];
  listComuni: ComuneModel[]=[];
  tuttiComuni: ComuneModel[];
  isFirstSearch:boolean = true;

  isComuneEstero:boolean = false;
  
  unsubscribe$ = new Subject<void>();

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;
  
  user :UserModel;
  isUserVivaio:boolean;
  tableSoggetti: any[] = [];
  totalCount:number;
  savedSearch: SavedSearchModel;
  paging: any;
  tableHeaders: TableHeader[] = [
    {field: 'cognomeRagSociale', header: 'COGNOME NOME / RAGIONE SOCIALE'},
    {field: 'cfPiva', header: 'CODICE FISCALE / PARTITA IVA'},
    {field: 'comune', header: 'COMUNE DI RESIDENZA'},
    {field: 'telefono', header: 'TELEFONO'},
    {field: 'mail', header: 'MAIL'}
  ];

  first:number=0;

  constructor(private fb: FormBuilder, private dizionariService :DizionariService,
    private router: Router, private dialogService: DialogService,
    private vivaiService: VivaiService, private utilService: UtilService) { }

    ngOnInit() {
      this.isUserVivaio = this.utilService.getUser().profile == CONST.PROFILE.VIVAIO ;
      if(this.isUserVivaio){
        this.tableHeaders.push({field: 'numDomande', header: 'Nr DOMANDE INOLTRATE AL VIVAIO'});
      }else{
        this.tableHeaders.push({field: 'numDomande', header: 'DOMANDE ASSOCIATE'});
      }

      this.dizionariService.getAllComuni().pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ComuneModel[]) => {
        this.tuttiComuni = res;
      });
      this.dizionariService.getAllProvince().subscribe((response: SelectItem[]) => {
        this.listProvince = response;
        let formValues = null;
        let savedSearch = JSON.parse(sessionStorage.getItem("anagSoggetti"));
        if(savedSearch){
          formValues = savedSearch.values;
        }
        this.initForm(formValues);
      });
    }

    showModalMsg(type:string, msg:string, btn1:string){
      this.popUpType= type;
      this.popUpMsg= msg;
      this.popUpBtn1 = btn1;
    }
    
    closePopUp(){
      this.popUpMsg = null;
    }

  initForm(ricercaData:any) {
    this.ricercaForm = this.fb.group({
      cognome: [{ value: ricercaData ? ricercaData.cognome : '', disabled: false },
        [Validators.minLength(3)]],
      ragioneSociale: [{ value: ricercaData ? ricercaData.ragioneSociale : '', disabled: false },
      [Validators.minLength(3)]],
      codFiscalePiva: [{ value: ricercaData ? ricercaData.codFiscalePiva : '', disabled: false },
      [Validators.minLength(3)]],
      provincia: [{ value: ricercaData ? ricercaData.provincia : '', disabled: false }],
      comune: [{ value: ricercaData ? ricercaData.comune : {}, disabled: false }]
      });
      if(ricercaData && ricercaData.comune){
        this.isComuneEstero = ricercaData.comune.codIstat == '999999';
      }
  }

  getTimeNow(){
    return new Date().getTime();
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  indietro(){
    if(this.tableSoggetti != null){
    this.tableSoggetti = null;
    }else{
      this.router.navigate(['/content/anagrafiche']);
    }
  }

  getUpdatedTable(event) {
    this.savedSearch = JSON.parse(sessionStorage.getItem("anagSoggetti"));
    if(this.savedSearch && this.isFirstSearch){
      this.first = this.savedSearch.paging.first;
      this.paging = this.savedSearch.paging;
      this.initForm(this.savedSearch.values);
      this.savedSearch = null;
      this.ricerca();
    }else{
      this.paging = event;
        if(this.isFirstSearch){
          this.tableSoggetti = null;
        }else{
          this.ricerca();
        }
    }
   
  }

  avviaRicerca(){
    this.paging.page=1;
    this.ricerca();
  }

  ricerca(){   
    this.isFirstSearch = false;
    let values = this.getValuesSearch();
    if(values.cognome.length == 0 && values.ragioneSociale.length == 0 
        && values.codFiscalePiva.length == 0 && !values.provincia && !values.comune.codIstat){
          this.showModalMsg('Attenzione', 'Valorizzare almeno un parametro di ricerca','OK');
          return;
        }
    values.comune = values.comune?values.comune.codIstat:null;
    this.vivaiService.searchSoggetti(this.paging,values)
    .subscribe((res)=>{
      this.tableSoggetti = res.content;
      this.totalCount = res.totalElements;
      sessionStorage.setItem('anagSoggetti', JSON.stringify({'paging':this.paging,'values':this.getValuesSearch()})); 
    })
  }

  getValuesSearch(){
    return JSON.parse(JSON.stringify(this.ricercaForm.value));
  }

  viewRow(event){
    this.router.navigate(['/content/form-soggetto/' + event.idSoggetto])
  }

  onChangeProvincia(obj){
    this.ricercaForm.get('comune').patchValue({});
      this.listComuni = this.tuttiComuni.filter((item:ComuneModel)=>{
        return item.codIstat.indexOf(obj.value) == 0;
      });
      this.isComuneEstero = false;
  }

reset(item){
  this.ricercaForm.get(item).patchValue('');
}

  chengeComuneEstero(){
    this.ricercaForm.get('provincia').patchValue({});
    this.ricercaForm.get('comune').patchValue({});
    this.isComuneEstero = document.getElementById('comuneEstero')['checked'];
    if(this.isComuneEstero){
      let comEstero = this.dizionariService.autocompleteFilter({query:'Comune estero'}, 
        this.tuttiComuni, 'Comune')[0];
      this.ricercaForm.get('comune').patchValue(comEstero);
      this.listComuni = [comEstero];
    }else{
      this.listComuni = [];
    }
  }

  filterComune(event) {    
    this.comune = this.dizionariService.autocompleteFilter(event, this.listComuni, 'Comune');    
  }

  clearValue(){
    this.ricercaForm.get('comune').patchValue({});
  }

}
