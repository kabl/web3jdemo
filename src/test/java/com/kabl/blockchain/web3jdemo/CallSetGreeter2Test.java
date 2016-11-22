package com.kabl.blockchain.web3jdemo;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;


public class CallSetGreeter2Test extends Scenario {

    //https://github.com/web3j/web3j/blob/master/src/integration-test/java/org/web3j/protocol/scenarios/Scenario.java
    public CallSetGreeter2Test() {
        super(Parity.build(new HttpService("http://localhost:8545")));
//        httpService = new HttpService("http://localhost:8545");
//        web3j = Web3j.build(httpService);
    }

    @Test
    public void doIt() throws Exception {

        boolean unlocked = unlockAccount();
        assertThat(unlocked, is(true));

        String transactionHash = sendCreateContractTransaction();
        assertFalse(transactionHash.isEmpty());

        TransactionReceipt transactionReceipt
                = waitForTransactionReceipt(transactionHash);

        assertThat(transactionReceipt.getTransactionHash(), is(transactionHash));

        assertFalse("Contract execution ran out of gas",
                transactionReceipt.getGasUsed().equals(GAS_LIMIT));

        Optional<String> contractAddressOptional = transactionReceipt.getContractAddress();

        assertTrue(contractAddressOptional.isPresent());
        String contractAddress = contractAddressOptional.get();

        ///old code
//        Function greetingFunction = createSetGreeting();
//        String responseValue = callSmartContractFunction(greetingFunction, CONTRACT_ADDR);
//        System.out.println("response: " + responseValue);
    }

    @Test
    public void callNormalContract() throws Exception {
        boolean unlocked = unlockAccount();
        assertThat(unlocked, is(true));

        Function greetingFunction = createSetGreeting();
        String transactionHash = sendTransactionCall(CONTRACT_ADDR, greetingFunction);

        TransactionReceipt transactionReceipt
                = waitForTransactionReceipt(transactionHash);

        assertThat(transactionReceipt.getTransactionHash(), is(transactionHash));

        assertFalse("Contract execution ran out of gas",
                transactionReceipt.getGasUsed().equals(GAS_LIMIT));

//        Optional<String> address = transactionReceipt.getContractAddress();

//        assertTrue(address.isPresent());
//        System.out.println("address: " + address.get());
    }

    Function createSetGreeting() {
        return new Function<>("setGreeting",
                Arrays.asList(new Utf8String("from web3j")),
                Collections.emptyList());
    }

    String callSmartContractFunction(
            Function function, String contractAddress) throws Exception {

        String encodedFunction = FunctionEncoder.encode(function);

        org.web3j.protocol.core.methods.response.EthCall response = parity.ethCall(
                Transaction.createEthCallTransaction(contractAddress, encodedFunction),
                DefaultBlockParameterName.LATEST)
                .sendAsync().get();

        return response.getValue();
    }

    private String sendCreateContractTransaction() throws Exception {

        BigInteger nonce = getNonce(ACCOUNT_ADDR);

        Transaction transaction = Transaction.createContractTransaction(
                ACCOUNT_ADDR,
                nonce,
                GAS_PRICE,
                GAS_LIMIT,
                BigInteger.ZERO,
                getSolidityContract());

        org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse = parity.ethSendTransaction(transaction)
                .sendAsync().get();

        return transactionResponse.getTransactionHash();
    }

    private String sendTransactionCall(String contractAddr, Function function) throws Exception {
        String encodedFunction = FunctionEncoder.encode(function);

        BigInteger nonce = getNonce(ACCOUNT_ADDR);

        Transaction transaction = Transaction.createFunctionCallTransaction(
                ACCOUNT_ADDR,
                nonce,
                GAS_PRICE,
                GAS_LIMIT,
                contractAddr,
                encodedFunction);

        org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse = parity.ethSendTransaction(transaction)
                .sendAsync().get();

        return transactionResponse.getTransactionHash();
    }
}
