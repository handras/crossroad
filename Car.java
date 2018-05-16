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
        this.sx = Float.parseFloat(a[2]);
        this.sy = Float.parseFloat(a[3]);
        this.gx = Float.parseFloat(a[4]);
        this.gy = Float.parseFloat(a[5]);
    }

    public void step(int steptime){
        try {
            this.x += this.sx * steptime / 1000f;
            this.y += this.sy * steptime / 1000f;
        }catch (Exception e){}
    }
}
