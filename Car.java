import java.sql.Time;

public class Car {

    String name;
    // position of the car
    float x,y;
    // absolute speed of the car
    float speed;
    // where the car moves
    int trajectory;

    int arrivalTime;
    boolean arrived = false;

    public Car(String name, String startState){
        this.name = name;
        String[] a = startState.split(",");
        this.trajectory = Integer.parseInt(a[0]);
        this.speed = Float.parseFloat(a[1]);
        this.arrivalTime = Integer.parseInt(a[2]);

        Trajectory.initOnTraj(this, 0);
    }

    public void step(int steptime){
        try {
            if(CrExecControl.worldTime >= arrivalTime && !arrived){
                Trajectory.initOnTraj(this, 0);
                arrived = true;
                return;
            }
            if(arrived){
                Trajectory.stepOnTraj(this, steptime);
            }
        }catch (Exception e){}
    }
}
