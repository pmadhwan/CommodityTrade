import { Component, OnInit, Inject } from '@angular/core';
import { Tender } from '../Tender';
import { DataService } from '../shared/data.service';
import {MatTableModule} from '@angular/material/table';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { SellerConfirmation } from '../seller-home/seller-home.component';

@Component({
  selector: 'buyer-bank-details',
  templateUrl: 'buyer-bank-details.html',
})
export class BuyerBankDetailsDialog {


  spinner:boolean=false;
  constructor(private route : Router,private dataService:DataService,
    public dialogRef: MatDialogRef<BuyerBankDetailsDialog>,public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log('--> ' + data);
    }

  accept(){
    console.log('in buyer bank'); 
    
    console.log('Accepted Buyer Bank: ' + this.data.contractAddress);
    this.spinner=true;
    this.dataService.acceptByBuyerBank(this.data.contractAddress).subscribe(
      res=>{
      console.log(res);
      this.confirmationDialog('Accepted By Bank ,tx hash :'+res.txHash);
      this.spinner=false;
      this.dialogRef.close({success: 'Yes'});
      this.route.navigate(['/buyer-bank']);

      }, err=>{
            console.log(err);
            
      }
    );
 
  }

  reject(){
    console.log('in buyer bank'); 
    
    console.log('Accepted Buyer Bank: ' + this.data.contractAddress);
    this.spinner=true;
    this.dataService.rejectByBuyerBank(this.data.contractAddress).subscribe(
      res=>{
      console.log(res);
      this.confirmationDialog('Rejected By Bank ,tx hash :'+res.txHash);
      this.spinner=false;
      this.dialogRef.close({success: 'Yes'});
      this.route.navigate(['/buyer-bank']);

      }, err=>{
            console.log(err);
            
      }
    );
  }

  confirmationDialog(element): void {
    const dialogRef = this.dialog.open(SellerConfirmation, {
      width: '40%',
      height:'30%',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The Confirmation dialog was closed');
    });
  }

}

@Component({
  selector: 'app-buyer-bank',
  templateUrl: './buyer-bank.component.html',
  styleUrls: ['./buyer-bank.component.css']
})
export class BuyerBankComponent implements OnInit {

  displayedColumns: any;
  dataSource: any;

  constructor(private dataService: DataService, public dialog: MatDialog) {
  
  }

  ngOnInit() {
    console.log('in buyer bank');
    
    this.getAll();
   
  }

  getAll(){
    this.dataService.getTenderForBuyerBank("0x323d2ede0a632a6334d1c995b9a27eba79e9a746")
 
    .subscribe(res => {
     
      
      
      console.log(res);
      
      this.dataSource = res;
      this.dataSource.map((data) => data['accept']='');
      this. displayedColumns = ['id','commodityName', 'weight','price', 'buyerId', 'status','accept'];
      console.log(this.dataSource);
      
    }, err => {
      console.log(err);
      
    });
  }

  
  viewDialog(element): void {

    console.log("elemenet :"+JSON.stringify(element));

    
    this.dataService.getByCommodityId(element.id).subscribe(
      data => {
        let merged = Object.assign(data, element);
        console.log('iii' + JSON.stringify(merged));
        const dialogRef = this.dialog.open(BuyerBankDetailsDialog, {
          width: '40%',
          height:'80%',
          data: merged
        });
        dialogRef.afterClosed().subscribe(result => {
          if(result.success == 'Yes'){
            // this.spinner = false;
             this.getAll();
           }
          console.log('The dialog was closed');
        });
      },
      err =>{

      });


    


  }

 



}
