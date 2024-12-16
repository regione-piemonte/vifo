/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { LoadingService } from './shared/services/loading.service';
import { ErrorService } from './shared/services/error.service';
import { ErrorCode } from '../app/shared/models/error.model';
import { DialogService } from './shared/services/dialog.service';
import { DialogButtons, ButtonType } from './shared/error-dialog/error-dialog.component';
import { Router } from '@angular/router';
import { ConfigService } from './shared/services/config.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit, OnDestroy{
  title = 'app';
  unsubscribe$ = new Subject<void>();
  isLoadingOperation: boolean = false;
  errorMsg: string;

  popUpType:string;
  popUpMsg:string;
  popUpBtn1:string;

  constructor(private loadingService: LoadingService, private errorService: ErrorService, 
    private dialogService: DialogService, private router: Router, private configService: ConfigService) {}

  ngOnInit() {
    this.subscribeLoading();
    this.subscribeError();

    let urlParams = new URLSearchParams(window.location.search);
    if(urlParams.get('userToken')){      
      let idDomanda = urlParams.get('userToken');
      this.router.navigate([ this.configService.getPathByStato("ASSEGNATA") + idDomanda], 
      { queryParams: {iuv:urlParams.get('iuv'),esito:urlParams.get('esito')}});
    }else if(window.location.href.indexOf('/vifo/private/?pageAnnulla=') > -1){
      let idDomanda = urlParams.get('pageAnnulla');
      this.router.navigate([ this.configService.getPathByStato("ASSEGNATA") + idDomanda]);
    }
  }

  subscribeLoading(): void {
    this.loadingService.loadingObservable
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (isActiveLoading: boolean) => {
        this.isLoadingOperation = isActiveLoading;
      }
    );
  }

  subscribeError() {
    this.errorService.errorObservable
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: string) => {
          switch (response) {
            case ErrorCode.E00: this.errorMsg = 'Si è verificato un errore generico.';
            break;
            case ErrorCode.E01: this.errorMsg = 'Mancano i dati.';
            break;
            case ErrorCode.E02: this.errorMsg = 'Errore dati.';
            break;
            case ErrorCode.E03: this.errorMsg = 'Errore di convalida.';
            break;
            default: this.errorMsg = 'Si è verificato un errore generico.';
            break;
          }
          console.log('ERROR MSG: ', this.errorMsg);
          this.showModalMsg('Errore',this.errorMsg,'OK');
          // this.dialogService.showDialog(true, this.errorMsg, 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
          //   if (buttonId === ButtonType.OK_BUTTON) {
          //     console.log('BUTTON WORKS');
          //   }
          // });
        }
      )
  }

  showModalMsg(type:string, msg:string, btn1:string){
    this.popUpType= type;
    this.popUpMsg= msg;
    this.popUpBtn1 = btn1;
  }
  
  closePopUp(){
    this.popUpMsg = null;
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
}
