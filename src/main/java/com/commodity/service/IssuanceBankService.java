package com.commodity.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;
import org.web3j.tuples.generated.Tuple9;

import com.commodity.blockchain.QuorumAdapter;
import com.commodity.blockchain.contractwrapper.CommodityDeal;
import com.commodity.blockchain.service.QuorumClientTransactionManager;
import com.commodity.blockchain.service.QuorumContractGasProvider;
import com.commodity.commons.ContractState;
import com.commodity.commons.DataTypeConverter;
import com.commodity.commons.Grade;
import com.commodity.entity.Commodity;
import com.commodity.entity.Deal;
import com.commodity.repository.DealRepository;
import com.commodity.repository.SalesContractRepository;
import com.commodity.vo.SalesContract;

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

	public String acceptContract(String contractAddress) throws Exception {
		

		CommodityDeal smartContract = CommodityDeal.load(contractAddress,
				quorum, transactionManager.getBuyerBankClientTransactionManager(),
				contractGasProvider);
		TransactionReceipt tr= smartContract.setStatus(BigInteger.ZERO).send();
		return tr.getTransactionHash();
	}

	public String rejectContract(String contractAddress) throws Exception {


		CommodityDeal smartContract = CommodityDeal.load(contractAddress, 
				quorum, transactionManager.getBuyerBankClientTransactionManager(),
				contractGasProvider);
		TransactionReceipt tr= smartContract.setStatus(BigInteger.ONE).send();
		return tr.getTransactionHash();
	}

	public String generateLOC(String contractAddress) {
		return "Success";
	}

	public SalesContract getDetails(String contractAddress) throws IOException, CipherException {

		Deal deal = dealRepository.findByContractAddress(contractAddress);
		System.out.println("deal:" + deal);
		SalesContract salescontract = new SalesContract();

		ClientTransactionManager transactionManager = new ClientTransactionManager(quorum, bankNodeAddress,
				"KANINW1JGnme35RXIUhgxGKmy2uImalVDlnzVqtR3UY="
				// "GyAVCka2T8ZDow/KzH2j1+pxJFSay4kIshCD2leYIEA="
				, Arrays.asList(), 5, 25000);

		CommodityDeal smartContract = CommodityDeal.load(contractAddress, quorum, transactionManager,
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
			commodity.setSelectedBidPrice(details.getValue5().longValue());
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
