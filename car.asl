// Agent car in project Crossroad.mas2j

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+test(X) : true <- 
	-test(X);
	.my_name(Name);
	.print("im tested, my name is ", Name).
	
+start(Traj) <- 
	.my_name(Name);
	.print("im ", Name, " and start movig on traj ", Traj);
	+myTraj(Traj);
	+unsafe.
	
// if we sure that we are in danger tell everybody our state
+unsafe <-
	!tellMyState.
	
+!tellMyState  <- 	
	.my_name(Name);
	?myTraj(Traj);
	.print("im ", Name, " and telling my traj: ", Traj);
	.broadcast(tell, moving(Name, Traj)).
	
// when we receive a moving broadcast and we are at the crossroad
+moving(Name, Traj)[source(Name)] : arrived
	<-
	.print("checking safety");
	calcIfSafe.
	
+moving(Name, Traj)[source(Name)] 
	<-
	.print("im not arrived").
	
+collision(Name) <-
	.print("WARNING collision with: ", Name);
	.my_name(ME);
	.send(Name, tell, collision(ME)).
	
// Agent car in project Crossroad.mas2j

