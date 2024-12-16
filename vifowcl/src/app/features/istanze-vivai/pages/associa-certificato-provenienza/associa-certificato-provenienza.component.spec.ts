/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociaCertificatoProvenienzaComponent } from './associa-certificato-provenienza.component';

describe('AssociaCertificatoProvenienzaComponent', () => {
  let component: AssociaCertificatoProvenienzaComponent;
  let fixture: ComponentFixture<AssociaCertificatoProvenienzaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssociaCertificatoProvenienzaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociaCertificatoProvenienzaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
