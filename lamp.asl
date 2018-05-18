// Agent lamp in project Crossroad.mas2j

/* Initial beliefs and rules */
// no pedestrian waiting at the beginning
numPed(0).

/* Initial goals */

/* Plans */

// stop the trafic if enough pedestrian is present 
+moving(Name, Traj, Speed)[source(Name)]: numPed(NUM) & NUM>2 
	<-
	.send(Name, tell, collision(lamp)).
	
// not enough pedestrian to stop the trafic
+moving(Name, Traj, Speed)[source(Name)]
	<- true.
	
+newPed(Name) : true <-
	.print(Name, " pedestrian arrived");
	!incrCnt.
	
-newPed(Name) : true <-
	.print(Name, " new pedestrian has crossed");
	!decrCnt.
	
+!incrCnt : true <-
	?numPed(X);
	-numPed(X);
	+numPed(X+1);
	.print("ped cnt ", X+1).
	
+!decrCnt : true <-
	?numPed(X);
	-numPed(X);
	+numPed(X-1);
	.print("ped cnt ", X+1).	
	
+numPed(3) <-
	!stopTrafic.
	
+!stopTrafic <-
	calcSafeLamp;
	if(safe){signGreen}else{!stopTrafic}.
	
// Agent lamp in project Crossroad.mas2j

