/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-table-fix-header',
  templateUrl: './table-fix-header.component.html',
  styleUrls: ['./table-fix-header.component.css']
})
export class TableFixHeaderComponent implements OnInit {

  @Input() headers: TableHeader[];
  @Input() data: any[];
  @Input() view: boolean;
  @Input() edit: boolean;
  @Output() editRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() viewRowEmitter: EventEmitter<any> = new EventEmitter();

 

  constructor() { }

  ngOnInit() {
  }

  viewRow(rowIndex) {
    this.viewRowEmitter.emit(rowIndex);
  }

  editRow(index) {
    this.editRowEmitter.emit(index);
  }

}
