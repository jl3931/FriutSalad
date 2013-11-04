package fruit.g10;

import java.util.*;
import java.io.*;

public class Distribution {
    private LinkedList<int[]> history;
    private Stat stat;
    private double[] estimateDistribution;
    private int[] numFruitSeen;
    private double fruitTotal = 0.0;
    private int nplayers;
    
    public Distribution(Stat stat, int nplayers) {
        this.stat = stat;
        estimateDistribution = new double[12];
        numFruitSeen = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.nplayers = nplayers;
    }

    public double[] estimateDistribution(LinkedList<int[]> history, int bowlId){
        if(history.size() >= 2){
            // calculate total fruits seen
            // sum all of the fruit types into an array of fruits seen
            int fruitNumber = 0;
            int[] roundHistory = history.get(bowlId);
            for(int numFruitType : roundHistory){
                numFruitSeen[fruitNumber] += numFruitType * 1.0;
                fruitTotal += numFruitType;
                fruitNumber++;
            }
            // based on the history -> determine an expected distribution for one bowl
         for(int i=0; i < estimateDistribution.length; i++){
            estimateDistribution[i] = (numFruitSeen[i]/fruitTotal) * stat.getNFruits()*1.0*nplayers;
          }
        }
        // if only one bowl has been passed- assume uniform distribution
        else{
            double numFruit = (stat.getNFruits()* nplayers)/12.0;
            for(int i = 0; i< estimateDistribution.length; i++){
                estimateDistribution[i] = numFruit;
            }
        }
        return estimateDistribution;
    }
}
