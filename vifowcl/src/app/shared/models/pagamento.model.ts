/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class PagamentoModel {
    constructor(
      public idPagamento?: number,
      public fkDomanda?: number,
      public fkTipoPagamento?: number,
      public flgFattura?: number,
      public iuv?: string,
      public esito?: string,
      public statoPagamento?: string,
      public dataInserimento?: Date,
      public dataAggiornamento?: Date,
      public fkConfigUtente?: number
    ) {}
  }