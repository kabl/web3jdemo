package com.kabl.blockchain.web3jdemo.gen.simpledns;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.Contract;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
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
public final class SimpleDnsABI extends Contract {
    private static final String BINARY = "contract SimpleDns {\r\n"
            + "\r\n"
            + "     struct DnsEntry\r\n"
            + "     {\r\n"
            + "         address owner;\r\n"
            + "         string ip;\r\n"
            + "     }\r\n"
            + "\r\n"
            + "     string value;\r\n"
            + "\r\n"
            + "     mapping (string => DnsEntry) entriesByName;\r\n"
            + "\r\n"
            + "     event newEntry(string name, string ip);\r\n"
            + "     event functionCalled();\r\n"
            + "\r\n"
            + "     function registerName(string name, string ip) {\r\n"
            + "         functionCalled();\r\n"
            + "         if(entriesByName[name].owner != address(0x0) &&\r\n"
            + "             entriesByName[name].owner != msg.sender)\r\n"
            + "             throw;\r\n"
            + "\r\n"
            + "        entriesByName[name] = DnsEntry(msg.sender, ip);\r\n"
            + "\r\n"
            + "        newEntry(name, ip);\r\n"
            + "     }\r\n"
            + "\r\n"
            + "     function getIpByName(string name) constant returns (string ip){\r\n"
            + "         if(entriesByName[name].owner != address(0x0))\r\n"
            + "             return entriesByName[name].ip;\r\n"
            + "         else\r\n"
            + "             return \"404\";\r\n"
            + "     }\r\n"
            + "\r\n"
            + "     function setValue(string _value){\r\n"
            + "         functionCalled();\r\n"
            + "         value = _value;\r\n"
            + "     }\r\n"
            + "\r\n"
            + "     function getValue() constant returns (string _value){\r\n"
            + "         _value = value;\r\n"
            + "     }\r\n"
            + "}\r\n";

    private SimpleDnsABI(String contractAddress, Web3j web3j, Credentials credentials) {
        super(contractAddress, web3j, credentials);
    }

    public Future<Utf8String> getValue() {
        Function function = new Function<>("getValue", 
                Arrays.asList(), 
                Arrays.asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setValue(Utf8String _value) {
        Function function = new Function<>("setValue", Arrays.asList(_value), Collections.emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> getIpByName(Utf8String name) {
        Function function = new Function<>("getIpByName", 
                Arrays.asList(name), 
                Arrays.asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> registerName(Utf8String name, Utf8String ip) {
        Function function = new Function<>("registerName", Arrays.asList(name, ip), Collections.emptyList());
        return executeTransactionAsync(function);
    }

    public EventValues processNewEntryEvent(TransactionReceipt transactionReceipt) {
        Event event = new Event("newEntry", 
                Arrays.asList(),
                Arrays.asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return extractEventParameters(event, transactionReceipt);
    }

    public EventValues processFunctionCalledEvent(TransactionReceipt transactionReceipt) {
        Event event = new Event("functionCalled", 
                Arrays.asList(),
                Arrays.asList());
        return extractEventParameters(event, transactionReceipt);
    }

    public static Future<SimpleDnsABI> deploy(Web3j web3j, Credentials credentials, BigInteger initialValue) {
        return deployAsync(SimpleDnsABI.class, web3j, credentials, BINARY, "", initialValue);
    }

    public static SimpleDnsABI load(String contractAddress, Web3j web3j, Credentials credentials) {
        return new SimpleDnsABI(contractAddress, web3j, credentials);
    }
}
