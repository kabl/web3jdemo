package com.kabl.blockchain.web3jdemo.gen;

import java.lang.String;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.Contract;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.</p>
 */
public final class HelloABI extends Contract {
    private static final String BINARY = "contract hello {\r\n"
            + "    string greeting;\r\n"
            + "    \r\n"
            + "    function setGreeting(string s){\r\n"
            + "        greeting = s;\r\n"
            + "    }\r\n"
            + "    \r\n"
            + "    function getGreeting() constant returns (string s){\r\n"
            + "        s = greeting;\r\n"
            + "    }\r\n"
            + "    \r\n"
            + "    function killMe(){\r\n"
            + "        suicide(msg.sender);\r\n"
            + "    }\r\n"
            + "}";

    public HelloABI(String contractAddress, Web3j web3j, Credentials credentials) {
        super(contractAddress, web3j, credentials);
    }

    public Future<TransactionReceipt> setGreeting(Utf8String s) {
        Function function = new Function<>("setGreeting", Arrays.asList(s), Collections.emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> killMe() {
        Function function = new Function<>("killMe", Arrays.asList(), Collections.emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> getGreeting() {
        Function function = new Function<>("getGreeting", 
                Arrays.asList(), 
                Arrays.asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }
}
