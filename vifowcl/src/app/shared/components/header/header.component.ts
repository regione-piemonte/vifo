/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { VivaiService } from 'src/app/features/istanze-vivai/services/vivai.service';
import { HomeModel } from '../../models/home.model';
import { HomeService } from '../../services/home.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {

  unsubscribe$ = new Subject<void>();
  user: HomeModel;
  userIniziali: string;

  constructor(private homeService : HomeService, private vivaiService: VivaiService) { }

  ngOnInit() {
    this.loadSoggetto();
  }
  
  loadSoggetto() {
    
    this.homeService.getUser().subscribe((soggetti : HomeModel)=>{ 
      sessionStorage.setItem('user', JSON.stringify(soggetti));
      this.user = {
        nome: soggetti.nome,
        cognome : soggetti.cognome,
      }
      this.userIniziali = (soggetti.nome.substr(0,1)+soggetti.cognome.substr(0,1)).toUpperCase();
    });
    
  }

  logout(){
    this.homeService.logout().subscribe((res) =>{
      sessionStorage.clear();
      this.vivaiService.getConfigationByName('URL_REDIRECT_LOGOUT').subscribe(res => {
        window.location.href = res.value;
      })
    })
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  openNav(){
    document.getElementById("myNav").style.width = "100%";
  }

  closeNav() {
    document.getElementById("myNav").style.width = "0%";
  }

  showInfo() {
    document.getElementById("headerModalInfo")
  }

  hideInfo() {
    document.getElementById("headerModalInfo")
  }

  showUser() {
    document.getElementById("headerModalInfo")
  }

  hideUser() {
    document.getElementById("headerModalInfo")
  }

}
