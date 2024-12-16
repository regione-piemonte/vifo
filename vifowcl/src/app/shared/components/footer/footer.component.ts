/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { VivaiService } from 'src/app/features/istanze-vivai/services/vivai.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  AGID_VALIDATION_LINK:string;

  constructor(private vivaiService: VivaiService) { }

  ngOnInit() {
    this.vivaiService.getConfigationByName('AGID_VALIDATION_LINK')
    .subscribe((res)=>this.AGID_VALIDATION_LINK = res.value)
  }

}
