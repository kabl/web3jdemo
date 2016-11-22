package com.kabl.blockchain.web3jdemo;

import org.web3j.codegen.SolidityFunctionWrapperGenerator;

public class GenerateCodeSimpleDns {

    public static void main(String[] args) throws Exception {

        System.out.println("Hello!");

        String[] args2 = {
            "./src/main/resources/SimpleDns.sol",
            "./src/main/resources/SimpleDnsABI.json",
            "-p",
            "com.kabl.blockchain.web3jdemo.gen.simpledns",
            "-o",
            "./src/main/java",};

        SolidityFunctionWrapperGenerator.main(args2);
    }
}
