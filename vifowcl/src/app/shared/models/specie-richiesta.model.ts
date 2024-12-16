/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SpecieRichiesta {
    constructor(
        public idSpecie?: number,
        public qtaRichiesta?: number,
        public nomeComune?: string,
        public nomeScientifico?: string
    ) {}
  }