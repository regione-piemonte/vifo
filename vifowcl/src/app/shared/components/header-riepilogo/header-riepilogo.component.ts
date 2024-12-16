/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, OnInit } from '@angular/core';
import { UtilService } from 'src/app/features/istanze-vivai/services/util.service';
import { DomandaModel } from '../../models/domanda.model';
import { SoggettoModel } from '../../models/soggetto.model';

@Component({
  selector: 'app-header-riepilogo',
  templateUrl: './header-riepilogo.component.html',
  styleUrls: ['./header-riepilogo.component.css']
})
export class HeaderRiepilogoComponent implements OnInit {

  @Input() domanda:DomandaModel;
  @Input() infoDomanda:any;
  @Input() personData:SoggettoModel;
  @Input() vivaio:string;

  constructor(private utilService: UtilService) { }

  ngOnInit() {
  }

  isMarcoBolloEnabled(){
    if(this.domanda.fkTipoDomanda == 1 && this.domanda.fkScopo == 5
      && !this.personData.flgEntePubblico){
        return true;
    }
    return false;
  }

  getDescrPagamento(){
    return this.utilService.getDescirzionePagamento(this.infoDomanda.statoPagamento);
  }
}
