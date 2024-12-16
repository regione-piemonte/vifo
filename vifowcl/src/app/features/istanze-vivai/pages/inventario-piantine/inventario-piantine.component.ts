/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/components/common/selectitem';
import { CONST } from 'src/app/shared/constants';
import { FiltroInventarioModel } from 'src/app/shared/models/filtro-inventario.model';
import { PartitaPianteModel } from 'src/app/shared/models/partita.model';
import { SavedSearchModel } from 'src/app/shared/models/savedSearchModel';
import { TableHeader } from 'src/app/shared/models/table-header';
import { UserModel } from 'src/app/shared/models/user.model';
import { DizionariService } from '../../services/dizionari.service';
import { InventarioService } from '../../services/inventario.service';
import { UtilService } from '../../services/util.service';

@Component({
  selector: 'app-inventario-piantine',
  templateUrl: './inventario-piantine.component.html',
  styleUrls: ['./inventario-piantine.component.css']
})
export class InventarioPiantineComponent implements OnInit {

  searchForm: FormGroup;
  isUserVivaio:boolean = false;  
  isUserGestore:boolean = false;
  listVivai: SelectItem[]=[];
  listAnni: SelectItem[]=[];
  totalCount:number;
  savedSearch: SavedSearchModel;
  paging: any;
  tableData: any[] = [];
  tableHeaders: TableHeader[] = [
    //{field: '', header: ''},
    {field: 'idInventarioPiante', header: 'ID PARTITA PIANTE'},
    {field: 'fkInventarioSemi', header: 'ID RIFERIMENTO PARTITA SEMI'},
    {field: 'fkInventarioPiante', header: 'ID RIFERIMENTO PARTITA PIANTE'},
    {field: 'specie', header: 'SPECIE'},
    {field: 'certificatoProvenienza', header: 'CERTIFICATO PROVENIENZA'},
    {field: 'dataCertificato', header: 'DATA CERTIFICATO'},
    {field: 'provenienza', header: 'PROVENIENZA'},
    {field: 'descrCategMateriale', header: 'TIPO MATERIALE'},
    {field: 'produttore', header: 'PRODUTTORE'},
    {field: 'descrTipoAllevamento', header: 'ALLEVAMENTO'},
    {field: 'eta', header: 'ETA\''},
    {field: 'altezza', header: 'ALTEZZA (cm)'},
    {field: 'annoRadicazioneSeme', header: 'ANNO RADICAZIONE SEME'},
    {field: 'prezzoUnitario', header: 'PREZZO UNITARIO (euro)'},
    {field: 'qtaScaricoSemiKg', header: 'RIFERIMENTO SCARICO SEMI (Kg)'},
    {field: 'dataCarico', header: 'DATA CARICO'},
    {field: 'qtaCarico', header: 'QUANTITA\' TOTALE (N)'},
    {field: 'qtaNonAssegnabile', header: 'QUANTITA\' NON ASSEGNABILE (N)'},
    {field: 'qtaScaricoAltrePiante', header: 'QUANTITA\' SCARICO ALTRE PIANTE (N)'},
    {field: 'qtaAssegnabileTeorica', header: 'QUANTITA\' ASSEGNABILE (N)'},
    {field: 'qtaAssegnata', header: 'QUANTITA\' ASSEGNATA (N)'},
    {field: 'qtaRitirata', header: 'QUANTITA\' RITIRATA (N)'},
    {field: 'qtaDistruzione', header: 'DISTRUZIONE RIMANENZE (N)'},
    {field: 'qtaDisponibile', header: 'QUANTITA\' RESIDUA DISPONIBILE (N)'}
  ];

  filter:FiltroInventarioModel;
  first:number=0;
  user:UserModel;
  
  richiestaTrasferimentoPartita: any;

  constructor(private router: Router, private inventarioService: InventarioService,
    private fb: FormBuilder, private dizionariService: DizionariService,
    private utilService: UtilService) {
  }

  ngOnInit() {
    this.user = this.utilService.getUser();
    this.isUserVivaio = this.user.profile == CONST.PROFILE.VIVAIO ;
    this.isUserGestore = this.user.profile == CONST.PROFILE.GESTORE ;
    this.savedSearch = JSON.parse(sessionStorage.getItem("invPiantine")); 
    if(this.savedSearch){
      this.first = this.savedSearch.paging.first;
    }
    this.dizionariService.getListVivaio().subscribe((response: SelectItem[]) => {
      this.listVivai = response.filter(item => item.value);
    });
    this.dizionariService.getPiantineRangeAnni().subscribe((response: SelectItem[]) => {
      this.listAnni = response;
      this.initForm();
    });
    //this.searchInArchivio({page:1,limit:150});
  }

  initForm() {
    let values = this.savedSearch?this.savedSearch.values:null;
    if(values){
      this.filter = values.nomeSpecie;
    }
    this.searchForm = this.fb.group({
      fkVivaio: [{ value: this.user.fkVivaio?this.user.fkVivaio:values?values.fkVivaio:'', disabled: false }],
      numeroPartita: [{ value: values?values.numeroPartita:'', disabled: false }],
      nomeSpecie: [{ value: values?values.nomeSpecie:'', disabled: false }],
      annata: [{ value: values?values.annata:this.listAnni[0].value, disabled: false }],
      attive: [{ value: values?values.attive:'', disabled: false }]
    });
  }

  goToHome(){
    this.router.navigate(['/content']);
  }

  isEditingEnabled(){
    return this.isUserGestore || (this.user.fkVivaio == this.searchForm.get('fkVivaio').value);
  }

  onVivaioChange(){
    this.searchInArchivio(this.paging);
  }

  getUpdatedTable(event) {
    this.paging = event;
    this.searchInArchivio(this.paging);
  }

  doFilter(){
    this.paging = {page:1,limit:25};
    this.tableData = null;
    this.first = 0;
    this.searchInArchivio(this.paging);
  }

  searchInArchivio(paging: any){
    this.filter = this.searchForm.value;
    if(this.searchForm.value.fkVivaio){
      this.inventarioService.getPaginatedListPiante(paging, this.filter.fkVivaio, this.filter)
      .subscribe(
        (res: any) => {
          sessionStorage.setItem('invPiantine', JSON.stringify({paging:paging,values:this.filter}));
          this.tableData = res.content;
          this.totalCount = res.totalElements;
          if(this.tableData){
            this.tableData.forEach(item => {
              if(item.prezzoUnitario){                
                item.prezzoUnitario =this.utilService.getEuro(item.prezzoUnitario);
              }
              if(item.dataCarico){
                item.dataCarico =  item.dataCarico.split("-").reverse().join("/");       
              }
            })
          }
        }
      )
    }
  }

  viewRow(event){
    this.router.navigate(['/content/dettaglio-piantine/' 
      + event.idInventarioPiante + '/' + false])
  }

  editRow(event){
    this.router.navigate(['/content/dettaglio-piantine/' 
      + event.idInventarioPiante + '/' + true])
  }

  insertNuovaPartita(){
    this.router.navigate(['/content/form-piantine'])
  }

  downloadElenco(){
    let fkVivaio  = this.searchForm.value.fkVivaio;
    this.inventarioService.downloadInventarioPiantineExcel(fkVivaio);
  }

  scaricaQRCodepartite(){
    alert('todo');
  }

  scaricaRegistroCaricoScarico(){
    this.inventarioService.downloadRegistoCaricoScaricoExcel();
  }

  creaNuovaAnnata(){
    let idVivaio = this.searchForm.value.fkVivaio;
    if(this.isUserVivaio){
      idVivaio = this.user.fkVivaio;
    }
    this.router.navigate(['/content/form-nuova-annata-inventario'], { queryParams: { idVivaio: idVivaio}})
  }

  indietro(){
    this.router.navigate(['/content/inventari'])
  }

  closeRichietaTrasferimento(isSaved:boolean){
    if(isSaved){
      this.searchInArchivio(this.paging);
    }
    this.richiestaTrasferimentoPartita=null;
  }

  richiediSpecieAdAltroVivaio(partita){
    

    this.inventarioService.getPartitaPianteById(partita.idInventarioPiante)
    .subscribe((res)=>{
      let vivaioFrom: SelectItem= this.listVivai.filter(item=>item.value == res['fkVivaio'])[0];
      let vivaioTo: SelectItem= this.listVivai.filter(item=>item.value == this.user.fkVivaio)[0];
      this.richiestaTrasferimentoPartita = {
        'fkInventarioPiante':partita.idInventarioPiante,
        'fkSpecie':res.fkSpecie,
        'specieName':partita.specie,
        'fkVivaioTo':vivaioTo.value,
        'vivaioToName':vivaioTo.label,
        'fkVivaioFrom':vivaioFrom.value,
        'vivaioFromName':vivaioFrom.label,
        'isTrasferimento': false
      }
    })
  }

}
