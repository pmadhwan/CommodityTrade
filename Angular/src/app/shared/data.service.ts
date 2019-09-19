import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Tender } from '../Tender';
import { environment } from '../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class DataService {
   httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
   options = {
    headers: this.httpHeaders
  };
  constructor(private http: HttpClient) { }

  getTendersForSeller(){
    console.log('--------------------------1');
    
    return this.http.get<any>(environment.rootUrl + '/seller-api/all');
    
    // .subscribe((data)=> {
    //   console.log(data);
      
    // })
    // return [
    //   {contractAddress: '1', commodity: 'Mobile', price: 5000, quantity: 5, buyer: 'H', seller: '', buyerAddress: '', sellerAddress: ''},
    //   {contractAddress: '2',commodity: 'Soap', price: 100, quantity: 100, buyer: 'He', seller: '', buyerAddress: '', sellerAddress: ''},
    //   {contractAddress: '3',commodity: 'Cement', price: 20, quantity: 5, buyer: 'Li', seller: '', buyerAddress: '', sellerAddress: ''},
    //   {contractAddress: '4',commodity: 'Bricks', price: 30, quantity: 1000, buyer: 'Be', seller: '', buyerAddress: '', sellerAddress: ''},
    //   {contractAddress: '5',commodity: 'Steel', price: 200, quantity: 96, buyer: 'B', seller: '', buyerAddress: '', sellerAddress: ''},
    //   {contractAddress: '6',commodity: 'Engines', price: 1000000, quantity: 7, buyer: 'C', seller: '', buyerAddress: '', sellerAddress: ''},
    //   {contractAddress: '7',commodity: 'Paper', price: 5, quantity: 6421, buyer: 'N', seller: '', buyerAddress: '', sellerAddress: ''},
    //   {contractAddress: '8',commodity: 'Pens', price: 10, quantity: 6546, buyer: 'O', seller: '', buyerAddress: '', sellerAddress: ''},
    //   {contractAddress: '9',commodity: 'Trucks', price: 500000, quantity: 2, buyer: 'F', seller: '', buyerAddress: '', sellerAddress: ''},
    //   {contractAddress: '10',commodity: 'Laptops', price: 10000, quantity: 71, buyer: 'Ne', seller: '', buyerAddress: '', sellerAddress: ''},
    // ];
   
  }

  

generateContract(contract:any):any{
 
  return this.http.post<Tender>(environment.rootUrl + '/seller-api/create',JSON.stringify(contract),this.options);
}

requestLOCBuyer(contractAddress){
  return this.http.get<any>(environment.rootUrl + '/buyer-api/request/loc?contractAddress='+contractAddress);
}


setBank(contractAddress, bankAddress):any{
 
  return this.http.post<any>(environment.rootUrl + '/seller-api/setbank?bankAddress='+bankAddress+'&bankType=buyer&contractAddress='+contractAddress,this.options);
}

setBankSeller(contractAddress, bankAddress):any{
 
  return this.http.post<any>(environment.rootUrl + '/seller-api/setbank?bankAddress='+bankAddress+'&bankType=seller&contractAddress='+contractAddress,this.options);
}


getAllBanks(){
  //return this.http.get<Tender>(environment.rootUrl + '/banks/get');
}



acceptByBuyerBank(contractAddress){
  return this.http.get<any>(environment.rootUrl + '/issuance-bank-api/accept?contractAddress='+contractAddress);
  
}

rejectByBuyerBank(contractAddress){
  return this.http.get<any>(environment.rootUrl + '/issuance-bank-api/reject?contractAddress='+contractAddress);
  
}

acceptBySellerBank(contractAddress){
  return this.http.get<any>(environment.rootUrl + '/beneficiary-bank-api/accept?contractAddress='+contractAddress);
  
}

rejectBySellerBank(contractAddress){
  return this.http.get<any>(environment.rootUrl + '/beneficiary-bank-api/reject?contractAddress='+contractAddress);
  
}

getTenderForBuyers(buyerAddress){
  return this.http.get<any>(environment.rootUrl + '/buyer-api/all',buyerAddress);
}


getTenderForBuyerBank(bankAddress){
  return this.http.get<any>(environment.rootUrl + '/issuance-bank-api/all?bankId='+bankAddress);
}

getTenderForSellerBank(bankAddress){
  return this.http.get<any>(environment.rootUrl + '/beneficiary-bank-api/all?bankId='+bankAddress);
}
getByCommodityId(hash){
  return this.http.get<any>(environment.rootUrl + '/buyer-api/commodity/id?CommodityId=' + hash);
}

}
