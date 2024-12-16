/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, Output, OnInit, EventEmitter } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { VivaiService } from 'src/app/features/istanze-vivai/services/vivai.service';
import { DocumentoAllegato } from '../../models/plain-sesto-sezione.model';
import { TableHeader } from '../../models/table-header';
import { Subject } from 'rxjs';
import { SaveFileService } from '../../services/save-file.service';
import { DialogService } from '../../services/dialog.service';
import { ButtonType, DialogButtons } from '../../error-dialog/error-dialog.component';
import { CONST } from '../../constants';
import { validFilename } from 'src/app/validators/string.validators';

@Component({
  selector: 'app-form-file',
  templateUrl: './form-file.component.html',
  styleUrls: ['./form-file.component.css']
})
export class FormFileComponent implements OnInit {

  @Input() idDomanda:number;
  @Input() tipoDomanda:number;
  @Input() userType:string;
  @Input() isEntePubblico:boolean
  @Input() listDocumenti:DocumentoAllegato[];
  @Input() isMarcaBolloPresent:boolean;
  @Input() viewType:string;
  @Output() emitAddAllegato = new EventEmitter<DocumentoAllegato>();
  @Output() emitDeleteAllegato = new EventEmitter<number>();

  unsubscribe$ = new Subject<void>();

  allegatiTableHeaders: TableHeader[] = [
    { field: "tipoAllegatoDescr", header: "Tipo allegato" },
    { field: "nomeFile", header: "Nome file" },
    { field: "dimensioneFileKb", header: "Kb" }];

  docType:string;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(
    private vivaiService: VivaiService, 
    private saveFileService: SaveFileService,
    private dialogService: DialogService) { }

  ngOnInit() {
    if(this.isEntePubblico){
      this.docType = 'D';
    }else{
      if(this.listDocumenti.length > 0){
        if(this.isDocPresent([3,6,13])){
          this.docType = 'A';
        }else if(this.isDocPresent([2,5])){
          this.docType = 'D';
        }
      }
    }
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }

  closePopUp(){
    this.popUpMsg = null;
  }

  changeDocType(docType){
    this.docType = docType;
  }

  isDomandaUploaded(){
    return false;
  }

  isDocumentoUploaded(){
    return false;
  }

  uploadFile(event, tipo){
    if(tipo == 0){
      if(this.docType == 'D'){
        tipo = this.tipoDomanda==1?2:5;
      }else{
        tipo = this.tipoDomanda==1?3:6;
      }
    }
    console.log("tipoDocumento: " + tipo);
    const formData: FormData = new FormData();
    formData.append('form', event.files[0]);
    let fileName = event.files[0].name;
    formData.append('fileName',fileName);
   
    if(!validFilename(fileName)){
      this.showModalMsg('Attenzione', "I caratteri speciali non sono accettati nel nome del file. Modificare il nome del file",'OK');
      //cmpUpload.getElementsByTagName('button')[2].click();
      return;
    }
    if(!this.checkFileAllowedFormat(fileName)){
      // this.dialogService.showDialog(true, 'Formato del file non ammesso! I formati validi sono: pdf, jpg, jpeg, tiff, png, p7m', 'Attenzione', DialogButtons.OK, '', (buttonId: number): void => {
      //   if (buttonId === ButtonType.OK_BUTTON) {
      //     console.log('BUTTON WORKS');
      //   }
      // });
      this.showModalMsg('Attenzione', 'Formato del file non ammesso! I formati validi sono: pdf, jpg, jpeg, tiff, png, p7m','OK')
      return;
    }
    this.vivaiService.uploadFileDomanda(formData, this.idDomanda, tipo).subscribe(
      (response) => {
        if(response.error){
          // this.dialogService.showDialog(true,response.error,'Errore');
          this.showModalMsg('Errore',response.error,'OK');
          return;
        }
        this.emitAddAllegato.emit(response);
      }
    )
    let cmpUpload = document.getElementById("fileUpload_" + (tipo==13?tipo:0));
    cmpUpload.getElementsByTagName('button')[2].click();
  }

  checkFileAllowedFormat(fileName:string){
    let idx = fileName.lastIndexOf('.');
    if(idx > -1){
      let ext = fileName.substr(idx,fileName.length-1).toLowerCase();
      if(ext == '.pdf' || ext == '.jpg' || ext == '.jpeg' || ext == '.tiff' 
      || ext == '.png' ||  ext == '.p7m'){
        return true;
      }
    }
    return false
  }

  deleteAllegato(event){
    let idAllegato = this.listDocumenti[event.index].idAllegato;
    this.vivaiService.deleteDocumentoAllegato(idAllegato).subscribe(
      (response) => {
        console.log("deleteDocumentoAllegato resp: " + response)
        this.emitDeleteAllegato.emit(event.index);
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

  downloadAllegato(event){
    this.downloadAllegatoRecall(event,1);
  }

  downloadDichiarazione(tipoFirma){
    this.vivaiService.pdfGenerator(CONST.PDF_TYPE.DOMANDA_GRATUITA ,this.idDomanda,{'tipoFirma':tipoFirma});
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

}
