import java.util.ArrayList;

/**
 * Created by Peter on 1/24/2015.
 */
public class MonoHybrid {
    int maxGenerations;
    private MonoCreature[] seeds = new MonoCreature[2];
    ArrayList<MonoCreature> bufferList = new ArrayList<MonoCreature>();
    ArrayList<ArrayList<MonoCreature>> totalList = new ArrayList<ArrayList<MonoCreature>>();
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
        /*ArrayList<MonoCreature> mcal = new ArrayList<MonoCreature>(bufferList);
        for (int i = 0; i < bufferList.size(); i++) {
            mcal.add(i, bufferList.get(i));
        }*/
        //Collections.copy(mcal, bufferList);
        totalList.add(whichIndex, (ArrayList<MonoCreature>) bufferList.clone());                                        // @TODO: fix this
        System.out.println(" In dump method \nTotal list's this index's size: " + totalList.get(whichIndex).size());
        /*for (int j = 0; j < bufferList.size(); j++) {
            bufferList.remove(j);
        }*/
        bufferList.clear();
        System.out.println("    Cleared the buffer\n    And now totalList's this index's size: " + totalList.get(whichIndex).size());
        System.out.println("    The buffer's size is " + bufferList.size());
        System.out.println("    totalList's size is " + totalList.size() + "\n");
    }
    private void addToBuffer(MonoCreature aCreature) {                                                                  //adds the given creature to buffer
        bufferList.add(aCreature);
    }

    private ArrayList<MonoCreature> getGeneration(int generation) {
        return this.totalList.get(generation);
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
         *      Creates two arrays, one for holding the gametes, other for the off-springs
         *      Then initializes the gametes array by enquiring about each gamete through the getGamete(int whichGamete) method
         *      Now four creatures are created via these four gametes, and simultaneously added to the offSpring array
         *      This array is then sent to buffer ArrayList
         *      Both parent MonoCreatures get to add each other in their respective fused lists, to prevent future fuses
         *      total creature count is increased by 4 each run
         * */
        String[] gametes = new String[4];
        MonoCreature[] offSprings = new MonoCreature[4];
        for (int counter = 0; counter < 4; counter++) {
            if ((counter == 0) || (counter == 1)) {
                System.out.println("Counter is less than 2");
                gametes[counter] = parentOne.getGamete(counter);
            } else if ((counter == 2) || (counter == 3)) {
                System.out.println("Counter is more than 2");
                gametes[counter] = parentTwo.getGamete(counter - 2);
            }
            System.out.println(" We have " + gametes[counter] + " at " + counter);
        }
        offSprings[0] = new MonoCreature(gametes[0], gametes[2]);
        offSprings[1] = new MonoCreature(gametes[0], gametes[3]);
        offSprings[2] = new MonoCreature(gametes[1], gametes[2]);
        offSprings[3] = new MonoCreature(gametes[1], gametes[3]);
        for (int i = 0; i < 4; i++) {
            addToBuffer(offSprings[i]);
        }
        parentOne.fusedWith(parentTwo);
        parentTwo.fusedWith(parentOne);
        totalCreaturesTillNow += 4;
        System.out.println("Total creatures till now: " + totalCreaturesTillNow);
    }

    protected void generate() {
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
        /*
         * @TODO: The bug is not here. It can be possibly in MonoCreature's hasFusedWith/hasNotFusedWith methods
         */
        int maxGen = this.maxGenerations;
        for (int f = 0; f < maxGen; f++) {
            System.out.println(" Entered the loop for the " + f + "th time (in generate() method)");                    //<--- Debug message
            ArrayList<MonoCreature> lastGenCreatures = this.getGeneration(f);
            int maxLengthLastGen = lastGenCreatures.size();
            System.out.println("    Last gen's size = " + maxLengthLastGen);
            int creatureOneLoc, creatureTwoLoc;
            //MonoCreature creatureOne, creatureTwo;
            for (creatureOneLoc = 0; creatureOneLoc < maxLengthLastGen; creatureOneLoc++) {
                System.out.println("      For c-1 loc = " + creatureOneLoc);
                MonoCreature creatureOne = lastGenCreatures.get(creatureOneLoc);
                for (creatureTwoLoc = 0; creatureTwoLoc < maxLengthLastGen; creatureTwoLoc++) {
                    System.out.println("      For c-2 loc = " + creatureTwoLoc);
                    MonoCreature creatureTwo = lastGenCreatures.get(creatureTwoLoc);
                    if ((creatureOneLoc != creatureTwoLoc) && ((creatureOne.hasNotFusedWith(creatureTwo)) || (creatureTwo.hasNotFusedWith(creatureOne)))) {
                        this.fuseTwo(creatureOne, creatureTwo);
                    } else if (creatureOneLoc == creatureTwoLoc) {
                        System.out.println("        The creatures are same");
                    } else if (creatureOne.hasFusedWith(creatureTwo) || (creatureTwo.hasFusedWith(creatureOne))) {
                        System.out.println("        The creatures have fused");
                    }
                }
            }
            this.dumpBufferToTotalList(f);
        }
    }
}
