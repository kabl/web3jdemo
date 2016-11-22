package com.kabl.blockchain.web3jdemo;

import org.web3j.codegen.SolidityFunctionWrapperGenerator;

public class GenerateCodeHello {

    public static void main(String[] args) throws Exception {

        System.out.println("Hello!");

        String[] args2 = {
            "./src/main/resources/hello.sol",
            "./src/main/resources/helloABI.json",
            "-p",
            "com.kabl.blockchain.web3jdemo.gen",
            "-o",
            "./src/main/java",};

        SolidityFunctionWrapperGenerator.main(args2);
    }
}
