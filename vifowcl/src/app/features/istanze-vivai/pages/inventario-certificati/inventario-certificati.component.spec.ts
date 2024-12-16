/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InventarioCertificatiComponent } from './inventario-certificati.component';

describe('InventarioCertificatiComponent', () => {
  let component: InventarioCertificatiComponent;
  let fixture: ComponentFixture<InventarioCertificatiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InventarioCertificatiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InventarioCertificatiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});