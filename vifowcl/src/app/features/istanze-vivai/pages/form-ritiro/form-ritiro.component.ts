/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { CONST } from 'src/app/shared/constants';
import { AssegnazioneModel } from 'src/app/shared/models/assegnazione.model';
import { DomandaModel } from 'src/app/shared/models/domanda.model';
import { HomeModel } from 'src/app/shared/models/home.model';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { UtilService } from '../../services/util.service';
import { VivaiService } from '../../services/vivai.service';

@Component({
  selector: 'app-form-ritiro',
  templateUrl: './form-ritiro.component.html',
  styleUrls: ['./form-ritiro.component.css']
})
export class FormRitiroComponent implements OnInit {

  datiAssegnazioneForm: FormArray = this.fb.array([]);
  idDomanda:number;
  domanda:DomandaModel;
  infoDomanda:any;
  userProfile:string;
  tipoDomanda:string;
  today: string;
  user : HomeModel;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(private vivaiService: VivaiService,
    public route: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router,
    private utilService: UtilService,
    private dialogService: DialogService) { }

  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      this.idDomanda = paramMap.get("idDomanda")?+paramMap.get("idDomanda"):null;
    });

    this.user = this.utilService.getUser();

    this.today = this.utilService.getTodayFormated();
    this.userProfile = this.utilService.getUserProfile();

    this.vivaiService.getInfoDomanda(this.idDomanda)
    .subscribe(res=>this.infoDomanda=res);

    this.vivaiService.loadDomanda(this.idDomanda)
      .subscribe(res => {
        this.domanda = res;
        this.tipoDomanda = res.fkTipoDomanda==1?'gratuita':'vendita'
        
      })
    
      this.vivaiService.getAllAssegnazioniByIdDomanda(this.idDomanda)
      .subscribe(res => {
        this.initAssegnazioniForm(res);
      })
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    this.router.navigate(['/content/riepilogo/ritiro/' + this.idDomanda]);
  }

  initAssegnazioniForm(assegnazioniList: AssegnazioneModel[]){
    for(let i  in assegnazioniList){
      this.datiAssegnazioneForm.push(this.createNewAssegnazione(assegnazioniList[i]));
    }
  }

  createNewAssegnazione(item: AssegnazioneModel){
    return this.fb.group({
      idAssegnata: [{ value: item?item.idAssegnata:'' , disabled: false }],
      nomeSpecie: [{ value: item?item.nomeSpecie:'' , disabled: false }],
      qtaRichiesta: [{ value: item?item.qtaRichiesta:'' , disabled: false }],
      idPartita: [{ value: item?item.idPartita:'' , disabled: false }],
      nomeVivaio: [{ value: item?item.nomeVivaio:'' , disabled: false }],
      certificato: [{ value: item?item.certificato:'' , disabled: false }],
      descrTipoAllevamento: [{ value: item?item.descrTipoAllevamento:'' , disabled: false }],
      eta: [{ value: item?item.eta:'' , disabled: false }],
      altezza: [{ value: item?item.altezza:'' , disabled: false }],
      prezzoUnitario: [{ value: item?item.prezzoUnitario:'' , disabled: false }],
      qtaDisponibile: [{ value: item?item.qtaDisponibile:'' , disabled: false }],
      qtaAssegnata:[{ value: item?item.qtaAssegnata:'' , disabled: false }],
      qtaRitirata: [{ value: item?item.qtaRitirata:'' , disabled: false }]
    })
  }

  confermaRitiro(){
    let values = this.datiAssegnazioneForm.value;
    this.vivaiService.saveRitiroPiantine(values, this.idDomanda)
    .subscribe(res=>{
      this.showModalMsg('Info','Ritiro salvato correttamente!','Termina');
    });
  }

  indietro(){
    this.router.navigate(['/content/riepilogo/ritiro/' + this.idDomanda]);
  }

  goToHome(){
    this.router.navigate(['/content']);
  }

  tooltip(idx,id){
    return this.datiAssegnazioneForm.controls[idx].get(id).value;
  }

}
