package swimming;

class TwoFreelay extends Relay {
    /*
     * Nothing new to add to constructor
     */
	TwoFreelay(Swimmer[] roster){
		super(roster);
	}
	/*
	 * Defines the name of the relay
	 */
	protected String name(){
		return "200-yard Freestyle Relay";
	}
	/*
	 * Defines the time to be used as the fifty free time
	 */
	protected double timeOf(Swimmer swimmer){
		return swimmer.fiftyFree;
	}
}
