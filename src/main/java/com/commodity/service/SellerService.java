package com.commodity.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.commodity.commons.BankType;
import com.commodity.commons.ContractState;
import com.commodity.commons.DataTypeConverter;
import com.commodity.commons.Grade;
import com.commodity.entity.Commodity;
import com.commodity.entity.Deal;
import com.commodity.repository.CommodityRepository;
import com.commodity.repository.DealRepository;
import com.commodity.repository.SalesContractRepository;
import com.commodity.vo.Bank;
import com.commodity.vo.SalesContract;

@Service
public class SellerService {

	@Autowired
	private Quorum quorum;

	@Value(value = "${seller.account}")
	private String sellerNodeAddress;

	@Value(value = "${buyer.account}")
	private String buyerNodeAddress;

	@Autowired
	private QuorumContractGasProvider contractGasProvider;

	@Autowired
	private QuorumClientTransactionManager transactionManager;

	@Autowired
	private QuorumAdapter quorumAdapter;

	@Autowired
	private CommodityRepository commodityRepository;

	@Autowired
	private DealRepository dealRepository;

	private String contractAddress;

	/*
	 * public List<SalesContract> getAll(String id) { return null;
	 * 
	 * }
	 */

	public String generateSalesContract(SalesContract salesContract) {

		// connect to quorum and deploy the contract by initializing its constructor
		System.out.println("inside service");
		try {

			/*
			 * ClientTransactionManager transactionManager1 = new
			 * ClientTransactionManager(quorum,
			 * "0x1589525e9c86049f287999523a11e4dc3a77f15a",
			 * "KANINW1JGnme35RXIUhgxGKmy2uImalVDlnzVqtR3UY=",
			 * //"GyAVCka2T8ZDow/KzH2j1+pxJFSay4kIshCD2leYIEA=", Arrays.asList(), 5, 25000);
			 */

			/*
			 * ClientTransactionManager transactionManager1 = new
			 * ClientTransactionManager(quorum,
			 * "0x53a68d89e4808004d0b99ab7540eebae1307c391",
			 * "GyAVCka2T8ZDow/KzH2j1+pxJFSay4kIshCD2leYIEA=", Arrays.asList(), 15, 25000);
			 */
			// System.out.println("tranx manager:" + transactionManager1.getFromAddress());

			System.out.println("salescontract:" + salesContract);

			CommodityDeal smartContract = CommodityDeal
					.deploy(quorum, transactionManager.getSellerClientTransactionManager(), contractGasProvider,
							DataTypeConverter.stringTo32Byte(salesContract.getBuyerName()),
							DataTypeConverter.stringTo32Byte(salesContract.getSellerName()),
							DataTypeConverter.stringTo32Byte(salesContract.getCommodity().getCommodityName()),
							BigInteger.valueOf(salesContract.getCommodity().getWeight()),
							BigInteger.valueOf(salesContract.getCommodity().getPrice()),
							BigInteger.valueOf(salesContract.getGrade().ordinal()),
							BigInteger.valueOf(salesContract.getIntendedDelievryDate()), salesContract.getComments(),
							buyerNodeAddress)
					.send();

			/*
			 * CommodityDeal smartContract = CommodityDeal.deploy(quorum,
			 * transactionManager1, contractGasProvider,
			 * DataTypeConverter.stringTo32Byte("Tata"),
			 * DataTypeConverter.stringTo32Byte("Birla"),
			 * DataTypeConverter.stringTo32Byte("Com"), BigInteger.valueOf(123l),
			 * BigInteger.valueOf(1234l), BigInteger.ZERO, BigInteger.valueOf(12312),
			 * "asdasd", "0x53a68d89e4808004d0b99ab7540eebae1307c391") .send();
			 */

			// System.out.println(smartContract);
			Optional<TransactionReceipt> txHash = smartContract.getTransactionReceipt();

			contractAddress = smartContract.getContractAddress();
			salesContract.setContractAddress(contractAddress);
			System.out.println("contractAddress :" + contractAddress);
			System.out.println("txhash :" + txHash);

			Commodity com = commodityRepository.findById(salesContract.getCommodity().getId()).orElse(null);
			com.setStatus(ContractState.SENT_TO_BUYER);
			System.out.println("commodity:" + com);
			commodityRepository.save(com);
			System.out.println("comm:" + com);
			Deal entity = new Deal();
			entity.setContractAddress(contractAddress);
			// entity.setBuyerBank(buyerNodeAddress);
			// entity.setSellerBank(sellerNodeAddress);
			entity.setBuyerId(salesContract.getBuyerName());
			entity.setSellerId(salesContract.getSellerName());

			entity.setCommodityId(salesContract.getCommodity().getId());
			System.out.println(entity);
			Deal entity1 = dealRepository.save(entity);
			if (entity1 != null && contractAddress != null) {
				return contractAddress;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contractAddress;

	}

	public SalesContract getDetails(String contractAddress) throws IOException, CipherException {

		Deal deal = dealRepository.findByContractAddress(contractAddress);
		System.out.println("deal:" + deal);
		SalesContract salescontract = new SalesContract();

		/*
		 * ClientTransactionManager transactionManager1 = new
		 * ClientTransactionManager(quorum, sellerNodeAddress,
		 * "KANINW1JGnme35RXIUhgxGKmy2uImalVDlnzVqtR3UY=", Arrays.asList(), 5, 25000);
		 */

		CommodityDeal smartContract = CommodityDeal.load(contractAddress, quorum,
				transactionManager.getSellerClientTransactionManager(),
				// transactionManager1,
				contractGasProvider);

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

			salescontract.setCommodity(commodity);
			salescontract.setGrade(Grade.values()[details.getValue6().intValue()]);
			salescontract.setIntendedDelievryDate(details.getValue7().longValue());
			salescontract.setSellerName(DataTypeConverter.Bytes32toString(details.getValue2()).trim());
			salescontract.setStatus(ContractState.values()[details.getValue9().intValue()]);
			salescontract.setContractAddress(contractAddress);
			commodity.setStatus(salescontract.getStatus());

			System.out.println("salescontrcat:" + salescontract);
			Commodity com = commodityRepository.findById(commodity.getId()).orElse(null);
			System.out.println("commodity:" + com);
			commodityRepository.save(com);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return salescontract;
	}

	public String setBank(String b, String type, String contractAddress) throws Exception {

		// Bank bb =new Bank();
		int typeVar;
		System.out.println("bank address:" + b);
		System.out.println("contract address:" + contractAddress);
		// bb.setType(BankType.BENEFICIARY);
		// bb.setBanknodeAddress(b);
		// System.out.println("bb:"+bb);
		Deal d = new Deal();
		d = dealRepository.findByContractAddress(contractAddress);
		System.out.println(d);
		if (type.equalsIgnoreCase("seller")) {
			d.setSellerBank(b);
			typeVar = 0;
		} else {
			d.setBuyerBank(b);
			typeVar = 1;
		}

		dealRepository.save(d);

		CommodityDeal smartContract = CommodityDeal.load(contractAddress, quorum,
				transactionManager.getSellerClientTransactionManager(), contractGasProvider);

		TransactionReceipt tr = smartContract.setbank(b, BigInteger.valueOf(typeVar)).send();
		return tr.getTransactionHash();
	}

	public List<Commodity> getAll() {

		List<Commodity> cm = commodityRepository.findAll();
		System.out.println(cm);
		return cm;
	}

}
