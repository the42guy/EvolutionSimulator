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
    protected void seedParents(MonoCreature mc1, MonoCreature mc2) {
        seeds[0] = mc1;
        seeds[1] = mc2;
        bufferList.add(seeds[0]);
        bufferList.add(seeds[1]);
    }
}
