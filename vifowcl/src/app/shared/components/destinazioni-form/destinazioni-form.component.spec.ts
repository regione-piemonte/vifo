/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DestinazioniFormComponent } from './destinazioni-form.component';

describe('DestinazioniFormComponent', () => {
  let component: DestinazioniFormComponent;
  let fixture: ComponentFixture<DestinazioniFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DestinazioniFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DestinazioniFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
