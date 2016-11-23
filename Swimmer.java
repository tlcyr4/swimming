package swimming;



class Swimmer {
   String name;
   double fiftyFly, fiftyBack, fiftyBreast,fiftyFree,  hundredFly, hundredBack,hundredBreast,  hundredFree;
   int maxRelays, relaysEntered;
   /*
    * 
    * I define a swimmer to have a name and a time for each fifty and each hundred of the four strokes
    */
   public Swimmer(String name, double fiftyFly,double fiftyBack,double fiftyBreast,double fiftyFree, double hundredFly,double hundredBack,double hundredBreast, double hundredFree, int maxRelays)
           {
               this.fiftyFly = fiftyFly;
               this.fiftyBack = fiftyBack;
               this.fiftyBreast = fiftyBreast;
               this.fiftyFree = fiftyFree;
               this.hundredFly = hundredFly;
               this.hundredBack = hundredBack;
               this.hundredBreast = hundredBreast;
               this.hundredFree = hundredFree;
               this.maxRelays = maxRelays;
               this.relaysEntered = 0;
               this.name = name;
           }
   /*
    * toString method returns the swimmer's name.
    */
   public String toString()
           {
               return name;
           }
   boolean isLegal()
   {
       return relaysEntered<=maxRelays;
   }
   /*
    * Check to see if the swimmer is already entered in the maximum number of relays.
    */
   boolean isFull()
   {
       return relaysEntered>1;
   }
}

