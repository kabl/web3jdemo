package com.kabl.blockchain.web3jdemo;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;

public class CallSetGreeter2 {

    private static final String CONTRACT_ADDR = "0x272dceabdba0f3875a56777c9649c0aae9b92205";

    private final HttpService httpService;
    private final Web3j web3j;
    private final Parity parity;

    public CallSetGreeter2() {
        httpService = new HttpService("http://localhost:8545");
        web3j = Web3j.build(httpService);
        parity = Parity.build(httpService);
    }

    public static void main(String[] args) throws Exception {
        new CallSetGreeter2().doIt();
    }

    private void doIt() throws Exception {
        
        unlockAccount();
        
        Function greetingFunction = createSetGreeting();
        String responseValue = callSmartContractFunction(greetingFunction, CONTRACT_ADDR);
        System.out.println("response: " + responseValue);
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

    boolean unlockAccount() throws Exception {
        PersonalUnlockAccount personalUnlockAccount
                = parity.personalUnlockAccount("0xde1e758511a7c67e7db93d1c23c1060a21db4615", "password", new BigInteger("100000"))
                .sendAsync().get();
        return personalUnlockAccount.accountUnlocked();
    }
}
