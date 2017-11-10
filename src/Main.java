import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new Line().run();
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
    }

}

class Line {

    private Pair[] lines;

    private static class Pair implements Comparable {
        int left;
        int right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Object o) {
            Pair oo = (Pair) o;
            return Comparator.comparing((Pair p)-> p.left)
                    .thenComparing(p -> p.right)
                    .compare(this, oo);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    void run() {
        Scanner in = new Scanner(System.in);
        //Creating number of lines
        int countLines = in.nextInt();

        // Creating reference
        lines = new Pair[countLines];

        StringBuilder SB = new StringBuilder();
        SB.append("Count of lines: " + countLines + "\n");

        /// Creating Pairs
        for (int i = 0; i<countLines; i++) {
            int left = in.nextInt();
            int right = in.nextInt();

            lines[i] = new Pair(left, right);

            SB.append("Line number: " + i + ". Left: " + left + ", right: " + right + "\n");
        }

        // Sorting Pairs
        Arrays.sort(lines);
        for (int i = 0; i<lines.length; i++)
            System.out.println(lines[i]);

        System.out.println(SB.toString());

        // Main Loop
        int optimalPoints = 0;
        for (int i = 0; i<lines.length-1; i++) {

            int currPos = lines[i].left;
            while(currPos < lines[i].right) {

                for (int j = i + 1; j<lines.length; j++) {

                }

            }

        }
    }

}
