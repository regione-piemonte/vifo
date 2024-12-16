/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
  import { ActivatedRoute, ParamMap, Router } from '@angular/router';
  import { Subject } from 'rxjs';
  import { CONST } from 'src/app/shared/constants';
  import { takeUntil } from 'rxjs/operators';
  import { SoggettoModel } from 'src/app/shared/models/soggetto.model';
  import { DestinazioneModel } from 'src/app/shared/models/destinazione.model';
  import { DocumentoAllegato } from 'src/app/shared/models/plain-sesto-sezione.model';
  import { SpecieRichiestaFull } from 'src/app/shared/models/specie-richiesta-full.model';
  import { VivaiService } from '../../services/vivai.service';
  import { DomandaModel } from 'src/app/shared/models/domanda.model';
  import { DizionariService } from '../../services/dizionari.service';
  import { SelectItem } from 'primeng/components/common/selectitem';
  import { TableHeader } from 'src/app/shared/models/table-header';
  import { SaveFileService } from 'src/app/shared/services/save-file.service';
  import { DialogService } from 'src/app/shared/services/dialog.service';
  import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';
  import { FormBuilder, FormGroup, Validators } from '@angular/forms';
  import { AssegnazioneModel } from 'src/app/shared/models/assegnazione.model';
  import { UtilService } from '../../services/util.service';
  
  @Component({
    selector: 'app-riepilogo-chiusa',
    templateUrl: './riepilogo-chiusa.component.html',
    styleUrls: ['./riepilogo-chiusa.component.css']
  })
  export class RiepilogoChiusaComponent implements OnInit {
  
    idDomanda:number;
    domanda:DomandaModel;
    isUserRichiedente:boolean;
    vivaio:string;
    infoDomanda:any;
    tipoDomanda:string;
    personData:SoggettoModel;
    listSpecie:SpecieRichiestaFull[];
    listDestinazioni:DestinazioneModel[]=[];
    listDocumenti:DocumentoAllegato[]=[];
    listDocumentiEditing:DocumentoAllegato[]=[];
    listAssegnazioni:AssegnazioneModel[];
    listDDT:any[]=[];
    listRitiro:any[]=[];
    variazioneForm: FormGroup;
    
  
    allegatiTableHeaders: TableHeader[] = [
      { field: "tipoAllegatoDescr", header: "Tipo allegato" },
      { field: "nomeFile", header: "Nome file" },
      { field: "dimensioneFileKb", header: "Kb" }];
  
    ddtTableHeaders: TableHeader[] = [
      { field: "numeroDdt", header: "NUMERO DDT" },
      { field: "dataCreazione", header: "DATA CREAZIONE" }];
  
    unsubscribe$ = new Subject<void>();
  
    constructor(private route: ActivatedRoute, 
      private router: Router,
      private vivaiService: VivaiService,
      private dizionariService: DizionariService,
      private saveFileService: SaveFileService,
      private fb: FormBuilder,
      private utilService: UtilService,
      private dialogService: DialogService) { }
  
    ngOnInit() {
      this.isUserRichiedente = this.utilService.getUserProfile()=='RICHIEDENTE';
      this.route.paramMap.subscribe((paramMap: ParamMap) => {
        this.idDomanda = paramMap.get("idDomanda")?+paramMap.get("idDomanda"):null;
      });
  
      this.vivaiService.getInfoDomanda(this.idDomanda)
      .subscribe(res=>this.infoDomanda=res);
  
      this.vivaiService.loadDomanda(this.idDomanda).pipe(takeUntil(this.unsubscribe$))
        .subscribe(res => {
          this.domanda = res;
          this.tipoDomanda = res.fkTipoDomanda==1?'gratuita':'vendita'
          this.personData = res.soggetto;
          this.listSpecie = res.listSpecie;
          this.listDestinazioni = res.listDestinazione;
          this.listDocumenti = res.listDocumenti;
          this.fillDescVivaio(this.domanda.fkVivaio);
         
          this.vivaiService.getAllAssegnazioniByIdDomanda(this.idDomanda)
          .subscribe(res => {
            this.listAssegnazioni = res;
          })
        })
      
      this.vivaiService.getListDdtByIdDomanda(this.idDomanda)
      .subscribe(res => {
        this.listDDT = res;
      })
    }
  
    formatDate(data:string){
      if(data && data.length == 10){
        if(data.indexOf('-') == 4){
          let dataVet = data.split('-');
          return data[2] + '-' + data[1] + '-' + data[0];
        }
      }
      return data;
    }
  
    fillDescVivaio(idVivaio){
      this.dizionariService.getListVivaio()
      .subscribe((response: SelectItem[]) => {
        let vivaio = response.filter(item => item.value == idVivaio)
        if(vivaio.length > 0){
          this.vivaio = vivaio[0].label;
        }
      })
    }

    isMarcoBolloEnabled(){
      if(this.tipoDomanda == 'gratuita' && this.domanda.fkScopo == 5
        && !this.personData.flgEntePubblico){
          return true;
      }
      return false;
    }
  
    goToHome(){
      this.router.navigate(['/content'])
    }

    indietro(){
      if(this.isUserRichiedente){
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

    viewRow(numeroDdt){
      let ddt = this.listDDT.filter(item => item.numeroDdt == numeroDdt)[0];
      this.router.navigate(['/content/dettaglio-ddt/' + numeroDdt + '/' + ddt.annoDdt]);
    }

    downloadRicevutaPagamento(){
      this.vivaiService.downloadRicevutaPagamento(this.idDomanda);
    }
  
    
  
  
  }
  
  
