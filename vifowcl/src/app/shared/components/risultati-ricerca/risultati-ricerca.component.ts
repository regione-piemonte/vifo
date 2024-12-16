/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { Router } from '@angular/router';
import { VivaiService } from 'src/app/features/istanze-vivai/services/vivai.service';
import { ButtonType, DialogButtons } from '../../error-dialog/error-dialog.component';
import { TableHeader } from '../../models/table-header';
import { InstanceDomandaTable } from '../../models/view-instance';
import { ConfigService } from '../../services/config.service';
import { DialogService } from '../../services/dialog.service';

@Component({
  selector: 'app-risultati-ricerca',
  templateUrl: './risultati-ricerca.component.html',
  styleUrls: ['./risultati-ricerca.component.css']
})
export class RisultatiRicercaComponent implements OnInit {

  @Input() first: number;
  @Input() totalCountDomande: number[];
  @Input() domandeTableData: InstanceDomandaTable[];
  @Input() rowsPerPageOptions: number[];
  domandeTableHeaders: TableHeader[];

  @Output() emitRicerca = new EventEmitter();
  @Output() emitUpdatedTable = new EventEmitter<any>();

  constructor(private configService: ConfigService, private vivaiService: VivaiService,
    private router: Router, private dialogService: DialogService) { }

  ngOnInit() {
    this.first = this.first?this.first:0;
    this.domandeTableHeaders = this.configService.getDomandeTableHeaders();
  }

  viewDomanda(event){
    const viewMode : boolean = true;
    this.router.navigate([ this.configService.getPathByStato(event.stato) + event.idDomanda]);
  }

  deleteRow(event){
    this.dialogService.showDialog(true, 'Confermare la cancellazione della domanda?', 'Attenzione', DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) {
        this.deleteConfirm(event.idDomanda);
      }
    });
  }

  deleteConfirm(event) {
    this.vivaiService.deleteDomanda(event).subscribe(res => {
      console.log('delete done');
      this.emitRicerca.emit();
    })
  }

  editRow(event) {
    if(event.stato == 'BOZZA'){
       this.router.navigate(['content/modifica/' + event.idDomanda]);
    }else{
      this.router.navigate([ this.configService.getPathByStato(event.stato) + event.idDomanda]);
    }
  }

  getUpdatedTable(event) {
    this.emitUpdatedTable.emit(event);
  }

}
