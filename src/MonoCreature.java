import java.util.ArrayList;

/**
 * Created by Peter on 1/24/2015.
 */
public class MonoCreature {
    private String dominantTrait;                                                                                       //The dom trait for the creature
    private String genotypeCharacter;                                                                                   //The character representing the gene
    private String geneMakeup;                                                                                          //The make-up of the gene
    ArrayList<Character> genes = new ArrayList<Character>(2);
    private char gamete1, gamete2;
    private String geneStructure;
    ArrayList<MonoCreature> fusedWith = new ArrayList<MonoCreature>();

    public MonoCreature(char characterGene1, char characterGene2) {
        this.genes.add(characterGene1);
        this.genes.add(characterGene2);
        if (!geneIntegrityCheck()) {
            throw new IllegalArgumentException("Genotype error");
        }
        this.setDominantTrait();
        this.createGametes();
    }

    private String createGeneStructure() {
        String geneStructure;
        if (gamete1 == gamete2) {
            geneStructure = gamete1.toString();
        }
    }

    private boolean geneIntegrityCheck() {                                                                                       //Checks integrity of genes
        boolean isOK = false;
        int allVerified = 0;
        String gene1 = genes.get(0).toString();
        String gene2 = genes.get(1).toString();
        System.out.println(gene1 + gene2);
        return isOK;
    }
    private void setDominantTrait() {                                                                                   //Sets the dominant trait
        char domTrait;
        for(int i = 0; i < 2; i++) {
            char geneThisIteration = this.genes.get(0);
            if(Character.isUpperCase(geneThisIteration)) {
                domTrait = geneThisIteration;
            }
        }
    }
    private void createGametes() {                                                                                      //Initializes the gametes for later use
        gamete1 = genes.get(0);
        gamete2 = genes.get(1);
    }

    protected char getGamete(int whichOne) {                                                                          //Gets the required gamete
        char gamete = ' ';
        if((whichOne==0) || (whichOne==1)) {
            gamete = genes.get(whichOne);
        }
        return gamete;
    }

    protected void fusedWith(MonoCreature aCreature) {                                                                  //Adds the creature to the ArrayList
        this.fusedWith.add(aCreature);
    }

    protected boolean hasFused(MonoCreature theCreatureToCheck) {                                                   //Gets the status of fusion with the given creature
        boolean hasFused = false;
        if (fusedWith.contains(theCreatureToCheck)) {
            hasFused = true;
        }
        return hasFused;
    }

    protected boolean hasNotFused(MonoCreature theCreatureToCheck) {
        boolean hasNotFused = false;
        if (fusedWith.contains(theCreatureToCheck)) {
            hasNotFused = true;
        }
        return hasNotFused;
    }
}

