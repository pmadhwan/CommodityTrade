// solhint-disable-next-line compiler-fixed, compiler-gt-0_4.21
pragma solidity >=0.4.21 <0.6.0;



contract CommodityDeal{

    /**** Storage Types *******/
   
   ///seller is the owner of the contract
    address public owner;
    ///buyer address
    address public buyer;
   
    enum  contractStatus { Default,ACTIVE,SENT_TO_BUYER,SENT_TO_BANK_BUYER,ACCEPTED_BANK_BUYER,REJECTED_BANK_BUYER,
    SENT_TO_BANK_SELLER,ACCEPTED_BANK_SELLER,REJECTED_BANK_SELLER}
  
    enum GRADE{Default,A, Bpos, B, Bneg}
    // to differentiate between a buyer side bank and seller side bank
    //seller -0 ,buyer -1
    enum  BankType{seller,buyer}
    
    // status of the deal from banks
    //accept -0 ,reject -1
    enum  Status{accept,reject}
    

    /*** Events ****************/
    
   
   //Trigger this event when a seller wants to generate sales contract for a particular commodity for a buyer
    event salescontractStatus(contractStatus status );
	
	
    /*** Modifiers *************/
    /// @dev Only allow whitelisted users/devices
    modifier onlyOwner() {
        require(msg.sender == owner, "Requestor has no permissions to Initiate this function");
        _;
    }
    
    modifier onlyBuyer() {
        require(msg.sender == buyer, "Requestor has no permissions to Initiate this function");
        _;
    }

    /// @dev Using Enum values in order to track status of the contract
    /// @dev Sales contract information related to  a particular Commodity
     struct SalesContract {
        //Define structure
		//address id;
        bytes32 buyer_Name;
        bytes32 seller_Name;
        bytes32 commodity_Name;
        uint256 weight;
        uint256 price;
        GRADE grade;
        uint256 intended_delivery_date;
        string comments; // Text message that contains information intended to be private to the buyer and seller
		contractStatus status;
        
    }
	//common instance of a contract per smartcontract 
	SalesContract sContract;
	
	BankType  bank;
	
    ///Mappings
    
    //address is the bank account address mapped to a value of enum of seller type or buyer type
    mapping (address=>BankType) public banks;
	

    /***  Methods **********************************/
   
   
	
		/// @dev  
    /// @param all the data for a particular sales contract
    /// @return data according to the bank type or other type
     function getcontractDetails() public view returns( bytes32 buyer_Name,
        bytes32 seller_Name,
        bytes32 commodity_Name,
        uint256 weight,
        uint256 price,
        GRADE grade,
        uint256 intended_delivery_date,
        string memory comments,
		contractStatus status){
    //if this method is accessed by buyer bank ,then only details required to be seen are returned ,rest are set to default
	 if(uint256(banks[msg.sender])==uint256(BankType.buyer)){
	     
	     return (sContract.buyer_Name,sContract.seller_Name,sContract.commodity_Name,sContract.weight, sContract.price,GRADE.Default,sContract.intended_delivery_date,'0',contractStatus.Default);
	 }
	 //all the sales contract data is returned
	 else{
	      return (sContract.buyer_Name,sContract.seller_Name,sContract.commodity_Name,sContract.weight, sContract.price,sContract.grade,sContract.intended_delivery_date,sContract.comments,sContract.status);
	 }
	 
        
    }
    
    
    /// @dev method to set the bank for a particular partner in the sales contract
    /// @param _bank account address and _Btype is the type of bank related to buyer/seller
    function setbank(address _bank,BankType _Btype)public {
        banks[_bank]=_Btype;
        
    }
	
	/// @dev buyer requests its bank to generate the letter of credit for the SalesContract 
     function requestLOC() public onlyBuyer(){
	 
	 sContract.status=contractStatus.SENT_TO_BANK_BUYER;
	  emit salescontractStatus( sContract.status);
 
    }
    
   
	
    /// @dev sets the status from seller/buyer  bank 
    /// @param _status is the accept/reject status from banks
     function setStatus(Status _status) public{
         
	    if(uint256(banks[msg.sender])==uint256(BankType.buyer)){
	        if(_status==Status.accept){
	           sContract.status=contractStatus.ACCEPTED_BANK_BUYER;
	           emit salescontractStatus( sContract.status);
	        }else{
	           sContract.status=contractStatus.REJECTED_BANK_BUYER; 
	           emit salescontractStatus( sContract.status);
	        }
	    }else if(uint256(banks[msg.sender])==uint256(BankType.seller)){
	         if(_status==Status.accept){
	             require(sContract.status==contractStatus.ACCEPTED_BANK_BUYER,"contract pending with other bank!");
	           sContract.status=contractStatus.ACCEPTED_BANK_BUYER; 
	           emit salescontractStatus( sContract.status);
	        }else{
	           sContract.status=contractStatus.REJECTED_BANK_BUYER; 
	           emit salescontractStatus( sContract.status);
	        }
	    }  
  
        
    }
    

   //constructor function to initialize contract 
   //contract will be deployed for each sales contract initiated by the seller
    constructor(bytes32 _buyer_Name, bytes32 _seller_Name, bytes32 _commodity_Name,
        uint256 _weight,uint256 _price,GRADE _grade,uint256 _intended_delivery_date,
        string memory _comments,address _buyerAddr)public{
      //assing contract creator as owner of the contract
      owner = msg.sender;
      sContract.buyer_Name=_buyer_Name;
      sContract.seller_Name=_seller_Name;
      sContract.commodity_Name=_commodity_Name;
      sContract.weight=_weight;
      sContract.price=_price;
      sContract.grade=_grade;
    sContract.intended_delivery_date=_intended_delivery_date;
    sContract.comments=_comments;
    sContract.status=contractStatus.ACTIVE;
    buyer=_buyerAddr;
    }
}