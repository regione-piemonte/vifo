/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, OnInit, Output,EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SelectItem } from 'primeng/components/common/selectitem';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { DizionariService } from 'src/app/features/istanze-vivai/services/dizionari.service';
import { UtilService } from 'src/app/features/istanze-vivai/services/util.service';
import { VivaiService } from 'src/app/features/istanze-vivai/services/vivai.service';
import { ButtonType, DialogButtons } from '../../error-dialog/error-dialog.component';
import { ComuneModel } from '../../models/comune.model';
import { HomeModel } from '../../models/home.model';
import { SoggettoModel } from '../../models/soggetto.model';
import { UserCompanyDataModel } from '../../models/user-company-data.model';
import { DialogService } from '../../services/dialog.service';

@Component({
  selector: 'app-persona-form',
  templateUrl: './persona-form.component.html',
  styleUrls: ['./persona-form.component.css']
})
export class PersonaFormComponent implements OnInit, OnChanges {

  @Input() personalOrCompanyForm: FormGroup;
  @Input() idScopo:number;
  @Input()submited: boolean;
  @Input()isPersonaGiuridica: boolean;
  @Input()isDomandaGratuita:boolean;
  @Input()isForcedPersonaGiuridica: boolean;
  @Output() emitPersonTypeChange = new EventEmitter<number>();
  @Output() emitPersonForm = new EventEmitter<SoggettoModel>();
  user : HomeModel;
  subForm: FormGroup;
  comune: ComuneModel[];
  tuttiComuni: ComuneModel[];
  personType:number
  unsubscribe$ = new Subject<void>();
  listAziende: UserCompanyDataModel[];  
  listAziendePubbliche: UserCompanyDataModel[];
  listAziendePrivate: UserCompanyDataModel[];
  myAziendeSelectItem: SelectItem[] = [];
  myAziendeSelectItemPubbliche: SelectItem[];
  myAziendeSelectItemPrivate: SelectItem[];

  userProfile:string;
  isComuneEstero:boolean=false;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(private dizionariService: DizionariService, 
    private vivaiService: VivaiService, 
    private utilService: UtilService,
    private dialogService: DialogService,
    private fb: FormBuilder) { }

  ngOnInit() {
    this.user = this.utilService.getUser();
    this.userProfile = this.utilService.getUserProfile();
    this.dizionariService.getAllComuni().pipe(takeUntil(this.unsubscribe$)).subscribe((res: ComuneModel[]) => {
      this.tuttiComuni = res;
    });
    this.initMyAziende();
    this.onComuneChange();
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }
  
  closePopUp(){
    this.popUpMsg = null;
  }

  isAziendePresent(){
    if(this.listAziende && this.listAziende.length > 0){
      return true;
    }
    return false;
  }

  ngOnChanges(){
    this.initMyAziende();
    this.onComuneChange();
    if(document.getElementById('tipologiaSoggetto1') && document.getElementById('tipologiaSoggetto2')){
      document.getElementById('tipologiaSoggetto1')['disabled'] = (this.idScopo > 1 && this.idScopo < 5); 
      document.getElementById('tipologiaSoggetto2')['disabled'] = (this.idScopo > 1 && this.idScopo < 5); 
    }
  }

  initMyAziende(){
    if(this.isPersonaGiuridica){
      let val = this.personalOrCompanyForm.get('flgEntePubblico').value;
      if(val == 1){
        this.loadMyAziende(true);
      }else if(val == 0){
        this.loadMyAziende(false);
      }
    }
  }

  clearValue(){
    this.personalOrCompanyForm.get('comune').patchValue('');
  }

  chengeComuneEstero(){
    this.isComuneEstero = document.getElementById('comuneEstero')['checked'];
    if(this.isComuneEstero){
      let comEstero = this.dizionariService.autocompleteFilter({query:'Comune estero'}, 
        this.tuttiComuni, 'Comune')[0];
      this.personalOrCompanyForm.get('comune').patchValue(comEstero);
    }else{
      this.clearValue();
    }
  }

  onComuneChange(){
    if(this.personalOrCompanyForm){
      let val = this.personalOrCompanyForm.get('comune').value;
      this.isComuneEstero = (val && val.codIstat == '999999');
    }
  }


  changePersonType(type:number){
    this.personType = type;
    this.emitPersonTypeChange.emit(type);
  }

  changeSoggettoType(isEntePubblico:boolean){
    this.loadMyAziende(isEntePubblico);
    let val = this.personalOrCompanyForm.get('flgEntePubblico').value;
    this.emitPersonForm.emit({flgEntePubblico:val});
  }

  filterComune(event) {
    if(this.tuttiComuni){
      this.comune = this.dizionariService.autocompleteFilter(event, this.tuttiComuni, 'Comune');
    }
  }

  onChangeAzienda(event){
    let item = this.listAziende.filter(e => e.codiceFiscale === event.value);
    this.personalOrCompanyForm.get('codiceFiscale').patchValue(item[0].codiceFiscale);
    this.personalOrCompanyForm.get('partitaIva').patchValue(item[0].partitaIva);
    this.personalOrCompanyForm.get('ragioneSociale').patchValue(item[0].ragioneSociale);
    this.personalOrCompanyForm.get('indirizzo').patchValue(item[0].indirizzo);
    this.personalOrCompanyForm.get('comune').patchValue(item[0].comune);
    this.personalOrCompanyForm.get('mail').patchValue(item[0].mail);
    this.personalOrCompanyForm.get('telefono').patchValue(item[0].telefono);
    this.personalOrCompanyForm.get('cap').patchValue(item[0].cap);
    this.personalOrCompanyForm.get('pec').patchValue(item[0].pec);
  }

  loadMyAziende(isEntePubblico:boolean){
    if(this.userProfile == 'RICHIEDENTE'){
      if(isEntePubblico && this.listAziendePubbliche){
        this.listAziende = this.listAziendePubbliche;
        this.myAziendeSelectItem = this.myAziendeSelectItemPubbliche;
      }else if(!isEntePubblico && this.listAziendePrivate){
        this.listAziende = this.listAziendePrivate;
        this.myAziendeSelectItem = this.myAziendeSelectItemPrivate;
      }else{
        this.vivaiService.getMyAziende(this.user.codiceFiscale, isEntePubblico)
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe(res => {
            this.listAziende = res;
            this.myAziendeSelectItem = [];
            this.listAziende.forEach((element, index) => {
              if (element !== null) { 
                this.myAziendeSelectItem.push({label: element.codiceFiscale + ' - ' + element.ragioneSociale, 
                value: element.codiceFiscale}); 
              }
            });
            if(isEntePubblico){
              this.listAziendePubbliche = res;
              this.myAziendeSelectItemPubbliche = this.myAziendeSelectItem;
            }else{
              this.listAziendePrivate = res;
              this.myAziendeSelectItemPrivate = this.myAziendeSelectItem;
            }
          });
        }
    }
    
  }

  cercaSoggettoByCf(){
    let cf = this.personalOrCompanyForm.get('codiceFiscale').value;
    this.vivaiService.getSoggettoByCf(cf).subscribe(res => {
      if(res.nome){
        this.emitPersonForm.emit(res);
      }else{
        this.showModalMsg('Attenzione','Nessun risultato trovato','OK');
      }
    })
  }

  cercaAziendaByCf(){
    let cf = this.personalOrCompanyForm.get('codiceFiscale').value;
    let isEntePubblico:boolean = this.personalOrCompanyForm.get('flgEntePubblico').value == 1;
    this.vivaiService.getAziendaByCfAndType(cf,isEntePubblico).subscribe(res => {
      if(res && res.ragioneSociale){
        this.emitPersonForm.emit(res);
        setTimeout(() => {
          this.checkValuePubblicoPrivato()
        }, 100);
      }else{
        this.showModalMsg('Attenzione','Nessun risultato trovato','OK');
      }
    })
  }

  checkValuePubblicoPrivato(){
    let val = this.personalOrCompanyForm.get('flgEntePubblico').value;
    if(val == 0 || val == 1){
      let key = 'tipologiaSoggetto' + (val+1);
      let input = document.getElementById(key)['checked'] = true;
    }
    
  }

}
