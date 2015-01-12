import java.util.ArrayList;

/**
 * Created by Peter on 12/7/2014.
 */
/**
 * Developers are Peter and Amol
 */
public class MonoCreature {                                                                                             //This is a monocreature
    private static int orgID = 0;
    protected int uniqueRID = (int) (Math.random() * 1000000000);                                                       //A random ID for each org, will be most probably unique. Maybe add a uniqueness checker?

    public MonoCreature() {                                                                                             //A blank constructor for debugging purposes
    }
    private ArrayList<MonoCreature> fusedWith = new ArrayList<MonoCreature>();

    private String dominantTrait;                                                                                       //The dom gene
    private String genotypeCharacter;                                                                                   //For comparing at the time of fusion
    private ArrayList<String> gene = new ArrayList<String>();                                                                //An arraylist that contains both the genes
    private String geneMakeup;                                                                                          //String for easy export in correct format
    private String gamete1;                                                                                             //You don't say?
    private String gamete2;

    public MonoCreature(String characterGene1, String characterGene2) {                                                 //Constructor: Takes in a gene set (Gx2)
        this.gene.add(characterGene1);                                                                                  //Adds two Strings to gene arraylist
        this.gene.add(characterGene2);
        orgID++;                                                                                                        //++ the id
        System.out.println("MonoCreature says this: ");
        System.out.println("Character genes initialized for " + orgID);
        boolean allSet = this.geneCheck();
        System.out.println("OK to proceed: " + allSet);
        if (!allSet) {
            throw new IllegalArgumentException("Genotype error");
        }
        this.setDominantTrait();                                                                                        //Sets dom
        System.out.println("Dom trait: " + dominantTrait);
        this.createGametes();                                                                                           //Creates two gametes by dividing the genes
        System.out.printf("G1: %s, G2: %s\n", gamete1, gamete2);
        this.fuseSelfGametes(gamete1, gamete2);                                                                                    //Fixes capital issue
        System.out.println("Gene makeup: " + this.geneMakeup() + "\n");
    }

    private void fuseSelfGametes(String gamete1, String gamete2) {                                                      //Fuses gametes and fixes capital issue
        String fusionResult = "";
        if (gamete1.equals(gamete2)) {                                                                                  //If both upper case, then just do it
            fusionResult = gamete1 + gamete2;
        }
        char g1 = gamete1.charAt(0);
        char g2 = gamete2.charAt(0);
        if (Character.isUpperCase(g1)) {
            fusionResult = gamete1 + gamete2;
        } else {
            fusionResult = gamete2 + gamete1;
        }
        this.geneMakeup = fusionResult;                                                                                 //Sets the fused gametes
    }

    protected String geneMakeup() {
        return geneMakeup;
    }

    protected boolean geneCheck() {                                                                                     //Checks integrity of genes
        boolean isOK = false;
        int verifiedAll = 0;                                                                                            //def values set
        String g1 = this.gene.get(0).toLowerCase();
        String g2 = this.gene.get(1).toLowerCase();
        if(g1.equals(g2)) {                                                                                             //if both the genes are same alphabetically,
            verifiedAll++;                                                                                              //increase the count
        }
        for(int k = 0; k < 2; k++) {                                                                                    //iterates over the two genes
            int len = gene.get(k).length();
            if (len == 1) {                                                                                             //if the current element's length is 1
                verifiedAll++;                                                                                          //increase verified count
            }
        }
        if(verifiedAll == 3) {                                                                                          //two times verified means oll korrect
            isOK = true;
        }
        return isOK;
    }

    private void setDominantTrait() {
        String domTrait = "none";
        DomSelector: for(int i = 0; i < 2; i++) {                                                                       //iterates over two genes
            String geneAtThisInt = gene.get(i);
            char geneChar = geneAtThisInt.charAt(0);                                                                    //converts current element to char
            if(Character.isUpperCase(geneChar)) {                                                                       //checks if it is capital
                domTrait = geneAtThisInt;
                break DomSelector;                                                                                      //if yes, then break things
            }
        }
        this.dominantTrait = domTrait;                                                                                  //set the capital one as dominant trait
    }

    protected void createGametes() {                                                                                    //Initializes the gametes
        gamete1 = gene.get(0);
        gamete2 = gene.get(1);
    }

    protected String getGamete(int whichGamete) {                                                                       //Starts from 0, not 1
        String gamete = "";
        if(whichGamete == 0) {
            gamete = gamete1;
        } else if(whichGamete == 1) {
            gamete = gamete2;
        } else {
            System.out.println("Invalid gamete index passed");
        }
        return gamete;
    }

    protected void fusedWith(MonoCreature toAdd) {                                                                      //Manages the fuses history
        fusedWith.add(toAdd);                                                                                           //Adds object references of the fused to array
    }

    protected boolean hasNotFused(MonoCreature verifyThis) {                                                               //Checks if this creature has fused with the passed creature
        boolean notFusedWithIt = true;
        if (fusedWith.contains(verifyThis)) {
            notFusedWithIt = false;
        }
        return notFusedWithIt;
    }

    protected boolean hasFused(MonoCreature verifyThis) {
        boolean hasFused = false;
        if (fusedWith.contains(verifyThis)) {
            hasFused = true;
        }
        return hasFused;
    }

    protected int orgID() {
        return orgID;
    }
}
