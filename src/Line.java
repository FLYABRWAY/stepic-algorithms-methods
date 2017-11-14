import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

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
            return Comparator.comparing((Pair p)-> p.right)
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
        int countLines = in.nextInt();

        lines = new Pair[countLines];

        //StringBuilder SB = new StringBuilder();
        //SB.append("Count of lines: " + countLines + "\n");

        /// Creating Pairs
        for (int i = 0; i<countLines; i++) {
            int left = in.nextInt();
            int right = in.nextInt();

            lines[i] = new Pair(left, right);

            //SB.append("Line number: " + i + ". Left: " + left + ", right: " + right + "\n");
        }
        //System.out.println(SB.toString());

        // Sorting Pairs
        Arrays.sort(lines);

        //init necessary variables for main loop
        int count = 0;
        StringBuilder resultBuilder = new StringBuilder();

        // Main Loop
        int i = 0;
        while (true) {

            int j;
            for (j = i + 1; j<lines.length; j++) {

                if (!(lines[j].left <= lines[i].right
                        && lines[i].right <= lines[j].right)) {
                    count++;
                    resultBuilder.append(lines[i].right + " ");
                    i = j;
                    break;
                }

            }

            if (j >= lines.length) {
                count++;
                resultBuilder.append(lines[i].right);
                break;
            }

        }

        //out
        System.out.println(count);
        System.out.println(resultBuilder.toString());
    }

}
