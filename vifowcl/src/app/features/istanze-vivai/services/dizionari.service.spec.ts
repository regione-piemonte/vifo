/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { DizionariService } from './dizionari.service';

describe('DizionariService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DizionariService]
    });
  });

  it('should be created', inject([DizionariService], (service: DizionariService) => {
    expect(service).toBeTruthy();
  }));
});
