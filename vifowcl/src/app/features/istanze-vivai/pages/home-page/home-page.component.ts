/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject, combineLatest } from 'rxjs';

import { VivaiService } from '../../services/vivai.service';
import { CONST } from 'src/app/shared/constants';
import { ProfilioUtente } from 'src/app/shared/models/profilio-utente.model';
import { HomeModel } from 'src/app/shared/models/home.model';
import { HomeService } from 'src/app/shared/services/home.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  unsubscribe$ = new Subject<void>();
  datiPersonaliForm:boolean = false;
  userRole:any;
  dialogText: string = CONST.TOOLTIP_CODICE_PROFESSIONISTA;
  emptyMessageAC: string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;
  homedata: HomeModel;
  modificaSelezione: boolean = false;

  constructor(
    private homeService: HomeService,
    private route: ActivatedRoute
  ) {}
  
  ngOnInit() {
   this.checkParams();
   this.getHomeData();
  }

  checkParams() {
    let si = this.route.snapshot.queryParams['modificaSelezione']
    if(si === 'true'){
      this.modificaSelezione = true;
    }
  }

  

  getHomeData() {

    this.homeService.getUser().subscribe((soggetto : HomeModel)=>{ 
      sessionStorage.setItem('user', JSON.stringify(soggetto));
    });
  }

}
