/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SpecieModel {
    constructor(
        public idSpecie:number,
        public codiceSpecie:string,
        public nomeComune:string,
        public nomeScientifico:string,
        public flgSpecie386:boolean
) {}
}