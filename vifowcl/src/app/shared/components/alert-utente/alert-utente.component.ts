/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-alert-utente',
  templateUrl: './alert-utente.component.html',
  styleUrls: ['./alert-utente.component.css']
})
export class AlertUtenteComponent implements OnInit, OnChanges {

  @Input() typeMessage:string
  @Input() textMessage:string;
  @Input() btn1Label:string;
  @Input() btn2Label:string;
  @Output() btn1Emitter = new EventEmitter();
  @Output() btn2Emitter = new EventEmitter();

  isLongText:boolean = false;

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(): void {
    this.isLongText = ('' + this.textMessage).length > 60;
  }

  btn1Clik(){
    this.btn1Emitter.emit();
  }

  btn2Clik(){
    this.btn2Emitter.emit();
  }

  getTextColor(){
    if(this.typeMessage == 'Errore'){
      return "red-text";
    }else if(this.typeMessage == 'Attenzione'){
      return "yellow-text";
    }else{
      return "green-text";
    }
  }

}
