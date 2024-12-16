/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NodeStyleEventEmitter } from 'rxjs/internal/observable/fromEvent';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-certificati-no-select-table',
  templateUrl: './certificati-no-select-table.component.html',
  styleUrls: ['./certificati-no-select-table.component.css']
})
export class CertificatiNoSelectTableComponent implements OnInit {

  @Input() headers: TableHeader[];
  @Input() first: number;
  @Input() data: any[];
  @Input() initiallySortedColumn: string;
  @Input() replaceTrueFalse: string[];
  @Input() view: boolean;
  @Input() edit: boolean;
  @Input() delete: boolean;
  @Input() download: boolean;
  @Input() connect: boolean;
  @Input() fieldToEdit: string;
  @Input() fieldToDownload: string;
  @Input() fieldToDelete: string;
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();
  @Output() editRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() viewRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() deleteRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() downloadEmitter: EventEmitter<string> = new EventEmitter();
  @Output() connectEmitter: EventEmitter<any> = new EventEmitter();  
  @Input() simpleTable?: boolean;
  @Input() rowsPerPageOptions: number[];
  @Input() totalSize: number;
  @Input() visualizaTable: boolean;

  constructor() { }

  ngOnInit() {
    this.first = this.first?this.first:0;
    if(!this.rowsPerPageOptions){
      this.rowsPerPageOptions = [25, 50, 100];
    }
    console.log('edit: ' + this.edit + ' - view: ' + this.view);
  }

  getValue(val:any){
    if(this.replaceTrueFalse && this.replaceTrueFalse.length == 2){
      return val==true?this.replaceTrueFalse[0]:val==false?this.replaceTrueFalse[1]:val;
    }
    return val;
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

  editRow(index) {
    this.editRowEmitter.emit(index);
  }

  deleteRow(rowIndex) {
    this.deleteRowEmitter.emit(rowIndex);
  }

  downloadRow(index) {
    this.downloadEmitter.emit(index);
  }

  isViewAllowd(item){
    if(item.stato){
      if(item.tipoNotifica){
        return item.stato.indexOf('DEVI ATTENDERE') > -1;
      }else{
        return item.stato != 'BOZZA';
      }
    }
    return true
  }

  connectRow(index){
    this.connectEmitter.emit(index);
  }

  isEditAllowd(item){
    if(item.stato){
      if(item.tipoNotifica){
        return item.stato.indexOf('DEVI VALIDARE') > -1;
      }else{
        return item.stato == 'BOZZA';
      }
    }
    return true
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