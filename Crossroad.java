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

    CrossroadModel model;
    CrossroadGraphic gr;

    @Override
    public void init(String[] args) {

		//set up model
        CrossroadModel model = new CrossroadModel(1, this);
		CrossroadGraphic gr = new CrossroadGraphic();
		gr.setModel(model);
    }

    public void informAgent(String ag, String msg){
        addPercept(ag, Literal.parseLiteral(msg));
    }
	
	@Override
    public boolean executeAction(String ag, Structure action) {
        logger.info(ag+" doing: "+ action);
		if (action.getFunctor().equals("calcIfSafe")) {
		    model.calcSafety(ag);
		}
		return true;
	}


}
