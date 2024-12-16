/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';


import { VivaiService } from '../../services/vivai.service';
import { DizionariService } from '../../services/dizionari.service';
import { SelectItem } from 'primeng/components/common/selectitem';
import { HomeModel } from 'src/app/shared/models/home.model';
import { CONST } from 'src/app/shared/constants';
import { SpecieRichiesta } from 'src/app/shared/models/specie-richiesta.model';
import { DestinazioneModel } from 'src/app/shared/models/destinazione.model';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { SoggettoModel } from 'src/app/shared/models/soggetto.model';
import { DocumentoAllegato } from 'src/app/shared/models/plain-sesto-sezione.model';
import { UtilService } from '../../services/util.service';
import { cfPiva, codiceFiscale, oneOfTwo, partitaIva } from '../../../../validators/string.validators';
import { ConfigService } from 'src/app/shared/services/config.service';
import { DomandaModel } from 'src/app/shared/models/domanda.model';


@Component({
  selector: 'app-form-domanda',
  templateUrl: './form-domanda.component.html',
  styleUrls: ['./form-domanda.component.css']
})
export class FormDomandaComponent implements OnInit{

  idDomanda:number;
  user : HomeModel;
  tipoDomanda: number = 0;
  datiDomandaForm: FormGroup;
  companyForm: FormGroup;
  personaForm: FormGroup;
  persCompForm: FormGroup;
  destinazioniForm: FormGroup;
  specieForm: FormArray = this.fb.array([]);
  personData:any;
  listVivai: SelectItem[]=[];
  listScopo: SelectItem[]=[];
  listTitolarita: SelectItem[]=[];
  domanda:DomandaModel;
  userProfile:string;

  viewMode:boolean = false;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  viewType:string='partOne';
  submited:boolean=false;

  loggedSoggetto: SoggettoModel;
  isForcedPersonaGiuridica:boolean;
  listDestinazioni:DestinazioneModel[]=[];
  listDocumenti:DocumentoAllegato[]=[];

  unsubscribe$ = new Subject<void>();

  idScopo:number;

  constructor(
    private router: Router,
    private vivaiService: VivaiService,
    private dizionariService: DizionariService,
    private fb: FormBuilder,
    private configService: ConfigService,
    private utilService: UtilService,
    public route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.user = this.utilService.getUser();

    this.userProfile = this.utilService.getUserProfile();
    
    this.initDestinazioniForm();
    this.dizionariService.getListTitolarita() 
    .subscribe((response: SelectItem[]) => {
      this.listTitolarita = response;
    }
    );
    this.dizionariService.getListVivaio()
    .subscribe((response: SelectItem[]) => {
      this.listVivai = response;
    });

    this.dizionariService.getListScopo()
    .subscribe((response: SelectItem[]) => {
      this.listScopo = response;
    });

    const data = this.route.snapshot.params['viewMode'];
    this.viewMode = data?data:false;
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      this.tipoDomanda = +paramMap.get("tipoDomanda");
      this.idDomanda = paramMap.get("idDomanda")?+paramMap.get("idDomanda"):null;
      if(!this.idDomanda){
        sessionStorage.removeItem('searchType');
      }
    });
    this.loadLoggedSoggetto();
    this.loadDomanda();
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }

  closePopUp(){
    this.popUpMsg = null;
    if(this.popUpBtn1 == 'Termina'){
      if(this.viewType == 'sent'){
        if(this.userProfile == 'VIVAIO'){
          this.router.navigate([ this.configService.getPathByStato("INVIATA") + this.idDomanda]);
        }else{
          this.goToHome();
        }
      }
    }
  }

  loadDomanda(){
    if(this.idDomanda){
      this.viewType = 'partTwo';
      this.vivaiService.loadDomanda(this.idDomanda).pipe(takeUntil(this.unsubscribe$))
      .subscribe(res => {
        this.domanda = res;
        this.idScopo = this.domanda.fkScopo;
        if(this.viewMode){
          this.viewType = 'VIEW';
        }else if(res.statoDomanda != 'BOZZA' ){
          if(this.userProfile == 'RICHIEDENTE'){
            this.viewType = res.statoDomanda;
          }else if(res.statoDomanda == 'BOZZA' || res.statoDomanda == 'INVIATA' || res.statoDomanda == 'PREASSEGNATA' ){
            //this.viewType = 'partOne'; //per tornare nella "parteOne" deve eliminare i documenti caricati

          }
        }
        this.initDomandaForm(res);
        this.tipoDomanda = res.fkTipoDomanda ;
        if(res.fkScopo > 0 && res.fkScopo < 5){
          this.isForcedPersonaGiuridica = true;
        }
        if(res.soggetto){
          this.personData = res.soggetto;
          if(res.soggetto.ragioneSociale){
            this.initCompanyForm(res.soggetto);
          }else{
            this.initPersonalForm(res.soggetto);
          }
        }
        if(res.listSpecie){
          for(let i in res.listSpecie){
            this.addSpecieForm(res.listSpecie[i]);
          }
        }
        if(res.listDestinazione){
          this.listDestinazioni = res.listDestinazione;
        }
        if(res.listDocumenti){
          this.listDocumenti = res.listDocumenti;
        }
      });
    }else{
      this.initDomandaForm(null);
    }
  }

  addDocumento(doc:DocumentoAllegato){
    //call be for add documento
    this.listDocumenti.push(doc);
  }

  personTypeChange(type:number){ 
    if(type == 1){
      this.initPersonalForm(this.personData != null?this.personData:
        this.userProfile=='RICHIEDENTE'?this.loggedSoggetto:null);
    }else{
      this.initCompanyForm(this.personData);
    }
  }

  initDomandaForm(domandaData){
    this.datiDomandaForm = this.fb.group({
      idDomanda: [{ value: domandaData ? domandaData.idDomanda : '', disabled: true }],
      fkTipoDomanda: [{ value: domandaData ? domandaData.fkTipoDomanda : this.tipoDomanda, disabled: false }],
      idVivaio: [{ value: this.user.fkVivaio? this.user.fkVivaio:domandaData ? domandaData.fkVivaio : '', 
        disabled: this.userProfile =='VIVAIO' },[Validators.required,Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      statoDomanda: [{ value: domandaData ? domandaData.statoDomanda : 'BOZZA', disabled: true }],
      scopoAssegnGratuita: [{ value: domandaData ? domandaData.fkScopo : '', disabled: false },
        this.tipoDomanda==1?Validators.required:null],
      titolaritaRichiedente: [{ value: domandaData ? domandaData.fkTitolarita : '', disabled: false },
        this.tipoDomanda==1?Validators.required:null],
      marcaBollo: [{ value: domandaData ? domandaData.numMarcaBollo : '', disabled: false },
        [Validators.minLength(14)]],
      note: [{ value: domandaData ? domandaData.note : '', disabled: false }],
      specieForm: this.specieForm
    });
  }

  fillPersonCompanyForm(data:HomeModel){
    if(data.ragioneSociale || data.flgEntePubblico){
      this.initCompanyForm(data);
    }else{
      this.initPersonalForm(data);
    }
  }

  initPersonalForm(personaData) {
    this.companyForm = null;
    this.personaForm = this.fb.group({
      nome: [{ value: personaData ? personaData.nome : '', disabled: false },
        [Validators.required]],
      cognome: [{ value: personaData ? personaData.cognome : '', disabled: false },
        [Validators.required]],
      codiceFiscale: [{ value: personaData ? personaData.codiceFiscale : '', disabled: false },
        [Validators.required, Validators.minLength(16),codiceFiscale()]],
      comune: [{ value: personaData ? personaData.comune : '', disabled: false }, Validators.required],
      cap: [{ value: personaData ? personaData.cap : '', disabled: false }, [Validators.required, Validators.pattern(CONST.PATTERN_CAP)]],
      indirizzo: [{ value: personaData ? personaData.indirizzo : '', disabled: false }, [Validators.required, Validators.maxLength(50)]],
      mail: [{ value: personaData ? personaData.mail : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL), Validators.maxLength(50)]],
      telefono: [{ value: personaData ? personaData.telefono : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO), Validators.maxLength(20)]]
      });
      this.persCompForm = this.personaForm;
    console.log('1 personaForm', this.personaForm);
  }
  initCompanyForm(companyData) {
    this.companyForm = null;
    this.personaForm = null;
    this.companyForm = this.fb.group({
      flgEntePubblico: [{ value: companyData ? companyData.flgEntePubblico : 0, disabled: (this.idScopo > 1 && this.idScopo < 5) },
      [Validators.required, Validators.maxLength(11)]],
      codiceFiscale: [{ value: companyData ? companyData.codiceFiscale : '', disabled: false },
        [Validators.required, cfPiva()]],
      partitaIva: [{ value: companyData ? companyData.partitaIva : '', disabled: false },
      companyData && companyData.flgEntePubblico == 1?[Validators.required,Validators.minLength(4)]
      :[Validators.required, Validators.minLength(11),Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
        ragioneSociale: [{ value: companyData ? companyData.ragioneSociale : '', disabled: false },
        [Validators.required, Validators.maxLength(200)]],
      indirizzo: [{ value: companyData ? companyData.indirizzo : '', disabled: false },
        [Validators.required, Validators.maxLength(50),]],
      comune: [{ value: companyData ? companyData.comune : '', disabled: false }, Validators.required],
      mail: [{ value: companyData ? companyData.mail : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL), Validators.maxLength(50)]],
        telefono: [{ value: companyData ? companyData.telefono : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO), Validators.maxLength(20)]],
      cap: [{ value: companyData ? companyData.cap : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_CAP)]],
      pec: [{ value: companyData ? companyData.pec : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL), Validators.maxLength(50)]],
      codiceBeneficiario: [{ value: companyData ? companyData.codiceBeneficiario : '', 
        disabled: this.tipoDomanda == 1 || !companyData || companyData.flgEntePubblico==0 },
        [Validators.required, Validators.minLength(6)]]
    });
    this.persCompForm = this.companyForm;
    
    if (this.companyForm.get("flgEntePubblico").value == 0){
      this.companyForm.get("codiceFiscale").setValidators([Validators.required, cfPiva()])
      this.companyForm.get("codiceFiscale").updateValueAndValidity()
    } else if(this.companyForm.get("flgEntePubblico").value == 1){
      this.companyForm.get("codiceFiscale").setValidators([Validators.required, partitaIva(),Validators.minLength(11),Validators.maxLength(11)])
      this.companyForm.get("codiceFiscale").updateValueAndValidity()
    }
    
    console.log('222 FINISH', this.companyForm);
  }

  initDestinazioniForm(){
    this.destinazioniForm = this.fb.group({
      istatComune: [{ value: '', disabled: false },[Validators.required]],
      sezione: [{ value: '', disabled: false }],
      foglio: [{ value: '', disabled: false },[Validators.required,Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      particella: [{ value: '', disabled: false },[Validators.required]],
      metriLineari: [{ value: '', disabled: false },
      [Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),oneOfTwo('metriLineari', 'metriQuadri')]],
      metriQuadri: [{ value: '', disabled: false },
      [Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),oneOfTwo('metriLineari', 'metriQuadri')]],
    })
  }

  loadLoggedSoggetto(){
    this.vivaiService.getLoggedSoggetto()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(res => {
        this.loggedSoggetto = res;    
      });
  }


  removeDestinazione(index:number): void {
    if (this.listDestinazioni.length > 1)
      this.listDestinazioni.splice(index,1);
  }

  addDestinazione(item:DestinazioneModel): void {
    this.listDestinazioni.push(item);
  }

  addSpecieForm(specie:SpecieRichiesta): void {
    this.specieForm.push(this.fillSpecieForm(specie,this))
  }

  fillSpecieForm(specie:SpecieRichiesta, that = this): FormGroup {
    return that.fb.group({
      idSpecie: [{ value: specie?specie.idSpecie:'' , disabled: false }],
      nomeComune: [{ value: specie?specie.nomeComune:'' , disabled: false }],
      nomeScientifico: [{ value: specie?specie.nomeScientifico:'', disabled: false }],
      qtaRichiesta: [{ value: specie?specie.qtaRichiesta:'', disabled: false },[Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO), Validators.maxLength(7)]]
    })
  }

  removeSpecieForm(i: number): void {
    if (this.specieForm.length > 0)
      this.specieForm.removeAt(i);
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  indietro(){
    if(this.userProfile == 'RICHIEDENTE'){
      this.goToHome();
    }else{
      if(this.utilService.isComeFromDomandaSearch()){
        this.router.navigate(['/content/ricerca'])
      }else if(this.utilService.isComeFromSoggettoSearch()){
        this.router.navigate(['/content/form-soggetto/' + this.domanda.fkSoggetto],
        {queryParams: {back:true}});
      }else{
        this.goToHome();
      }
    }
  }

  confermaDati(){
    if(!this.isFormPartOneValid()){
      this.submited = true;
      this.showModalMsg('Errore','Sono presenti degli errori nella compilazione dei campi. Seguire le indicazioni evidenziate in rosso.','OK');
      return;
    }
    let domValues = this.datiDomandaForm.value;
    //let sogValues = this.personaForm?this.personaForm.value:this.companyForm.value;
    let desClone = JSON.parse(JSON.stringify(this.listDestinazioni));
    desClone.forEach(element => {
      delete element.nomeComune;
    });
    let perFormValues = (this.companyForm?this.companyForm:this.personaForm).value;
    if(this.companyForm){
      perFormValues.flgEntePubblico = this.companyForm.get('flgEntePubblico').value
    }
    let domandaData ={
      idDomanda:this.idDomanda,
      fkTipoDomanda:domValues.fkTipoDomanda,
      fkVivaio:this.user.fkVivaio? this.user.fkVivaio:domValues.idVivaio,
      fkScopo:domValues.scopoAssegnGratuita,
      fkTitolarita:domValues.titolaritaRichiedente,
      numMarcaBollo: domValues.marcaBollo,
      note: domValues.note,
      soggetto: perFormValues,
     listDestinazione:desClone,
     listSpecie:domValues.specieForm
    };
    
    if(this.idDomanda){
      this.vivaiService.updateDomanda(domandaData)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (res: any) => {
          this.viewType = 'partTwo';
        },
        error => {
          //alert("Errore!!!");
        }
      )
    }else{
      delete domandaData.idDomanda;
      this.vivaiService.saveDomanda(domandaData)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (res: any) => {
          this.idDomanda = res;
          this.datiDomandaForm.get('idDomanda').patchValue(res);
          this.viewType = 'partTwo';
        },
        error => {
          //alert("Errore!!!");
        }
      )
    }
  }

  modificaDomanda(){
    if(this.isDocPresent([2,3,5,6])){
      this.showModalMsg('Attenzione', 'Per poter entrare in modifica è necessario prima eliminare la domanda caricata','OK');
    }else{
      this.viewType = 'partOne';
      console.log(this.viewType);
    }
  }

  inviaDomanda(){
    if(!this.isFormValid()){
      this.showModalMsg('Errore','Allegare i documenti richesti','OK');
      return;
    }
    this.vivaiService.inviaDomanda({idDomanda:this.idDomanda})
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (res: any) => {
          this.viewType = 'sent';
          this.showModalMsg('Info','Domanda inviata correttamente al Vivaio Forestale regionale di competenza','Termina');
        },
        error => {
          //alert("Errore!!!");
        }
      )
  }

  onChangeScopo(event){
    this.idScopo = event.value;
    if(event.value > 4){
      this.isForcedPersonaGiuridica = null;
      this.companyForm = null;
    }else{
      this.isForcedPersonaGiuridica = true;
      if(event.value > 1){
        this.initCompanyForm({'flgEntePubblico':'1'});
      }else{
        this.initCompanyForm({});
      }
    }
  }

  isFormPartOneValid(){
    let perForm = this.companyForm?this.companyForm:this.personaForm;
    if(this.companyForm){
      let tipSog = this.companyForm.get('flgEntePubblico');
      if(tipSog.value == '1'){
        this.datiDomandaForm.get('marcaBollo').setErrors(null);
      }
    }
    if(perForm && this.specieForm.length > 0 && (this.tipoDomanda == 2 || this.listDestinazioni.length > 0)){
      if(this.datiDomandaForm && !this.datiDomandaForm.valid){
        console.log('this.datiDomandaForm.error'); 
        console.log(this.datiDomandaForm);
        return false;
      }
       if(!perForm.valid){
        console.log('personaForm.error');
        console.log(perForm);
        return false;
      }
      return true;
      
    }
    return false;
  }

  isFormValid(){
    if((this.personaForm && this.personaForm.valid) || (this.companyForm && this.companyForm.valid)){
      if(this.companyForm && this.companyForm.get('flgEntePubblico').value == 1){
        return this.isDocPresent([2,5]);
      }
      if(this.tipoDomanda==1 && 
        (this.isDocPresent([2]) || (this.isDocPresent([3]) && this.isDocPresent([13]))) ){
          return true;
      }else if(this.tipoDomanda==2){
        return true;
      }
    }else{
      return false;
    }
  }

  isDocPresent(idsDocs:number[]){
    for(let i in this.listDocumenti){
      if(idsDocs.filter(e => this.listDocumenti[i].fkTipoAllegato == e).length > 0){
        return true;
      }
    }
    return false;
  }

  isMarcaBolloPresent(){
    return this.datiDomandaForm && this.datiDomandaForm.controls['marcaBollo'].value
      && this.datiDomandaForm.controls['marcaBollo'].value.length > 0
  }

  addAllegato(allegato:DocumentoAllegato){
    this.listDocumenti.push(allegato);
  }

  deleteAllegato(event){
    //this.listDocumenti.splice(event.index,1);
    this.vivaiService.loadAllegatiByIdDomanda(this.idDomanda).subscribe(
      (response: DocumentoAllegato[]) => {
        this.listDocumenti = response;
      }
    )
  }

}
