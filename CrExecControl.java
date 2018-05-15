import jason.control.ExecutionControlGUI;
import jason.control.ExecutionControl;

public class CrExecControl extends jason.control.ExecutionControl {

    Crossroad env;
    int cycleNumber=0;

    int TIME = 75;

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
        startNewCycle();
        infraControl.informAllAgsToPerformCycle(cycleNumber);
    }
}