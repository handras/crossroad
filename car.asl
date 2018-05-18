// Agent car in project Crossroad.mas2j

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+test(X) : true <- 
	-test(X);
	.my_name(Name);
	.print("im tested, my name is ", Name).
	
+start(Traj, Speed) <- 
	.my_name(Name);
	.print("im ", Name, " and start movig on traj ", Traj, " my speed is ", Speed);
	+myTraj(Traj);
	+mySpeed(Speed);
	+unsafe.
	
// if we sure that we are in danger so tell everybody our state
+unsafe <-
	!tellMyState.
	
+!tellMyState  <- 	
	.my_name(Name);
	?myTraj(Traj);	
	?mySpeed(Speed);
	.print("im ", Name, " and telling my traj: ", Traj, " and speed: ", Speed);
	.broadcast(tell, moving(Name, Traj, Speed)).
	
// when we receive a moving broadcast and we are at the crossroad
+moving(Name, Traj, Speed)[source(Name)] : arrived
	<-
	.print("checking safety");
	calcIfSafe(Name, Traj, Speed);
	!ensureSafety.
	
+moving(Name, Traj)[source(Name)] 
	<-
	true;
	-moving(Name, Traj)[source(Name)].
	//.print("im not arrived atthe crossroad").
	
+!ensureSafety: safe 
	<- true.
	
+!ensureSafety: unsafe & collision(Other)
	<- .print("im not safe, warn this: ", Other);
	?myTraj(Traj);	
	?mySpeed(Speed);
	.send(Other, tell, moving(Name, Traj, Speed)).	
	
+!ensureSafety: setSpeed(S) <-
	setSpeed(S);
	-setSpeed(S).
	
+collision(Name) <-
	.print("WARNING collision with: ", Name).
	
// Agent car in project Crossroad.mas2j

