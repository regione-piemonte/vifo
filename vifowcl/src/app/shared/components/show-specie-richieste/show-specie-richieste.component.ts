/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input} from '@angular/core';
import { SpecieRichiestaFull } from '../../models/specie-richiesta-full.model';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-show-specie-richieste',
  templateUrl: './show-specie-richieste.component.html',
  styleUrls: ['./show-specie-richieste.component.css']
})
export class ShowSpecieRichiesteComponent implements OnInit {

  @Input() listSpecie:SpecieRichiestaFull[];

  tableHeaders: TableHeader[] = [
    { field: 'nomeComune', header: 'Nome comune'},
    { field: 'nomeScientifico', header: 'Nome scientifico'},
    { field: 'qtaRichiesta', header: 'Quantità richiesta'}
];

  constructor() { }

  ngOnInit() {
  }

}
