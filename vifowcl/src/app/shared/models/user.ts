/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ComuneModel } from "./comune.model";


export class User {
    idSoggetto?: number;
    idConfigUtente?: number;
    codiceFiscale?: string;
    cognome?: string;
    nome?: string;
    ruolo?: string;
    profile?: string; 
    provider?: string;
    comune?: ComuneModel;
    indirizzo?: string;
    nrTelefonico?: string;
    email?: string;
    flgPrivacy?: number;
    civico?: string;
    cap?: string;
}