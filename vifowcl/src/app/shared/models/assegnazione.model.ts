/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class AssegnazioneModel {
    constructor(
    public nomeSpecie?: string,
    public idAssegnata?: number,
    public idPartita?: number,
    public qtaRichiesta?: number,
    public fkVivaio?: number,
    public nomeVivaio?: string,
    public certificato?: string,
    public flgNoFiniForestali?: number,
    public descrTipoAllevamento?: string,
    public eta?: number,
    public altezza?: number,
    public prezzoUnitario?: number,
    public prezzoUnitarioEuro?: string,
    public qtaDisponibile?: number,
    public qtaAssegnata?: number,
    public qtaRitirata?: number,
    public tipoDomanda?: number,
    public importo?: number,
    public importoEuro?: string
    ) {}
  }