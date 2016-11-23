package swimming;

class Medley extends Relay{
	
	Medley(Swimmer[] roster){
		super(roster);
	}
	protected String name(){
		return "200-yard Medley Relay";
	}
	protected double timeOf(Swimmer swimmer){
		if (swimmer.equals(roster[0])) return swimmer.fiftyBack;
		else if (swimmer.equals(roster[1])) return swimmer.fiftyBreast;
		else if (swimmer.equals(roster[2])) return swimmer.fiftyFly;
		else if (swimmer.equals(roster[3])) return swimmer.fiftyFree;
		else return 0;
	}
}