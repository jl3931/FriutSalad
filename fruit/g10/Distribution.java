package fruit.g2;

import java.util.*;
import java.io.*;

public class Distribution {
    private LinkedList<int[]> history;
    private LinkedList<Integer> scoreHistory;
    private int nplayers;
    private int nkindfruits;
    private int nfruits;
    private int[] pref;
    private int nround;
    private HashMap chiSquareTable = new HashMap();
    private Stat stat;
    private int[] estimateDistribution; = new int[12];
    private int[] numFruitSeen;
    
    public Distribution(Stat stat, int nfruits) {
        this.nfruits = nfruits;
        this.stat = stat;
        estimateDistribution = new int[12];
        numFruitSeen = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public int[] estimateDistribution(LinkedList<int[]> history, int round){
        if(history.length >= 2){
            // calculate total fruits seen
            int fruitNumber = 0;
            int fruitTotal = 0;
            for(int numFruitType; history[round]){
                numFruitSeen[fruitNumber] += numFruitType;
                fruitTotal += numFruitType;
                fruitNumber++;
            }
         for(int i=0; i < estimateDistribution.length; i++){
            estimateDistribution[i] = (numFruitSeen[i]/fruitTotal) * nfruits;
         }
        }
        else{
            double numFruit = nfruits/12.0;
            for(int i = 0; i< estimateDistribution.length; i++){
                estimateDistribution[i] = numFruit;
            }
        }
        return estimateDistribution;
    }
}
