/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit,Input } from '@angular/core';
import { DestinazioneModel } from '../../models/destinazione.model';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-show-destinazioni',
  templateUrl: './show-destinazioni.component.html',
  styleUrls: ['./show-destinazioni.component.css']
})
export class ShowDestinazioniComponent implements OnInit {

  @Input() listDestinazioni:DestinazioneModel[];

  tableHeaders: TableHeader[] = [
    { field: 'nomeComune', header: 'Comune'},
    { field: 'sezione', header: 'Sezione'},
    { field: 'foglio', header: 'Foglio'},
    { field: 'particella', header: 'Particella'},
    { field: 'metriLineari', header: 'Metri lineari'},
    { field: 'metriQuadri', header: 'Superficie MQ' }
];

  constructor() { }

  ngOnInit() {
  }

}
