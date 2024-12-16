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
import { UserModel } from 'src/app/shared/models/user.model';
import { PagamentoModel } from 'src/app/shared/models/pagamento.model';
import { validFilename } from 'src/app/validators/string.validators';

@Component({
  selector: 'app-riepilogo-assegnata',
  templateUrl: './riepilogo-assegnata.component.html',
  styleUrls: ['./riepilogo-assegnata.component.css']
})
export class RiepilogoAssegnataComponent implements OnInit {

  idDomanda:number;
  domanda:DomandaModel;
  infoDomanda:any;
  userProfile:string;
  vivaio:string;
  tipoDomanda:string;
  personData:SoggettoModel;
  isEntePubblico:boolean;
  listSpecie:SpecieRichiestaFull[];
  listDestinazioni:DestinazioneModel[]=[];
  listDocumenti:DocumentoAllegato[]=[];
  listDocumentiEditing:DocumentoAllegato[]=[];
  listAssegnazioni:AssegnazioneModel[];
  docType:string;
  popUpText:string;
  variazioneForm: FormGroup;
  showPagamento: boolean=false;

  stepAssegnazione:number=0;
  isUserUploadEnbled:boolean=false;

  allegatiTableHeaders: TableHeader[] = [
    { field: "tipoAllegatoDescr", header: "Tipo allegato" },
    { field: "nomeFile", header: "Nome file" },
    { field: "dimensioneFileKb", header: "Kb", width:"100px" }];

  unsubscribe$ = new Subject<void>();

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;
  popUpBtn2:string;
  backFromPagamenti:boolean=false;

  isAnnullaPagamento:boolean=false;

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
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      this.idDomanda = paramMap.get("idDomanda")?+paramMap.get("idDomanda"):null;
    });

    this.route.queryParams.subscribe(params => {
      let esito = params['esito'];
      if(esito){
        //back from pagamenti
        if(esito == 'OK'){
          this.showModalMsg('Info','L\'operazione si è conclusa con successo! ','Chiudi',null);
        }else{
          this.showModalMsg('Attenzione','L\'operazione è andata in errore! ','Chiudi',null);
        }
      }
  });

    this.userProfile = this.utilService.getUserProfile();

    this.vivaiService.getInfoDomanda(this.idDomanda)
    .subscribe(res=>this.infoDomanda=res);

    this.vivaiService.loadDomanda(this.idDomanda).pipe(takeUntil(this.unsubscribe$))
      .subscribe(res => {
        this.domanda = res;
        this.setIsUserUploadEnbled();
        this.tipoDomanda = res.fkTipoDomanda==CONST.DOMANDA_TYPE.DOMANDA_GRATUITA?'gratuita':'vendita'
        this.personData = res.soggetto;
        this.isEntePubblico = this.personData.flgEntePubblico == 1;
        this.listSpecie = res.listSpecie;
        this.listDestinazioni = res.listDestinazione;
        this.listDocumenti = res.listDocumenti?res.listDocumenti:[];
        this.docType = this.isEntePubblico || this.isDocPresent([11])?'D':this.isDocPresent([12])?'A':'';
        this.fillDescVivaio(this.domanda.fkVivaio);
        if(res.statoDomanda.indexOf('ACCETTATA') == 0 || this.tipoDomanda == 'gratuita'){
          this.stepAssegnazione = 3;
        }else if(this.userProfile !='RICHIEDENTE'){
          this.stepAssegnazione = 1;
        }
        this.createVariazioneForm(this.domanda.note);
        this.loadDocumentiEditing();
      })
    
      this.vivaiService.getAllAssegnazioniByIdDomanda(this.idDomanda)
      .subscribe(res => {
        this.listAssegnazioni = res;
      })
  }

  showModalMsg(type:string, msg:string, btn1:string,btn2:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
    this.popUpBtn2 = btn2;
  }
  
  closePopUp(){
    this.isAnnullaPagamento=false;
    this.popUpMsg = null;
    if(this.popUpBtn1 == 'Termina'){
      if(this.domanda.statoDomanda == 'CHIUSA'){
        this.router.navigate([ this.configService.getPathByStato("CHIUSA") + this.idDomanda]);
      }else if(this.domanda.statoDomanda == 'RICHIESTA VARIAZIONI'){
          this.router.navigate([ this.configService.getPathByStato("RICHIESTA VARIAZIONI") + this.idDomanda]);
      }else{
        this.stepAssegnazione = 3;
      }
    }
  }

  setIsUserUploadEnbled(){
    if(this.userProfile =='RICHIEDENTE'){
      this.isUserUploadEnbled = true;
    }else{
      this.vivaiService.getConfigById(this.domanda.fkConfigUtente)
      .subscribe((res)=>{
        if(res.fkVivaio == this.domanda.fkVivaio){
          this.isUserUploadEnbled = true;
        } 
      })
    }
  }

  loadDocumentiEditing(){
    if(this.listDocumenti){
      this.listDocumentiEditing = this.getDocsByTypes([
        CONST.DOCS_TYPE.ASSEGNAZIONE_ONEROSA_FIRMA_DIGITALE,
        CONST.DOCS_TYPE.ASSEGNAZIONE_ONEROSA_FIRMA_AUTOGRAFA,
        CONST.DOCS_TYPE.SCANSIONE_DOCUMENTO_IDENTITA
      ])
    }
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

  formatIt(value){
    if(value){
      return (value + '').replace('.',',');
    }
    return '-';
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

  accettaAssegnazione(){
    this.stepAssegnazione = 1;
  }

  confermaAssegnazione(){
    this.vivaiService.confermaAssegnazione(this.idDomanda)
    .subscribe((response: any) => {
      this.domanda.statoDomanda = 'ACCETTATA';
      this.domanda.fkStatoDomanda = 5;
      this.showModalMsg('Info','Accettazione inviata correttamente al Vivaio Forestale regionale di competenza!','Termina',null);
    })
  }

  richiediVariazioni(){
    this.stepAssegnazione = 2;
  }

  confermaVariazioni(){
    let domanda = {idDomanda:this.domanda.idDomanda,note:this.variazioneForm.controls['note'].value}
    this.vivaiService.confermaVariazioni(domanda)
    .subscribe((response: any) => {
      this.domanda.statoDomanda = 'RICHIESTA VARIAZIONI';
      this.showModalMsg('Info','Richiesta variazioni inviata correttamente al Vivaio Forestale regionale di competenza!','Termina',null);
      // this.popUpText = 'Richiesta variazioni inviata correttamente al Vivaio Forestale regionale di competenza';
      // this.filePopUp = true;
    })
  }

  goToHome(){
    this.router.navigate(['/content'])
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

  changeDocType(docType){
    this.docType = docType;
  }

  uploadFile(event, id){
    const formData: FormData = new FormData();
    formData.append('form', event.files[0]);
    let fileName = event.files[0].name;
    formData.append('fileName',fileName);
    let tipo = null;
    if(id == 0){
      if(this.docType == 'D'){
        tipo = CONST.DOCS_TYPE.ASSEGNAZIONE_ONEROSA_FIRMA_DIGITALE;
      }else if(this.docType == 'A'){
        tipo = CONST.DOCS_TYPE.ASSEGNAZIONE_ONEROSA_FIRMA_AUTOGRAFA;
      }
    }else{
      tipo = CONST.DOCS_TYPE.SCANSIONE_DOCUMENTO_IDENTITA;
    }
    let cmpUpload = document.getElementById("fileUpload_" + id);
    if(!validFilename(fileName)){
      this.showModalMsg('Attenzione', "I caratteri speciali non sono accettati nel nome del file. Modificare il nome del file",'OK',null);
      cmpUpload.getElementsByTagName('button')[2].click();
      return;
    }
    if(!this.checkFileAllowedFormat(fileName)){
      this.showModalMsg('Attenzione', 'Formato del file non ammesso! I formati validi sono: pdf, jpg, jpeg, tiff, png, p7m','OK',null);
      cmpUpload.getElementsByTagName('button')[2].click();
      return;
    }
    this.vivaiService.uploadFileDomanda(formData, this.idDomanda, tipo).subscribe(
      (response) => {
        if(response.error){
          this.showModalMsg('Errore',response.error,'OK',null);
          return;
        }
        this.listDocumenti.push(response);
        this.listDocumentiEditing.push(response);
      }
    )
    
    cmpUpload.getElementsByTagName('button')[2].click();
  }

  deleteAllegato(event){
    let idAllegato = this.listDocumentiEditing[event.index].idAllegato;
    this.vivaiService.deleteDocumentoAllegato(idAllegato).subscribe(
      (response) => {
        console.log("deleteDocumentoAllegato resp: " + response)
        let idx = this.listDocumenti.indexOf(this.listDocumentiEditing[event.index]);
        if(idx > -1){
          this.listDocumenti.splice(idx, 1);
          this.loadDocumentiEditing();
        }
      }
    )
  }

  isDocPresent(idsDocs:number[]){
    for(let i in this.listDocumenti){
      if(idsDocs.filter(e => this.listDocumenti[i].fkTipoAllegato == e).length > 0){
        return true;
      }
    }
    return false;
  }

  getDocsByTypes(idsDocs:number[]){
    return this.listDocumenti.filter(e => idsDocs.indexOf(e.fkTipoAllegato) > -1);
  }

  checkFileAllowedFormat(fileName:string){
    let idx = fileName.lastIndexOf('.');
    if(idx > -1){
      let ext = fileName.substr(idx,fileName.length-1).toLowerCase();
      if(ext == '.pdf' || ext == '.jpg' || ext == '.jpeg' || ext == '.tiff' 
        || ext == '.png' || ext == '.p7m'){
        return true;
      }
    }
    return false
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

  isMarcoBolloEnabled(){
    if(this.tipoDomanda == 'gratuita' && this.domanda.fkScopo == 5
      && !this.personData.flgEntePubblico){
        return true;
    }
    return false;
  }
  
  indietro(){
    if(this.userProfile =='RICHIEDENTE'){
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

  stampaEtichette(){
    this.vivaiService.pdfGenerator(CONST.PDF_TYPE.ETICHETTA ,this.idDomanda,{});
  }

  creaDdt(){
    this.router.navigate(['/content/form-ddt/' + this.idDomanda]);
  }

  inserisciRitiro(){
    this.router.navigate(['/content/form-ritiro/' + this.idDomanda]);
  }

  downloadAssegnazione(tipoFirma){
    let tipoDomanda = this.domanda.fkTipoDomanda==CONST.DOMANDA_TYPE.DOMANDA_GRATUITA?
    CONST.PDF_TYPE.ASSEGNAZIONE_GRATUITA:CONST.PDF_TYPE.ASSEGNAZIONE_ONEROSA;
    this.vivaiService.pdfGenerator(tipoDomanda ,
      this.idDomanda,{'tipoFirma':tipoFirma});
  }

  annullaAssegnazione(){
    this.vivaiService.rejectAssegnazione(this.idDomanda)
      .subscribe(response => {
        this.router.navigate(['/content/riepilogo/preassegnata/' + this.idDomanda]);
      })
  }

  createVariazioneForm(note:string){
    this.variazioneForm = this.fb.group({
      note: [{ value: note?note:'',disabled: this.domanda.statoDomanda == 'RICHIESTA VARIAZIONI'},[Validators.required,Validators.minLength(20),Validators.maxLength(200)]]
    })
  }

  effettuaPagamento(){
    this.showPagamento = true;
  }

  chiudiPagamento(){
    this.showPagamento = false;
  }

  modalAnnullaPagamento(){
    this.isAnnullaPagamento=true;
    this.showModalMsg('Errore','ATTENZIONE! Procedendo con l’operazione verrà annullato lo IUV che è stato generato per il pagamento in corso: l’eventuale avviso di pagamento scaricato non avrà più valore. Sarà necessario ripetere nuovamente l’operazione di “Effettua pagamento “ e, in caso di pagamento differito, scaricare un nuovo avviso di pagamento.  ','Annulla','Prosegui')  
  }

  annullaPagamento(){
    this.vivaiService.annullaPagamentoPiantine(this.domanda)
    .subscribe((res)=>{
      this.showModalMsg('Info','Operazione eseguita con successo! ','Chiudi',null);
      },
      (e)=> console.log(e),
      ()=> this.vivaiService.getInfoDomanda(this.idDomanda)
      .subscribe((res)=>{
        console.log(res);
        this.infoDomanda=res
      })
      )
  }

  downloadAvvisoPagamento(){
    this.vivaiService.downloadAvvisoPagamento(this.idDomanda);
  }

  downloadRicevutaPagamento(){
    this.vivaiService.downloadRicevutaPagamento(this.idDomanda);
  }

  isLocalhost(){
    return window.location.href.indexOf('http://localhost:17000/vifo/private/') == 0;
  }

  testRiceviRT(){
    this.vivaiService.testRiceviRT()
    .subscribe((res)=>{
      alert(res.esito)
    })
  }


}
