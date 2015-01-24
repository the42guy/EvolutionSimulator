import java.util.ArrayList;

/**
 * Created by Peter on 1/24/2015.
 */
public class MonoCreature {
    private String dominantTrait;                                                                                       //The dom trait for the creature
    private String genotypeCharacter;                                                                                   //The character representing the gene
    private String geneMakeup;                                                                                          //The make-up of the gene
    ArrayList<Character> genes = new ArrayList<Character>(2);
    private String gamete1, gamete2;

    public MonoCreature(char characterGene1, char characterGene2) {
        this.genes.add(characterGene1);
        this.genes.add(characterGene2);
        if(!geneCheck()) {
            throw new IllegalArgumentException("Genotype error");
        }

    }

    private boolean geneCheck() {                                                                                       //Checks integrity of genes
        boolean isOK = false;
        int allVerified = 0;
        String gene1 = genes.get(0).toString();
        String gene2 = genes.get(1).toString();
        System.out.println(gene1 + gene2);
        return isOK;
    }

    private void setDominantTrait() {
        char domTrait;
        for(int i = 0; i < 2; i++) {
            char geneThisIteration = this.genes.get(0);
            if(Character.isUpperCase(geneThisIteration)) {
                domTrait = geneThisIteration;
            }
        }

    }
}

