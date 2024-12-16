/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { getNumberOfCurrencyDigits } from '@angular/common';
import { AbstractClassPart } from '@angular/compiler/src/output/output_ast';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { CONST } from '../shared/constants';

export const equalValidator = (k1: string, k2: string) => {
    return (g: AbstractControl) => {
        let p = g.parent;
        if (p && p.get(k1).value !== p.get(k2).value) {
            return {
                equal: true
            };
        }
    };
}

export const oneOfTwo = (k1: string, k2: string) => {
    return (g: AbstractControl) => {
        let p = g.parent;
        if (p && p.get(k1).value.length==0 && p.get(k2).value.length==0) {
            return {oneOfTwo: true};
        }
    };
}

export const partitaIva = () => {
    return (g: AbstractControl) => {        
        if (g.value && g.value.length == 11) {
            var pattern = CONST.PATTERN_PARTITA_IVA;
            var re = new RegExp(pattern);
            if (!re.test(g.value)) {
                return {partitaIva: true};
            }
        }
    };
}

export const codiceFiscale = ():ValidatorFn => {
    return (g: AbstractControl):ValidationErrors|null => {        
        if (g.value && g.value.length == 16) {
            var cf = g.value.toUpperCase();
            var cfReg = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
            if (!cfReg.test(cf))
            {
                return {codiceFiscale:true};
            }
              
            var set1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            var set2 = "ABCDEFGHIJABCDEFGHIJKLMNOPQRSTUVWXYZ";
            var setpari = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            var setdisp = "BAKPLCQDREVOSFTGUHMINJWZYX";
            var s = 0;
            for( let i = 1; i <= 13; i += 2 ){
                s += setpari.indexOf( set2.charAt( set1.indexOf( cf.charAt(i) )));
            }
            for( let j = 0; j <= 14; j += 2 ){
                s += setdisp.indexOf( set2.charAt( set1.indexOf( cf.charAt(j) )));
            }
            if ( s%26 != cf.charCodeAt(15)-'A'.charCodeAt(0) ){
                return {codiceFiscale:true};
            }
            return null;
        }
    };
}

export const cfPiva = () => {
    return (g: AbstractControl) => {        
        if (g.value && g.value.length == 16 ) {
            var cf = g.value.toUpperCase();
            var cfReg = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
            if (!cfReg.test(cf))
            {
                return {codiceFiscale:true};
            }
              
            var set1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            var set2 = "ABCDEFGHIJABCDEFGHIJKLMNOPQRSTUVWXYZ";
            var setpari = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            var setdisp = "BAKPLCQDREVOSFTGUHMINJWZYX";
            var s = 0;
            for( let i = 1; i <= 13; i += 2 ){
                s += setpari.indexOf( set2.charAt( set1.indexOf( cf.charAt(i) )));
            }
            for( let j = 0; j <= 14; j += 2 ){
                s += setdisp.indexOf( set2.charAt( set1.indexOf( cf.charAt(j) )));
            }
            if ( s%26 != cf.charCodeAt(15)-'A'.charCodeAt(0) ){
                return {codiceFiscale:true};
            }
            return null;
        } else if (g.value && g.value.length == 11) {
         
            var pattern = CONST.PATTERN_PARTITA_IVA;
            var re = new RegExp(pattern);
            if (!re.test(g.value)) {
                return {partitaIva: true};
            }
        } else if (g.value && g.value.length != 16 && g.value.length != 11) {
            return {cfPiva: true};
        }
    };
}

export const checkQuantita = (qtaBase:number,add1:string,add2:string,sum:string) => {
    return (g: AbstractControl) => {        
        let p = g.parent;
        if(p){
            let v1 = p.get(add1) && p.get(add1).value?+(''+p.get(add1).value).replace(',','.'):0; 
            let v2 = p.get(add2) && p.get(add2).value?+(''+p.get(add2).value).replace(',','.'):0; 
            let v3 = p.get(sum).value?parseFloat((''+p.get(sum).value).replace(',','.')):0; 
            v3 = v3 + (qtaBase?qtaBase:0);
            if((''+v3).indexOf('.') > -1){
                v3 = parseFloat(v3.toFixed(3));
            }
            if (v3 >= 0 && v1 + v2 > v3) {
                return {quantitaOk: true};
            }
        }        
        //return {quantitaOk: false};
    };
}

export const checkQtaNegative = (qtaBase:number) => {
    return (g: AbstractControl) => {        
        
        let v3 = g.value?parseFloat((''+g.value).replace(',','.')):0; 
        v3 = v3 + (qtaBase?qtaBase:0);
        if((''+v3).indexOf('.') > -1){
            v3 = parseFloat(v3.toFixed(3));
        }
        if (v3 < 0) {
            return {quantitaNegative: true};
        }
    };
}

export const checkMaxValue = (maxValue:number) => {
    return (g: AbstractControl) => { 
        if (g.value) {
            let numVal:number =  +('' + g.value).replace(',','.');
            if(numVal > maxValue){
                return {maxValue: true};
            }            
        }
    };
}

// Funzione ESTERNA
// Accoglie le informazioni necessarie per effettuare la validazione
export const forbiddenValidator = (...values: string[]): ValidatorFn => {
    // Funzione INTERNA
    // Viene restituita dalla funzione esterna ed è quella utilizzata da Angular
    // ai fini della validazione
    return (c: AbstractControl) => {
        for (const value of values) {
            if (value === c.value) {
                // Oggetto di errore
                // chiave: nome validatore
                // valore: true
                return {
                    forbidden: true
                };
            }
        }
    };
};

export const validFilename = (text: string): boolean => {
    const regex = /^[A-Za-z0-9 ._-]*$/;
    return regex.test(text);
  };
