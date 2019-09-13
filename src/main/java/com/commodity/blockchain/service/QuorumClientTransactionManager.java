
package com.commodity.blockchain.service;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;

@Service
public class QuorumClientTransactionManager {

	@Autowired
	Quorum quorum;

	@Value(value = "${seller.account}")
	private String sellernodeAddress;
	
	@Value(value = "${buyer.account}")
	private String buyernodeAddress;
	
	@Value(value = "${buyerBank.account}")
	private String issuanceBanknodeAddress;
	
	@Value(value = "${sellerBank.account}")
	private String beneficiaryBanknodeAddress;
	
	@Value(value = "${seller.pubkey}")
	private String sellerpubkey;
	
	@Value(value = "${buyer.pubkey}")
	private String buyerpubkey;
	
	@Value(value = "${sellerBank.pubkey}")
	private String sellerBankpubkey;
	
	@Value(value = "${buyerBank.pubkey}")
	private String buyerBankpubkey;
	
	//String walletPassword = null;
	//String walletDirectory = "C:\\Users\\Admin2\\Desktop\\payal\\commodityTrade\\Springboot\\SalesContract\\src\\main\\resources\\credentials";
	//String walletName = "UTC--2019-04-25T07-40-19.131608579Z--1589525e9c86049f287999523a11e4dc3a77f15a.json";
	
	/*
	 * public void unloackAccount() throws IOException, CipherException {
	 * 
	 * 
	 * 
	 * // Load the JSON encryted wallet Credentials credentials =
	 * WalletUtils.loadCredentials(walletPassword, walletDirectory + "/" +
	 * walletName);
	 * 
	 * // Get the account address String accountAddress = credentials.getAddress();
	 * System.out.println("account address:"+accountAddress);
	 * 
	 * // Get the unencrypted private key into hexadecimal String privateKey =
	 * credentials.getEcKeyPair().getPrivateKey().toString(16); }
	 */
	public ClientTransactionManager getSellerClientTransactionManager() {
		ClientTransactionManager transactionManager = new ClientTransactionManager(quorum, 
				sellernodeAddress, sellerpubkey,
				Arrays.asList(buyerpubkey), 5, 25000);
		System.out.println("tranx manager:"+transactionManager.getFromAddress());
		return transactionManager;

	}
	
	public ClientTransactionManager getBuyerClientTransactionManager() {
		ClientTransactionManager transactionManager = new ClientTransactionManager(quorum,
				buyernodeAddress, buyerpubkey,
				Arrays.asList(buyerBankpubkey),
				5, 25000);
		return transactionManager;

	}
	
	public ClientTransactionManager getSellerBankClientTransactionManager() {
		ClientTransactionManager transactionManager = new ClientTransactionManager(quorum,
				beneficiaryBanknodeAddress, sellerBankpubkey,
				Arrays.asList(buyerBankpubkey,buyerpubkey,sellerpubkey), 5, 25000);
		return transactionManager;

	}
	
	public ClientTransactionManager getBuyerBankClientTransactionManager() {
		ClientTransactionManager transactionManager = new ClientTransactionManager(quorum,
				issuanceBanknodeAddress, buyerBankpubkey,
				Arrays.asList(sellerBankpubkey,buyerpubkey,sellerpubkey), 5, 25000);
		return transactionManager;

	}

}
