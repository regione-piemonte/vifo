/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class DomandaModel {
    constructor(
        public idDomanda?: number,
        public fkSoggetto?: number,
        public fkTipoDomanda?: number,
        public fkTitolarita?: number,
        public fkScopo?: number,
        public fkVivaio?: number,
        public fkConfigUtente?: number,
        public note?: string,
        public numMarcaBollo?: string,
        public totImportiEuro?: number,
        public scontoApplicato?: number,
        public totImponibileEuro?: number,
        public aliquotaIva?: number,
        public importoIva?: number,
        public totFattura?: number,
        public dataForm?: string,
        public dataAggiornamento?: string,
        public statoDomanda?: string,
        public fkStatoDomanda?: number
    ) {}
  }
