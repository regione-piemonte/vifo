/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class FiltroInventarioModel {
    constructor(
        public fkVivaio?: number,
        public numeroPartita?: number,
        public nomeSpecie?:string,
        public annata?: number,
        public attive?: boolean
    ) {}
  }