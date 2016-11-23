package swimming;

import myUtils.MyArrayUtils;

abstract class Relay {

    protected double time = -1; // Default: a number that won't come up any other way
    protected String name = this.name(); // Calls an abstract method that acts as an accessor in child classes
    protected Swimmer[] roster = new Swimmer[4]; // Each relay has four swimmers in it
    protected boolean legal = true; //Checks to see if the relay is currently legal
    protected int projectedPoints = 0;
    Relay() //Default constructor, nothing is initialized
    {
        
    }
    /*
     * Create relay based on an array of swimmers
     * Takes the first four swimmers in the array to make sure it doesnt take too many
     * Throws an exception if too few swimmers are given
     */
    Relay(Swimmer[] initRoster)
    {
        if(initRoster.length < 4) {
            throw new IllegalArgumentException("Not full relay");
         }
        for(int i = 0; i < 4; i++) {
         roster[i] = initRoster[i];
            initRoster[i].relaysEntered++;
        }
        this.roster = this.findFastestOrder(roster);
        time = this.calculateTime(roster);
    }
    
    
    /*
     * Returns a string including the relay's name, time, and roster
     * 
     * Might try finding total length ahead of time to avoid performance
     * issues with concatenation
     */
    public String toString(){
        String str = "";
        if (time > 0) {
            str = "\n" + name + ":  " + (int) (time / 60) + ":" + (int) (time % 60 / 10);
            str += (int) (time % 60 % 10) + "." + (int) (time % 1) * 100 + "\n---------------------\n";
        }
        for (Swimmer swimmer : roster) 
            str += swimmer.name + "\n";
        return str;
    }
     /*
     * Implemented as a getter in child classes for each class's name String
     */
    protected abstract String name();
    /*
     * Still needs to be implemented
     */
    public boolean isLegal()
    {
        for(Swimmer swimmer : roster)
        {
            if (!swimmer.isLegal()) 
                return false;
        }
        return true;
    }
    
    /*
     * Adds up the times of the four legs and returns it
     */
    
    protected double calculateTime(Swimmer[] roster){
        double calcTime = 0;
        for (Swimmer swimmer : roster) 
            calcTime += this.timeOf(swimmer);
        return calcTime;
    }
    /*
     * Defined differently for each subclass so that different relays grab the right times from their 
     * swimmers
     */
    protected abstract double timeOf(Swimmer swimmer);
    /*
     * Returns the correct order for the relay.  To be used on medley relays.  Maybe should be moved to 
     * that class
     */
    //Working on general method for finding all orders
    //Need to work out casting issue
    
    // Should make better version that has better performance
    protected Swimmer[] findFastestOrder(Swimmer[] roster)
    {
        Swimmer[] fastestOrder = roster;
        double fastestTime = this.calculateTime(roster);
        // System.out.println(MyArrayUtils.arrayToString(MyArrayUtils.findAllOrders(roster)));//Testing
        
        // Want to make a method out of this section:
        Object[][] allOrdersObj = MyArrayUtils.findAllOrders(roster); 
        Swimmer[][] options = new Swimmer[allOrdersObj.length][allOrdersObj[0].length];
        for(int i = 0; i < options.length; i++)
        {
         for(int j = 0; j < options[0].length; j++)
         {
          options[i][j] = (Swimmer) allOrdersObj[i][j];
         }
        }
        // Useful for casting arrays
     for (Swimmer[] order : options)
     {
      if (this.calculateTime(order)<fastestTime){
                fastestOrder = order;
                fastestTime = this.calculateTime(order);
      }
     }
     return fastestOrder;
    }
    /*
     * Takes in an array and an element from that array and removes the first instance of that swimmer
     * Then returns the resulting array
     * 
     * Should be generalized and moved to MyArrayUtils
     */
    protected static Swimmer[] remove(Swimmer[] array, Swimmer swimmer){
        Swimmer[] newArray = new Swimmer[array.length-1];
        int count = 0;
        for(Swimmer swim : array){
            if(!swimmer.equals(swim)){
                newArray[count] = swim;
                count++;
            }
        }
        return newArray;
    }
    /*
     * Adds a swimmer to a specific spot in the relay
     * 
     * May be obsolete
     */

    /*protected void removeAll()
    {
     for(Swimmer swimmer : roster) swimmer.relaysEntered--;
    }*/
    
    
    protected int calculatePoints(double[] competition)
    {
     /*
      * Plan for How to Implement:
      * 
      * Go to Swimmeet.com Rankings by District, put all 4 districts into one array of times
      * Find spot in array of times
      * Map spot to point value 
      */
     int[] relayPlaceToPoints = {40,34,32,30,28,26,24,22,18,14,12,10,8,6,4,2,0};
     int i = 0;
     while(i<competition.length && i< relayPlaceToPoints.length && time > competition[i])
     {
      i++;
     }
     return relayPlaceToPoints[i];
     
    }
    
}

