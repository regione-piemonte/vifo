/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-gestione-anagrafiche',
  templateUrl: './gestione-anagrafiche.component.html',
  styleUrls: ['./gestione-anagrafiche.component.css']
})
export class GestioneAnagraficheComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

  indietro(){
    this.goToHome()
  }

}
