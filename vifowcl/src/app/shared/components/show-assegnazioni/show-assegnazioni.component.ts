/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { UtilService } from 'src/app/features/istanze-vivai/services/util.service';
import { AssegnazioneModel } from '../../models/assegnazione.model';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-show-assegnazioni',
  templateUrl: './show-assegnazioni.component.html',
  styleUrls: ['./show-assegnazioni.component.css']
})
export class ShowAssegnazioniComponent implements OnInit, OnChanges {

  @Input() listAssegnazioni:AssegnazioneModel[];
  @Input() tipoDomanda:string;

  tableHeadersGratuita: TableHeader[] = [
    { field: 'nomeSpecie', header: 'SPECIE'},
    { field: 'qtaRichiesta', header: 'QUANTITA\' RICHIESTE (N.)'},
    { field: 'idPartita', header: 'ID PARTITA'},
    { field: 'nomeVivaio', header: 'VIVAIO'},
    { field: 'descrTipoAllevamento', header: 'ALLEVAMENTO'},
    { field: 'eta', header: 'ETA\''},
    { field: 'altezza', header: 'ALTEZZA (cm)'},
    { field: 'certificato', header: 'CERTIFICATO PROVENIENZA'},
    { field: 'qtaAssegnata', header: 'QUANTITA\' ASSEGNATA' }
  ];

  tableHeadersVendita: TableHeader[] = [
    { field: 'nomeSpecie', header: 'SPECIE'},
    { field: 'qtaRichiesta', header: 'QUANTITA\' RICHIESTE (N.)'},
    { field: 'idPartita', header: 'ID PARTITA'},
    { field: 'nomeVivaio', header: 'VIVAIO'},
    { field: 'descrTipoAllevamento', header: 'ALLEVAMENTO'},
    { field: 'eta', header: 'ETA\''},
    { field: 'altezza', header: 'ALTEZZA (cm)'},
    { field: 'certificato', header: 'CERTIFICATO PROVENIENZA'},
    { field: 'prezzoUnitarioEuro', header: 'PREZZO UNITARIO (euro)'},
    { field: 'qtaAssegnata', header: 'QUANTITA\' ASSEGNATA' },
    { field: 'importoEuro', header: 'IMPORTO (euro)' }
  ];

  constructor(private utilService:UtilService) { }

  ngOnInit() {
    this.elaboraDati();
  }

  ngOnChanges(){
    this.elaboraDati();
  }

  elaboraDati(){
    if(this.listAssegnazioni && this.tipoDomanda=='vendita' ){
      this.listAssegnazioni.forEach((item)=>{
        item.prezzoUnitarioEuro = this.getEuro(item.prezzoUnitario);
        item.importoEuro = this.getEuro(item.importo);
      })
    }
  }

  getEuro(value:number){
    return this.utilService.getEuro(value);
  }

}
