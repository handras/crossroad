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

    public void calcSafety(String ag, String other, int traj, float speed){
        String info = String.format("%s checks safety against %s on traj %d with speed: %f", ag, other, traj, speed);
        Crossroad.logger.info(info);
        Car me = cars.get(ag);
        Car othercar = cars.get(other);
        Trajectory.calcCollision(me, othercar);
    }

    public void calcSafeLamp(){
        for (Car car : cars.values()){
            if (!Trajectory.calcSafeLamp(car));
            return;
        }
        Crossroad.logger.info("lamp is safe");
        Crossroad.instance.informAgent("lamp", "safe");
    }

    public void avoidCollision(String ag, String other){
        Car me = cars.get(ag);
        Car othercar = cars.get(other);
        Trajectory.avoidCollision(me, othercar);
    }
}

