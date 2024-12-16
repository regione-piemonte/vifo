/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/api';
import { FiltroInventarioModel } from 'src/app/shared/models/filtro-inventario.model';
import { SavedSearchModel } from 'src/app/shared/models/savedSearchModel';
import { TableHeader } from 'src/app/shared/models/table-header';
import { DizionariService } from '../../services/dizionari.service';
import { InventarioService } from '../../services/inventario.service';

@Component({
  selector: 'app-inventario-semi',
  templateUrl: './inventario-semi.component.html',
  styleUrls: ['./inventario-semi.component.css']
})
export class InventarioSemiComponent implements OnInit {

  totalCount:number;
  savedSearch: SavedSearchModel;
  paging: any;
  tableData: any[] = [];
  tableHeaders: TableHeader[] = [
    {field: 'idInventario', header: 'ID PARTITA SEMI'},
    {field: 'fkInventarioFrutti', header: 'ID RIFERIMENTO PARTITA FRUTTI'},
    {field: 'specie', header: 'SPECIE'},
    {field: 'certificatoProvenienza', header: 'CERTIFICATO PROVENIENZA'},
    {field: 'dataCertificato', header: 'DATA CERTIFICATO'},
    {field: 'provenienza', header: 'PROVENIENZA'},
    {field: 'descrCategoriaMaterialie', header: 'TIPO MATERIALE'},
    {field: 'produttore', header: 'PRODUTTORE'},
    {field: 'qtaScaricoFruttiKg', header: 'QUANTITA\' SCARICO FRUTTI'},
    {field: 'dataCarico', header: 'DATA CARICO'},
    {field: 'qtaCaricoKg', header: 'QUANTITA\' CARICO'},
    {field: 'qtaScaricoPiantineKg', header: 'QUANTITA\' SCARICO IN PIANTINE'},
    {field: 'qtaScaricoVenditaKg', header: 'QUANTITA\' SCARICO VENDITA'},
    {field: 'qtaDistruzioneKg', header: 'DISTRUZIONE RIMANENZE'},
    {field: 'qtaResiduaKg', header: 'QUANTITA\' RESIDUA'}
  ];

  searchForm: FormGroup;
  filter:FiltroInventarioModel;
  first:number=0;
  
  listAnni: SelectItem[]=[];

  constructor(private router: Router, private fb: FormBuilder, 
    private inventarioService: InventarioService, private dizionariService: DizionariService) {

  }

  ngOnInit() {
    console.log(this.tableData.length);
    this.savedSearch = JSON.parse(sessionStorage.getItem("invSemi")); 
    if(this.savedSearch){
      this.first = this.savedSearch.paging.first;
    }
    
    this.dizionariService.getSemiRangeAnni().subscribe((response: SelectItem[]) => {
      this.listAnni = response;
      this.initForm();
    });
  }

  initForm() {
    let values = this.savedSearch?this.savedSearch.values:null;
    this.searchForm = this.fb.group({
      numeroPartita: [{ value: values?values.numeroPartita:'', disabled: false }],
      nomeSpecie: [{ value: values?values.nomeSpecie:'', disabled: false }],
      annata: [{ value: values?values.annata:this.listAnni[0].value, disabled: false }],
      attive: [{ value: values?values.attive:'', disabled: false }]
    });
  }

  goToHome(){
    this.router.navigate(['/content']);
  }

  getUpdatedTable(event) {
    if(this.savedSearch){
      this.paging = this.savedSearch.paging;
      this.savedSearch = null;
    }else{
      this.paging = event;
    }   
    this.searchInArchivio();
  }

  doFilter(){
    this.paging = {page:1,limit:25};
    this.tableData = null;
    this.first = 0;
    this.searchInArchivio();
  }

  searchInArchivio(){    
    this.filter = this.searchForm.value;
    this.inventarioService.getPaginatedListSemi(this.paging,this.filter)
    .subscribe(
      (res: any) => {        
        sessionStorage.setItem('invSemi', JSON.stringify({paging:this.paging,values:this.filter}));
        this.tableData = res.content;
        this.totalCount = res.totalElements;
      }
    )
  }

  viewRow(event){
    this.router.navigate(['/content/dettaglio-semi/' 
      + event.idInventario + '/' + false])
  }

  editRow(event){
    this.router.navigate(['/content/dettaglio-semi/' 
      + event.idInventario + '/' + true])
  }

  insertNuovaPartita(){
    this.router.navigate(['/content/form-semi'])
  }

  downloadElenco(){
    this.inventarioService.downloadInventarioSemiExcel();
  }

  indietro(){
    this.router.navigate(['/content/inventari'])
  }

}
