// Agent car in project Crossroad.mas2j

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+test(X) : true <- 
	-test(X);
	.my_name(Name);
	.print("im tested, my name is ", Name).
	
+start(X, Y, SPEEDX, SPEEDY, GOALX, GOALY): true <-
	-start(X, Y, SPEEDX, SPEEDY, GOALX, GOALY);
	+pos(X, Y);
	+speed(SPEEDX, SPEEDY);
	+moveto(GOALX, GOALY);
	.send(lamp, tell, newPed);
	!tellMyState.
	
+!tellMyState : true <- 	
	.my_name(Name);
	?pos(X, Y);
	?speed(SPEEDX, SPEEDY);
	?moveto(GOALX, GOALY);
	.broadcast(tell, moving(Name, X, Y, SPEEDX, SPEEDY, GOALX, GOALY)).
	
+moving(Name, X, Y, SPEEDX, SPEEDY, GOALX, GOALY)[source(Name)] : pos(_, _)
	<-
	.print("checking safety")
	calcIfSafe.
	
+collision(Name) <-
	.print("WARNING collision with: ", Name);
	.my_name(ME);
	.send(Name, tell, collision(ME)).
	
// Agent car in project Crossroad.mas2j

