import java.util.ArrayList;

/**
 * Created by Peter on 1/24/2015.
 */
public class MonoHybrid {
    int maxGenerations;
    private MonoCreature[] seeds = new MonoCreature[2];
    ArrayList<MonoCreature> bufferList = new ArrayList<MonoCreature>();
    ArrayList<ArrayList<MonoCreature>> totalList = new ArrayList<ArrayList<MonoCreature>>(100);                         //just a sample value
    MonoCreature[] creatureArray = new MonoCreature[100];
    private int totalCreaturesTillNow = 0;
    public MonoHybrid(int generations) {
        this.maxGenerations = generations;
    }
    protected void seedParents(MonoCreature mc1, MonoCreature mc2) {                                                    //initializes the two seed creatures
        seeds[0] = mc1;
        seeds[1] = mc2;
        addToBuffer(seeds[0]);
        addToBuffer(seeds[1]);
        dumpBufferToTotalList(0);
        this.fuseTwo(seeds[0], seeds[1]);
        dumpBufferToTotalList(1);
    }
    protected void dumpBufferToTotalList(int whichIndex) {                                                              //puts the complete content of the buffer to totalList
        totalList.add(whichIndex, bufferList);
        System.out.println("Total list's this index's size: " + totalList.get(whichIndex).size());
        /*for (int j = 0; j < bufferList.size(); j++) {
            bufferList.remove(j);
        }*/
        //bufferList.clear();

    }
    private void addToBuffer(MonoCreature aCreature) {                                                                  //adds the given creature to buffer
        bufferList.add(aCreature);
    }

    private void addToBuffer(MonoCreature creatureOne, MonoCreature creatureTwo, MonoCreature creatureThree, MonoCreature creatureFour) {   //possibly useless
        bufferList.add(creatureOne);
        bufferList.add(creatureTwo);
        bufferList.add(creatureThree);
        bufferList.add(creatureFour);
    }
    private void fuseTwo(MonoCreature parentOne, MonoCreature parentTwo) {                                              //fuses two parents to create four off-springs
        /**
         * @javadoc
         * Fuses two parents' gametes to obtain 4 off-springs.
         * Working:
         *      Takes two MonoCreatures as arguments
         *      Creates two ArrayLists, one for holding the gametes, other for the off-springs (ArrayList here contributed by @flamingrush2)
         *      Then initializes the gametes ArrayList by enquiring about each gamete through the getGamete(int whichGamete) method
         *      Now four creatures are created via these four gametes, and simultaneously added to the offSpring ArrayList
         *      This ArrayList is then sent to buffer ArrayList
         *      Both parent MonoCreatures get to add each other in their respective fused lists, to prevent future fuses
         *      total creature count is increased by 4 each run
         * */
        ArrayList<String> gametes = new ArrayList<String>(4);
        ArrayList<MonoCreature> offSpring = new ArrayList<MonoCreature>(4);
        for (int counter = 0; counter < 4; counter++) {
            /*switch (counter) {                                                                                        //this was buggy, can't be
                case 0:
                case 1:
                    gametes.add(counter, parentOne.getGamete(counter));
                    break;
                case 2:
                case 3:
                    gametes.add(counter, parentTwo.getGamete(counter - 2));
                    break;
                default:
                    System.err.println("Error in Program.");
                    throw new IllegalArgumentException("Error in Program.");
            }*/
            if ((counter == 0) || (counter == 1)) {
                System.out.println("Counter is less than 2");
                gametes.add(parentOne.getGamete(counter));
            } else if ((counter == 2) || (counter == 3)) {
                System.out.println("Counter is more than 2");
                gametes.add(parentTwo.getGamete(counter - 2));
            }
            System.out.println(" We have " + gametes.get(counter) + " at " + counter);
        }
        MonoCreature creatureOne = new MonoCreature(gametes.get(0), gametes.get(2));
        MonoCreature creatureTwo = new MonoCreature(gametes.get(0), gametes.get(3));
        MonoCreature creatureThree = new MonoCreature(gametes.get(1), gametes.get(2));
        MonoCreature creatureFour = new MonoCreature(gametes.get(1), gametes.get(3));
        offSpring.add(creatureOne);
        offSpring.add(creatureTwo);
        offSpring.add(creatureThree);
        offSpring.add(creatureFour);
        /*offSpring.add(new MonoCreature(gametes.get(0), gametes.get(2)));
        offSpring.add(new MonoCreature(gametes.get(0), gametes.get(3)));
        offSpring.add(new MonoCreature(gametes.get(1), gametes.get(2)));
        offSpring.add(new MonoCreature(gametes.get(1), gametes.get(3)));*/
        for (int i = 0; i < 4; i++) {
            addToBuffer(offSpring.get(i));
        }
        parentOne.fusedWith(parentTwo);
        parentTwo.fusedWith(parentOne);
        totalCreaturesTillNow += 4;
        System.out.println("Total creatures till now: " + totalCreaturesTillNow);
    }

    protected void generate() {
        /* @TODO: THE BUG IS IN HERE!*/
        /**
         * What does this bug do?
         * Due to something, the lastGenCreatures.size() is returning 0. Strange.
         * The solution: the clear method on the buffer somehow also clears lastGenCreatures. Need to solve this.
         * Possibly create a new method for clearing the buffer.
         * */
        /**
         * @javadoc
         * This method is where the real simulation takes place.
         * generationCount is the int which keeps on increasing each iteration.
         * int f takes the maximum generation count initialized by the constructor.
         * An ArrayList of MonoCreatures, which contains the creatures from the last generation. This is done in each iteration.
         * The last generation's length is initialized, two other ints to hold the position for creatures
         * Enter the L1 creature initializer. Here the creatures are taken from the ArrayList (above)
         * Now the L2 creature gets initialized. Same as above.
         * If both of them are not the same, and have not fused previously, then they will fuse.
         */
        int generationCount;
        int f = this.maxGenerations;
        //ArrayList<MonoCreature> lastGenCreatures;
        for (generationCount = 2; generationCount <= f; generationCount++) {
            System.out.println("Entered inside the looper for creating generation " + generationCount);                 // <-- debug message
            ArrayList<MonoCreature> lastGenCreatures = totalList.get(generationCount - 1);                              //totalList is an AL of ALs. So this returns an AL.
            int totalLengthOfLastGen = lastGenCreatures.size();                                                         //this is 0? Why?
            System.out.println("Last gen's size: " + totalLengthOfLastGen);
            int creatureOneLoc, creatureTwoLoc;
            MonoCreature creatureOne, creatureTwo;
            for (creatureOneLoc = 0; creatureOneLoc < totalLengthOfLastGen; creatureOneLoc++) {
                System.out.print("For creatureOne at " + creatureOneLoc);
                creatureOne = lastGenCreatures.get(creatureOneLoc);
                for (creatureTwoLoc = 0; creatureTwoLoc < totalLengthOfLastGen; creatureTwoLoc++) {
                    System.out.println(" and creatureTwo at " + creatureTwoLoc);
                    creatureTwo = lastGenCreatures.get(creatureTwoLoc);
                    if ((creatureOne.hasNotFused(creatureTwo)) && (creatureOne != creatureTwo)) {
                        System.out.println("Both the creatures haven't fused previously, and are not the same");        // <-- debug message
                        this.fuseTwo(creatureOne, creatureTwo);
                    } //ends if
                } //ends the loop for second level creatures
            } //ends the loop for first level creatures
            this.dumpBufferToTotalList(generationCount);
        } //ends total loop, the complete simulation
    }
}
