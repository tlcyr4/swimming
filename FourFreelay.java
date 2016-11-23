package swimming;

class FourFreelay extends Relay{

	FourFreelay(Swimmer[] roster){
		super(roster);
	}
	protected String name(){
		return "400-yard Freestyle Relay";
	}
	protected double timeOf(Swimmer swimmer){
		return swimmer.hundredFree;
	}
}
