// Agent lamp in project Crossroad.mas2j

/* Initial beliefs and rules */
// no pedestrian waiting at the beginning
numPed(0).

/* Initial goals */

/* Plans */

// stop the trafic if enough pedestrian is present 
+moving(Name, X, Y, SPEEDX, SPEEDY, GOALX, GOALY)[source(Name)]: 
	numPed(NUM) & NUM>2 
	<-
	.send(Name, tell, collision(lamp)).
	
// not enough pedestrian to stop the trafic
+moving(Name, X, Y, SPEEDX, SPEEDY, GOALX, GOALY)[source(Name)]
	<- true.
	
+newPed : true <-
	.print("new pedastrian arrived");
	!updateCnt.
	
+!updateCnt : true <-
	?numPed(X);
	-numPed(X);
	+numPed(X+1);
	.print("ped cnt ", X+1);
	-newPed.
// Agent lamp in project Crossroad.mas2j

