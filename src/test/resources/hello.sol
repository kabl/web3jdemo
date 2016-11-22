contract hello {
    string greeting;
    
    function setGreeting(string s){
        greeting = s;
    }
    
    function getGreeting() constant returns (string s){
        s = greeting;
    }
    
    function killMe(){
        suicide(msg.sender);
    }
}