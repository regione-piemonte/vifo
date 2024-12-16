/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class DestinazioneModel {
    constructor(
    public idCatasto?: number,
    public fkDomanda?: string,
    public istatComune?: string,
    public nomeComune?: string,
    public sezione?: string,
    public foglio?: string,
    public particella?: string,
    public metriLineari?: string,
    public metriQuadri?: string
    ) {}
  }