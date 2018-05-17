public class Trajectory {

    //these trajectories are defined on the keresztezode_irany.png

    // traj 1 start, turn and goal points
    static float t1sx = 0f;
    static float t1sy = 4.5f;
    static float t1tx = 23f;
    static float t1ty = 4.5f;
    static float t1gx = 46f;
    static float t1gy = 4.5f;

    static float t2sx = 46f;
    static float t2sy = 1.5f;
    static float t2tx = 23f;
    static float t2ty = 1.5f;
    static float t2gx = 0f;
    static float t2gy = 1.5f;

    static float t3sx = 0f;
    static float t3sy = 4.5f;
    static float t3tx = 21.5f;
    static float t3ty = 4.5f;
    static float t3gx = 21.5f;
    static float t3gy = 26f;

    static float t4sx = 24.5f;
    static float t4sy = 26f;
    static float t4tx = 24.5f;
    static float t4ty = 4.5f;
    static float t4gx = 46f;
    static float t4gy = 4.5f;

    static float t5sx = 46f;
    static float t5sy = 1.5f;
    static float t5tx = 21.5f;
    static float t5ty = 1.5f;
    static float t5gx = 21.5f;
    static float t5gy = 26f;

    static float t6sx = 24.5f;
    static float t6sy = 26f;
    static float t6tx = 24.5f;
    static float t6ty = 1.5f;
    static float t6gx = 0f;
    static float t6gy = 1.5f;
	
	static float t7sx = 17f;
	static float t7sy = 7f;
	
	static float t8sx = 28f;
	static float t8sy = 9f;
	
	static float t9sx = 27f;
	static float t9sy = 8f;
    
    static float[][] points = new float[][]{
            new float[]{t1sx, t1sy, t1tx, t1ty, t1gx, t1gy},
            new float[]{t2sx, t2sy, t2tx, t2ty, t2gx, t2gy},
            new float[]{t3sx, t3sy, t3tx, t3ty, t3gx, t3gy},
            new float[]{t4sx, t4sy, t4tx, t4ty, t4gx, t4gy},
            new float[]{t5sx, t5sy, t5tx, t5ty, t5gx, t5gy},
            new float[]{t6sx, t6sy, t6tx, t6ty, t6gx, t6gy},
			new float[]{t7sx, t7sy},
			new float[]{t8sx, t8sy},
			new float[]{t9sx, t9sy}
    };

    public static void initOnTraj(Car car, int steptime){
        float[] trajpoints = points[car.trajectory-1];
        car.x = trajpoints[0];
        car.y = trajpoints[1];

        Crossroad.logger.info(String.format("traj after x:%f, y:%f", car.x, car.y));
    }
	
	public static void initOnTraj(Pedestrian ped, int steptime){
		//if(ped.arrivalTime >= steptime){
			float[] trajpoints = points[ped.trajectory+5];
			ped.x = trajpoints[0];
			ped.y = trajpoints[1];	

			Crossroad.logger.info(String.format("traj after x:%f, y:%f", ped.x, ped.y));
		//}
        
    }
	
	public static void stepOnTraj(Pedestrian ped, int steptime){
			float[] trajpoints = points[ped.trajectory+5];
			float sx = trajpoints[0];
			
			float animatedSpeed = ped.speed * steptime/1000f;
			
			if(ped.trajectory == 1){
				ped.x += animatedSpeed;
			}
			if(ped.trajectory == 2){
				ped.x -= animatedSpeed;
			}
			if(ped.trajectory == 3){
				ped.x -= animatedSpeed;
			}
	}
    
    public static void stepOnTraj(Car car, int steptime){
        float[] trajpoints = points[car.trajectory-1];
        float sx = trajpoints[0];
        float sy = trajpoints[1];
        float tx = trajpoints[2];
        float ty = trajpoints[3];
        float gx = trajpoints[4];
        float gy = trajpoints[5];

		float animatedSpeed = car.speed * steptime/1000f;
		
		if(car.trajectory == 1){
			car.x += animatedSpeed;
		}
		if(car.trajectory == 2){
			car.x -= animatedSpeed;
		}
		if(car.trajectory == 3){
			if(car.x > t3tx){
				car.y += animatedSpeed;
			} else {
				car.x += animatedSpeed;
			}
		}
		if(car.trajectory == 4){
			if(car.y < t4ty){
				car.x += animatedSpeed;
			} else {
				car.y -= animatedSpeed;
			}
		}
		if(car.trajectory == 5){
			if(car.x < t5tx){
				car.y += animatedSpeed;
			} else {
				car.x -= animatedSpeed;
			}
		}
		if(car.trajectory == 6){
			if(car.y < t6ty){
				car.x -= animatedSpeed;
			} else {
				car.y -= animatedSpeed;
			}
		}
		
    }
}
