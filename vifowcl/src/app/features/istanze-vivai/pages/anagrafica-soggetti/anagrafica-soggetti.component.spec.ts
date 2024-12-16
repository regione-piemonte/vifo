/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnagraficaSoggettiComponent } from './anagrafica-soggetti.component';

describe('AnagraficaSoggettiComponent', () => {
  let component: AnagraficaSoggettiComponent;
  let fixture: ComponentFixture<AnagraficaSoggettiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnagraficaSoggettiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnagraficaSoggettiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
