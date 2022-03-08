import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.awt.Point;

class Result {

    /*
     * Complete the 'bfs' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. 2D_INTEGER_ARRAY edges
     *  4. INTEGER s
     */

    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
    // Write your code here
      LinkedList<Integer> total = new LinkedList<Integer>();
      int[] tally = new int[n];
      
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(s);
      LinkedList<Integer> passed = new LinkedList<Integer>();
      passed.add(s);
      
      for(int i = 1; i <= n-1; i++) {

        int looking = q.remove();
        for(int j = 0; j < m; j++) {
          if(edges.get(j).contains(looking)) {
            int first = edges.get(j).get(0);
            int second = edges.get(j).get(1);
            if(first != looking && !passed.contains(first)) {
              passed.add(first);
              tally[first-1] = 6 + tally[second-1];
              q.add(first);
            }
            else if (second != looking && !passed.contains(second)){
              passed.add(second);
              tally[second-1] += 6 + tally[first-1];
              q.add(second);
            }
          }
        }
      }
      for(int i = 0; i < tally.length; i++) {
        if(tally[i] == 0 && i+1 != s) 
          total.add(-1);
        else if (i+1 != s) total.add(tally[i]);
      }
      
      return total;

    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int m = Integer.parseInt(firstMultipleInput[1]);

            List<List<Integer>> edges = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                String[] edgesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                List<Integer> edgesRowItems = new ArrayList<>();

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowTempItems[j]);
                    edgesRowItems.add(edgesItem);
                }

                edges.add(edgesRowItems);
            }

            int s = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> result = Result.bfs(n, m, edges, s);

            for (int i = 0; i < result.size(); i++) {
                bufferedWriter.write(String.valueOf(result.get(i)));

                if (i != result.size() - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
