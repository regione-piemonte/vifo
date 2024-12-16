/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class InventarioFrutti {
    constructor(
      public idInventarioFrutti?: number,
      public certificatoProvenienza?: string,
      public dataCertificato?: string,
      public flgNoFiniForestali?: boolean,
      public flgSpecie386?: boolean,
      public specie?: string,
      public provenienza?: string,
      public produttore?: string,
      public codCategoriaMateriale?: string,
      public descrCategoriaMaterialie?: string,
      public dataCarico?: string,
      public qtaCaricoKg?: number,
      public qtaScaricoSemiKg?: number,
      public qtaScaricoVenditaKg?: number,
      public qtaDistruzioneKg?: number,
      public qtaResidua?: number
    ) {}
  }