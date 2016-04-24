import java.util.ArrayList;

public class Scenario {
	boolean left;
	boolean right;
	boolean forward;
	boolean back;
	Scenario leftScen;
	Scenario rightScen;
	Scenario forwardScen;
	Scenario backScen;
	int elevator;
	int numberofEnemies;
	ArrayList<Interactible> interactibles;
	
	
	public Scenario(int progress, int floor, char action, Scenario prevScen){
		left = (Math.random()<.5);
		right = (Math.random()<.5);
		forward = (Math.random()<.5);
		back = (Math.random()<.5);
		boolean deadend;
		switch(action){
		case 'l':
			right = true;
			this.rightScen = prevScen;
			prevScen.leftScen = this;
			if(!(left || back || forward)){
				deadend = true;
			}
			break;
		case 'r':
			left = true;
			this.leftScen = prevScen;
			prevScen.rightScen = this;
			if(!(right || back || forward)){
				deadend = true;
			}
			break;
		case 'f':
			back = true;
			this.backScen = prevScen;
			prevScen.forwardScen = this;
			if(!(left || right || forward)){
				deadend = true;
			}
			break;
		case 'b':
			forward = true;
			this.forwardScen = prevScen;
			prevScen.backScen = this;
			if(!(left || back || right)){
				deadend = true;
			}
			break;
		}
		
		numberofEnemies = (int) (Math.random()*3);
		interactibles = new ArrayList<Interactible>();
		int numofInteracts = (int) (Math.random()*10);
		for(int i = 0; i<numofInteracts;i++){
			generateInteractible();
		}
		
	}
	
	public Scenario (int progress, int floor){
		left = true;
		forward = true;
		right = true;
		numberofEnemies = 0;
		interactibles = new ArrayList<Interactible>();
	}
	
	public ArrayList<Character> getActions(){
		ArrayList<Character> actions = new ArrayList<Character>();
		if(left) actions.add('l');
		if(right) actions.add('r');
		if(back) actions.add('b');
		if(forward) actions.add('f');
		int count = 0;
		for(Interactible c: interactibles){
			actions.add((char) count);
			count++;
		}
		return actions;
	}
	
	public void generateInteractible(){
		
	}
}