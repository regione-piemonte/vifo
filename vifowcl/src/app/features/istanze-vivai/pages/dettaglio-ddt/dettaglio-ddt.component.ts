/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';
import { DdtModel } from 'src/app/shared/models/ddt.model';
import { CONST } from 'src/app/shared/constants';
import { DomandaModel } from 'src/app/shared/models/domanda.model';
import { HomeModel } from 'src/app/shared/models/home.model';
import { TableHeader } from 'src/app/shared/models/table-header';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { UtilService } from '../../services/util.service';
import { VivaiService } from '../../services/vivai.service';
import { ConfigService } from 'src/app/shared/services/config.service';

@Component({
  selector: 'app-dettaglio-ddt',
  templateUrl: './dettaglio-ddt.component.html',
  styleUrls: ['./dettaglio-ddt.component.css']
})
export class DettaglioDdtComponent implements OnInit {

  numeroDdt:number;
  annoDdt:number;
  domanda:DomandaModel;
  infoDdt:any;
  userProfile:string;
  tipoDomanda:string;
  today: string;
  user : HomeModel;
  filePopUp = false;
  listDdt:DdtModel[]=[];

  ddtTableHeaders: TableHeader[] = [
    { field: 'specie', header: 'SPECIE'},
    { field: 'idPartita', header: 'ID PARTITA'},
    { field: 'vivaio', header: 'VIVAIO'},
    { field: 'allevamento', header: 'ALLEVAMENTO'},
    { field: 'eta', header: 'ETA\''},
    { field: 'altezza', header: 'ALTEZZA (cm)'},
    { field: 'certificato', header: 'CERTIFICATO PROVENIENZA'},
    { field: 'qtaInDdt', header: 'QUANTITA\' IN DDT' }];

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(private vivaiService: VivaiService,
    public route: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router,
    private utilService: UtilService,
    private configService: ConfigService,
    private dialogService: DialogService) { }

  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      this.numeroDdt = paramMap.get("numeroDdt")?+paramMap.get("numeroDdt"):null;
      this.annoDdt = paramMap.get("annoDdt")?+paramMap.get("annoDdt"):null;
    });

    this.vivaiService.getInfoDdt(this.numeroDdt, this.annoDdt)
    .subscribe(res=>{
      this.infoDdt=res;
      this.vivaiService.loadDomanda(this.infoDdt.idDomanda)
      .subscribe(res => {
        this.domanda = res;
      });
    });

    this.vivaiService.getDdtDetails(this.numeroDdt, this.annoDdt)
      .subscribe(res=>{
        this.listDdt = res;
      })

    this.user = this.utilService.getUser();

    this.today = this.utilService.getTodayFormated();

    this.userProfile = this.utilService.getUserProfile();
  }

  modificaDdt(){
    this.router.navigate(['/content/modifica-ddt/' + this.numeroDdt + '/' + this.annoDdt]);
  }

  indietro(){
    if(this.utilService.isComeFromDdtSearch()){
      this.router.navigate(['/content/ricerca']);
    }else{
      this.router.navigate([ this.configService.getPathByStato(this.domanda.statoDomanda) + this.domanda.idDomanda]);
    }
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  annullaDdt(){
    this.vivaiService.annullaDdt(this.numeroDdt, this.annoDdt)
    .subscribe((res)=>{
      this.showModalMsg('Info','DDT annullato correttamente','OK');
    });
  }

  stampaDdt(){
    this.vivaiService.pdfGenerator(CONST.PDF_TYPE.DDT, this.numeroDdt, {'anno':this.annoDdt})
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    this.indietro()
  }

}

