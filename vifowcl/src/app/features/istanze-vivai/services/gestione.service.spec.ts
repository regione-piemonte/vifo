/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { GestioneService } from './gestione.service';

describe('GestioneService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GestioneService]
    });
  });

  it('should be created', inject([GestioneService], (service: GestioneService) => {
    expect(service).toBeTruthy();
  }));
});
