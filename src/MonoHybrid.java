import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Peter on 12/7/2014.
 */
/**
 * Developers are Peter and Amol
 */
public class MonoHybrid {
    private int f;
    ArrayList<MonoCreature> bufferMC = new ArrayList<MonoCreature>();                                                              //A buffer for temporarily storing mono-creatures
    ArrayList<ArrayList<MonoCreature>> monoCreaturesTotalList = new ArrayList<ArrayList<MonoCreature>>();                                     //An arrayList of an arrayList of mono-creatures
    MonoCreature[] seedCreatures = new MonoCreature[2];                                                                 //Creates an array to hold the input parents

    public MonoHybrid(int maxGenerationsToSee) {                                                                        //Most probable constructor, will take in max gen count
        this.f = maxGenerationsToSee;
    }

    protected void seedParents(MonoCreature parent1, MonoCreature parent2) {                                            //@TODO: include with constructor
        seedCreatures[0] = parent1;
        seedCreatures[1] = parent2;
        bufferMC.add(parent1);
        bufferMC.add(parent2);
        this.addToList();
        this.generator();
    }

    private void fuseTwo(MonoCreature parent1, MonoCreature parent2) {                                                  //Generates the four DNA outcomes from two parents
        /**@javadoc
         * Fuses two parents' gametes to obtain 4 off-springs.
         * Working:
         *      Takes two MonoCreatures as arguments
         *      Creates two arrays, one for holding the gametes, other for the off-springs
         *      Then initializes the gamete array by enquiring about each gamete through the getGamete(int whichGamete) method
         *          Working of this:
         *              Iterates from 0 to 3 (0, 1, 2, 3)
         *              In each iteration, it will add a gamete to the array by the method (getGamete(int whichOne))
         *      Now four creatures are created via these four gametes.
         * */
        String[] gamete = new String[4];
        MonoCreature[] offSpring = new MonoCreature[4];                                                                 //Temporarily holds the offspring creatures
        if (parent1.hasFused(parent2)) {
            System.out.println("Parents have already fused.");
            System.out.println("Returning");
            return;
        } else if (parent1 == parent2) {
            System.out.println("Both parents are same");
            System.out.println("Returning");
            return;
        } else if ((parent1.hasNotFused(parent2)) || (parent2.hasNotFused(parent1))) {
            for (int i = 0; i < 4; i++) {                                                                                    //Initializes the gametes array
                if ((i == 0) || (i == 1)) {                                                                                    //The first two gametes
                    gamete[i] = parent1.getGamete(i);
                    System.out.print("From p1: ");
                } else if ((i == 2) || (i == 3)) {                                                                             //The second two gametes
                    gamete[i] = parent2.getGamete(i - 2);
                    System.out.print("From p2: ");
                }
                System.out.println("Gamete " + i + " is " + gamete[i]);
            }
            System.out.println("\n");
            offSpring[0] = new MonoCreature(gamete[0], gamete[2]);                                                          //Clean and better at improving performance
            offSpring[1] = new MonoCreature(gamete[0], gamete[3]);
            offSpring[2] = new MonoCreature(gamete[1], gamete[2]);
            offSpring[3] = new MonoCreature(gamete[1], gamete[3]);
            //4 off-springs from 2 parents created. Now send them to bufferMC arrayList.
            for (int childInt = 0; childInt < 4; childInt++) {
                System.out.println("for " + childInt);
                System.out.println("Adding " + offSpring[childInt].geneMakeup() + " to buffer AL");
                bufferMC.add(offSpring[childInt]);
                System.out.println(offSpring[childInt].geneMakeup() + " added\nTotal length of the buffer array-list: " + bufferMC.size() + "\n");
            }
            //this.debugMC();
            parent1.fusedWith(parent2);                                                                                     //Adds parents to each others' lists
            parent2.fusedWith(parent1);                                                                                     //This prevents them from refusing
        }
    }

    private void addToList() {
        monoCreaturesTotalList.add(bufferMC);
        bufferMC = new ArrayList<MonoCreature>();
    }

    private void generator() {
        /**
         * @javadoc
         * Working of this method:
         *    A number of generations are provided. Last generation's creatures are taken from the ArrayList (monoCreaturesTotalList).
         *    These creatures act as parents for this generation/iteration. Total size of this ArrayList of the parents are taken.
         *    Two ints, one for each parent, are created. They are used to iterate over the creatures of that generation.
         *    MonoCreatures a and b are two parents from each for-loop. They are independent of each other. b increments first. When
         *    all the third-level iterations in one second-level iterations run out, naturally the next second-level iteration
         *    takes place. Inside the third-level for-loop, the three if-else-if conditions (two, rather) determine whether to fuse
         *    the two creatures or not. It is determined by two conditions in total: if the creatures have not fused with each other, and they are not the
         *    same, only then can they fuse.
         *
         * FAQs:
         * Q: Why is hasNotFused method not working?
         * A: It works now. The issue was not with the method, but that the same creature fused with itself whenever the
         *    two for-loops came on the same iteration (1 and 1, 2 and 2 and so on...)
         * Q: What is lastGenCreatures arrayList?
         * A: Rather self-explanatory. They are the last gen's creatures which will act as this gen's parents.
         * */
        int generation;
        int maxGen = 3;
        ArrayList<MonoCreature> lastGenCreatures;
        for (generation = 1; generation <= maxGen; generation++) {                                                             //The generations...
            lastGenCreatures = monoCreaturesTotalList.get(generation - 1);
            int totalLengthOfPreviousGeneration = lastGenCreatures.size();
            int parentOneCreatureInArray;
            int parentTwoCreatureInArray;
            for (parentOneCreatureInArray = 0; parentOneCreatureInArray < totalLengthOfPreviousGeneration; parentOneCreatureInArray++) {
                MonoCreature a = lastGenCreatures.get(parentOneCreatureInArray);
                for (parentTwoCreatureInArray = 0; parentTwoCreatureInArray < totalLengthOfPreviousGeneration; parentTwoCreatureInArray++) {
                    MonoCreature b = lastGenCreatures.get(parentTwoCreatureInArray);
                    //this.fuseTwo(a, b);
                    if ((a.hasNotFused(b)) && (a != b)) {
                        System.out.println("a and b have not fused, for a = " + a + " and b = " + b);
                        System.out.println("Fusing them...");
                        this.fuseTwo(a, b);
                    } else if (a == b) {
                        System.out.println("a and b are same");
                    } else {
                        System.out.println("a and b have already fused");
                    }
                }
            }
            addToList();
        }
        /* Amol's code */
        /*

        for (int i = 1; i <= f; i++) {
            System.out.println("In generation: " + i);
            ArrayList<MonoCreature> arrayListNew = monoCreaturesTotalList.get(0);
            for (int z = 0; z < arrayListNew.size(); z++) {
                arrayOfMonoCreatures.add(arrayListNew.get(z));
            }

            if (i == 1) {
                System.out.println("In generation: 1");
                for (int z = 0; z < arrayOfMonoCreatures.size(); z++) {
                    for (int y = arrayOfMonoCreatures.size() - 1; y >= z; y--) {
                        MonoCreature firstC = arrayOfMonoCreatures.get(z);
                        MonoCreature secondC = arrayOfMonoCreatures.get(y);
                        this.fuseTwo(firstC, secondC);
                        break;
                    }
                }
            }

            this.addToList();
        }*/
        System.out.println("Finished generation " + generation + " with " + bufferMC.size() + " creatures, and a total of " + totalCreaturesEver());
    }

    private int totalCreaturesEver() {
        int creatures = 0;
        for (int i = 0; i < monoCreaturesTotalList.size(); i++) {
            ArrayList<MonoCreature> q = monoCreaturesTotalList.get(i);
            creatures = creatures + q.size();
        }
        return creatures;
    }

    private void debugMC() {                                                                                            // Debugging; Can be removed
        System.out.println("Debug Start");
        //Prints contents of bufferMC
        Iterator arrayListIterator = bufferMC.iterator();
        while (arrayListIterator.hasNext()) {
            System.out.println(arrayListIterator.next());
        }
        System.out.println("Debug End");
    }

    protected void nextGen() {
        f++;
    }
}
