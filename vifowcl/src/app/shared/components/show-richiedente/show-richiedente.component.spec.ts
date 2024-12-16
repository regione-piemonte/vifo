/*!
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowRichiedenteComponent } from './show-richiedente.component';

describe('ShowRichiedenteComponent', () => {
  let component: ShowRichiedenteComponent;
  let fixture: ComponentFixture<ShowRichiedenteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowRichiedenteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowRichiedenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
