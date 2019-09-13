package com.commodity.blockchain;

import java.io.IOException;
import java.math.BigInteger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;

import ch.qos.logback.classic.Logger;

@Configuration
public class QuorumAdapter {

	@Value(value = "${quorum.url}")
	private String URL;

	@Bean
	public Quorum getQuorum() throws IOException {
		System.out.println("quorum url" + URL);
		Quorum quorum = Quorum.build(new HttpService(URL));
		System.out.println("quorum client :" + quorum.web3ClientVersion().send().getResult());

		return quorum;

	}

	public Credentials getCredentials() throws IOException, CipherException {
		// unix path /home/ubuntu/payal/arbour/springboot/projectcode
		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials("",
					"C:\\Users\\Admin2\\Desktop\\payal\\commodityTrade\\Springboot\\SalesContract\\src\\main\\resources\\credentials");

		} catch (Exception e) {

		}
		return credentials;
	}

	public BigInteger getGasPrice() {
		BigInteger gasprice = BigInteger.valueOf(4700000);
		return gasprice;
	}

	public BigInteger getGasLimit() {
		BigInteger gaslimit = BigInteger.valueOf(4700000);
		return gaslimit;
	}

}
