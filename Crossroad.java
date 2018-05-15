import java.util.*;
import java.util.logging.Logger;

import jason.asSyntax.Literal;
import jason.asSyntax.Term;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Logger;

public class Crossroad extends Environment {
	static Logger logger = Logger.getLogger(Crossroad.class.getName());
	
	
    public static final Literal test = Literal.parseLiteral("test(t)");

	private String[] allAgents = new String[]{"car1","car2","car3","car4","car5","car6","emergency","lamp"};
	
	private Literal getAgentPos(String agentName){
		return Literal.parseLiteral("pos("+agentName+ "," + 1 + "," + 1 + ")");
	}

	public Map<String, Car> cars;
	
	@Override
    public void init(String[] args) {
		cars = new HashMap();

		TestCase testCase = new TestCase(1);
		for (String ag : allAgents){
			String startState = testCase.getAgentStartState(ag);
			if (startState != null) {
				cars.put(ag, new Car(startState));
				addPercept(ag, Literal.parseLiteral("start("+startState+")"));
			}
		}
		//set up model
		CrossroadGraphic gr = new CrossroadGraphic();
		gr.addCars(cars);
    }
	
	@Override
    public boolean executeAction(String ag, Structure action) {
        logger.info(ag+" doing: "+ action);
		if (action.getFunctor().equals("calcIfSafe")) {
			Car c1 = cars.get(ag);
			boolean safe = true;
			Car c2;
			for(String otherCarName : allAgents){
				if(otherCarName.equals(ag)) continue;
				c2 = cars.get(otherCarName);
				try {
					float tx = c1.x - c2.x / (c2.sx - c1.sx);
					float ty = c1.y - c2.y / (c2.sy - c1.sy);
					logger.info("tx: "+tx+" ty: "+ty);

					if(tx>0 || ty>0){
						addPercept(ag, Literal.parseLiteral("collision("+otherCarName+")"));
						safe = false;
					}
				} catch (NullPointerException e){
					continue;
				}
			}
			if (safe){
				addPercept(ag, Literal.parseLiteral("safe"));
			}
		}
		return true;
	}

	class Car{
		String name;
		float x,y, sx,sy, gx,gy;

		Car(String startState){
			String[] a = startState.split(",");
			this.x  = Float.parseFloat(a[0]);
			this.y  = Float.parseFloat(a[1]);
			this.sx = Float.parseFloat(a[2]);
			this.sy = Float.parseFloat(a[3]);
			this.gx = Float.parseFloat(a[4]);
			this.gy = Float.parseFloat(a[5]);
		}

		Car(float x,float y, float sx,float sy, float gx,float gy){
			this.x = x;
			this.y = y;
			this.sx = sx;
			this.sy = sy;
			this.gx = gx;
			this.gy = gy;
		}
	}
}
