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
import { ConfigService } from 'src/app/shared/services/config.service';

@Component({
  selector: 'app-riepilogo-inviata',
  templateUrl: './riepilogo-inviata.component.html',
  styleUrls: ['./riepilogo-inviata.component.css']
})
export class RiepilogoInviataComponent implements OnInit {

  idDomanda:number;
  domanda:DomandaModel;
  isUserRichiedente:boolean;
  vivaio:string;
  tipoDomanda:string;
  personData:SoggettoModel;
  listSpecie:SpecieRichiestaFull[];
  listDestinazioni:DestinazioneModel[]=[];
  listDocumenti:DocumentoAllegato[]=[];
  listDocumentiEditing:DocumentoAllegato[]=[];
  docType:string;
  filePopUp:boolean = false;
  popUpText:string;
  rifiutoForm: FormGroup;
  infoDomanda:any;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;
  popUpBtn2:string;

  statoDomanda:string;

  allegatiTableHeaders: TableHeader[] = [
    { field: "tipoAllegatoDescr", header: "Tipo allegato" },
    { field: "nomeFile", header: "Nome file" },
    { field: "dimensioneFileKb", header: "Kb" }];

  unsubscribe$ = new Subject<void>();

  constructor(private route: ActivatedRoute, 
    private router: Router,
    private vivaiService: VivaiService,
    private dizionariService: DizionariService,
    private saveFileService: SaveFileService,
    private fb: FormBuilder,
    private utilService: UtilService,
    private configService: ConfigService,
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
      })
  }

  showModalMsg(type:string, msg:string, btn1:string,btn2:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
    this.popUpBtn2 = btn2;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    if(this.popUpBtn1 == 'Termina'){    
      if(this.statoDomanda == 'RIFIUTATA'){  
        this.router.navigate(['/content/riepilogo/rifiutata/' + this.idDomanda]);
      }else if(this.statoDomanda == 'CHIUSA'){
        this.router.navigate([ this.configService.getPathByStato("CHIUSA") + this.idDomanda]);
      }
    }
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

  downloadAllegato(event){
    this.downloadAllegatoRecall(event,1);
  }

  downloadAllegatoRecall(idAlleato: number, rip:number){
    this.vivaiService.downloadAllegato(idAlleato).pipe(takeUntil(this.unsubscribe$)).subscribe(
      response => {
        if(response.status === 200){
          //se il file è vuoto fa fino a 5 tentativi
          if(response.body.size > 5 ||  rip > 5){
            let contentDisposition = response.headers.get("content-disposition");
            let filename = contentDisposition
              .split(";")[1]
              .split("filename")[1]
              .split("=")[1];
            this.saveFileService.saveFile(response, filename);
          }else{
            console.log(rip + " - Retry document download. Document size: " + response.body.size)
            this.downloadAllegatoRecall(idAlleato, rip +1);
          }
        }
      });
  }

  closePopUpEnd(){
    this.filePopUp = false;
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

  rifiutaDomanda(){
    this.rifiutoForm = this.fb.group({
      motivoRifiuto: [{ value: '', disabled:false },
        [Validators.required,Validators.minLength(20)]]
    })
    this.filePopUp = true;
  }

  eseguiRifiuto(){
    this.vivaiService.updateDomandaToRifiutata(this.idDomanda,this.rifiutoForm.value.motivoRifiuto)
    .subscribe((res)=>{
      this.statoDomanda = 'RIFIUTATA';
      this.showModalMsg('Info','Salvataggio eseguito correttamente','Termina',null);
    })
  }

  inserisciAssegnazione(){
    this.router.navigate(['/content/form-assegnazione/' + this.idDomanda]);
  }

  chiudiDomanda(){
    this.showModalMsg('Attenzione','La domanda in oggetto verrà archiviata. Procedere?','No','Si');
  }

  eseguiChiudiDomanda(){
    this.vivaiService.updateDomandaToClose(this.idDomanda)
     .subscribe(res=>{
      this.domanda.statoDomanda = 'CHIUSA';
      this.showModalMsg('Info','Domanda chiusa correttamente!','Termina',null);
     })
  }

}