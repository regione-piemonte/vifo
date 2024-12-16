/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';
import { CONST } from 'src/app/shared/constants';
import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';
import { UserModel } from 'src/app/shared/models/user.model';
import { DialogService } from 'src/app/shared/services/dialog.service';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor(private dialogService: DialogService) { }

  getUser(){
    return JSON.parse(sessionStorage.getItem("user"));
  }


  getBackUrlNoParams(){
    let navHistory = JSON.parse(sessionStorage.getItem("navHistory"));
    if(navHistory && navHistory.length > 1){
      navHistory.shift();
      sessionStorage.setItem('navHistory', JSON.stringify(navHistory));
      let res =  navHistory[0];
      let idx = res.indexOf('?');
      if(idx > -1){
        res = res.substr(0,idx);
      }
      return res;
    }
    return '/';
  }

  getUserProfile(){
    let user:UserModel = this.getUser();
    if(user.profile == CONST.PROFILE.GESTORE){
      return 'GESTORE';
    }else if(user.profile == CONST.PROFILE.VIVAIO){
      return 'VIVAIO';
    }else{
      return 'RICHIEDENTE';
    }
  }

  getDescirzionePagamento(value:string){
    if(value){
      value = value.toLocaleLowerCase();
      if(value.indexOf('pagamento') > -1){
        return ' - ' + value;
      }else if(value.indexOf('attesa') > -1){
        return ' - pagamento in ' + value;
      }else{
        return ' - pagamento ' + value;
      }
    }
    return '';
  }

  isComeFromDdtSearch(){
    return sessionStorage.getItem("searchType") == 'ddt';
  }
  isComeFromDomandaSearch(){
    return sessionStorage.getItem("searchType") == 'domande';
  }
  isComeFromSoggettoSearch(){
    return sessionStorage.getItem("searchType") == 'soggetto';
  }

  showMsg(msgType,msg){
    this.dialogService.showDialog(true, msg, msgType, DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) {}
    });
  }

  getTodayFormated(){
    let data = new Date();
    return this.getFormatDate(data);
  }

  getFormatDate(data: Date){
    return this.getFormatDateBySeparator(data,'/');
  }

  getFormatDateBySeparator(data: Date, separator: string){
    return  this.getTwoDigits(data.getDate()) + separator 
    + (this.getTwoDigits(data.getMonth() + 1)) + separator + data.getFullYear();
  }

  getTwoDigits(value: number){
    if(value < 10){
      return '0' + value;
    }
    return value;
  }

  getEuro(value:number){
    if(value){
      return this.fillDecimali((value + '').replace('.',','));
    }
    return '';
  }

  fillDecimali(value:string){
    value = value.indexOf(',')==-1?value + ',':value;
    let dimDecimali = value.length - 1 - value.indexOf(',');
    if(dimDecimali == 0){
      value+='00';
    }else if(dimDecimali == 1){
      value+='0';
    }
    return value;
  }

  getItNum(val){
    if(val){
      val = (val + '').replace('.',',');
    }
    return val;
  }

  getNum(num:string){
    if(num){
      return parseFloat(num.replace(',','.'));
    }
    return 0;
  }

}
