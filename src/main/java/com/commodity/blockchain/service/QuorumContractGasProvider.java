package com.commodity.blockchain.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
public class QuorumContractGasProvider extends DefaultGasProvider {

	@Override
	public BigInteger getGasPrice(String contractFunc) {
		return BigInteger.valueOf(129000000);
	}

	@Override
	public BigInteger getGasLimit(String contractFunc) {
		BigInteger gaslimit = BigInteger.valueOf(48000000);
		return gaslimit;
	}
	/*
	 * public BigInteger getGasPrice() { return DefaultGasProvider.GAS_LIMIT; }
	 * 
	 * public BigInteger getGasLimit() { return DefaultGasProvider.GAS_PRICE; }
	 */
} 