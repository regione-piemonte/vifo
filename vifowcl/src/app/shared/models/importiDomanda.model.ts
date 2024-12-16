/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class ImportiDomandaModel {
    constructor(
        public totaleImporti?: number,
        public sconto?: number,
        public totaleScontato?: number,
        public aliquotaIVA?: number,
        public importoIVA?: number,
        public totaleDaPagare?: number
    ) {}
  }