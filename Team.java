package swimming;
import myUtils.MyArrayUtils;

class Team {

    String name;
    Swimmer[] roster;
    int projectedPoints = 0;
    Relay[] relays = new Relay[3];
    Swimmer[][] allCombos;
    Relay[][] legalCombos;
    Relay[][] optimalRelays;
    int optimalsCount;
    public Team(String name, Swimmer[] roster)
    {
        this.name = name;
        this.roster=roster;
        Object[][] allCombosObj = MyArrayUtils.allCombos(roster, 4); 
        allCombos = new Swimmer[allCombosObj.length][allCombosObj[0].length];
        for(int i = 0; i< allCombos.length; i++)
        {
        	for(int j = 0; j<allCombos[0].length;j++)
        	{
        		allCombos[i][j] = (Swimmer) allCombosObj[i][j];
        	}
        }
        legalCombos = this.findLegal();
        //System.out.println(MyArrayUtils.arrayToString(legalCombos)); //Testing
        optimalRelays = findOptimalRelays();
    }
    public String toString()
    {
    	return name+"\n*******************************\n\n";
    	//+MyArrayUtils.arrayToString(legalCombos);
    }
    public static boolean isLegal(Relay[] relays)
    {
        Swimmer[] tempRoster = new Swimmer[12];
        int filled = 0;
        for(Relay relay : relays)
        {
        	for(Swimmer swimmer : relay.roster)
        	{
        		if(!MyArrayUtils.has(tempRoster, swimmer)) 
        		{
        			tempRoster[filled] = swimmer;
        			filled++;
        		}
        	}
        }
        Swimmer[] roster = new Swimmer[filled];
        for(int i = 0; i< filled; i++) roster[i] = tempRoster[i];
        int relaysEntered = 0;
        for(int i = 0; i<roster.length ; i++)
        	{
        		for(Relay relay: relays)
        		{
        			for(Swimmer swimmer : relay.roster)
        			{
        				if(roster[i].equals(swimmer)) relaysEntered++;
        			}
        		}
        		if (relaysEntered>roster[i].maxRelays) return false;
        		//System.out.println(relaysEntered); //Testing
        		relaysEntered = 0;
        	}
        return true;
    }
    private Relay[][] findOptimalRelays()
    {
    	int tempProjectedPoints;
    	Relay[][] tempOptimalRelays = new Relay[allCombos.length*allCombos.length*allCombos.length][3]; //Array length is too long, will cut down later in program
    	//System.out.println(MyArrayUtils.arrayToString(allCombos)); //Testing
        optimalsCount = 0; //Need to keep a count of how many elements are actually used
        for(Relay[] set : legalCombos)
        {
        	tempProjectedPoints = 0; //reset team projected points before recalculating
            for(Relay relay : set) //Adding up the points of the relays
            {
                tempProjectedPoints+=relay.projectedPoints;
            }
            if(tempProjectedPoints==this.projectedPoints) 
            	//If it has the same projected points as the current leading set, add it to an array of leaders
            {
                tempOptimalRelays[optimalsCount] = set;
                optimalsCount++;
            }
            else if(tempProjectedPoints>this.projectedPoints) //New high-scoring set of relays
            {
                tempOptimalRelays = new Relay[tempOptimalRelays.length][3];//Clear the set of optimal relays
                optimalsCount = 0;
                tempOptimalRelays[optimalsCount] = set; // New leader becomes first element in cleared array
                optimalsCount++;
            }
        }
        Relay[][] optimalRelays = new Relay[optimalsCount][3];//new array of the correct, cut down length
        for(int i = 0;i<optimalsCount;i++) optimalRelays[i] = tempOptimalRelays[i];	//filling final array
        return optimalRelays;
    }
    private Relay[][] findLegal()
    {
    	Relay[] currentSet = new Relay[3];
    	Relay[][] temp = new Relay[(int)Math.pow(allCombos.length, 3)][3];
    	int tempCount = 0;
    	for(Swimmer[] med : allCombos)
    	{
    		for(Swimmer[] two : allCombos)
    		{
    			for(Swimmer[] four : allCombos)
    			{
    				currentSet[0] = new Medley(med);
                    currentSet[1] = new TwoFreelay(two);
                    currentSet[2] = new FourFreelay(four);
                    if(isLegal(currentSet)) 
                    {
                    	//temp[tempCount] = currentSet;
                    	for(int i = 0; i<temp[tempCount].length; i++)
                    	{
                    		temp[tempCount][i] = currentSet[i];
                    	}
                    	tempCount++;
                    }
    			}
    		}
    	}

    	Relay[][] total = new Relay[tempCount][3];
    	for(int i = 0; i< tempCount ; i++) total[i] = temp[i];
    	return total;
    }
}
