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
    }

    private Car(String name){}

    public Car clone(String name){
        Car c = new Car(name);
        c.x = x;
        c.y = y;
        c.speed = speed;
        c.trajectory = trajectory;
        return c;
    }
    
    public boolean Collide(Car b){
        return Math.sqrt((x-b.x)*(x-b.x) + (y-b.y)*(y-b.y)) < 4;
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
