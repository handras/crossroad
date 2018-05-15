import java.sql.Time;

public class Car {

    String name;
    float x,y, sx,sy, gx,gy;

    public Car(String startState){
        String[] a = startState.split(",");
        this.x  = Float.parseFloat(a[0]);
        this.y  = Float.parseFloat(a[1]);
        this.sx = Float.parseFloat(a[2]);
        this.sy = Float.parseFloat(a[3]);
        this.gx = Float.parseFloat(a[4]);
        this.gy = Float.parseFloat(a[5]);
    }

    public Car(float x,float y, float sx,float sy, float gx,float gy){
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        this.gx = gx;
        this.gy = gy;
    }

    public void step(int steptime){
        try {
            this.x += this.sx * steptime / 1000f;
            this.y += this.sy * steptime / 1000f;
        }catch (Exception e){}
    }
}
