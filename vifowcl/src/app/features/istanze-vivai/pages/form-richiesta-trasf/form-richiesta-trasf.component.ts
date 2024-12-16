/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, OnChanges, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SelectItem } from 'primeng/components/common/selectitem';
import { CONST } from 'src/app/shared/constants';
import { UserModel } from 'src/app/shared/models/user.model';
import { DizionariService } from '../../services/dizionari.service';
import { InventarioService } from '../../services/inventario.service';
import { UtilService } from '../../services/util.service';

@Component({
  selector: 'app-form-richiesta-trasf',
  templateUrl: './form-richiesta-trasf.component.html',
  styleUrls: ['./form-richiesta-trasf.component.css']
})
export class FormRichiestaTrasfComponent implements OnInit, OnChanges {

  @Input() richiestaTrasferimentoPartita: any;
  @Output() emitBack = new EventEmitter<boolean>();
  submited: boolean = false;
  richiestaTrasfForm: FormGroup;
  accetazioeRifiutoTrasfForm: FormGroup;
  partitaPiante: any = {};
  listVivai: SelectItem[]=[];
  maxQtaTrasferibile: number;
  idInventario: number;
  isEdit: boolean;
  isUserVivaio:boolean;
  user :UserModel;
  isTrasferimento:boolean;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(private fb: FormBuilder, private inventarioService :InventarioService, 
    private router: Router, private utilService: UtilService, 
    private dizionariService: DizionariService, public route: ActivatedRoute) { }

  ngOnInit() {
    this.user = this.utilService.getUser();
    this.isUserVivaio = this.user.profile == CONST.PROFILE.VIVAIO ;
    this.idInventario = this.route.snapshot.params['id'];
    this.isEdit = !('false' === this.route.snapshot.params['edit']);

    let richiesta = this.richiestaTrasferimentoPartita;
    
    if(this.idInventario){
      this.inventarioService.getDetailTrasferimentoPiantineById(this.idInventario)
      .subscribe((res:any) =>{
        this.richiestaTrasferimentoPartita = res;
        this.initPartitaPianteForm(res);
        this.isTrasferimento = res.isTrasferimento;        
        this.isEdit = this.isUserVivaio 
          && ((res.isTrasferimento && res.fkVivaioTo == this.user.fkVivaio) 
          || (!res.isTrasferimento && res.fkVivaioFrom == this.user.fkVivaio));
        
      })
    }else{
      this.initPartitaPianteForm(richiesta);
    }
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    if(this.popUpBtn1=='Termina'){
      if(!this.idInventario){
        this.emitBack.emit(true);
      }else{
        this.indietro();
      }
    }
  }

  ngOnChanges() {
    if(this.user == null){
      this.user = this.utilService.getUser();
    }
    this.initPartitaPianteForm(this.richiestaTrasferimentoPartita);
    
  }

  getFormatDate(data: Date){
    return this.utilService.getFormatDate(data);
  }

  initPartitaPianteForm(richiestaTrafData: any) {
    if(richiestaTrafData.isTrasferimento){
      this.dizionariService.getListVivaio().subscribe((response: SelectItem[]) => {
        this.listVivai = response.filter(item=>item.value != richiestaTrafData.fkVivaioFrom);
      });
    }

    if(!richiestaTrafData.idInventarioPiante){
      richiestaTrafData.dataInvioNotifica = new Date();
      richiestaTrafData.operatoreName = this.user.nome + ' ' + this.user.cognome;
    }else{
      richiestaTrafData.dataInvioNotifica = new Date(richiestaTrafData.dataInvioNotifica);
    }
    this.richiestaTrasfForm = this.fb.group({
      idInventario: [{ value: richiestaTrafData ? richiestaTrafData.idInventario : '', disabled: false }],
      isTrasferimento: [{ value: richiestaTrafData ? richiestaTrafData.isTrasferimento : '', disabled: false }],
      fkInventarioPiante: [{ value: richiestaTrafData ? richiestaTrafData.fkInventarioPiante : '', disabled: false }],
      fkSpecie: [{ value: richiestaTrafData ? richiestaTrafData.fkSpecie : '', disabled: false }],
      qtaCarico: [{ value: richiestaTrafData ? richiestaTrafData.qtaCarico : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      fkVivaioTo: [{ value: richiestaTrafData ? richiestaTrafData.fkVivaioTo : '', disabled: false },
        [Validators.required]],
      fkVivaioFrom: [{ value: richiestaTrafData ? richiestaTrafData.fkVivaioFrom : '', disabled: false },
        [Validators.required]],
      motivoRichiesta: [{ value: richiestaTrafData ? richiestaTrafData.motivoRichiesta : '', disabled: false },
        [Validators.required]],
        noteValidInvalidTrasf: [{ value: richiestaTrafData ? richiestaTrafData.noteValidInvalidTrasf : '', disabled: false },
        this.idInventario?[Validators.required]:[]],
      });

      this.inventarioService.getRiepilogoPiantineById(richiestaTrafData.fkInventarioPiante)
      .subscribe((res: any) => {
        this.maxQtaTrasferibile = res.qtaDisponibile
        if(richiestaTrafData.isTrasferimento && !richiestaTrafData.qtaCarico){
          this.richiestaTrasfForm.controls['qtaCarico'].patchValue(res.qtaDisponibile);
        }
      });
  }

  inviaRichiestaTrasferimento(){
    let values = this.richiestaTrasfForm.value;
    if(this.richiestaTrasfForm.invalid){
      this.submited = true;
    }else if(parseInt(values.qtaCarico) > this.maxQtaTrasferibile){
      this.showModalMsg('Attenzione','La quantità inserita non può essere maggiore della quantità disponible: ' 
      + this.maxQtaTrasferibile,'OK');
    }else{
      this.inventarioService.saveRichiestaTrasferimento(values)
      .subscribe((res:any) =>{
        this.showModalMsg("Info","La richiesta è stata inviata correttamente!",'Termina');
      })
    }
  }

  indietro(){
    if(this.idInventario){
      this.router.navigate(['/content/trasferimenti'])
    }else{
      this.emitBack.emit(false);
    }
  }

  accettaTrasferimento(){
    let values = this.richiestaTrasfForm.value;
    values.flgValid = 'S';
    this.accettaRifiutaTrasferimento(values);
  }

  rifiutaTrasferimento(){
    let values = this.richiestaTrasfForm.value;
    values.flgValid = 'N';
    this.accettaRifiutaTrasferimento(values);
  }

  accettaRifiutaTrasferimento(values:any){
    if(this.richiestaTrasfForm.invalid){
      this.submited = true;
    }else{
      this.inventarioService.accettaRifiutaTrasferimento(values)
      .subscribe((res)=>{
        if(values.flgValid == 'S'){
          this.showModalMsg('Info','Accettazione trasferimento salvata correttamente!','Termina');
        }else{
          this.showModalMsg('Info','Rifiuto trasferimento salvato correttamente!','Termina');
        }
      })
    }
  }

}
