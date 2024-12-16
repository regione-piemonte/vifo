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
  selector: 'app-riepilogo-preassegnata',
  templateUrl: './riepilogo-preassegnata.component.html',
  styleUrls: ['./riepilogo-preassegnata.component.css']
})
export class RiepilogoPreassegnataComponent implements OnInit {

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
  docType:string;
  variazioneForm: FormGroup;
  importoScontatoForm: FormGroup;
  
  totFatturaInizialeEuro:Number;
  stepAssegnazione:number=0;
  aliquotaIVA:number;

  allegatiTableHeaders: TableHeader[] = [
    { field: "tipoAllegatoDescr", header: "Tipo allegato" },
    { field: "nomeFile", header: "Nome file" },
    { field: "dimensioneFileKb", header: "Kb" }];

  unsubscribe$ = new Subject<void>();

  scontoIniziale:number;
  assegnazioneForm: FormGroup;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;
  popUpBtn2:string;

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
    .subscribe((res)=>{
      this.infoDomanda=res;
      if(this.isUserRichiedente == false){
        this.initAssegnazioneForm(this.infoDomanda.notePreassegnata);
      }
    });

    this.vivaiService.loadDomanda(this.idDomanda).pipe(takeUntil(this.unsubscribe$))
      .subscribe(res => {
        this.domanda = res;
        this.totFatturaInizialeEuro = res.totFattura;
        this.tipoDomanda = res.fkTipoDomanda==1?'gratuita':'vendita'
        this.personData = res.soggetto;
        this.listSpecie = res.listSpecie;
        this.listDestinazioni = res.listDestinazione;
        this.listDocumenti = res.listDocumenti;
        this.fillDescVivaio(this.domanda.fkVivaio);
        if(res.statoDomanda.indexOf('ACCETTATA') == 0){
          this.stepAssegnazione = 3;
        }
        if(this.listDocumenti){
          this.listDocumentiEditing = this.getDocsByTypes([
            CONST.DOCS_TYPE.ASSEGNAZIONE_GRATUITA_NO_FIRMA,
            CONST.DOCS_TYPE.ASSEGNAZIONE_GRATUITA_FIRMA_DIGITALE,
            CONST.DOCS_TYPE.ASSEGNAZIONE_GRATUITA_FIRMA_AUTOGRAFA,
            CONST.DOCS_TYPE.ASSEGNAZIONE_ONEROSA_NO_FIRMA,
            CONST.DOCS_TYPE.ASSEGNAZIONE_ONEROSA_FIRMA_DIGITALE,
            CONST.DOCS_TYPE.ASSEGNAZIONE_ONEROSA_FIRMA_AUTOGRAFA
          ])
        }
       
        this.vivaiService.getAllAssegnazioniByIdDomanda(this.idDomanda)
        .subscribe(res => {
          this.listAssegnazioni = res;
          this.vivaiService.getConfigationByName('ALIQUOTA_IVA').subscribe(res => {
            this.aliquotaIVA = parseFloat(res.value);
            this.domanda.aliquotaIva = this.domanda.totImportiEuro == 0?this.aliquotaIVA:this.domanda.aliquotaIva;
            this.dizionariService.getScontoByImporto(this.domanda.totImportiEuro != 0? 
              this.domanda.totImportiEuro:this.getImportiEuro())
            .subscribe((res)=>{
              this.scontoIniziale = res;
              this.fillImporti();
            })
          })
        })        
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
      if(this.domanda.statoDomanda == 'CHIUSA'){
        this.router.navigate([ this.configService.getPathByStato("CHIUSA") + this.idDomanda]);
      }else if(this.domanda.statoDomanda == 'ASSEGNATA'){
        this.router.navigate(['/content/riepilogo/assegnata/' + this.idDomanda]);
      }
    }
  }

  initAssegnazioneForm(note){
    this.assegnazioneForm = this.fb.group({
      note: [{ value:note?note:'', disabled: false }]
    })
  }

  isMarcoBolloEnabled(){
    if(this.tipoDomanda == 'gratuita' && this.domanda.fkScopo == 5
      && !this.personData.flgEntePubblico){
        return true;
    }
    return false;
  }

  getImportiEuro(){
    let res = 0;
    for(let i in this.listAssegnazioni){
      res+=this.listAssegnazioni[i].importo;
    }
    return res;
  }

  fillImporti(){
    if(this.domanda.totImportiEuro == 0){
      this.domanda.totImportiEuro = this.getImportiEuro();
      this.fillImportiBySconto(this.scontoIniziale);
    }
      this.createFormImportoTotaleSconto(this.domanda.totImponibileEuro);
  }

  fillImportiBySconto(sconto:number){
    this.domanda.scontoApplicato = sconto;
    this.domanda.totImponibileEuro = this.domanda.totImportiEuro - 
              this.getValuePercentuale(this.domanda.totImportiEuro,this.domanda.scontoApplicato);
    this.domanda.importoIva = this.getValuePercentuale(this.domanda.totImponibileEuro,this.domanda.aliquotaIva);
    this.domanda.totFattura = this.domanda.totImponibileEuro + this.domanda.importoIva;
  }

  fillImportiByTotaleScontato(totScontato:number){
    this.domanda.totImponibileEuro = totScontato;
    this.domanda.scontoApplicato = (this.domanda.totImportiEuro - totScontato)*100/this.domanda.totImportiEuro;
    this.domanda.importoIva = this.getValuePercentuale(this.domanda.totImponibileEuro,this.domanda.aliquotaIva);
    this.domanda.totFattura = this.domanda.totImponibileEuro + this.domanda.importoIva;
  }

  getValuePercentuale(value:number,percentuale:number){
    if(percentuale == 0){
      return 0;
    }else{
      return value * percentuale/100;
    }
  }

  createFormImportoTotaleSconto(value:number){
    this.importoScontatoForm = this.fb.group({
      importoScontato: [this.formatTwoDecimal(value),[Validators.required,Validators.pattern(CONST.PATTERN_NUMERIC_WITH_2_DECIMAL)]]
    })
  }

  formatIfDecimal(val){
    if((''+val).indexOf('.')>0){
      return this.formatTwoDecimal(val);
    }
    return val;
  }

  formatTwoDecimal(value:number){
    if(value || value == 0){
      let valueStr = parseFloat('' + value).toFixed(2);
      return (valueStr).replace('.',',');
    }else{
      return '';
    }
  }

  onTotaleScontatoChagne(){
    let value = this.importoScontatoForm.get('importoScontato').value;
    if(value){
      value = value.replace(',','.');
      if(!isNaN(value)){
        this.fillImportiByTotaleScontato(parseFloat(value));
      }
    }
    
  }

  isScaricaAssegnazioneEnabled(){
    return  this.domanda.fkTipoDomanda==CONST.DOMANDA_TYPE.DOMANDA_GRATUITA ||
     (this.totFatturaInizialeEuro == this.domanda.totFattura && this.domanda.totFattura > 0);
  }

  onIvaChange(){
    let val = document.getElementById('esenteIVA')['checked'];
    if(val){
      this.domanda.aliquotaIva = 0;
    }else{
      this.domanda.aliquotaIva = this.aliquotaIVA;
    }
    this.onTotaleScontatoChagne();
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

  deleteAllegato(event){
    let idAllegato = this.listDocumenti[event.index].idAllegato;
    this.vivaiService.deleteDocumentoAllegato(idAllegato).subscribe(
      (response) => {
        console.log("deleteDocumentoAllegato resp: " + response)
        let elem = this.listDocumentiEditing[0];
        this.listDocumentiEditing = [];
        let index = this.listDocumenti.map(function(e) { return e.idAllegato; }).indexOf(elem.idAllegato);
        this.listDocumenti.splice(index, 1);
      }
    )
  }

  getDocsByTypes(idsDocs:number[]){
    return this.listDocumenti.filter(e => idsDocs.indexOf(e.fkTipoAllegato) > -1);
  }

  downloadAssegnazione(tipoFirma){
    if(!this.isScaricaAssegnazioneEnabled()){
      this.showModalMsg('Attenzione','E\' necessario prima confermare i dati','OK',null);
      return;
    }
    let tipoDomanda = this.domanda.fkTipoDomanda==CONST.DOMANDA_TYPE.DOMANDA_GRATUITA?
    CONST.PDF_TYPE.ASSEGNAZIONE_GRATUITA:CONST.PDF_TYPE.ASSEGNAZIONE_ONEROSA;
    this.vivaiService.pdfGenerator(tipoDomanda ,
      this.idDomanda,{'tipoFirma':tipoFirma,'isBozza':"true"});
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

  chiudiDomanda(){
    this.showModalMsg('Attenzione','La domanda in oggetto verrà archiviata. Procedere?','No','Si');
  }

  eseguiChiudiDomanda(){
    delete this.domanda['listDestinazione'];
    delete this.domanda['listDocumenti'];
    delete this.domanda['listSpecie'];
    delete this.domanda['soggetto'];
    this.domanda.note = this.assegnazioneForm.value.note;
    this.vivaiService.updateDomandaPreassegnata(this.domanda)
    .subscribe(response => {
      this.vivaiService.updateDomandaToClose(this.idDomanda)
      .subscribe(res=>{
       this.domanda.statoDomanda = 'CHIUSA';
       this.showModalMsg('Info','Domanda chiusa correttamente!','Termina',null);
      })
    })
   
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

  modificaAssegnazione(){
    this.router.navigate(['/content/form-assegnazione/' + this.idDomanda]);
  }

  inviaAssegnazione(){
    if(!this.isScaricaAssegnazioneEnabled()){
      this.showModalMsg('Attenzione','E\' necessario prima confermare i dati','OK',null);
      return;
    }
    delete this.domanda['listDestinazione'];
    delete this.domanda['listDocumenti'];
    delete this.domanda['listSpecie'];
    delete this.domanda['soggetto'];
    this.domanda.note = this.assegnazioneForm.value.note;
    this.vivaiService.updateDomandaToAssegnata(this.domanda)
    .subscribe(response => {
      this.domanda.statoDomanda = 'ASSEGNATA';
      this.showModalMsg('Info','Assegnazione inviata correttamente al richiedente!','Termina',null);
    })
  }

  updateDomandaPreassegnata(){
    delete this.domanda['listDestinazione'];
    delete this.domanda['listDocumenti'];
    delete this.domanda['listSpecie'];
    delete this.domanda['soggetto'];
    this.domanda.note = this.assegnazioneForm.value.note;
    this.vivaiService.updateDomandaPreassegnata(this.domanda)
    .subscribe(response => {
      this.totFatturaInizialeEuro = response.totFattura;
      this.domanda = response;
      this.showModalMsg('Info','Dati salvati correttamente!','Chiudi',null);
    })
  }



  createVariazioneForm(){
    this.variazioneForm = this.fb.group({
      note: ['',[Validators.required,Validators.minLength(10),Validators.maxLength(200)]]
    })
  }

}
