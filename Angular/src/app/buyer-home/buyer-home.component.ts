import { Component, OnInit, Inject } from '@angular/core';
import { Tender } from '../Tender';
import { DataService } from '../shared/data.service';
import {MatTableModule} from '@angular/material/table';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { SellerConfirmation } from '../seller-home/seller-home.component';

@Component({
  selector: 'buyer-tender-details',
  templateUrl: 'buyer-tender-details.html',
})
export class BuyerTenderDetailsDialog {



  isBank:boolean=true; //add bank details
  checked:boolean=false;
  isBankAdded:boolean=false;
  bankAddress:any;
  spinner:boolean=false;
  constructor(private route : Router,private dataService:DataService,
    public dialogRef: MatDialogRef<BuyerTenderDetailsDialog>,public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any) {console.log('--> ' + data);
    }

  requestLOC(){
    console.log('Accepted Buyer: ' + this.data.contractAddress);
    this.spinner=true;
    this.dataService.requestLOCBuyer(this.data.contractAddress).subscribe(res=>{
      console.log("data from request LOC ");
      console.log(res);
      this.confirmationDialog('LOC request sent to bank!Transaction hash :'+res.txHash);
      this.spinner=false;
      
      this.dialogRef.close({success: 'Yes'});
  this.route.navigate(['/buyer-home']);
      
    },
    err=>{
      console.log(err);
      

    });

   
  }

  check(){
    console.log("hello");
    this.isBank=false;
    this.isBankAdded=true;
    this.checked=true;
    
  }

  setBank(){
    console.log("In Set Bank ");
    console.log(this.data.bankAddress);
    //this.isBankAdded=true;
    this.checked=false;
   
   this.spinner=true;
    this.dataService.setBank(this.data.contractAddress, this.data.bankAddress).subscribe(
      data=>{

        this.confirmationDialog('Bank '+this.data.bankAddress+' added Successfully!');
        this.spinner=false;
        console.log("data from service:");
        console.log(data);
        this.dialogRef.close({success: 'Yes'});
    this.route.navigate(['/buyer-home']);
      },err=>{
        console.log(err);
      
      }
    );
    
  }
  
  confirmationDialog(element): void {
    const dialogRef = this.dialog.open(SellerConfirmation, {
      width: '50%',
      height:'30%',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The Confirmation dialog was closed');
    });
  }
}



@Component({
  selector: 'app-buyer-home',
  templateUrl: './buyer-home.component.html',
  styleUrls: ['./buyer-home.component.css']
})
export class BuyerHomeComponent implements OnInit {
 displayedColumns: any;
 dataSource: any;
 spinner:boolean =false;

  buyerId:any;
  

  constructor(private dataService: DataService, public dialog: MatDialog) {
  
   }

   ngOnInit() {
    console.log('dddddd');
    
    this.getAll();
    
    
  }

  getAll(){
    this.dataService.getTendersForSeller()
    
    
    
    .subscribe(res => {
     
      console.log('------------------11');
      
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
        console.log(data);
        const dialogRef = this.dialog.open(BuyerTenderDetailsDialog, {
          width: '40%',
          height:'80%',
          data: merged
        });
        dialogRef.afterClosed().subscribe(result => {
          if(result.success == 'Yes'){
            this.spinner = false;
            this.getAll();
          }
          console.log('The dialog was closed');
        });
      },
      err =>{

      });


    


  }




}
