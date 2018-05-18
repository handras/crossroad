// Agent lamp in project Crossroad.mas2j

/* Initial beliefs and rules */
// no pedestrian waiting at the beginning
numPed(0).

/* Initial goals */

/* Plans */

// stop the trafic if enough pedestrian is present 
+moving(Name, Traj, Speed)[source(Name)]: 
	numPed(NUM) & NUM>2 
	<-
	.send(Name, tell, collision(lamp)).
	
// not enough pedestrian to stop the trafic
+moving(Name, Traj, speed)[source(Name)]
	<- true.
	
+newPed(Name) : true <-
	.print("new pedastrian arrived");
	!updateCnt.
	
+!updateCnt : true <-
	?numPed(X);
	-numPed(X);
	+numPed(X+1);
	.print("ped cnt ", X+1).
// Agent lamp in project Crossroad.mas2j

