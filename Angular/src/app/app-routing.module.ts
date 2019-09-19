import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SellerHomeComponent } from './seller-home/seller-home.component';
import { BuyerHomeComponent } from './buyer-home/buyer-home.component';
import { Page404Component } from './page404/page404.component';
import { BuyerBankComponent } from './buyer-bank/buyer-bank.component';
import { SellerBankComponent } from './seller-bank/seller-bank.component';


const routes: Routes = [
  { path: 'seller-home', component: SellerHomeComponent },
  { path: 'buyer-home', component: BuyerHomeComponent },
  { path: 'buyer-bank', component: BuyerBankComponent },
  { path: 'seller-bank', component: SellerBankComponent },
  { path:'', redirectTo:'/seller-home', pathMatch:'full' },
  { path: '**', component: Page404Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
