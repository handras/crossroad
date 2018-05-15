import java.util.HashMap;
import java.util.Map;

import jason.asSyntax.Literal;

public class CrossroadModel {

    private String[] allAgents = new String[]{"car1","car2","car3","car4","car5","car6","emergency","lamp"};
    public Map<String, Car> cars;
    Crossroad env;

    public CrossroadModel(int test, Crossroad env) {
        this.env=env;
        cars = new HashMap();

        TestCase testCase = new TestCase(test);
        for (String ag : allAgents) {
            String startState = testCase.getAgentStartState(ag);
            if (startState != null) {
                cars.put(ag, new Car(startState));
            }
        }
    }

    public Literal getAgentPosLiteral(String agentName){
        return Literal.parseLiteral("pos("+agentName+ "," + 1 + "," + 1 + ")");
    }

    public void calcSafety(String ag){
        Car c1 = cars.get(ag);
        boolean safe = true;
        Car c2;
        for(String otherCarName : allAgents){
            if(otherCarName.equals(ag)) continue;
            c2 = cars.get(otherCarName);
            try {
                float tx = c1.x - c2.x / (c2.sx - c1.sx);
                float ty = c1.y - c2.y / (c2.sy - c1.sy);
                Crossroad.logger.info("tx: "+tx+" ty: "+ty);

                if(tx>0 || ty>0){
                    env.informAgent(ag, "collision("+otherCarName+")");
                    safe = false;
                }
            } catch (NullPointerException e){
                continue;
            }
        }
        if (safe){
            env.informAgent(ag, "safe");
        }
    }
}

