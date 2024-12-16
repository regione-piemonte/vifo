/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TableHeader } from '../models/table-header';

@Injectable()
export class ConfigService {
    beRootPath = '/vifo/restfacade/be/private';

    bePublishedRootPath = '/lgspa-demo/v1';

    getBERootUrl(publishedApi: boolean): string {
        if (publishedApi) {
            return environment.apiGWServerPrefix + this.bePublishedRootPath;
        } else {
            return environment.beServerPrefix + this.beRootPath;
        }
    }

    getPathByStato(stato:string){
      const viewMode : boolean = true;
      if(stato == "BOZZA"){
        return 'content/modifica/';
      }else if(stato == "INVIATA"){
        return 'content/riepilogo/inviata/';
      }else if(stato == "PREASSEGNATA"){
        return 'content/riepilogo/preassegnata/';
      }else if(stato == "ASSEGNATA"){
        return 'content/riepilogo/assegnata/';
      }else if(stato.indexOf("ACCETTATA") == 0){
        return 'content/riepilogo/assegnata/';
      }else if(stato.indexOf("RICHIESTA VARIAZIONI") == 0){
        return 'content/riepilogo/assegnata/';
      }else if(stato.indexOf("RITIRO IN CORSO") == 0){
        return 'content/riepilogo/ritiro/';
      }else if(stato.indexOf("RIFIUTATA")== 0){
        return 'content/riepilogo/rifiutata/';
      }else if(stato.indexOf("CHIUSA") == 0){
        return 'content/riepilogo/chiusa/';
      }
    }

    getDomandeTableHeaders(){
      let tableHeader:TableHeader[] = [
      {field: 'idDomanda', header: 'Numero domanda'},
      {field: 'dataInvio', header: 'Data invio'},
      {field: 'tipoDomanda', header: 'Tipo domanda'},
      {field: 'richiedente', header: 'Richiedente'},
      {field: 'vivaio', header: 'Vivaio forestale'},
      {field: 'stato', header: 'Stato'}
    ];
    return tableHeader;
  }
}
