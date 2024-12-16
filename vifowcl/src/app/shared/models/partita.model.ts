/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class PartitaFruttiModel {
    constructor(
      public idInventarioFrutti?: number,
      public idProvenienza?: number,
      public certificatoType?: string,
      public codiceCertificato?: string,
      public qtaCertificato?:number,
      public fkSpecie?: number,
      public dataCertificato?: Date,
      public provenienza?: string,
      public fkCategMateriale?: number,
      public produttore?: string,
      public dataCarico?: Date,
      public qtaCaricoKg?: number,
      public qtaScaricoVenditaKg?: number,
      public qtaDistruzioneKg?: number,
      public isSemiFather?: boolean
    ) {}
  }

  export class PartitaSemiModel {
    constructor(
      public idInventarioSemi?: number,
      public fkInventarioFrutti?: number,
      public idProvenienza?: number,
      public certificatoType?: string,
      public qtaCertificato?:number,
      public codiceCertificato?: string,
      public fkSpecie?: number,
      public dataCertificato?: Date,
      public provenienza?: string,
      public fkCategMateriale?: number,
      public produttore?: string,
      public dataCarico?: Date,
      public qtaScaricoFruttiKg?: number,
      public qtaCaricoKg?: number,
      public qtaScaricoVenditaKg?: number,
      public qtaDistruzioneKg?: number,
      public isPianteFather?: boolean
    ) {}
  }

  export class PartitaPianteModel {
    constructor(
      public idInventarioPiante?: number,
      public fkInventarioPiante?: number,
      public fkInventarioSemi?: number,
      public fkVivaio?: number,
      public idProvenienza?: number,
      public certificatoType?: string,
      public codiceCertificato?: string,
      public fkSpecie?: number,
      public dataCertificato?: Date,
      public provenienza?: string,
      public fkCategMateriale?: number,
      public produttore?: string,
      public dataCarico?: Date,
      public fkTipoAllevamento?: number,
      public eta?: string,
      public altezza?: string,
      public annoRadicazioneSeme?: number,
      public prezzoUnitario?: string,
      public qtaScaricoSemiKg?: number,
      public qtaNonAssegnabile?: number,
      public qtaCarico?: number,
      public qtaDistruzione?: number,
      public posizionePartita?:string,
      public isPianteFather?: boolean,
      public numAssegnazioni?:number,
      public flgTipoTrasferimento?:string
    ) {}
  }