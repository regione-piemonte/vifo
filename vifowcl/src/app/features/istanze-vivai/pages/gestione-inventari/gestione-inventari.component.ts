/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InventarioService } from '../../services/inventario.service';

@Component({
  selector: 'app-gestione-inventari',
  templateUrl: './gestione-inventari.component.html',
  styleUrls: ['./gestione-inventari.component.css']
})
export class GestioneInventariComponent implements OnInit {

  numTrasferimenti: number;

  constructor(private router: Router, private inventarioService :InventarioService) { }

  ngOnInit() {
    this.inventarioService.getCountTrasferimentoPiantine()
    .subscribe((res:any) => {
      this.numTrasferimenti = res})
  }

  goToHome(){
    this.router.navigate(['/content'])
  }

}
