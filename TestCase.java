import java.util.HashMap;
import java.util.Map;

public class TestCase {

    private Map startState;

    TestCase(int caseNum){
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
        startState = new HashMap();
//        startState.put("agentName", "X, Y, SPEEDX, SPEEDY, GOALX, GOALY");
        startState.put("car1", "2 ,10, 7,0, 100,0");
        startState.put("car2", "5 ,10, 5,0, 100,0");
    }
}
