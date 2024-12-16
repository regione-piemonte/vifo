/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { HomeModel } from "../models/home.model";

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    redirectUrl: string;
    hasPermision: boolean;
    user : HomeModel;

    checkPermision(): boolean {
        this.user = JSON.parse(sessionStorage.getItem("user"));
        if(this.user !== null) {
            return true;
        }
        return false;
    }
    
}
