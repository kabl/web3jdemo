package com.kabl.blockchain.web3jdemo;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class CallSetGreeter {

    public static void main(String[] args) throws Exception {

//        HttpService httpService = new HttpService("http://192.168.56.1:8545");
//        HttpService httpService = new HttpService("http://172.29.225.127:8545");
        HttpService httpService = new HttpService("http://localhost:8545");
        Web3j web3j = Web3j.build(httpService);
        
//        Parity parity = Parity.build(httpService);
//        parity.
        
        
//        EthAccounts accounts = web3j.ethAccounts().send();
        
        
//        HelloABI helloAbi = new HelloABI("0x272dceabdba0f3875a56777c9649c0aae9b92205", web3j);
//        helloAbi.setGreeting(new Utf8String("Hello from web3j"));
//        System.out.println("ok!");
    }
}
