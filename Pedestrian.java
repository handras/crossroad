import java.sql.Time;

public class Pedestrian {

    String name;
    // position
    float x,y;
    // absolute speed
    float speed;
    // which way it goes
	// 1 - left to right side
	// 2, 3 - right to left side)
    int trajectory;
	
	int arrivalTime;
	boolean arrived = false;


    public Pedestrian(String name, String startState){
        this.name = name;
        String[] a = startState.split(",");
        this.trajectory = Integer.parseInt(a[0]);
        this.speed = Float.parseFloat(a[1]);
		this.arrivalTime = Integer.parseInt(a[2]);
    }

    public void step(int steptime){
        try {
            if(CrExecControl.worldTime >= arrivalTime && !arrived){
                Trajectory.initOnTraj(this, 0);
                arrived = true;
                return;
            }
            if(arrived & Crossroad.instance.lampIsGreen){
                Trajectory.stepOnTraj(this, steptime);
            }
        }catch (Exception e){}
    }
}
