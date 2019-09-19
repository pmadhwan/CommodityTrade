package com.commodity.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.quorum.Quorum;
import org.web3j.tuples.generated.Tuple9;

import com.commodity.blockchain.contractwrapper.CommodityDeal;
import com.commodity.blockchain.service.QuorumClientTransactionManager;
import com.commodity.blockchain.service.QuorumContractGasProvider;
import com.commodity.commons.ContractState;
import com.commodity.commons.DataTypeConverter;
import com.commodity.entity.Commodity;
import com.commodity.entity.Deal;
import com.commodity.repository.CommodityRepository;
import com.commodity.repository.DealRepository;
import com.commodity.vo.SalesContract;

import io.reactivex.internal.observers.ForEachWhileObserver;

@Service
public class IssuanceBankService {
	
	@Value(value = "${buyerBank.account}")
	private String bankNodeAddress;
	
	@Autowired
	private Quorum quorum;

	@Autowired
	private QuorumContractGasProvider contractGasProvider;
	
	@Autowired
	private QuorumClientTransactionManager transactionManager;
	
	@Autowired
	private DealRepository dealRepository;
	
	@Autowired
	private CommodityRepository commRepository;
	
	@Autowired
	private BuyerService buyerService;
	
	public List<Commodity> getAll(String bankAddress) {
		List<Commodity> clist = new ArrayList<>();
		
		List<Deal> dealList=dealRepository.findByBuyerBank(bankAddress);
		if(!dealList.isEmpty()&&dealList!=null) {
			for (Deal deal : dealList) {
				Commodity comm=new Commodity();
				comm=commRepository.findById(deal.getCommodityId()).orElse(null);
				clist.add(comm);
			}
		}
		
		System.out.println("commodity list for buyer bank:"+clist);

		return clist;
	}

	public String acceptContract(String contractAddress) throws Exception {
		

		CommodityDeal smartContract = CommodityDeal.load(contractAddress,
				quorum, transactionManager.getSellerClientTransactionManager(),
				contractGasProvider);
		TransactionReceipt tr= smartContract.setStatus(BigInteger.ZERO).send();
		Boolean state= buyerService.updateStatus(ContractState.ACCEPTED_BANK_BUYER, contractAddress);
		if(state) {
			return tr.getTransactionHash();
		}
		return null;
	}

	public String rejectContract(String contractAddress) throws Exception {


		CommodityDeal smartContract = CommodityDeal.load(contractAddress, 
				quorum, transactionManager.getSellerClientTransactionManager(),
				contractGasProvider);
		TransactionReceipt tr= smartContract.setStatus(BigInteger.ONE).send();
		Boolean state= buyerService.updateStatus(ContractState.REJECTED_BANK_BUYER, contractAddress);
		if(state) {
			return tr.getTransactionHash();
		}
		return null;
	}

	

	public SalesContract getDetails(String contractAddress) throws IOException, CipherException {

		Deal deal = dealRepository.findByContractAddress(contractAddress);
		System.out.println("deal:" + deal);
		SalesContract salescontract = new SalesContract();

		

		CommodityDeal smartContract = CommodityDeal.load(contractAddress, quorum, transactionManager.getBuyerBankClientTransactionManager(),
				contractGasProvider);

		try {

			Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, String, BigInteger> details = smartContract
					.getcontractDetails().send();

			System.out.println("details:" + DataTypeConverter.Bytes32toString(details.getValue1()).trim());

			salescontract.setBuyerName(DataTypeConverter.Bytes32toString(details.getValue1()).trim());
			//salescontract.setComments(details.getValue8());
			Commodity commodity = new Commodity();
			commodity.setCommodityName(DataTypeConverter.Bytes32toString(details.getValue3()).trim());
			commodity.setWeight(details.getValue4().longValue());
			commodity.setPrice(details.getValue5().longValue());
			commodity.setStatus(salescontract.getStatus());
			salescontract.setCommodity(commodity);
			//salescontract.setGrade(Grade.values()[details.getValue6().intValue()]);
			salescontract.setIntendedDelievryDate(details.getValue7().longValue());
			salescontract.setSellerName(DataTypeConverter.Bytes32toString(details.getValue2()).trim());
			salescontract.setStatus(ContractState.values()[details.getValue9().intValue()]);
			salescontract.setContractAddress(contractAddress);

			System.out.println("salescontrcat:" + salescontract);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return salescontract;
	}
}
