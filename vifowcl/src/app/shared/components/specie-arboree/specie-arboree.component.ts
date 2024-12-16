/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output,EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { DizionariService } from 'src/app/features/istanze-vivai/services/dizionari.service';
import { VivaiService } from 'src/app/features/istanze-vivai/services/vivai.service';
import { ButtonType, DialogButtons } from '../../error-dialog/error-dialog.component';
import { SpecieRichiestaFull } from '../../models/specie-richiesta-full.model';
import { SpecieRichiesta } from '../../models/specie-richiesta.model';
import { TableHeader } from '../../models/table-header';
import { DialogService } from '../../services/dialog.service';

@Component({
  selector: 'app-specie-arboree',
  templateUrl: './specie-arboree.component.html',
  styleUrls: ['./specie-arboree.component.css']
})
export class SpecieArboreeComponent implements OnInit {

  @Output() emitDettaglioSpecie = new EventEmitter<string>();
  @Output() emitAddSpecie = new EventEmitter<string>();
  @Input() listSpecieSelected:SpecieRichiesta[];
  @Input() isAnagraficaSpecie:boolean;
  
  unsubscribe$ = new Subject<void>();
  specieArboreaDettaglioPopUp:boolean=false;
  listSpecie: SpecieRichiestaFull[];
  listSpecieFiltered: SpecieRichiestaFull[];
  listSpecieToShow: SpecieRichiestaFull[];

  popUp:boolean=true;

  totalSize: number;
  first: number = 0;

  tableHeaders: TableHeader[] = [
    { field: 'path', header: ''},
    { field: 'nomeComune', header: 'NOME COMUNE'},
    { field: 'nomeScientifico', header: 'NOME SCIENTIFICO'},
    { field: 'gruppoDescr', header: 'GRUPPO' },
    { field: 'famiglia', header: 'FAMIGLIA' },
    { field: 'attitudine', header: 'ATTITUDINE' },
    { field: 'flgProdotta', header: 'PRODOTTA' }
];

  paging = {page: 1, limit: 5};
  specieSort:string = 'nomeComune';
  specieForm: FormGroup;

  constructor(private dizionariService: DizionariService,private dialogService: DialogService,
    private router: Router, private vivaiService: VivaiService, private fb: FormBuilder) { }

  ngOnInit() {
    if(this.isAnagraficaSpecie){//flgSpecieZp":false,"flgSpecie386
      this.tableHeaders.push({ field: 'flgSpecie386', header: 'SPECIE 386' });
      this.tableHeaders.push({ field: 'flgSpecieZp', header: 'SPECIE ZP' });
    }
    let savedSearch = JSON.parse(sessionStorage.getItem("pagingSpecie"));
    if(savedSearch){
      this.initForm(savedSearch.specie);
      this.first = savedSearch.paging.first;
      this.getUpdatedTable(savedSearch.paging);
    }else{
      this.initForm(null);
      this.getUpdatedTable(null);
    }
  }

  initForm(value:string){
    this.specieForm = this.fb.group({
      nomeSpecie: [{ value: value ? value : '', disabled: false }]
    })
  }

  filterSpecie(){
    this.listSpecie = [];
    this.first = 0;
    this.getUpdatedTable(null);
  }

  viewRow(specie){
    if(this.listSpecieSelected){
      this.emitDettaglioSpecie.emit(specie);
    }else{
      this.router.navigate(['/content/form-specie/' + specie.idSpecie]);
    }
  }

  addSpecie(specie){
    if(this.listSpecieSelected.filter(val => { return val.idSpecie == specie.idSpecie}).length > 0){
      let x = this.dialogService.showDialog(true, 'Specie già inserita!', 'Attenzione', DialogButtons.OK, '', (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
          console.log('BUTTON WORKS');
        }
      });
    }else{
      this.emitAddSpecie.emit(specie);
    }
  }

  isInserted(specie){
    return this.listSpecieSelected.filter(val => { return val.idSpecie == specie.idSpecie}).length > 0;
  }

  getUrlImg(specie: SpecieRichiestaFull){
    if(specie.tipiFoto && specie.tipiFoto.indexOf('1') > -1){
      return this.vivaiService.getBaseUrlImgSpecie() + '/documento/download/image/' + specie.idSpecie + '/1';
    }else{
      return 'assets/img/no_foto.png';
    } 
    
  }

  getUpdatedTable(event) {
    if(event){
      event.page = event.first/event.rows + 1;
      event.limit = event.rows;
      this.paging = event;
    }
    let val = this.specieForm.get('nomeSpecie').value;
    this.dizionariService.getListSpeciePaginated(this.isAnagraficaSpecie, this.paging,val)
    .pipe(takeUntil(this.unsubscribe$)).subscribe((res: any) => {
      sessionStorage.setItem('pagingSpecie', JSON.stringify({'paging':this.paging,'specie':val})); 
      if(res.content){
        res.content.forEach((elem)=>{
          elem.flgProdotta = elem.flgProdotta?'SI':'NO';
          elem.flgSpecie386 = elem.flgSpecie386?'SI':'NO';
          elem.flgSpecieZp = elem.flgSpecieZp?'SI':'NO';
        })
      }

      this.listSpecie = res.content;
      this.totalSize =  res.totalElements;
    });


  }

  getDescr(elem:any){
    if(elem){
      return "specie " + elem['nomeComune'];
    }
    return '';
  }

}
