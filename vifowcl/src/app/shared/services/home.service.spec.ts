/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { HomeService } from './home.service';

describe('HomeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HomeService]
    });
  });

  it('should be created', inject([HomeService], (service: HomeService) => {
    expect(service).toBeTruthy();
  }));
});
