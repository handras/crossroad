// Agent car in project Crossroad.mas2j

/* Initial beliefs and rules */
//first trajactory has priority over the second
prio(1,4).
prio(1,5).
prio(1,6). 
prio(2,6).
prio(3,5).
prio(5,6).

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
	
+collision(Name, Traj) : myTraj(MyTraj) & prio(MyTraj, Traj)<-
	.print("WARNING: ", Name, " should slow down").
	
+collision(Name, Traj) : myTraj(MyTraj)<-
	.print("WARNING: i should let pass ", Name);
	avoidcollision(Name).
	
// Agent car in project Crossroad.mas2j

