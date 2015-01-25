import java.util.ArrayList;

/**
 * Created by Peter on 1/24/2015.
 */
public class MonoHybrid {
    int maxGenerations;
    private MonoCreature[] seeds = new MonoCreature[2];
    ArrayList<MonoCreature> bufferList = new ArrayList<MonoCreature>();
    ArrayList<ArrayList<MonoCreature>> totalList = new ArrayList<ArrayList<MonoCreature>>();
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

    private void fuseTwo(MonoCreature parentOne, MonoCreature parentTwo) {                                              //fuses two parents to create four off-springs
        ArrayList<String> gametes = new ArrayList<String>(4);                                                   // Replaced String Array with Array list
        ArrayList<MonoCreature> offSpring = new ArrayList<MonoCreature>(4);
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
    }
}
