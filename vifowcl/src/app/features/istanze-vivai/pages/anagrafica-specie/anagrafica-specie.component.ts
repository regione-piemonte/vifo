/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/components/common/selectitem';
import { SavedSearchModel } from 'src/app/shared/models/savedSearchModel';
import { TableHeader } from 'src/app/shared/models/table-header';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { DizionariService } from '../../services/dizionari.service';
import { VivaiService } from '../../services/vivai.service';

@Component({
  selector: 'app-anagrafica-specie',
  templateUrl: './anagrafica-specie.component.html',
  styleUrls: ['./anagrafica-specie.component.css']
})
export class AnagraficaSpecieComponent implements OnInit {

  ricercaForm: FormGroup;
  listProvince: SelectItem[]=[];
  isFirstSearch:boolean = true;

  tableSpecie: any[] = [];
  totalCount:number;
  savedSearch: SavedSearchModel;
  paging: any;
  tableHeaders: TableHeader[] = [
    {field: 'XXX', header: ''},
    {field: 'XXX', header: 'NOME COMUNE'},
    {field: 'XXX', header: 'NOME SCIENTIFICO'},
    {field: 'XXX', header: 'GRUPPO'},
    {field: 'XXX', header: 'FAMIGLIA'},
    {field: 'XXX', header: 'ATTITUDIONE'},
    {field: 'XXX', header: 'PRODOTTA'}
  ];

  constructor(private fb: FormBuilder, private dizionariService :DizionariService,
    private router: Router, private dialogService: DialogService,
    private vivaiService: VivaiService) { }

  ngOnInit() {


    this.initForm(null);
  }

  initForm(ricercaData:any) {
    this.ricercaForm = this.fb.group({
      filtro: [{ value: ricercaData ? ricercaData.filtro : '', disabled: false }]
      });
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  indietro(){
    if(this.tableSpecie != null){
    this.tableSpecie = null;
    }else{
      this.router.navigate(['/content/anagrafiche']);
    }
  }

  inserisciNuova(){
    this.router.navigate(['/content/form-specie']);
  }

  getUpdatedTable(event) {
    this.savedSearch = JSON.parse(sessionStorage.getItem("anagSoggetti"));
    if(this.savedSearch){
      this.paging = this.savedSearch.paging;
      this.initForm(this.savedSearch.values);
      this.savedSearch = null;
      this.ricerca();
    }else{
      this.paging = event;
    }
    if(this.isFirstSearch){
      this.isFirstSearch = false;
      this.tableSpecie = null;
    }else{
      this.ricerca();
    }
  }

  ricerca(){
    let values = this.ricercaForm.value;
    // this.vivaiService.searchSoggetti(this.paging.page, this.paging.limit,values)
    // .subscribe((res)=>{
    //   this.tableSpecie = res.content;
    //   this.totalCount = res.totalElements;
    //   sessionStorage.setItem('anagSoggetti', JSON.stringify({'paging':this.paging,'values':values})); 
    // })
  }

  openDettaglioSpecie(event){}

  viewRow(event){
    this.router.navigate(['/content/form-soggetto/' + event.idSoggetto])
  }
}
