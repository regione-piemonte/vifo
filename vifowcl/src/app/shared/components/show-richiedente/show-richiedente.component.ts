/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';
import { SoggettoModel } from '../../models/soggetto.model';

@Component({
  selector: 'app-show-richiedente',
  templateUrl: './show-richiedente.component.html',
  styleUrls: ['./show-richiedente.component.css']
})
export class ShowRichiedenteComponent implements OnInit {

  @Input() richiedente:SoggettoModel;
  @Input() titolarita:string;
  constructor() { }

  ngOnInit() {
  }

}
