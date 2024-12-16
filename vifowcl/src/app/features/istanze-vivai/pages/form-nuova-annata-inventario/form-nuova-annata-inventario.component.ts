/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { timeout } from 'rxjs/operators';
import { UserModel } from 'src/app/shared/models/user.model';
import { InventarioService } from '../../services/inventario.service';
import { UtilService } from '../../services/util.service';

@Component({
  selector: 'app-form-nuova-annata-inventario',
  templateUrl: './form-nuova-annata-inventario.component.html',
  styleUrls: ['./form-nuova-annata-inventario.component.css']
})
export class FormNuovaAnnataInventarioComponent implements OnInit {

  infoAnnataInventario: any;
  user :UserModel;
  idVivaio:any;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;
  popUpBtn2:string;

  constructor(private inventarioService :InventarioService, 
    private router: Router,  public route: ActivatedRoute, private utilService: UtilService) { }


  ngOnInit() {
    this.user = this.utilService.getUser();
    this.idVivaio = this.route.snapshot.queryParams['idVivaio'];
    this.inventarioService
      .getInfoAnnataInventario(this.user.fkVivaio > 0?this.user.fkVivaio:this.idVivaio)
    .subscribe((resp)=>{
      this.infoAnnataInventario = resp});
  }

  showModalMsg(type:string, msg:string, btn1:string, btn2:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
    this.popUpBtn2 = btn2;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    if(this.popUpBtn2 == 'Termina'){
      this.indietro();
    }
  }

  indietro(){
    this.router.navigate(['/content/inventario-piantine'])
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  creaNuovaAnnata(){
    let msg = 'Procedendo con la presente funzionalità, il sistema genera massivamente ' +
    'nell’inventario in oggetto nuove partite di piantine a partire dalle partite attualmente ' +
    'presenti ancora attive. Sei sicuro di voler procedere?';
    this.showModalMsg('Attenzione',msg,'SI','NO');
  }

  yesPopUp(){
    this.popUpMsg = null;
    this.inventarioService.creaAnnataInventario({'idVivaio':this.idVivaio})
    .subscribe((res)=>{
      this.showModalMsg('Info','La nuova annata inventario è stata creata correttamente!',null,'Termina');
    })
  }

}
