/**
 * Created by Peter on 12/7/2014.
 */

/**
 * Developers are Peter and Amol
 */
public class EvoSimDriver {
    public static void main(String[] args) {
        EvoSimDriver.getOption();
    }

    private static void getOption() {
        try {
            MonoCreature mc1 = new MonoCreature("Q", "Q");                                                                  //A monocreature (with two genes of one character
            MonoCreature mc2 = new MonoCreature("q", "q");                                                                  //Another one
            MonoHybrid m = new MonoHybrid(3);
            m.seedParents(mc1, mc2);
            m.generate();     //Throws an exception, look at it
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong genotype entered!");
        } finally {
            System.out.println("\n\n\nEnd of program");
        }
    }
}
