/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SrvError {
    code: string;
    errorMessage: string;
    fields: string;
    messaggioCittadino: string;
    sessionExpired? : boolean;
}