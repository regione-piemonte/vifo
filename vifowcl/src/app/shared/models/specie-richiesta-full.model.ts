/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SpecieRichiestaFull {
    constructor(
        public idSpecie?: number,
        public qtaRichiesta?: number,
        public nomeComune?: string,
        public nomeScientifico?: string,
        public gruppoDesc?:string,
        public famiglia?:string,
        public zonaOrigine?:string,
        public dimensione?:string,
        public foglia?:string,
        public fioreFrutto?:string,
        public ecologia?:string,
        public impiego?:string,
        public noteCaratteristiche?:string,
        public attitudine?:string,
        public flgProdotta?:string,
        public flgSpecieZp?:string,
        public flgSpecie386?:string,
        public flgSchedaPubblicabile?:number,
        public tipiFoto?:string
    ) {}
  }