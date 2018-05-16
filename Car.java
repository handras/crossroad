import java.sql.Time;

public class Car {

    String name;
    // position of the car
    float x,y;
    // absolute speed of the car
    float speed;
    // where the car moves
    int trajectory;

    public Car(String startState){
        String[] a = startState.split(",");
        this.x  = Float.parseFloat(a[0]);
        this.y  = Float.parseFloat(a[1]);
    }

    public void step(int steptime){
        try {
            Trajectory.stepOnTraj(this);
        }catch (Exception e){}
    }
}
