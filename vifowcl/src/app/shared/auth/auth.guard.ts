/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { CONST } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(private authService: AuthService, private router: Router){}
  
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    let url: string = state.url; 
    let res = this.checkIsAuth(url, next);
    if(res){
      this.storeNavigationHistory(url);
    }
    return res;
  }

  storeNavigationHistory(path:string){
    let navHistory = JSON.parse(sessionStorage.getItem("navHistory"));
    if(!navHistory){
      navHistory = [];
    }
    navHistory.unshift(path);
    sessionStorage.setItem('navHistory', JSON.stringify(navHistory)); 
  }

  checkIsAuth(url: string, next : ActivatedRouteSnapshot): boolean {
    this.authService.redirectUrl = url;
    if (this.authService.checkPermision()) {
      let numberOfParams = Object.keys(next.params);
      next.routeConfig.path = numberOfParams.length > 2 ? next.routeConfig.path = '' : next.routeConfig.path;
      switch (this.authService.user.profile) {
        case CONST.PROFILE.RICHIEDENTE:
          let isRichiedenteRoute = CONST.RICHIEDENTE_ROUTES.some(route => {
            return route === next.routeConfig.path;
          })
          if (isRichiedenteRoute) {
            return true;
          } else {
            this.router.navigate(['/content']);
            return false;
          }
          case CONST.PROFILE.VIVAIO:
            let isVivaioRoute = CONST.VIVAIO_ROUTES.some(route => {
              return route === next.routeConfig.path;
            })
            if (isVivaioRoute) {
              return true;
            } else {
              this.router.navigate(['/content']);
              return false;
            }
        case CONST.PROFILE.GESTORE:
          let isGestoreRoute = CONST.GESTORE_ROUTES.some(route => {
            return route === next.routeConfig.path;
          })
          if (isGestoreRoute) {
            return true;
          } else {
            this.router.navigate(['/content']);
            return false;
          }
        default:
          this.router.navigate(['/content'])
          return false;  
      }
    }
    this.router.navigate(['/content']);
    return false;
  }

  splitRedirctionURI(url: string) {
    let onlyModifica: string[] = url.split(';', 3);
    return onlyModifica[2];
  }
}
