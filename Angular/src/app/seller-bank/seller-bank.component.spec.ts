import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerBankComponent } from './seller-bank.component';

describe('SellerBankComponent', () => {
  let component: SellerBankComponent;
  let fixture: ComponentFixture<SellerBankComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SellerBankComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerBankComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
