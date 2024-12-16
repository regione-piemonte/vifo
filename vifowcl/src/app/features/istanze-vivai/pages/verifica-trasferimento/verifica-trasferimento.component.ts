/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SavedSearchModel } from 'src/app/shared/models/savedSearchModel';
import { TableHeader } from 'src/app/shared/models/table-header';
import { InventarioService } from '../../services/inventario.service';

@Component({
  selector: 'app-verifica-trasferimento',
  templateUrl: './verifica-trasferimento.component.html',
  styleUrls: ['./verifica-trasferimento.component.css']
})
export class VerificaTrasferimentoComponent implements OnInit {

  totalCount:number;
  savedSearch: SavedSearchModel;
  paging: any;
  tableData: any[] = [];
  tableHeaders: TableHeader[] = [
    {field: 'idPartita', header: 'ID PARTITA'},
    {field: 'fkPartitaOriginaria', header: 'ID RIFERIMENTO PARTITA ORIGINALE'},
    {field: 'specie', header: 'SPECIE'},
    {field: 'vivaioOrigineDescr', header: 'VIVAIO ORIGINE PARTITA'},
    {field: 'vivaioDestinazioneDescr', header: 'VIVAIO DESTINAZIONE PARTITA'},
    {field: 'vivaioNotificaDescr', header: 'VIVAIO INSERIMENTO NOTIFICA'},
    {field: 'dataNotifica', header: 'DATA NOTIFICA'},
    {field: 'qtaTrasferita', header: 'QUANTITA\' TRASFERITA'},
    {field: 'tipoNotifica', header: 'TIPO NOTIFICA'},
    {field: 'stato', header: 'STATO'}
  ];

  constructor(private router: Router, private inventarioService: InventarioService) {

  }

  ngOnInit() {
    console.log(this.tableData.length)
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  getUpdatedTable(event) {
    if(this.savedSearch){
      this.paging = this.savedSearch.paging;
      this.savedSearch = null;
    }else{
      this.paging = event;
    }
   
    this.searchInArchivio(this.paging);
  }

  searchInArchivio(paging: any){
    this.inventarioService.getPaginatedListTrasferimentoPiantine(paging)
    .subscribe(
      (res: any) => {
        this.tableData = res.content;
        this.totalCount = res.totalElements;
      }
    )
  }

  viewRow(event){
    this.router.navigate(['/content/dettaglio-trasferimento/' 
      + event.idPartita + '/' + false])
  }

  editRow(event){
    this.router.navigate(['/content/dettaglio-trasferimento/' 
      + event.idPartita + '/' + true])
  }

  insertNuovaPartita(){
    this.router.navigate(['/content/form-frutti'])
  }

  indietro(){
    this.router.navigate(['/content/inventari'])
  }

}
