import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SellerHomeComponent, TenderDetailsDialog, SellerConfirmation } from './seller-home/seller-home.component';
import { BuyerHomeComponent, BuyerTenderDetailsDialog } from './buyer-home/buyer-home.component';
import { Page404Component } from './page404/page404.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { AppMaterialModule } from './app-material/app-material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LayoutModule } from '@angular/cdk/layout';
import { HttpClientModule } from '@angular/common/http';
import { DataService } from './shared/data.service';
import { BuyerBankComponent, BuyerBankDetailsDialog } from './buyer-bank/buyer-bank.component';
import { SellerBankComponent, SellerBankDetailsDialog } from './seller-bank/seller-bank.component';


@NgModule({
  declarations: [
    AppComponent,
    SellerHomeComponent,
    BuyerHomeComponent,
    Page404Component,
    NavbarComponent,
    FooterComponent,
    TenderDetailsDialog,
    BuyerBankDetailsDialog,
    BuyerTenderDetailsDialog,
    BuyerBankComponent,
    SellerBankDetailsDialog,
    SellerBankComponent,
    SellerConfirmation
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    LayoutModule,
    HttpClientModule
  ],
  providers: [DataService],
  entryComponents: [TenderDetailsDialog, BuyerTenderDetailsDialog, BuyerBankDetailsDialog,SellerBankDetailsDialog, SellerConfirmation],
  bootstrap: [AppComponent]
})
export class AppModule { }
