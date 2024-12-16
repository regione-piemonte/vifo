/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, OnInit } from '@angular/core';
import { AssegnazioneModel } from '../../models/assegnazione.model';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-show-ritiri',
  templateUrl: './show-ritiri.component.html',
  styleUrls: ['./show-ritiri.component.css']
})
export class ShowRitiriComponent implements OnInit {

  @Input() listAssegnazioni:AssegnazioneModel[];
  @Input() tipoDomanda:string;

  ritiroTableHeaders: TableHeader[] = [
    { field: 'nomeSpecie', header: 'SPECIE'},
    { field: 'qtaRichiesta', header: 'QUANTITA\' RICHIESTE (N.)'},
    { field: 'idPartita', header: 'ID PARTITA'},
    { field: 'nomeVivaio', header: 'VIVAIO'},
    { field: 'descrTipoAllevamento', header: 'ALLEVAMENTO'},
    { field: 'eta', header: 'ETA\''},
    { field: 'altezza', header: 'ALTEZZA (cm)'},
    { field: 'certificato', header: 'CERTIFICATO PROVENIENZA'},
    { field: 'qtaAssegnata', header: 'QUANTITA\' ASSEGNATA' },
    { field: 'qtaRitirata', header: 'QUANTITA\' RITIRATE' }];

  constructor() { }

  ngOnInit() {
  }

}
