/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/api';
import { CertificatoModel } from 'src/app/shared/models/certificato.molde';
import { FiltroInventarioModel } from 'src/app/shared/models/filtro-inventario.model';
import { SavedSearchModel } from 'src/app/shared/models/savedSearchModel';
import { TableHeader } from 'src/app/shared/models/table-header';
import { DizionariService } from '../../services/dizionari.service';
import { InventarioService } from '../../services/inventario.service';

@Component({
  selector: 'app-inventario-certificati',
  templateUrl: './inventario-certificati.component.html',
  styleUrls: ['./inventario-certificati.component.css']
})
export class InventarioCertificatiComponent implements OnInit {

  @Input() tipoPartita:string;
  @Output() emitIndietro = new EventEmitter();
  @Output() emitCertificato = new EventEmitter<number>();
  @Output() emitAssocciaCertificato = new EventEmitter<any>();
  totalCount:number;
  savedSearch: SavedSearchModel;
  paging: any;
  tableData: any[] = [];
  tableHeaders: TableHeader[] = [
    {field: 'codiceCertificatoProvenienza', header: 'Codice'},
    {field: 'numeroCertificato', header: 'Nr certificato'},
    {field: 'dataCertificato', header: 'Data certificato'},
    {field: 'nomeSpecie', header: 'Nome specie'},
    {field: 'descrCategMateriale', header: 'Materiale'},
    {field: 'popolamentoSeme', header: 'Popolamento seme'}
  ];
  
  searchForm: FormGroup;
  first:number=0;

  listAnni:SelectItem[]=[];

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(private router: Router, private fb: FormBuilder, 
    private inventarioService: InventarioService, private dizionariService: DizionariService) { }

  ngOnInit() {
    console.log(this.tableData.length);
    this.savedSearch = JSON.parse(sessionStorage.getItem("invCertificato")); 
    this.first = this.savedSearch?this.savedSearch.paging.first:0;
    this.dizionariService.getListCertificatiAnni()
    .subscribe((res)=>{
      this.listAnni = res;
      this.initForm();
    })
    
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }
  
  closePopUp(){
    this.popUpMsg = null;
    if(this.popUpBtn1 == 'Termina'){
      this.indietro();
    }
  }

  initForm() {
    let values = this.savedSearch?this.savedSearch.values:null;

    this.searchForm = this.fb.group({
      nomeSpecie: [{ value: values?values.nomeSpecie:'', disabled: false }],
      annata: [{ value: values?values.annata:this.listAnni[0].value, disabled: false }]
    });
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
   
    this.searchInArchivio();
  }

  filterSpecie(){
    this.paging = {page:1,limit:25};
    this.tableData = null;
    this.first = 0;
    this.searchInArchivio();
  }

  searchInArchivio(){
    let filter:FiltroInventarioModel = this.searchForm.value;
    this.inventarioService.getPaginatedListCertificati(this.paging,filter)
    .subscribe(
      (res: any) => {
        sessionStorage.setItem('invCertificato', JSON.stringify({paging:this.paging,values:this.searchForm.value}));
        this.tableData = res.content;
        this.totalCount = res.totalElements;
      }
    )
  }

  

  editRow(event){
    if(this.tipoPartita){
      this.emitCertificato.emit(event.idProvenienza);
    }else{
      this.router.navigate(['/content/modifica-certificato/' 
        + event.idProvenienza]);
    }
  }

  insertNuovoCertificato(){
    if(this.tipoPartita){
      this.emitCertificato.emit(null);
    }else{
      this.router.navigate(['/content/form-certificato'])
    }
  }

  associaCertificato(event){
    if(this.tipoPartita){
      this.inventarioService.getCertificatoPiemonteById(event.idProvenienza)
      .subscribe((resp: CertificatoModel) => {
        let materiale = resp.fkUdmMateriale == 1?'semi':resp.fkUdmMateriale == 2?'frutti':'altro'
        if(resp.qtaResidua <=0 && (materiale == this.tipoPartita )){
          this.showModalMsg('Attenzione','Nessuna quantità residua disponibile','OK');
          return;
        }
        this.emitAssocciaCertificato.emit(event);
      })
    }
  }

  downloadElenco(){
    this.inventarioService.downloadInventarioFruttiExcel();
  }

  indietro(){
    if(this.tipoPartita){
      this.emitIndietro.emit();
    }else{
      this.router.navigate(['/content/anagrafiche']);
    }
  }

}
