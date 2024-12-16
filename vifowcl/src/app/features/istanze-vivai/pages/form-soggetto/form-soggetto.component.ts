/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { CONST } from 'src/app/shared/constants';
import { ComuneModel } from 'src/app/shared/models/comune.model';
import { SoggettoModel } from 'src/app/shared/models/soggetto.model';
import { Content, InstanceDomandaTable } from 'src/app/shared/models/view-instance';
import { DizionariService } from '../../services/dizionari.service';
import { VivaiService } from '../../services/vivai.service';
import { codiceFiscale } from 'src/app/validators/string.validators';

@Component({
  selector: 'app-form-soggetto',
  templateUrl: './form-soggetto.component.html',
  styleUrls: ['./form-soggetto.component.css']
})
export class FormSoggettoComponent implements OnInit {

  soggettoForm: FormGroup;
  isPF:boolean = true;
  idSoggetto:number;
  submited:boolean = false;
  isDomandeVisibili:boolean = false;
  domandeTableData: InstanceDomandaTable[];
  totalCountDomande:number;
  unsubscribe$ = new Subject<void>();
  paging: any;
  comune: ComuneModel[];
  tuttiComuni: ComuneModel[];

  isComuneEstero:boolean = false;
  isEntePubblico:boolean;

  numDomandeBySoggetto:number;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(private fb: FormBuilder,private router: Router, public route: ActivatedRoute,
    private vivaiService: VivaiService, private dizionariService: DizionariService) { }

  ngOnInit() {
    this.idSoggetto = this.route.snapshot.params['id'];
    sessionStorage.setItem('searchType','soggetto');
    this.vivaiService.getSoggettiById(this.idSoggetto)
    .subscribe((res)=>{
      this.initSoggettoForm(res, res.cognome != null);
    })

    this.vivaiService.getNumDomandeBySoggetto(this.idSoggetto)
    .subscribe((res)=>{
      this.numDomandeBySoggetto = res;
    })

    if(this.route.snapshot.queryParamMap.get("back")){
      this.ricerca();
    }

    this.dizionariService.getAllComuni().pipe(takeUntil(this.unsubscribe$)).subscribe((res: ComuneModel[]) => {
      this.tuttiComuni = res;
    });
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    if(this.popUpBtn1 == 'Termina'){
      this.indietro();
    }
  }

  changeSoggettoType(){
    this.soggettoForm.get('partitaIva').patchValue('');
    this.initSoggettoForm(this.soggettoForm.value, this.isPF);
  }

  initSoggettoForm(soggettoData:SoggettoModel, isPF:boolean) {
    this.isPF = isPF;
    this.isEntePubblico = (soggettoData.flgEntePubblico == 1);
    this.soggettoForm = this.fb.group({
      idSoggetto: [{ value: soggettoData ? soggettoData.idSoggetto : '', disabled: false },
        [Validators.required]],
      codiceFiscale: [{ value: soggettoData ? soggettoData.codiceFiscale : '', disabled: false },
        [Validators.required,codiceFiscale()]],
      cognome: [{ value: soggettoData ? soggettoData.cognome : '', disabled: false },
        isPF?[Validators.required]:[]],
      nome: [{ value: soggettoData ? soggettoData.nome : '', disabled: false },
        isPF?[Validators.required]:[]],
      partitaIva: [{ value: soggettoData ? soggettoData.partitaIva : '', disabled: false },
        isPF?[]: !this.isEntePubblico?[Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),Validators.minLength(11)]
        :[Validators.required,Validators.minLength(4)]],
      ragioneSociale: [{ value: soggettoData ? soggettoData.ragioneSociale : '', disabled: false },
        !isPF?[Validators.required]:[]],
      pec: [{ value: soggettoData ? soggettoData.pec : '', disabled: false },
        !isPF?[Validators.required, Validators.pattern(CONST.PATTERN_MAIL)]:[Validators.pattern(CONST.PATTERN_MAIL)]],
      indirizzo: [{ value: soggettoData ? soggettoData.indirizzo : '', disabled: false },
        [Validators.required]],
      comune: [{ value: soggettoData ? soggettoData.comune : '', disabled: false },
        [Validators.required]],
      cap: [{ value: soggettoData ? soggettoData.cap : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      mail: [{ value: soggettoData ? soggettoData.mail : '', disabled: false },
        [Validators.required,Validators.pattern(CONST.PATTERN_MAIL)]],
      telefono: [{ value: soggettoData ? soggettoData.telefono : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      codiceBeneficiario: [{ value: soggettoData ? soggettoData.codiceBeneficiario : '', disabled: true },
        [Validators.required]],
      flgEntePubblico: [{ value: soggettoData ? soggettoData.flgEntePubblico : 0, disabled: false },
        [Validators.required]]

    })
    this.onComuneChange();

  }

  clearValue(){
    this.soggettoForm.get('comune').patchValue('');
  }

  chengeComuneEstero(){
    this.isComuneEstero = document.getElementById('comuneEstero')['checked'];
    if(this.isComuneEstero){
      let comEstero = this.dizionariService.autocompleteFilter({query:'Comune estero'}, 
        this.tuttiComuni, 'Comune')[0];
      this.soggettoForm.get('comune').patchValue(comEstero);
    }else{
      this.clearValue();
    }
  }

  onComuneChange(){
    let val = this.soggettoForm.get('comune').value;
    this.isComuneEstero = (val && val.codIstat == '999999');
  }



  goToHome(){
    this.router.navigate(['/content'])
  }

  indietro(){
    this.router.navigate(['/content/anagrafica-soggetti'])
  }

  conferma(){
    if(this.soggettoForm.invalid){
      this.submited = true;
      this.showModalMsg('Errore','Sono presenti degli errori nella compilazione dei campi. Seguire le indicazioni evidenziate in rosso.','OK');
    }else{
      let values = this.soggettoForm.value;
      this.vivaiService.saveSoggetto(values)
      .subscribe((res)=>{
        this.showModalMsg('Info','Soggetto salvato correttamente!','Termina');
      })
    }
  }

  hideDomande(){
    this.domandeTableData = null;
  }

  ricerca(){
    if(!this.paging){
      this.paging = {
        page: 1,
        limit: 5
      };
    }
    this.ricercaExecute(this.paging);
  }

  ricercaExecute(paging:any){
    this.vivaiService.searchDomanda(paging,{'idSoggetto':this.idSoggetto})
    .pipe(takeUntil(this.unsubscribe$))
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

  getUpdatedTable(event) {
    this.paging = event;
    this.ricercaExecute(this.paging);
  }

  filterComune(event) {
    if(this.tuttiComuni){
      this.comune = this.dizionariService.autocompleteFilter(event, this.tuttiComuni, 'Comune');
    }
  }

}
