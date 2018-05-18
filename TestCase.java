import java.util.HashMap;
import java.util.Map;

public class TestCase {

    private Map startState;

    TestCase(int caseNum){
        startState = new HashMap();
        switch (caseNum){
            case 1:
                init1();
                break;
            case 2:
                init2();
                break;
        }
    }

    String getAgentStartState(String agentName){
        if (startState.containsKey(agentName)){
            return (String) startState.get(agentName);
        }
        return null;
    };


    void init1(){
//        startState.put("agentName", "trajNum, speed, arrival time");
        //startState.put("car1", "1,10,50");
        startState.put("car2", "2,8,50");
        //startState.put("car3", "3,10,50");
//        startState.put("car4", "4,10,100");
		//startState.put("car5", "5,10,50");
        startState.put("car6", "6,9,100");

		startState.put("ped1","1,2,500");
		startState.put("ped2","2,2,1500");
		startState.put("ped3","3,3,1600");
    }

    void init2(){
//        startState.put("agentName", "trajNum, speed, arrival time");
        //startState.put("car1", "1,10,50");
        //startState.put("car2", "2,8,50");
        startState.put("car3", "3,10,50");
        //startState.put("car4", "4,10,100");
        startState.put("car5", "5,10,1700");
        //startState.put("car6", "6,9,100");

        startState.put("ped1","1,2,500");
        startState.put("ped2","2,2,1500");
        startState.put("ped3","3,3,1550");
    }
}
