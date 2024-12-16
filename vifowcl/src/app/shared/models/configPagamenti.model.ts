/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class ConfigPagamentiModel {
    constructor(
        public idProcedimento?: number,
        public codicePagamentoCorrispettivo?: string,
        public codicePagamentoFattura?: string,
        public ruoloIride?: string,
        public livelloIride?: number,
        public webAppUrl?: string
    ) {}
  }