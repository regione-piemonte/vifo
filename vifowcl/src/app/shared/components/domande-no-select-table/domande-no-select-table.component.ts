/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { UtilService } from 'src/app/features/istanze-vivai/services/util.service';
import { CONST } from '../../constants';
import { DomandaModel } from '../../models/domanda.model';
import { TableHeader } from '../../models/table-header';
import { UserModel } from '../../models/user.model';

@Component({
  selector: 'app-domande-no-select-table',
  templateUrl: './domande-no-select-table.component.html',
  styleUrls: ['./domande-no-select-table.component.css']
})
export class DomandeNoSelectTableComponent implements OnInit {

  @Input() headers: TableHeader[];
  @Input() first: number;
  @Input() data: any[];
  @Input() initiallySortedColumn: string;
  @Input() replaceTrueFalse: string[];
  @Input() view: boolean;
  @Input() edit: boolean;
  @Input() delete: boolean;
  @Input() download: boolean;
  @Input() fieldToEdit: string;
  @Input() fieldToDownload: string;
  @Input() fieldToDelete: string;
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();
  @Output() editRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() viewRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() deleteRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() downloadEmitter: EventEmitter<string> = new EventEmitter();
  @Input() simpleTable?: boolean;
  @Input() rowsPerPageOptions: number[];
  @Input() totalSize: number;
  @Input() visualizaTable: boolean;

  user:UserModel;

  constructor(private utilService: UtilService) { }

  ngOnInit() {
    this.first = this.first?this.first:0;
    this.user = this.utilService.getUser();
    if(!this.rowsPerPageOptions){
      this.rowsPerPageOptions = [5, 10, 15];
    }
    console.log('edit: ' + this.edit + ' - view: ' + this.view);

  }

  updateData(event) {
    let data = event;
    if (event.sortOrder === -1) {
      data.field = event.sortField ? "-" + event.sortField : null;
    } else {
      data.field = event.sortField ? "+" + event.sortField : null;
    }
    data.page = event.first/event.rows + 1;
    data.limit = event.rows;
    this.updateTableChanges.emit(data);
  }

  viewRow(rowIndex) {
    this.viewRowEmitter.emit(rowIndex);
  }

  editRow(rowIndex) {
    this.editRowEmitter.emit(rowIndex);
  }

  deleteRow(rowIndex) {
    this.deleteRowEmitter.emit(rowIndex);
  }

  downloadRow(index) {
    this.downloadEmitter.emit(index);
  }

  isEditAllowd(item){
    let stato = item.stato;
    let aux= null;
    let idx = item.stato.indexOf(' - ');
    if( idx > -1){
      stato = item.stato.substr(0,idx);
      aux = item.stato.split(' - ')[1];
    }
    switch (stato)
    {
      case "BOZZA":
        return true;
      case "INVIATA":
        if(this.user.profile != CONST.PROFILE.RICHIEDENTE){
          return true;
        }else{
          return false;
        }
      case "PREASSEGNATA":
        if(this.user.profile != CONST.PROFILE.RICHIEDENTE){
          return true;
        }else{
          return false;
        }
      case "ASSEGNATA":
        if(this.user.profile == CONST.PROFILE.RICHIEDENTE && item.tipoDomanda == 'GRATUITA'){
          return false;
        }else{
          return true;
        }
      case "ACCETTATA":
        if(this.user.profile != CONST.PROFILE.RICHIEDENTE || 
          (this.user.profile == CONST.PROFILE.RICHIEDENTE && item.entePubblico == false 
            && (aux == null || aux == 'pagamento annullato' || aux == 'attesa pagamento'))){
          return true;
        }else{
          return false;
        }
      case "RICHIESTA VARIAZIONI":
        if(this.user.profile != CONST.PROFILE.RICHIEDENTE){
          return true;
        }else{
          return false;
        }
      case "RITIRO IN CORSO":
        if(this.user.profile != CONST.PROFILE.RICHIEDENTE){
          return true;
        }else{
          return false;
        }
      case "RIFIUTATA":
        return false;
      case "CHIUSA":
        return false;
      default: 
          return false;
    }
  }

  getDescr(elem:any){
    if(elem){
      let label = this.headers[0].header;
      let key = this.headers[0].field;
      return label.toLocaleLowerCase() + " " + elem[key];
    }
    return '';
  }
}

