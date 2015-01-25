import java.util.ArrayList;

/**
 * Created by Peter on 1/24/2015.
 */
public class MonoHybrid {
    int maxGenerations;
    private MonoCreature[] seeds = new MonoCreature[2];
    ArrayList<MonoCreature> bufferList = new ArrayList<>();
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
    }
    protected void dumpBufferToTotalList(int whichIndex) {                                                              //puts the complete content of the buffer to totalList
        totalList.add(whichIndex, bufferList);
        bufferList.clear();
    }
    private void addToBuffer(MonoCreature aCreature) {                                                                  //adds the given creature to buffer
        bufferList.add(aCreature);
    }

    private void addToBuffer(MonoCreature creatureOne, MonoCreature creatureTwo, MonoCreature creatureThree, MonoCreature creatureFour) {
        bufferList.add(creatureOne);
        bufferList.add(creatureTwo);
        bufferList.add(creatureThree);
        bufferList.add(creatureFour);
    }
    private void fuseTwo(MonoCreature parentOne, MonoCreature parentTwo) {                                              //fuses two parents to create four off-springs
        /**@javadoc
         * Fuses two parents' gametes to obtain 4 off-springs.
         * Working:
         *      Takes two MonoCreatures as arguments
         *      Creates two ArrayLists, one for holding the gametes, other for the off-springs (ArrayList here contributed by @flamingrush2)
         *      Then initializes the gametes ArrayList by enquiring about each gamete through the getGamete(int whichGamete) method
         *      Now four creatures are created via these four gametes, and simultaneously added to the offSpring ArrayList
         *      This ArrayList is then sent to buffer ArrayList
         *      total creature count is increased by 4 each run
         * */
        ArrayList<String> gametes = new ArrayList<>(4);
        ArrayList<MonoCreature> offSpring = new ArrayList<>(4);
        for (int counter = 0; counter < 4; counter++) {
            switch (counter) {
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
            }
        }
        offSpring.add(new MonoCreature(gametes.get(0), gametes.get(2)));
        offSpring.add(new MonoCreature(gametes.get(0), gametes.get(3)));
        offSpring.add(new MonoCreature(gametes.get(1), gametes.get(2)));
        offSpring.add(new MonoCreature(gametes.get(1), gametes.get(3)));
        for (int i = 0; i < 4; i++) {
            addToBuffer(offSpring.get(i));
        }
        totalCreaturesTillNow += 4;
    }
}
