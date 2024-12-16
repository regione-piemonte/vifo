/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { SoggettoModel } from "./soggetto.model";

export class DdtModel {
    constructor(
    public idDdt?: number,
    public idAssegnata?: number,
    public idPartita?: number,
    public numeroDdt?: number,
    public annoDdt?: number,
    public specie?: string,
    public vivaio?: string,
    public allevamento?: string,
    public eta?: number,
    public altezza?: number,
    public certificato?: string,
    public qtaRichiesta?: number,
    public fkVivaio?: number,
    public nomeVivaio?: string,
    public flgNoFiniForestali?: number,
    public descrTipoAllevamento?: string,
    public qtaAssegnataRimanente?: number,
    public qtaInDdt?: number,
    public numeroFatturaAcconto?: string,
    public dataFatturaAcconto?: string,
    ) {}
  }

  export class DdtRiepilogoModel {
    constructor(
    public idDdt?: number,
    public numeroDdt?: number,
    public annoDdt?: number,
    public dataCreazione?: string,
    public idConfUtende?: number,
    public operatore?: SoggettoModel
    ) {}
  }