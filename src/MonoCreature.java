import java.util.ArrayList;

/**
 * Created by Peter on 1/24/2015.
 */
public class MonoCreature {
    private String dominantTrait;                                                                                       //The dom trait for the creature
    private char genotypeCharacter;                                                                                   //The character representing the gene
    ArrayList<String> genes = new ArrayList<String>(2);
    private char charGamete1, charGamete2;
    private String gamete1, gamete2;
    private String geneStructure;
    ArrayList<MonoCreature> fusedWith = new ArrayList<MonoCreature>();
    boolean allVerified = false;

    public MonoCreature(String characterGene1, String characterGene2) {
        this.genes.add(characterGene1);
        this.genes.add(characterGene2);
        allVerified = this.geneIntegrityCheck();
        if (!allVerified) {                                                                                             //if allVerified is false, then throw exception
            throw new IllegalArgumentException("Genotype error");
        }
        this.setDominantTrait();
        this.createGametes();
        geneStructure = this.createGeneStructure();
        dominantTrait = this.setDominantTrait();
        genotypeCharacter = this.setGenotypeCharacter();
    }
    private String createGeneStructure() {
        String geneStructure = "";
        if (gamete1 == gamete2) {
            geneStructure = gamete1.toString();
        } else if (Character.isUpperCase(charGamete1)) {
            geneStructure = gamete1 + gamete2;
        } else if (Character.isUpperCase(charGamete2)) {
            geneStructure = gamete2 + gamete1;
        }
        return geneStructure;
    }

    protected String getGeneStructure() {
        return geneStructure;
    }
    private boolean geneIntegrityCheck() {                                                                                       //Checks integrity of genes
        boolean isOK = false;
        int allVerified = 0;
        String gene1 = genes.get(0);
        String gene2 = genes.get(1);
        if (gene1.equalsIgnoreCase(gene2)) {
            allVerified++;
        }
        if ((gene1.length() == 1) && (gene2.length() == 1)) {
            allVerified++;
        }
        if (allVerified == 2)
            isOK = true;
        return isOK;
    }

    private char setGenotypeCharacter() {
        char genotype = ' ';
        if (allVerified) {
            genotype = gamete1.charAt(0);
        }
        return genotype;
    }

    private String setDominantTrait() {                                                                                   //Sets the dominant trait
        String domTrait = "";
        for(int i = 0; i < 2; i++) {
            String geneThisIteration = this.genes.get(i);
            char geneThisIter = geneThisIteration.charAt(0);
            if (Character.isUpperCase(geneThisIter)) {
                domTrait = geneThisIteration;
            }
        }
        return domTrait;

    }
    private void createGametes() {                                                                                      //Initializes the gametes for later use
        gamete1 = genes.get(0);
        gamete2 = genes.get(1);
        charGamete1 = gamete1.charAt(0);
        charGamete1 = gamete2.charAt(0);
    }

    protected String getGamete(int whichOne) {                                                                          //Gets the required gamete
        String gamete = "";
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

