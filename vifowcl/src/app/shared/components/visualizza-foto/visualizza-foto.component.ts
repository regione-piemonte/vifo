/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit,OnChanges, Input} from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-visualizza-foto',
  templateUrl: './visualizza-foto.component.html',
  styleUrls: ['./visualizza-foto.component.css']
})
export class VisualizzaFotoComponent implements OnInit,OnChanges {

  @Input() dettaglioFoto: any;
  imageUrl: SafeResourceUrl;


  constructor(private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.createSafeUrl();
  }

  ngOnChanges() {
    this.createSafeUrl();
  }

  createSafeUrl(){
    this.imageUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.dettaglioFoto);
  }

}
