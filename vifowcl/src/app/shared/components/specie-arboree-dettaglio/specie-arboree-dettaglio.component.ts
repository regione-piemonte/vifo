/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, Output, OnInit, EventEmitter, OnChanges } from '@angular/core';
import { SpecieRichiestaFull } from '../../models/specie-richiesta-full.model';
import { VivaiService } from 'src/app/features/istanze-vivai/services/vivai.service';

@Component({
  selector: 'app-specie-arboree-dettaglio',
  templateUrl: './specie-arboree-dettaglio.component.html',
  styleUrls: ['./specie-arboree-dettaglio.component.css']
})
export class SpecieArboreeDettaglioComponent implements OnInit, OnChanges {

  
  @Input() dettaglioSpecie: SpecieRichiestaFull;
  @Output() emitDettaglioFoto = new EventEmitter<string>();

  constructor(private vivaiService: VivaiService) { }

  ngOnInit() {
    console.log('init');
  }

  ngOnChanges(){
    console.log('change');
  }

  openFoto(fotoType){
    this.emitDettaglioFoto.emit(this.vivaiService.getBaseUrlImgSpecie() + '/documento/download/image/' + this.dettaglioSpecie.idSpecie + '/'+ fotoType);
  }

  noPhoto(val){
    let photo = this.dettaglioSpecie.tipiFoto?this.dettaglioSpecie.tipiFoto:'';
    return photo.indexOf(val) == -1;
  }

}
