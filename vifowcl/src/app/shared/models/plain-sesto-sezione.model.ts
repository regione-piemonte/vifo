/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class PlainSestoSezione {
  etichetta?: string;
  proprieta?: DichProprieta;
  dissensi?: DichDissensi;
  paesaggistica?: DichAutorizzazione;
  vincIdrogeologico?: DichAutorizzazione;
  valIncidenza?: DichAutorizzazione;
  altriPareri?: DichAltriPareri;
  compFisica?: DichCompensazione;
  compMonetaria?: DichCompensazione;
  altroAllega?: DichCompensazione;
  allegati?: DocumentoAllegato[];
  istanzi?: DichIstanzaTaglio;
  nota?: DichNota;
}

export class DichDissensi {
  etichetta?: string;
  haDissenso?: boolean;
}

export class DichNota {
  etichetta?: string;
  testo?: string;
}

export class DichIstanzaTaglio {
  etichetta?: string;
  visible?: boolean;
  checked?: boolean;
  required?: boolean;
  istanziList?: IstanzaTaglio[];
}

export class IstanzaTaglio {
  idIstanza?: number;
  anno: Date;
  numIstanza?: number;
  dataPresentazioneIstanza: Date;
  dataAutorizzazioneIstanza: Date;
  descIntervento?: string;
  stimaMassaRetraibile?: number;
  unita?: string;
  tipoComunicazione?: string;
  stato?: string;
  governo?: string;
  tipoIntervento?: number;
  categoriaIntervento?: number;
  deleted?: boolean;
}

export class DocumentoAllegato {
  idAllegato?: number;
  fkDomanda?: number;
  fkTipoAllegato?: number;
  tipoAllegatoDescr?: string;
  nomeFile?: string;
  estensioneFile?: string;
  dimensioneFileKb?: number;
  dataUpload?: Date;
  note?: string;
  fkConfigUtente?: number;
  uidIndex?: string;
  idDocDoqui?: string;
  idClassificazioneDoqui?: string;
}

export class DichProprieta {
  etichetta1?: string;
  etichetta2?: string;
  isOwner?: boolean;
}

export class DichAutorizzazione {
  etichetta?: string;
  checked?: boolean;
  required?: boolean;
  visible?: boolean;
  numeroAutorizzazione?: string;
  dataAutorizzazione?: Date | string;
  rilasciataAutorizzazione?: string;
}

export class DichAltriPareri {
  etichetta?: string;
  checked?: boolean;
  testo?: string;
}

export class DichCompensazione {
  etichetta?: string;
  visible?: boolean;
  checked?: boolean;
  required?: boolean;
}
