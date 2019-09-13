package com.commodity.blockchain.contractwrapper;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple9;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class CommodityDeal extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060405161091f38038061091f83398101604090815281516020830151918301516060840151608085015160a086015160c087015160e088015161010089015160008054600160a060020a031916331790556002889055600389905560048781556005879055600686905560078054999b989997989697959694959390940193919286929160ff199091169060019084908111156100aa57fe5b0217905550600883905581516100c7906009906020850190610105565b50600a8054600160ff1990911681179091558054600160a060020a031916600160a060020a0392909216919091179055506101a09650505050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061014657805160ff1916838001178555610173565b82800160010185558215610173579182015b82811115610173578251825591602001919060010190610158565b5061017f929150610183565b5090565b61019d91905b8082111561017f5760008155600101610189565b90565b610770806101af6000396000f3006080604052600436106100825763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416632e49d78b8114610087578063439ac6c8146100a45780637150d8ae146100cb57806380c3b8c2146100fc57806383fbd9af146101415780638da5cb5b14610226578063fa9e99671461023b575b600080fd5b34801561009357600080fd5b506100a260ff60043516610250565b005b3480156100b057600080fd5b506100a2600160a060020a036004351660ff60243516610465565b3480156100d757600080fd5b506100e061049c565b60408051600160a060020a039092168252519081900360200190f35b34801561010857600080fd5b5061011d600160a060020a03600435166104ab565b6040518082600181111561012d57fe5b60ff16815260200191505060405180910390f35b34801561014d57600080fd5b506101566104c0565b604080518a8152602081018a9052908101889052606081018790526080810186905260a0810185600481111561018857fe5b60ff168152602001848152602001806020018360088111156101a657fe5b60ff168152602001828103825284818151815260200191508051906020019080838360005b838110156101e35781810151838201526020016101cb565b50505050905090810190601f1680156102105780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b34801561023257600080fd5b506100e061062e565b34801561024757600080fd5b506100a261063d565b6001336000908152600c602052604090205460ff16600181111561027057fe5b141561032a57600081600181111561028457fe5b14156102d557600a805460ff1916600417908190556040516000805160206107258339815191529160ff1690808260088111156102bd57fe5b60ff16815260200191505060405180910390a1610325565b600a80546005919060ff19166001835b0217905550600a546040516000805160206107258339815191529160ff16908082600881111561031157fe5b60ff16815260200191505060405180910390a15b610462565b336000908152600c602052604081205460ff16600181111561034857fe5b141561046257600081600181111561035c57fe5b141561041b576004600a5460ff16600881111561037557fe5b1461040757604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152602160248201527f636f6e74726163742070656e64696e672077697468206f746865722062616e6b60448201527f2100000000000000000000000000000000000000000000000000000000000000606482015290519081900360840190fd5b600a80546004919060ff19166001836102e5565b600a805460ff1916600517908190556040516000805160206107258339815191529160ff16908082600881111561044e57fe5b60ff16815260200191505060405180910390a15b50565b600160a060020a0382166000908152600c60205260409020805482919060ff19166001838181111561049357fe5b02179055505050565b600154600160a060020a031681565b600c6020526000908152604090205460ff1681565b60008080808080806060816001336000908152600c602052604090205460ff1660018111156104eb57fe5b141561055257505060025460035460045460055460065460085460408051808201909152600181527f30000000000000000000000000000000000000000000000000000000000000006020820152959c50939a509198509650945060009350915082610623565b60028054600354600454600554600654600754600854600a5460098054604080516020610100600185161502600019019093169c909c04601f810183900483028d018301909152808c52999a989997989697959660ff958616969495929492909316929184918301828280156106095780601f106105de57610100808354040283529160200191610609565b820191906000526020600020905b8154815290600101906020018083116105ec57829003601f168201915b505050505091509850985098509850985098509850985098505b909192939495969798565b600054600160a060020a031681565b600154600160a060020a031633146106dc57604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152603660248201527f526571756573746f7220686173206e6f207065726d697373696f6e7320746f2060448201527f496e69746961746520746869732066756e6374696f6e00000000000000000000606482015290519081900360840190fd5b600a805460ff1916600317908190556040516000805160206107258339815191529160ff16908082600881111561070f57fe5b60ff16815260200191505060405180910390a15600d6b93ba2cf5a77d5b208f36e902b11a23d0635bc839f5c5a55b94d47f1c20d01a165627a7a72305820f2822ddee7dfe5341ff984cd8e5ac9b8e9e84eb3ca2c80948deaf19fda8ae9700029";

    public static final String FUNC_SETSTATUS = "setStatus";

    public static final String FUNC_SETBANK = "setbank";

    public static final String FUNC_BUYER = "buyer";

    public static final String FUNC_BANKS = "banks";

    public static final String FUNC_GETCONTRACTDETAILS = "getcontractDetails";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REQUESTLOC = "requestLOC";

    public static final Event SALESCONTRACTSTATUS_EVENT = new Event("salescontractStatus", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
    ;

    @Deprecated
    protected CommodityDeal(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CommodityDeal(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CommodityDeal(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CommodityDeal(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setStatus(BigInteger _status) {
        final Function function = new Function(
                FUNC_SETSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setbank(String _bank, BigInteger _Btype) {
        final Function function = new Function(
                FUNC_SETBANK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_bank), 
                new org.web3j.abi.datatypes.generated.Uint8(_Btype)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> buyer() {
        final Function function = new Function(FUNC_BUYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> banks(String param0) {
        final Function function = new Function(FUNC_BANKS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, String, BigInteger>> getcontractDetails() {
        final Function function = new Function(FUNC_GETCONTRACTDETAILS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, String, BigInteger>>(
                new Callable<Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, String, BigInteger>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (String) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue());
                    }
                });
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> requestLOC() {
        final Function function = new Function(
                FUNC_REQUESTLOC, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<SalescontractStatusEventResponse> getSalescontractStatusEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SALESCONTRACTSTATUS_EVENT, transactionReceipt);
        ArrayList<SalescontractStatusEventResponse> responses = new ArrayList<SalescontractStatusEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SalescontractStatusEventResponse typedResponse = new SalescontractStatusEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SalescontractStatusEventResponse> salescontractStatusEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, SalescontractStatusEventResponse>() {
            @Override
            public SalescontractStatusEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SALESCONTRACTSTATUS_EVENT, log);
                SalescontractStatusEventResponse typedResponse = new SalescontractStatusEventResponse();
                typedResponse.log = log;
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SalescontractStatusEventResponse> salescontractStatusEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SALESCONTRACTSTATUS_EVENT));
        return salescontractStatusEventFlowable(filter);
    }

    @Deprecated
    public static CommodityDeal load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CommodityDeal(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CommodityDeal load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CommodityDeal(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CommodityDeal load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CommodityDeal(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CommodityDeal load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CommodityDeal(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CommodityDeal> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, byte[] _buyer_Name, byte[] _seller_Name, byte[] _commodity_Name, BigInteger _weight, BigInteger _price, BigInteger _grade, BigInteger _intended_delivery_date, String _comments, String _buyerAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_buyer_Name), 
                new org.web3j.abi.datatypes.generated.Bytes32(_seller_Name), 
                new org.web3j.abi.datatypes.generated.Bytes32(_commodity_Name), 
                new org.web3j.abi.datatypes.generated.Uint256(_weight), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint8(_grade), 
                new org.web3j.abi.datatypes.generated.Uint256(_intended_delivery_date), 
                new org.web3j.abi.datatypes.Utf8String(_comments), 
                new org.web3j.abi.datatypes.Address(_buyerAddr)));
        return deployRemoteCall(CommodityDeal.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<CommodityDeal> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, byte[] _buyer_Name, byte[] _seller_Name, byte[] _commodity_Name, BigInteger _weight, BigInteger _price, BigInteger _grade, BigInteger _intended_delivery_date, String _comments, String _buyerAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_buyer_Name), 
                new org.web3j.abi.datatypes.generated.Bytes32(_seller_Name), 
                new org.web3j.abi.datatypes.generated.Bytes32(_commodity_Name), 
                new org.web3j.abi.datatypes.generated.Uint256(_weight), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint8(_grade), 
                new org.web3j.abi.datatypes.generated.Uint256(_intended_delivery_date), 
                new org.web3j.abi.datatypes.Utf8String(_comments), 
                new org.web3j.abi.datatypes.Address(_buyerAddr)));
        return deployRemoteCall(CommodityDeal.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CommodityDeal> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, byte[] _buyer_Name, byte[] _seller_Name, byte[] _commodity_Name, BigInteger _weight, BigInteger _price, BigInteger _grade, BigInteger _intended_delivery_date, String _comments, String _buyerAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_buyer_Name), 
                new org.web3j.abi.datatypes.generated.Bytes32(_seller_Name), 
                new org.web3j.abi.datatypes.generated.Bytes32(_commodity_Name), 
                new org.web3j.abi.datatypes.generated.Uint256(_weight), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint8(_grade), 
                new org.web3j.abi.datatypes.generated.Uint256(_intended_delivery_date), 
                new org.web3j.abi.datatypes.Utf8String(_comments), 
                new org.web3j.abi.datatypes.Address(_buyerAddr)));
        return deployRemoteCall(CommodityDeal.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CommodityDeal> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, byte[] _buyer_Name, byte[] _seller_Name, byte[] _commodity_Name, BigInteger _weight, BigInteger _price, BigInteger _grade, BigInteger _intended_delivery_date, String _comments, String _buyerAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_buyer_Name), 
                new org.web3j.abi.datatypes.generated.Bytes32(_seller_Name), 
                new org.web3j.abi.datatypes.generated.Bytes32(_commodity_Name), 
                new org.web3j.abi.datatypes.generated.Uint256(_weight), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint8(_grade), 
                new org.web3j.abi.datatypes.generated.Uint256(_intended_delivery_date), 
                new org.web3j.abi.datatypes.Utf8String(_comments), 
                new org.web3j.abi.datatypes.Address(_buyerAddr)));
        return deployRemoteCall(CommodityDeal.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class SalescontractStatusEventResponse {
        public Log log;

        public BigInteger status;
    }
}
