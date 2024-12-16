/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormNuovaAnnataInventarioComponent } from './form-nuova-annata-inventario.component';

describe('FormNuovaAnnataInventarioComponent', () => {
  let component: FormNuovaAnnataInventarioComponent;
  let fixture: ComponentFixture<FormNuovaAnnataInventarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormNuovaAnnataInventarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormNuovaAnnataInventarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
