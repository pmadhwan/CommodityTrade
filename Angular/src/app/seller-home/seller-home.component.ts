import { Component, OnInit, Inject } from '@angular/core';
import { Tender } from '../Tender';
import { DataService } from '../shared/data.service';
import {MatTableModule} from '@angular/material/table';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material';
import { Router } from '@angular/router';




@Component({
  selector: 'seller-tender-details',
  templateUrl: 'seller-tender-details.html',
})
export class TenderDetailsDialog {

  public isButtonVisible: Boolean = true;
  isBank:boolean=false; //add bank details
  checked:boolean=false;
  isBankAdded:boolean=false;
  bankAddress:any;
  public contractAdd:any;

  constructor(private route : Router,
    public dialogRef: MatDialogRef<TenderDetailsDialog>,
    public dialog: MatDialog,private dataService:DataService,
    @Inject(MAT_DIALOG_DATA) public data: any) {console.log('--> ' + data);
    }

  generateSaleContract(){
    this.isButtonVisible = false;
    console.log(this.data);
    
    let comm={
      "id":this.data.id,
      "weight":this.data.weight,
      "price":this.data.price,
      "commodityName": this.data.commodityName,
    }
    let dd={
      
	  "buyerName" : this.data.buyerId,
 "sellerName": this.data.sellerId,
    "commodity":comm,
	 "grade": this.data.grade,
	"intendedDelievryDate": this.data.intendedDelievryDate,
 "comments": this.data.comments,
 
    }

    console.log(dd);
   
    this.dataService.generateContract(dd).subscribe(
      data=>{
        this.confirmationDialog('Contract '+data.contractAddress+' generated Successfully!');
        this.contractAdd=data.contractAddress;
        console.log("data fro service:");
        console.log(data);
        this.isButtonVisible = true;
        this.isBank=true;
        //this.dialogRef.close({success: 'Yes'});
        
      },err=>{
        console.log(err);
      
      }
    );
    
   
    
  }

  setBank(){
    console.log("In Set Bank ");
    console.log(this.data.bankAddress);
    console.log(this.contractAdd);
    //this.isBankAdded=true;
    //this.checked=false;
    
    this.dataService.setBankSeller(this.contractAdd, this.data.bankAddress).subscribe(
      data=>{
        this.confirmationDialog('Bank '+this.data.bankAddress+' added Successfully!');
        console.log("data from service:");
        console.log(data);
       this.dialogRef.close({success: 'Yes'});
    this.route.navigate(['/seller-home']);
      },err=>{
        console.log(err);
      
      }
    );
  }

  check(){
    console.log("hello");
    this.isBank=false;
   this.isBankAdded=true;
    this.checked=true;
    
  }
  confirmationDialog(element): void {
    const dialogRef = this.dialog.open(SellerConfirmation, {
      width: '50%',
      height:'40%',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
     // this.data['success'] = 'Yes';
      console.log('The Confirmation dialog was closed');
      //this.route.navigate(['/seller-home']);
    });
  }

}


@Component({
  selector: 'confirmation-seller',
  templateUrl: 'confirmation-seller.html',
})
export class SellerConfirmation {

  constructor(private route : Router,
    public dialogRef: MatDialogRef<SellerConfirmation>,
    @Inject(MAT_DIALOG_DATA) public data: string) {console.log('--> ' + data);
    }

}




@Component({
  selector: 'app-seller-home',
  templateUrl: './seller-home.component.html',
  styleUrls: ['./seller-home.component.css']
})
export class SellerHomeComponent implements OnInit {

  displayedColumns: string[] 
  dataSource: any;
  commId:any;
  spinner: Boolean = false;
  isBank:boolean=true; //add bank details
  checked:boolean=false;
  isBankAdded:boolean=false;
  constructor(private dataService: DataService, public dialog: MatDialog) {
  
   }

  ngOnInit() {
    console.log('dddddd');
    
    this.getAll();
    
    
  }

  getAll(){
    this.dataService.getTendersForSeller().subscribe(res => {
     
      console.log('------------------11');
      
      console.log(res);
      
      this.dataSource = res;
      this.dataSource.map((data) => data['accept']='');
      this. displayedColumns = ['id','commodityName', 'weight','price', 'buyerId', 'status','accept'];
      this.commId=this.dataSource.id;
      console.log(this.dataSource.id);
      
    }, err => {
      console.log(err);
      
    });
  }

  viewDialog(element): void {
    this.spinner = true;
    const dialogRef = this.dialog.open(TenderDetailsDialog, {
      width: '50%',
      height:'80%',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result.success == 'Yes'){
        this.spinner = false;
        this.getAll();
      }
      console.log('The dialog was closed');
    });
  }

}
