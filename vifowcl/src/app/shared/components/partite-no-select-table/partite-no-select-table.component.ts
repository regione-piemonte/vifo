/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { DocumentoAllegato } from '../../models/plain-sesto-sezione.model';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-partite-no-select-table',
  templateUrl: './partite-no-select-table.component.html',
  styleUrls: ['./partite-no-select-table.component.css']
})
export class PartiteNoSelectTableComponent implements OnInit {

  @Input() headers: TableHeader[];
  @Input() first: number;
  @Input() data: any[];
  @Input() initiallySortedColumn: string;
  @Input() replaceTrueFalse: string[];
  @Input() view: boolean;
  @Input() edit: boolean;
  @Input() delete: boolean;
  @Input() download: boolean;
  @Input() export: boolean;
  @Input() fieldToEdit: string;
  @Input() fieldToDownload: string;
  @Input() fieldToExport: string;
  @Input() fieldToDelete: string;
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();
  @Output() editRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() viewRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() deleteRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() downloadEmitter: EventEmitter<string> = new EventEmitter();
  @Output() exportEmitter: EventEmitter<any> = new EventEmitter();
  @Input() simpleTable?: boolean;
  @Input() rowsPerPageOptions: number[];
  @Input() totalSize: number;
  @Input() visualizaTable: boolean;

  isFullScreen:boolean = false;

  constructor() { }

  ngOnInit() {
    this.first = this.first?this.first:0;
    if(!this.rowsPerPageOptions){
      this.rowsPerPageOptions = [5, 10, 15];
    }
    console.log('edit: ' + this.edit + ' - view: ' + this.view);
  }

  getValue(val:any){
    if(this.replaceTrueFalse && this.replaceTrueFalse.length == 2){
      return val==true?this.replaceTrueFalse[0]:val==false?this.replaceTrueFalse[1]:val;
    }
    return val;
  }

  loadDataOnScroll(event){
    alert('Scrollato');
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
    document.body.style.overflow = 'auto';
    this.viewRowEmitter.emit(rowIndex);
  }

  editRow(index) {
    document.body.style.overflow = 'auto';
    this.editRowEmitter.emit(index);
  }

  deleteRow(rowIndex) {
    this.deleteRowEmitter.emit(rowIndex);
  }

  downloadRow(index) {
    this.downloadEmitter.emit(index);
  }

  exportRow(index) {
    document.body.style.overflow = 'auto';
    this.exportEmitter.emit(index);
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

  isExportAllowed(item){
    return item.qtaDisponibile > 0;
  }

  getDescr(elem:any){
    if(elem){
      let label = this.headers[0].header;
      let key = this.headers[0].field;
      return label.toLocaleLowerCase() + " " + elem[key];
    }
    return '';
  }

  getTitle(elem:any){
    return elem[this.headers[0].field] + ' - ' + elem['specie'];
  }

  espandiRiduci(){
    this.isFullScreen = !this.isFullScreen;
    document.body.style.overflow = this.isFullScreen?'hidden':'auto';
  }

  getScrollHeight(){
    let x = window.innerHeight;
    return (x*0.97 - 180 ).toFixed(0) + 'px'
  }
}
