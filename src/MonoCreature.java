import java.util.ArrayList;

/**
 * Created by Peter on 1/24/2015.
 */
public class MonoCreature {
    private String dominantTrait;                                                                                       //The dom trait for the creature
    private String genotypeCharacter;                                                                                   //The character representing the gene
    private String geneMakeup;                                                                                          //The make-up of the gene
    ArrayList<String> genes = new ArrayList<String>(2);
    private String gamete1, gamete2;

    public MonoCreature(String characterGene1, String characterGene2) {
        this.genes.add(characterGene1);
        this.genes.add(characterGene2);
    }

    private boolean geneCheck() {                                                                                       //Checks integrity of genes
        boolean isOK = false;
        int allVerified = 0;
        for(int i = 0; i < 2; i++) {                                                                                    //Iterates over two genes and increases verif count for each success
            int length = genes.get(i).length();
            if(length == 1) {
                allVerified++;
            }
        }
        if(genes.get(0).equalsIgnoreCase(genes.get(1))) {                                                               //If both are equal, another increase
            allVerified++;
        }
        if(allVerified == 3) {                                                                                          //makes it true
            isOK = true;
        }
        return isOK;
    }
}

