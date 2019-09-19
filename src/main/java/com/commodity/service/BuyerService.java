package com.commodity.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.quorum.Quorum;
import org.web3j.tuples.generated.Tuple9;

import com.commodity.blockchain.contractwrapper.CommodityDeal;
import com.commodity.blockchain.service.QuorumClientTransactionManager;
import com.commodity.blockchain.service.QuorumContractGasProvider;
import com.commodity.commons.BankType;
import com.commodity.commons.ContractState;
import com.commodity.commons.DataTypeConverter;
import com.commodity.commons.Grade;
import com.commodity.entity.Commodity;
import com.commodity.entity.Deal;
import com.commodity.repository.CommodityRepository;
import com.commodity.repository.DealRepository;
import com.commodity.vo.Bank;
import com.commodity.vo.SalesContract;

@Service
public class BuyerService {

	@Autowired
	CommodityRepository commRepository;

	// @Value(value = "${buyer.account}")
	// private String buyerNodeAddress;

	@Autowired
	private QuorumContractGasProvider contractGasProvider;

	@Autowired
	private QuorumClientTransactionManager transactionManager;

	@Autowired
	Quorum quorum;

	@Autowired
	SellerService sellerservice;

	@Autowired
	private CommodityRepository commrepo;

	@Autowired
	private DealRepository dealRepository;

	public Commodity createTender(Commodity commodity) {
		return commRepository.save(commodity);

	}

	public String requestLOC(String contractAddress) throws Exception {
		
		CommodityDeal smartContract = CommodityDeal.load(contractAddress, quorum,
				transactionManager.getSellerClientTransactionManager(), contractGasProvider);

		TransactionReceipt tr = smartContract.requestLOC().send();

		System.out.println("tranasaction hash:" + tr.getTransactionHash());
		
		Boolean state= updateStatus(ContractState.SENT_TO_BANK_BUYER, contractAddress);
		if(state) {
			return tr.getTransactionHash();
		}
		return null;
	}

	public SalesContract getDetails(String contractAddress) throws IOException, CipherException {

		// Deal deal =dealRepository.findByContractAddress(contractAddress);
		System.out.println(" contract Addrerss:" + contractAddress);
		SalesContract salescontract = new SalesContract();

		CommodityDeal smartContract = CommodityDeal.load(contractAddress, quorum,
				transactionManager.getBuyerClientTransactionManager(), contractGasProvider);

		try {

			Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, String, BigInteger> details = smartContract
					.getcontractDetails().send();

			System.out.println("details:" + DataTypeConverter.Bytes32toString(details.getValue1()).trim());

			salescontract.setBuyerName(DataTypeConverter.Bytes32toString(details.getValue1()).trim());
			salescontract.setComments(details.getValue8());
			Commodity commodity = new Commodity();
			commodity.setCommodityName(DataTypeConverter.Bytes32toString(details.getValue3()).trim());
			commodity.setWeight(details.getValue4().longValue());
			commodity.setPrice(details.getValue5().longValue());
			commodity.setStatus(salescontract.getStatus());
			salescontract.setCommodity(commodity);
			salescontract.setGrade(Grade.values()[details.getValue6().intValue()]);
			salescontract.setIntendedDelievryDate(details.getValue7().longValue());
			salescontract.setSellerName(DataTypeConverter.Bytes32toString(details.getValue2()).trim());
			salescontract.setStatus(ContractState.values()[details.getValue9().intValue()]);
			salescontract.setContractAddress(contractAddress);

			System.out.println("salescontrcat:" + salescontract);
			return salescontract;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return salescontract;
	}

	public String setBank(String b, String type, String contractAddress) throws Exception {
		/*
		 * Bank bb =new Bank(); bb.setType(BankType.ISSUANCE); bb.setBanknodeAddress(b);
		 * Deal d =new Deal(); d= dealRepository.findByContractAddress(contractAddress);
		 * d.setBuyerBank(b); dealRepository.save(d);
		 * 
		 * CommodityDeal smartContract = CommodityDeal.load(contractAddress, quorum,
		 * transactionManager.getBuyerClientTransactionManager(), contractGasProvider);
		 * 
		 * TransactionReceipt tr = smartContract.setbank(b, BigInteger.ONE).send();
		 */
		String tr = sellerservice.setBank(b, type, contractAddress);

		System.out.println(tr);
		return tr;
	}

	public List<Commodity> getAll(String buyerid) {

		List<Commodity> cm = commrepo.findAllByBuyerId(buyerid);
		System.out.println(cm);
		return cm;
	}

	public SalesContract getByCommId(String id) throws IOException, CipherException {
		SalesContract ss = new SalesContract();
		Deal cm = dealRepository.findByCommodityId(id);
		System.out.println("dael obj:" + cm);
		ss = getDetails(cm.getContractAddress());

		System.out.println("contarct add:" + ss);
		return ss;
	}

	public String getByContractAddress(String add) {
		Deal cc = dealRepository.findByContractAddress(add);
		return cc.getCommodityId();
	}

	public Boolean updateStatus(ContractState status, String contractAdd) {
		Commodity com = commrepo.findById(getByContractAddress(contractAdd)).orElse(null);
		com.setStatus(status);
		System.out.println("commodity:" + com);
		commrepo.save(com);

		return true;
	}

}
