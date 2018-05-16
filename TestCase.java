import java.util.HashMap;
import java.util.Map;

public class TestCase {

    private Map startState;

    TestCase(int caseNum){
        startState = new HashMap();
        switch (caseNum){
            case 1:
                init1();
        }
    }

    String getAgentStartState(String agentName){
        if (startState.containsKey(agentName)){
            return (String) startState.get(agentName);
        }
        return null;
    };


    void init1(){
//        startState.put("agentName", "X, Y, SPEEDX, SPEEDY, GOALX, GOALY");
//        startState.put("agentName", "trajNum, speed");
        //startState.put("car1", "1,10");
        startState.put("car2", "2,10");
        //startState.put("car3", "3,10");
        startState.put("car4", "4,10");
		//startState.put("car5", "5,10");
        //startState.put("car6", "6,10");
    }
}
