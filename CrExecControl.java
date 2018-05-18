import jason.control.ExecutionControlGUI;
import jason.control.ExecutionControl;

public class CrExecControl extends jason.control.ExecutionControl {

    Crossroad env;
    int cycleNumber=0;

    static int TIME = 10;

    static int worldTime=-1000;

    public int getCycleNumber(){
        return cycleNumber;
    }

    protected void allAgsFinished(){
        if(env == null) {
            env = Crossroad.instance;
        }
        env.step(TIME);
        try {
            Thread.sleep(TIME);
        }catch (Exception e){}
//        Crossroad.logger.info("All agents finished");
        cycleNumber++;
        worldTime+=TIME;
        startNewCycle();
        infraControl.informAllAgsToPerformCycle(getCycleNumber());
    }
}
