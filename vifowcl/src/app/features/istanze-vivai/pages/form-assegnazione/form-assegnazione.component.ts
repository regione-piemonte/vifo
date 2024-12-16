/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { CONST } from 'src/app/shared/constants';
import { takeUntil } from 'rxjs/operators';
import { AssegnazioneModel } from 'src/app/shared/models/assegnazione.model';
import { DizionariService } from '../../services/dizionari.service';
import { VivaiService } from '../../services/vivai.service';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';
import { SpecieRichiestaFull } from 'src/app/shared/models/specie-richiesta-full.model';
import { SelectItem } from 'primeng/components/common/selectitem';
import { SpecieModel } from 'src/app/shared/models/specie.model';
import { UtilService } from '../../services/util.service';
@Component({
  selector: 'app-form-assegnazione',
  templateUrl: './form-assegnazione.component.html',
  styleUrls: ['./form-assegnazione.component.css']
})
export class FormAssegnazioneComponent implements OnInit {

  datiAssegnazioneForm: FormArray;
  datiAssegnazioneNRForm: FormArray = this.fb.array([]);
  isDomandaOnerosa:boolean = false;
  idVivaio: number;
  idDomanda:number;
  isFromVivaioCompetenza:boolean=true;
  assegnazioniList: AssegnazioneModel[];
  assegnazioniNRList: AssegnazioneModel[];
  listSpecie: SpecieModel[];
  listSpecieCombo: SelectItem[] = [];
  listVivai: SelectItem[];
  isAggiungiRigaDisabled:boolean = true;
  richiestaTrasferimentoPartita: any;
  mapVivai: any = {};

  values: any;
  valuesNR: any;

  mapLabelSpecie:any={};

  unsubscribe$ = new Subject<void>();
  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;
  popUpBtn2:string;

  constructor(private vivaiService: VivaiService,
    public route: ActivatedRoute,
    private dizionariService: DizionariService, 
    private fb: FormBuilder,
    private router: Router,
    private utilService:UtilService,
    private dialogService: DialogService) { }

  ngOnInit() {
    this.dizionariService.getListSpecie().subscribe((res: SpecieModel[]) => {
      this.listSpecie = res.sort((a, b) => a.nomeComune > b.nomeComune && 1 || -1);
      this.listSpecie.forEach((item)=>{
        let val = item.nomeComune + (item.nomeScientifico? ' - ' + item.nomeScientifico:'');
        this.listSpecieCombo.push({value: item.nomeComune, label: val})
      })
      //this.codiceFiscaleSelectItem.push({value: element, label: element});
    });

    this.dizionariService.getListVivaio().subscribe((res:SelectItem[]) => {
      this.listVivai = res;
    })

    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      this.idDomanda = paramMap.get("idDomanda")?+paramMap.get("idDomanda"):null; 
    });
    this.vivaiService.loadDomanda(this.idDomanda)
    .subscribe(res => {
      this.isDomandaOnerosa = res.fkTipoDomanda == 2;
      this.idVivaio = res.fkVivaio;

      this.loadAssegnazioniByIdDomanda();

      this.vivaiService.getAssegnazioniNRByIdDomanda(this.idDomanda)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(res => {
          this.assegnazioniNRList = res;
          this.initAssegnazioniNRForm(res);
      })
    })
  }

  loadAssegnazioniByIdDomanda(){
    this.vivaiService.getAssegnazioniByIdDomanda(this.idDomanda)
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(res => {
      this.initAssegnazioniForm(res);
  })
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
    this.popUpBtn2 = null;
  }

  showModalMsgSiNo(type:string, msg:string, btn1:string, btn2:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
    this.popUpBtn2 = btn2;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    if(this.popUpBtn1 == 'Termina'){
      this.router.navigate(['/content/riepilogo/preassegnata/' + this.idDomanda]);
    }else if(this.popUpBtn1 == 'SI'){
      this.completeSalvaAssegnazioni();
    }
  }

  noPopUp(){
    this.popUpMsg = null;
  }

  initAssegnazioniForm(assegnazioniList: AssegnazioneModel[]){
    this.assegnazioniList = assegnazioniList;
    this.datiAssegnazioneForm = this.fb.array([])
    for(let i  in assegnazioniList){
      this.datiAssegnazioneForm.push(this.createNewAssegnazione(assegnazioniList[i]));
    }
  }

  initAssegnazioniNRForm(assegnazioniList: AssegnazioneModel[]){
    if(assegnazioniList && assegnazioniList.length > 0){
      for(let i  in assegnazioniList){
        assegnazioniList[i].nomeSpecie = assegnazioniList[i].nomeSpecie.split(' - ')[0];
        this.datiAssegnazioneNRForm.push(this.createNewAssegnazione(assegnazioniList[i]));
      }
      this.isAggiungiRigaDisabled = false;
    }else{
      this.datiAssegnazioneNRForm.push(this.createNewAssegnazione(null));
    }
  }

  createNewAssegnazione(item: AssegnazioneModel){
    if(item){
      this.mapVivai[item.nomeVivaio] = item.fkVivaio;
    }
    return this.fb.group({
      nomeSpecie: [{ value: item?item.nomeSpecie:'' , disabled: false }],
      qtaRichiesta: [{ value: item?item.qtaRichiesta:'' , disabled: false }],
      idPartita: [{ value: item?item.idPartita:'' , disabled: false }],
      fkVivaio: [{ value: item?item.fkVivaio:'' , disabled: false }],
      nomeVivaio: [{ value: item?item.nomeVivaio:'' , disabled: false }],
      certificato: [{ value: item?item.certificato:'' , disabled: false }],
      descrTipoAllevamento: [{ value: item?item.descrTipoAllevamento:'' , disabled: false }],
      eta: [{ value: item?item.eta:'' , disabled: false }],
      altezza: [{ value: item?item.altezza:'' , disabled: false }],
      prezzoUnitario: [{ value: item?this.getEuro(item.prezzoUnitario):'' , disabled: false }],
      qtaDisponibile: [{ value: item?item.qtaDisponibile:'' , disabled: false }],
      qtaRitirata: [{ value: item?item.qtaRitirata:'' , disabled: false }],
      qtaAssegnata: [{ value: item?item.qtaAssegnata:'' , 
        disabled: item?item.qtaRitirata > 0 || (item.qtaDisponibile < 1 && item.qtaAssegnata == 0): false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]]
    })
  }

  getEuro(value:number){
    return this.utilService.getEuro(value);
  }

  fillQuantita(index){

    let cmd = this.datiAssegnazioneForm.controls[index].get('qtaAssegnata');
    if(this.assegnazioniList[index].qtaDisponibile > 0){
      cmd.patchValue(this.assegnazioniList[index].qtaRichiesta);
    }
   
  }

  isSpcieChaged(i){
    if(this.assegnazioniList[i]){
      let idx = this.mapLabelSpecie[this.assegnazioniList[i].nomeSpecie];
      if(typeof idx === 'undefined' || idx == i){
        this.mapLabelSpecie[this.assegnazioniList[i].nomeSpecie] = i;
        return true;
      }
      return false;
    }
    return false;
  }

  isSpecieNRChaged(i){
    if(i == 0){
      return true;
    }
    if(this.datiAssegnazioneNRForm.controls[i].get('nomeSpecie').value != 
        this.datiAssegnazioneNRForm.controls[i-1].get('nomeSpecie').value){
      return true;
    }
    return false;
  }

  salvaAssegnazioni(){
    this.values = JSON.parse(JSON.stringify(this.datiAssegnazioneForm.value));
    this.valuesNR = JSON.parse(JSON.stringify(this.datiAssegnazioneNRForm.value));
    if(this.checkAssegnazioniOk(this.values) && this.checkAssegnazioniNRok(this.valuesNR)){
      this.completeSalvaAssegnazioni();
    }
  }

  completeSalvaAssegnazioni(){
    for(let i in this.valuesNR){
      this.values.push(this.valuesNR[i]);
    }
    
    this.values.forEach((item)=>{
      item.prezzoUnitario = item.prezzoUnitario?item.prezzoUnitario.replace(',','.'):''
      delete item.fkVivaio;
    })
    
    this.vivaiService.saveAssegnazione(this.values,this.idDomanda).subscribe(res => {
      if(res && res.error){
        this.showModalMsg('Errore', res.error,'OK');
      }else{
        this.showModalMsg('Info', 'Assegnazione salvata correttamente','Termina');
      }
    })
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  checkAssegnazioniOk(values: any){
    let qta;
    let tot;
    let lastSpecie;
    for(let i in values){
      if(this.assegnazioniList[i].nomeSpecie != lastSpecie){
        lastSpecie = this.assegnazioniList[i].nomeSpecie;
        tot = 0;
      }
      qta = parseInt(values[i]['qtaAssegnata']);
      if(!qta && qta!=0){
        qta = this.assegnazioniList[i].qtaAssegnata;
      }
      
      if(qta > this.assegnazioniList[i].qtaDisponibile && qta > this.assegnazioniList[i].qtaAssegnata){
        this.showModalMsg('Errore','Errore nella partita: ' + this.assegnazioniList[i].idPartita 
                    + '. La quantità richiesta non è disponibile!','OK')
        return false;
      }
      tot = tot + qta;
      if(tot > this.assegnazioniList[i].qtaRichiesta){
        this.showModalMsgSiNo('Attenzione','Per la specie "' + this.assegnazioniList[i].nomeSpecie +
        '" la quantità assegnata è maggiore di quella richiesta. Continuare?','SI','NO');
        return false;
      }
    }
    return true;
  }

  checkAssegnazioniNRok(values: any){
    for(let i in values){
      delete values[i]['nomeSpecie'];
      if(values[i].qtaDisponibile < values[i].qtaAssegnata && values[i].qtaAssegnata > 0){
        let items = this.assegnazioniNRList.filter(item => item.idPartita == values[i].idPartita);
        if(items.length == 0 || items[0].qtaAssegnata < values[i].qtaAssegnata){
          this.showModalMsg('Errore','Errore nelle "SPECIE NON RICHIESTE". Nella partita: ' + values[i].idPartita 
                      + ' la quantità richiesta non è disponibile!','OK')
          return false;
        }
      }
    }
    return true;
  }

  isSpecieGiaPresente(specie){
    let specieArboree = this.datiAssegnazioneForm.value;
      let specieArboreeNR = this.datiAssegnazioneNRForm.value;
      if(specieArboree.filter(item => this.getNomeSpecie(item.nomeSpecie) == specie).length > 0 ||
          specieArboreeNR.filter(item => item.nomeSpecie == specie).length > 1){
        this.showModalMsg('Attenzione','La specie "' + specie +'" è già presente!','OK')
        return true;
      }
    return false;
  }

  getNomeSpecie(value){
    if(value){
      return value.split(" - ")[0];
    }
    return null
  }

  onSpecieComboChange(i){
    let item = this.datiAssegnazioneNRForm.controls[i];
    if(this.isSpecieGiaPresente(item.get('nomeSpecie').value)){
      item.get('nomeSpecie').patchValue('');
    }
  }


  conferma(i){
    let specie = this.datiAssegnazioneNRForm.controls[i].get('nomeSpecie').value;
    if(specie && !this.isSpecieGiaPresente(specie)){
      let specieObj = this.listSpecie.filter(item=>item.nomeComune == specie);
      this.vivaiService.getPartiteBySpecieAndVivaio(specieObj[0].idSpecie,this.idVivaio)
      .subscribe(res => {
        //fill
        if(res && res.length > 0){
          let k = this.datiAssegnazioneNRForm.controls.length -1;
          this.datiAssegnazioneNRForm.controls.splice(k,1);
          let countInVivaio:number = 0;
          for(let i=0; i<res.length;i++){
            this.datiAssegnazioneNRForm.push(this.createNewAssegnazione(res[i]));
            if(res[i].fkVivaio == this.idVivaio){
              countInVivaio++;
            }
          }
          if(countInVivaio == 0 && this.isFromVivaioCompetenza){
            this.showModalMsg('Attenzione','Sono stati trovati risultati solo su altri vivai. Per visualizzarli selezionare la voce "tutti i vivai"','OK')
          }
          
          document.getElementById('idBtnConferma_'+i)['disabled'] = true;
          this.isAggiungiRigaDisabled = false;
        }else{
          this.showModalMsg('Attenzione','Nessuna partita trovata per la specie "' + specie +'"','OK')
        }
      })
    }
  }

  addSpecieNR(){
    this.datiAssegnazioneNRForm.push(this.createNewAssegnazione(null));
    this.isAggiungiRigaDisabled = true;
    console.log('datiAssegnazioneNRForm: ' + this.datiAssegnazioneNRForm.controls.length);
    console.log(this.datiAssegnazioneNRForm.controls);
  }

  fillSpecieNonRichiesta(ctrl,values){
    ctrl.get('idPartita').patchValue(values['idPartita']);
    ctrl.get('nomeVivaio').patchValue(values['nomeVivaio']);
    ctrl.get('certificato').patchValue(values['certificato']);
    ctrl.get('descrTipoAllevamento').patchValue(values['descrTipoAllevamento']);
    ctrl.get('eta').patchValue(values['eta']);
    ctrl.get('altezza').patchValue(values['altezza']);
    ctrl.get('prezzoUnitario').patchValue(this.getEuro(values['prezzoUnitario']));
    ctrl.get('qtaDisponibile').patchValue(values['qtaDisponibile']);
    ctrl.get('qtaAssegnata').patchValue(values['qtaAssegnata']);
  }

  isVivaioDomanda(i){
    return this.assegnazioniList[i].fkVivaio == this.idVivaio || !this.assegnazioniList[i].fkVivaio;
  }

  isVivaioDomandaNR(i){
    if(this.datiAssegnazioneNRForm.controls[i]){
      let fkVivaio = this.datiAssegnazioneNRForm.controls[i].get('fkVivaio').value;
      return !fkVivaio || fkVivaio == this.idVivaio;
    }
    return true;
  }

  isRowDisabled(i){
    let idPartita = this.datiAssegnazioneNRForm.controls[i].get('idPartita').value;
    return (''+ idPartita).length > 0;
  }

  richiediSpecieNRAdAltroVivaio(i){
    this.richiediSpecieAdAltroVivaioAll(i, this.datiAssegnazioneNRForm);
  }

  richiediSpecieAdAltroVivaio(i){
    this.richiediSpecieAdAltroVivaioAll(i, this.datiAssegnazioneForm);
  }

  richiediSpecieAdAltroVivaioAll(i:number, datiAssegnazioneForm){
    let idVivaioFrom = this.mapVivai[datiAssegnazioneForm.controls[i].get('nomeVivaio').value];
    let vivaioFrom: SelectItem= this.listVivai.filter(item=>item.value == idVivaioFrom)[0];
    let vivaioTo: SelectItem= this.listVivai.filter(item=>item.value == this.idVivaio)[0];
    let specie: SpecieModel= this.listSpecie.filter(item=>{
      let valSpecie = datiAssegnazioneForm.controls[i].get('nomeSpecie').value;
        return item.nomeComune == valSpecie.split(' - ')[0]})[0];
    this.richiestaTrasferimentoPartita = {
      'fkInventarioPiante':datiAssegnazioneForm.controls[i].get('idPartita').value,
      'fkSpecie':specie.idSpecie,
      'specieName':specie.nomeComune,
      'fkVivaioTo':vivaioTo.value,
      'vivaioToName':vivaioTo.label,
      'fkVivaioFrom':vivaioFrom.value,
      'vivaioFromName':vivaioFrom.label,
      'isTrasferimento': false
    }
  }

  setFromVivaioCompetenza(value){
    this.isFromVivaioCompetenza = value;
  }

  deleteRowAltreSpecieNR(i){
    let specie = this.datiAssegnazioneNRForm.controls[i].get('nomeSpecie').value;
    this.datiAssegnazioneNRForm.controls.splice(i,1);
    if(specie && specie.length > 0){
      for(let k=this.datiAssegnazioneNRForm.controls.length-1; k>=0;k--){
        if(this.datiAssegnazioneNRForm.controls[k].get('nomeSpecie').value == specie){
          this.datiAssegnazioneNRForm.controls.splice(k,1);
        }
      }
    }else{
      this.isAggiungiRigaDisabled = false;
    }
    if(this.datiAssegnazioneNRForm.controls.length == 0){
      this.addSpecieNR();
    }
  }

  closeRichietaTrasferimento(isSaved:boolean){
    let idSpecie = this.richiestaTrasferimentoPartita.fkSpecie;
    let idPartita = this.richiestaTrasferimentoPartita.idPartita;

    this.updateSpecieRichiestaTrasferimento(idSpecie,idPartita);
    this.richiestaTrasferimentoPartita = null;
    this.loadAssegnazioniByIdDomanda();
  }

  tooltip(idx,id){
    return this.datiAssegnazioneForm.controls[idx].get(id).value;
  }
  tooltipNR(idx,id){
    return this.datiAssegnazioneNRForm.controls[idx].get(id).value; 
  }

  updateSpecieRichiestaTrasferimento(idSpecie, idPartita){
    this.vivaiService.getPartiteBySpecieAndVivaio(idSpecie,this.idVivaio)
      .subscribe(res => {
        let partita = res.filter((item)=>item.idPartita == idPartita)[0];
        let form:AbstractControl;
        for(let i in this.datiAssegnazioneForm.controls){
          form = this.datiAssegnazioneForm.controls[i];
          if(form.get('idPartita').value == idPartita){
            form.get('qtaDisponibile').patchValue(partita.qtaDisponibile);
            return;
          }
        }
        for(let i in this.datiAssegnazioneNRForm.controls){
          form = this.datiAssegnazioneNRForm.controls[i];
          if(form.get('idPartita').value == idPartita){
            form.get('qtaDisponibile').patchValue(partita.qtaDisponibile);
            return;
          }
        }
      })
  }

  indietro(){
    this.router.navigate(['/content/riepilogo/preassegnata/' + this.idDomanda])
  }

}
