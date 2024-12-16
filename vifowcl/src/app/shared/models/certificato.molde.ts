/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class CertificatoModel {
    constructor(
        public idProvenienza?: number,
        public numeroCertificato?: string,
        public siglaProvincia?: number,
        public flgTipoProduzione?: number,
        public fkNaturaMateriale?: number,
        public fkSpecie?:number,
        public fkCategMateriale?: number,
        public fkTipoMateriale?: number,
        public destinazione?: number,
        public fkPopolamento?: number,
        public miscuglio?: string,
        public fkOrigine?: number,
        public descrizioneOrigine?: string,
        public provenienza?: string,
        public produttore?: string,
        public altitudine?: string,
        public annoMaturazioneSemi?: number,
        public qtaMaterialeBase?: number,
        public qtaResidua?: number,
        public fkUdmMateriale?: number,
        public flgPartitaPrecedente?: string,
        public nCertificatoPrecedente?: number,
        public qtaPartitaIniziale?: number,
        public durataAllevamento?: string,
        public flgPropagazione?: string,
        public metodoPropagazione?: string,
        public nCicliPropagazione?: string,
        public dataCertificato?: string,
        public note?: string,
        public origineNonAutoctoni?: string
    ) {}
  }