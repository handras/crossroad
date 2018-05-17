import java.util.HashMap;
import java.util.Map;

public class CrossroadModel {

    private String[] allAgents = new String[]{"car1","car2","car3","car4","car5","car6","emergency","lamp"};
	private String[] allPeds = new String[]{"ped1","ped2","ped3"};
    public Map<String, Car> cars;
	public Map<String, Pedestrian> peds;
    Crossroad env;
    CrossroadGraphic graphic;

    public CrossroadModel(int test, Crossroad env) {
        this.env=env;
        cars = new HashMap();
		peds = new HashMap();

        TestCase testCase = new TestCase(test);
        for (String ag : allAgents) {
            String startState = testCase.getAgentStartState(ag);
            if (startState != null) {
                cars.put(ag, new Car(ag, startState));
            }
        }
		
		for (String ag : allPeds) {
            String startState = testCase.getAgentStartState(ag);
            if (startState != null) {
                peds.put(ag, new Pedestrian(ag, startState));
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
		
		for(String ag : allPeds){
            Pedestrian ped = peds.get(ag);
            if (ped==null)
                continue;
            ped.step(time);
        }

        graphic.repaint();
    }

    public void calcSafety(String ag){

    }
}

