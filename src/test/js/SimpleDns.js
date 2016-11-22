var Web3 = require('web3');
var web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));

//var abi = [{"constant":true,"inputs":[],"name":"getValue","outputs":[{"name":"_value","type":"string"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_value","type":"string"}],"name":"setValue","outputs":[],"pa$
var contract = web3.eth.contract(abi);
var contractInstance = contract.at('0x53498400e7917ae6cb2b2ae5167ae8963156fc6a');

var result = contractInstance.getIpByName("google.com");
console.log("result: " + result);

contractInstance.newEntry(function (error, result) {
  if (!error){
        console.log(result);
var result = contractInstance.getIpByName("web3js.com");
console.log("result: " + result);

}else{
        consloe.log(error);
}
});

web3.personal.unlockAccount(web3.eth.accounts[0], "password", 36000);
contractInstance.registerName("web3js.com", "2.2.2.2", {from: web3.eth.accounts[0]});
