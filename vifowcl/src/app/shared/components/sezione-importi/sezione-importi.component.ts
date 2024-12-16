/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, OnInit } from '@angular/core';
import { DomandaModel } from '../../models/domanda.model';

@Component({
  selector: 'app-sezione-importi',
  templateUrl: './sezione-importi.component.html',
  styleUrls: ['./sezione-importi.component.css']
})
export class SezioneImportiComponent implements OnInit {

  @Input() domanda:DomandaModel;

  constructor() { }

  ngOnInit() {
  }

  formatIfDecimal(val){
    if((''+val).indexOf('.')>0){
      return this.formatTwoDecimal(val);
    }
    return val;
  }

  formatTwoDecimal(value:number){
    if(value || value == 0){
      let valueStr = parseFloat('' + value).toFixed(2);
      return (valueStr).replace('.',',');
    }else{
      return '';
    }
  }

  formatIt(value){
    if(value){
      return (value + '').replace('.',',');
    }
    return '-';
  }


  formatDate(data:string){
    if(data && data.length == 10){
      if(data.indexOf('-') == 4){
        let dataVet = data.split('-');
        return data[2] + '-' + data[1] + '-' + data[0];
      }
    }
    return data;
  }

}
