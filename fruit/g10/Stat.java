package fruit.g10;

import java.util.*;
import java.io.*;
import java.util.Enumeration;

public class Stat {
    private LinkedList<int[]> history;
    private LinkedList<Integer> scoreHistory;
    private int nplayers;
    private int nkindfruits;
    private int nfruits;
    private int[] pref;
    private int nround;
    private HashMap<Double,Double> chiSquareTable = new HashMap<Double,Double>();
    final double LOWEST_DESIRED_CHI = .750;
    
    public Stat(int nplayers, int[] pref) {
        history = new LinkedList<int[]>();
        scoreHistory = new LinkedList<Integer>();
        this.nplayers = nplayers;
        this.pref = pref.clone();
        nkindfruits = pref.length;
        nfruits = 0;
        nround = 0;
        chiSquareTable.put( new Double( .995 ), 2.603);
        chiSquareTable.put( new Double( .990 ), new Double( 3.053 ) );
        chiSquareTable.put( new Double( .975 ), new Double( 3.816 ) );
        chiSquareTable.put( new Double( .950 ), new Double( 4.575 ) );
        chiSquareTable.put( new Double( .900 ), new Double( 5.578 ) );
        chiSquareTable.put( new Double( .750 ), new Double( 7.584 ) );
    }

    public int getNFruits(){
        return nfruits;
    }

    public void add(int[] bowl) {
        assert(nkindfruits == bowl.length);
        if (nfruits == 0)
            nfruits = sum(bowl);
        assert(nfruits == sum(bowl));
        nround++;
        history.add(bowl);
        scoreHistory.add(dot(bowl, pref));
        assert(nround == history.size());
    }

    public int score(int round) {
        assert(round < nround);
        assert(round >= 0);
        return scoreHistory.get(round);
    }

    public double average() {
        // no data - guess an average
        if (nround == 0)
            return nfruits * sum(pref)/nkindfruits;
        double sum = 0;
        for (int i = 0; i < nround; i++)
            sum += score(i);
        return sum/nround;
    }


    public double stdev() {
        if (nround == 0)
            return nfruits;
        double avg = average();
        int sum = 0;
        for (int i = 0; i < nround; i++) {
            double diff = avg - score(i);
            sum += Math.pow(diff, 2.0);
        }
        return Math.pow((sum/nround), 0.5);

    }

    public void dump(String filename) {
        LinkedList<Integer> scores = new LinkedList<Integer>(scoreHistory);
        int size = scores.size();
        double avg = average();
        double std = stdev();
        Collections.sort(scores, Collections.reverseOrder());
        try {
            PrintWriter writer = new PrintWriter(filename+".txt");
            for (int i = 1; i < 100; i++) {
                // rank top 1/i
                int rank = (int)(size / i - 1);
                if (rank < 0)
                    rank = 0;
                double score = scores.get(rank);
                double coeff = (score - avg) / std;
                writer.println(coeff);
            }
            writer.flush();
            writer.close();
            writer = new PrintWriter(filename+"_raw.txt");
            for (int i = 1; i < nround; i++) {
                writer.println(scores.get(i));
            }
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private int sum(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    private int dot(int[] a, int[] b) {
        assert(a.length == b.length);
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    // Chi Square distribution - assumes normal distribution
    // sums together all (observed - expecte values)/ expected value
    public double chiSquare(int[] bowl, double[] expectedDistribution){
        double chiSqaureSum = 0;
        for(int i=0; i< bowl.length; i++ ){
            double observedMinusExpected = bowl[i] - expectedDistribution[i];
            chiSqaureSum += Math.pow(observedMinusExpected, 2.0)/(expectedDistribution[i]+.0000001) ;
        } 
        return chiSqaureSum;
    }

    //If according to the Chi Square chart the estimate is a good one
    public boolean decentEstimate(double chiSquareSum){
        return chiSquareSum <= (chiSquareTable.get(LOWEST_DESIRED_CHI) + 1);  
    }

    public LinkedList<int[]> getHistory(){
        return history;
    }

}
