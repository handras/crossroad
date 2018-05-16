import java.util.HashMap;
import java.util.Map;

import jason.asSyntax.Literal;

public class CrossroadModel {

    private String[] allAgents = new String[]{"car1","car2","car3","car4","car5","car6","emergency","lamp"};
    public Map<String, Car> cars;
    Crossroad env;
    CrossroadGraphic graphic;

    public CrossroadModel(int test, Crossroad env) {
        this.env=env;
        cars = new HashMap();

        TestCase testCase = new TestCase(test);
        for (String ag : allAgents) {
            String startState = testCase.getAgentStartState(ag);
            if (startState != null) {
                cars.put(ag, new Car(startState));
                env.informAgent(ag, "start("+startState+")");
            }
        }
    }

    public void step(int time){
        //Crossroad.logger.info("model refreshing");
        for(String ag : allAgents){
            Car car = cars.get(ag);
            if (car==null)
                continue;
            car.step(time);
        }

        graphic.repaint();
    }

    public void calcSafety(String ag){

    }
}

