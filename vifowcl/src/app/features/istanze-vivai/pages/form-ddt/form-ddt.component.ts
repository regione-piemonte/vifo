/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';
import { DdtModel } from 'src/app/shared/models/ddt.model';
import { DomandaModel } from 'src/app/shared/models/domanda.model';
import { HomeModel } from 'src/app/shared/models/home.model';
import { ConfigService } from 'src/app/shared/services/config.service';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { UtilService } from '../../services/util.service';
import { VivaiService } from '../../services/vivai.service';
import { CONST } from 'src/app/shared/constants';

@Component({
  selector: 'app-form-ddt',
  templateUrl: './form-ddt.component.html',
  styleUrls: ['./form-ddt.component.css']
})
export class FormDdtComponent implements OnInit {

  datiAssegnazioneForm: FormArray = this.fb.array([]);
  fatturaAccontoForm: FormGroup;
  idDomanda:number;
  numeroDdt:number;
  annoDdt:number;
  domanda:DomandaModel;
  infoDomanda:any;
  userProfile:string;
  tipoDomanda:string;

  listSavesDdt:DdtModel[];
  
  today: string;
  currentYear: number = new Date().getFullYear();
  it: any = CONST.IT;
  user : HomeModel;
  
  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(private vivaiService: VivaiService,
    public route: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router,
    private utilService: UtilService,
    private configService:ConfigService,
    private dialogService: DialogService) { }

  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      this.idDomanda = paramMap.get("idDomanda")?+paramMap.get("idDomanda"):null;
      this.numeroDdt = paramMap.get("numeroDdt")?+paramMap.get("numeroDdt"):null;
      this.annoDdt = paramMap.get("annoDdt")?+paramMap.get("annoDdt"):null;
    });

    this.fatturaAccontoForm = new FormGroup({
      numeroFatturaAcconto: new FormControl('', Validators.maxLength(30)),
      dataFatturaAcconto: new FormControl(null)
    })

    this.user = this.utilService.getUser();

    
    this.today = this.utilService.getTodayFormated();

    this.userProfile = this.utilService.getUserProfile();

    if(this.idDomanda){
      this.loadDatiDomanda();
    }else{
      this.vivaiService.getDdtDetails(this.numeroDdt, this.annoDdt)
      .subscribe(res=>{
        this.listSavesDdt = res;
      },e=>console.log(e),()=>{
        if(this.numeroDdt && this.annoDdt && (this.listSavesDdt[0].numeroFatturaAcconto != null)){
          this.fatturaAccontoForm.get('numeroFatturaAcconto').setValue(this.listSavesDdt[0].numeroFatturaAcconto)
        }
        if(this.numeroDdt && this.annoDdt && (this.listSavesDdt[0].dataFatturaAcconto != null)){
          this.fatturaAccontoForm.get('dataFatturaAcconto').setValue(new Date(this.listSavesDdt[0].dataFatturaAcconto))
        }
      })

      this.vivaiService.getInfoDdt(this.numeroDdt, this.annoDdt)
      .subscribe(res=>{
        this.idDomanda = res['idDomanda'];
        this.loadDatiDomanda();
    });
    }

  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    if(this.popUpBtn1 == 'Termina'){
      this.closePopUpEnd();
    }
  }

  closePopUpEnd(){
    this.domanda.statoDomanda = 'RITIRO IN CORSO';
    this.indietro();
  }

  goToHome(){
    this.router.navigate(['/content']);
  }

  getIdentificativoDdt(){
    return (this.numeroDdt && this.annoDdt)? (this.numeroDdt + ' / ' + this.annoDdt): '-'
  }

  loadDatiDomanda(){
    this.vivaiService.getInfoDomanda(this.idDomanda)
    .subscribe(res=>this.infoDomanda=res);

    this.vivaiService.loadDomanda(this.idDomanda)
      .subscribe(res => {
        this.domanda = res;
        this.tipoDomanda = res.fkTipoDomanda==1?'gratuita':'vendita'
      })
    
      this.vivaiService.getAvailableDdtByIdDomanda(this.idDomanda)
      .subscribe(res => {
        if(this.listSavesDdt){
          this.mergeSavedDdt(res);
        }
        this.initAssegnazioniForm(res);
        
       /*  this.fatturaAccontoForm = this.fb.group({
          numeroFatturaAcconto: [{value :(this.numeroDdt && this.annoDdt && this.listSavesDdt[0].numeroFatturaAcconto != null) ? this.listSavesDdt[0].numeroFatturaAcconto : '', disabled:false}, Validators.maxLength(30)],
          dataFatturaAcconto:[{value:(this.numeroDdt && this.annoDdt && this.listSavesDdt[0].dataFatturaAcconto != null) ? new Date(this.listSavesDdt[0].dataFatturaAcconto) : null, disabled:false}],
        }) */
       
      }
      )
  }

  mergeSavedDdt(list:DdtModel[]){
    for(let i in this.listSavesDdt){
      let item = list.filter(item=>item.idAssegnata == this.listSavesDdt[i].idAssegnata)[0];
      item.qtaAssegnataRimanente += this.listSavesDdt[i].qtaInDdt;
      item.qtaInDdt = this.listSavesDdt[i].qtaInDdt;
    }
  }

  initAssegnazioniForm(assegnazioniList: DdtModel[]){
    for(let i  in assegnazioniList){
      if(assegnazioniList[i].qtaAssegnataRimanente > 0){
        this.datiAssegnazioneForm.push(this.createNewAssegnazione(assegnazioniList[i]));
      }
    }
  }

  createNewAssegnazione(item: DdtModel){
    return this.fb.group({
      idDdt: [{ value: item?item.idDdt:'' , disabled: false }],
      idAssegnata: [{ value: item?item.idAssegnata:'' , disabled: false }],
      idPartita: [{ value: item?item.idPartita:'' , disabled: false }],
      numeroDdt: [{ value: item?item.numeroDdt:'' , disabled: false }],
      annoDdt: [{ value: item?item.annoDdt:'' , disabled: false }],
      specie: [{ value: item?item.specie:'' , disabled: false }],
      vivaio: [{ value: item?item.vivaio:'' , disabled: false }],
      allevamento: [{ value: item?item.allevamento:'' , disabled: false }],
      eta: [{ value: item?item.eta:'' , disabled: false }],
      altezza: [{ value: item?item.altezza:'' , disabled: false }],
      certificato: [{ value: item?item.certificato:'' , disabled: false }],
      qtaAssegnataRimanente: [{ value: item?item.qtaAssegnataRimanente:'' , disabled: false }],
      qtaInDdt:[{ value: item?this.numeroDdt?item.qtaInDdt:item.qtaAssegnataRimanente:'' , disabled: false }],
     
    })
  }

  confermaDDT(){
    let values = this.datiAssegnazioneForm.value;
    values[0].numeroFatturaAcconto= this.fatturaAccontoForm.value.numeroFatturaAcconto;
    values[0].dataFatturaAcconto= this.fatturaAccontoForm.value.dataFatturaAcconto;
    

    let count = 0;
    values.forEach(item => { count+=item.qtaInDdt;});
    if(count == 0){
      this.showModalMsg('Attenzione','Inserire almeno una quantità con valore maggiore di zero!','OK');
      return;
    }
  
    if(this.numeroDdt){
      this.vivaiService.updateDdt(values, this.numeroDdt, this.annoDdt)
      .subscribe(res=>{
        this.showModalMsg('Info','Documento di trasporto salvato correttamente!','Termina');
      });
    }else{
      this.vivaiService.saveDdt(values, this.idDomanda)
      .subscribe(res=>{
        this.showModalMsg('Info','Documento di trasporto salvato correttamente!','Termina');
      });
    }
  }

  indietro(){
    if(this.router.url.indexOf('modifica-ddt')> -1){
      this.router.navigate(['/content/dettaglio-ddt/' + this.numeroDdt + '/' + this.annoDdt]);
    }else{
      this.router.navigate([ this.configService.getPathByStato(this.domanda.statoDomanda) + this.domanda.idDomanda]);
    }
  }

  onChangeQtaDdt(idx:number){
    let ctrl = this.datiAssegnazioneForm.controls[idx];
    if(isNaN(ctrl.get('qtaInDdt').value)){
      this.showModalMsg('Attenzione','Il valore deve essere numerico','OK');
      ctrl.get('qtaInDdt').patchValue(ctrl.get('qtaAssegnataRimanente').value)
    }
    if(ctrl.get('qtaInDdt').value > ctrl.get('qtaAssegnataRimanente').value){
      this.showModalMsg('Attenzione','La quantità assegnata non può essere maggiore di ' 
        + ctrl.get('qtaAssegnataRimanente').value + '!','OK');
      ctrl.get('qtaInDdt').patchValue(ctrl.get('qtaAssegnataRimanente').value)
    }
  }

  tooltip(idx,id){
    return this.datiAssegnazioneForm.controls[idx].get(id).value;
  }

}

