/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-reusable-front-table',
  templateUrl: './reusable-front-table.component.html',
  styleUrls: ['./reusable-front-table.component.css']
})
export class ReusableFrontTableComponent implements OnInit {

  @Input() headers: TableHeader[];
  @Input() data: any[];
  @Input() initiallySortedColumn: string;
  @Input() view: boolean;
  @Input() add: boolean;
  @Input() edit: boolean;
  @Input() delete: boolean;
  @Input() download: boolean;
  @Input() fieldToEdit: string;
  @Input() fieldToView: string;
  @Input() fieldToDownload: string;
  @Input() fieldToDelete: string;
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();
  @Output() editRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() viewRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() deleteRow: EventEmitter<any> = new EventEmitter();
  @Output() downloadEmitter: EventEmitter<string> = new EventEmitter();
  @Input() simpleTable?: boolean;

  listType:string='altro';

  constructor() { }

  ngOnInit() {
    if(this.headers && this.headers.length > 0 && this.headers[0].header == 'Tipo allegato'){
      this.listType = 'allegati'
    }
  }

  viewRow(index) {
    this.viewRowEmitter.emit(index);
  }

  editRow(index) {
    this.editRowEmitter.emit(index);
  }

  deleteRowFromParcels(rowIndex, index) {
    this.deleteRow.emit({rowIndex, index});
  }

  downloadRow(index) {
    this.downloadEmitter.emit(index);
  }
  
  getDescr(elem:any){
    if(elem){
      let label = this.headers[0].header;
      let key = this.headers[0].field;
      return label.toLocaleLowerCase() + " " + elem[key];
    }
    return '';
  }

  isDdtEmpty(obj,field){
    return field == 'numeroDdt' && obj['qtaDdt'] == 0;
  }
}
