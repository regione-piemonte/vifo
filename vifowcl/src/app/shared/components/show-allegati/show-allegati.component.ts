/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input} from '@angular/core';
import { VivaiService } from 'src/app/features/istanze-vivai/services/vivai.service';
import { DocumentoAllegato } from '../../models/plain-sesto-sezione.model';
import { TableHeader } from '../../models/table-header';
import { SaveFileService } from '../../services/save-file.service';

@Component({
  selector: 'app-show-allegati',
  templateUrl: './show-allegati.component.html',
  styleUrls: ['./show-allegati.component.css']
})
export class ShowAllegatiComponent implements OnInit {

  @Input() listDocumenti:DocumentoAllegato[];

  tableHeaders: TableHeader[] = [
    { field: "tipoAllegatoDescr", header: "Tipo allegato" },
    { field: "nomeFile", header: "Nome file" },
    { field: "dimensioneFileKb", header: "Kb", width:"100px" }];

  constructor(private vivaiService: VivaiService,
    private saveFileService: SaveFileService) { }

  ngOnInit() {
  }

  downloadAllegato(event){
    this.downloadAllegatoRecall(event,1);
  }

  downloadAllegatoRecall(idAlleato: number, rip:number){
    this.vivaiService.downloadAllegato(idAlleato).subscribe(
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
