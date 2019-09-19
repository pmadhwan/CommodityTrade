import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyerBankComponent } from './buyer-bank.component';

describe('BuyerBankComponent', () => {
  let component: BuyerBankComponent;
  let fixture: ComponentFixture<BuyerBankComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuyerBankComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuyerBankComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
