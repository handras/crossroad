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
        this.trajectory = Integer.parseInt(a[0]);
        this.speed = Float.parseFloat(a[1]);

        Crossroad.logger.info(String.format("car before x:%f, y:%f", x, y));
        Trajectory.initOnTraj(this);
        Crossroad.logger.info(String.format("car after x:%f, y:%f", x, y));
    }

    public void step(int steptime){
        try {
            Trajectory.stepOnTraj(this);
        }catch (Exception e){}
    }
}
