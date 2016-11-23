package swimming;
//import myUtils.MyArrayUtils;

import myUtils.MyArrayUtils;

public class SwimTeamManager {

	static Swimmer Tigar = new Swimmer("Tigar",27.0,26.74,31.50,23.29,56.12,55.85,66.96,50.07,2);
	static Swimmer Tindar = new Swimmer("Tindar", 26.04, 29.1, 28.2,23.25,57.0,57.0,59.2,52.9,2);
	static Swimmer Marquardt = new Swimmer("Matt Q", 23.0,23.0,29.0,21.5,50.62,49.62,62.0,46.7,2);
	static Swimmer Henry = new Swimmer("Henry",23.5,25.0,32.0,22.0,52.0,52.0,68.0,47.5,2);
	static Swimmer Tim = new Swimmer("Tim", 22.9,24.0,30.0,21.1,49.9,51.5,62.0,46.2,2);
	static Swimmer Greg = new Swimmer("Greg", 23.0,23.9,33.0,21.8,52.0,53.0,70.0,47.0,2);
	static Swimmer[] myRoster = {Tigar,Tindar, Marquardt, Henry, Tim, Greg};
	static Team SevenHills; 
	public static void main(String[] args) {
		SevenHills = new Team("Seven Hills Stingers", myRoster);
		//System.out.println(MyArrayUtils.arrayToString(MyArrayUtils.allCombos(myRoster, 4))); //Testing
		System.out.println(SevenHills);
		System.out.println(MyArrayUtils.arrayToString(SevenHills.legalCombos));
		//SevenHills.relays = System.out.println(SevenHills.isLegal());
	}
	

}

/*
 * Things I should make this do:
 * 
 * Calculate State Qualifiers
 * Seed Out State Meet
 * Plan MVC's
 * Do Entries
 * 
 * 
 * 
 */
