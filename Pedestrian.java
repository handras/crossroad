import java.sql.Time;

public class Pedestrian {

    // position
    float x,y;
    // absolute speed
    float speed;
    // which way it goes
	// 1 - left to right side
	// 2, 3 - right to left side)
    int trajectory;
	
	int arrivalTime;

    public Pedestrian(String startState){
        String[] a = startState.split(",");
        this.trajectory = Integer.parseInt(a[0]);
        this.speed = Float.parseFloat(a[1]);
		this.arrivalTime = Integer.parseInt(a[2]);

        Crossroad.logger.info(String.format("pedestrian before x:%f, y:%f", x, y));
        Trajectory.initOnTraj(this, 0);
        Crossroad.logger.info(String.format("pedestrian after x:%f, y:%f", x, y));
    }

    public void step(int steptime){
        try {
            Trajectory.stepOnTraj(this, steptime);
        }catch (Exception e){}
    }
}
