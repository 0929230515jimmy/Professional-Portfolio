public class Rabbit extends Animal {
	private int Currentdirection; 
    public Rabbit(Model model, int row, int column) {
        super(model, row, column); 
    	Currentdirection = Model.STAY;
    }
    
    int decideMove() {

    	 for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) { 
    		 if(look(i) == Model.FOX) {
    			 	if(canMove(Model.turn(i, 5))) {
    			 		return Model.turn(i, 5);
    			 	}
    			 	else if(canMove(Model.turn(i,3))) {
    			 		return Model.turn(i,3);
    			 	}
    			 	else if(canMove(Model.turn(i, 6))) {
    			 		return Model.turn(i,6);
    			 	}
    			 	else if(canMove(Model.turn(i, 2))) {
    			 		return Model.turn(i,2);
    			 	}
    			 	else if(canMove(Model.turn(i, 7))) {
    			 		return Model.turn(i,7);
    			 	}
    			 	else if(canMove(Model.turn(i, 1))) {
    			 		return Model.turn(i,1);
    			 	}
    		 	} 
   	 }
    	return
    		Model.STAY;

    }

}



