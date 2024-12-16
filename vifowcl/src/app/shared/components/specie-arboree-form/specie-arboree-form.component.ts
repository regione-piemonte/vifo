/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ValueTransformer } from '@angular/compiler/src/util';
import { Component, Input, OnInit, Output,EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { DizionariService } from 'src/app/features/istanze-vivai/services/dizionari.service';
import { ButtonType, DialogButtons } from '../../error-dialog/error-dialog.component';
import { SpecieRichiestaFull } from '../../models/specie-richiesta-full.model';
import { SpecieRichiesta } from '../../models/specie-richiesta.model';
import { TableHeader } from '../../models/table-header';
import { DialogService } from '../../services/dialog.service';

@Component({
  selector: 'app-specie-arboree-form',
  templateUrl: './specie-arboree-form.component.html',
  styleUrls: ['./specie-arboree-form.component.css']
})
export class SpecieArboreeFormComponent implements OnInit {

  @Input() submited: boolean;
  @Input() specieForm: FormGroup;
  @Input() listSpecieSelected:SpecieRichiesta[];  
  @Output() emitRemove = new EventEmitter<number>();
  @Output() emitAdd = new EventEmitter<SpecieRichiesta>();
  listSpecie: SpecieRichiesta[];
  dettaglioSpecie: SpecieRichiestaFull;
  dettaglioFoto:any;
  unsubscribe$ = new Subject<void>();

  specieArboreaPopUp:boolean=false;

  tableHeaders: TableHeader[] = [
    { field: 'nomeComune', header: 'Nome Comune'},
    { field: 'nomeScientifico', header: 'Nome Scientifico'},
    { field: 'qtaRichiesta', header: 'Quantità (n.)' }
];

  constructor(private dizionariService: DizionariService, private dialogService: DialogService) { }

  ngOnInit() {
  }

  filterSpecie(event) {
    this.dizionariService.getListSpecie().pipe(takeUntil(this.unsubscribe$)).subscribe((res: SpecieRichiesta[]) => {
      this.listSpecie = this.dizionariService.autocompleteFilter(event, res, 'Specie');
    });
  }

  onSpecieSelect(event){
    this.specieForm.get("nomeScientifico").patchValue(event.nomeScientifico);
  }

  addSpecie(specie){
    this.emitAdd.emit(specie);
  }

  deleteSpecie(event){
    this.emitRemove.emit(event.index);
  }

  openDialog(){
    this.specieArboreaPopUp = true;
  }

  closeDialog(){
    if(this.dettaglioFoto){
      this.dettaglioFoto = null;
      return;
    }
    if(this.dettaglioSpecie){
      this.dettaglioSpecie = null;
      return;
    }
    this.specieArboreaPopUp = false;
  }

  openDettaglioSpecie(event){ 
    this.dettaglioSpecie = event;
  }

  openDettaglioFoto(event){
    this.dettaglioFoto = event;
  }

}
