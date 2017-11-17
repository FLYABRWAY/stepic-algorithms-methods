import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Coordinate {

    class Line implements Comparable<Line> {
        int start;
        int finish;

        Line(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public int compareTo(Line o) {
            return Comparator.comparingInt((Line line) -> line.start)
                    .thenComparingInt((Line line) -> line.finish)
                    .compare(this, o);
        }
    }

    class Point {
        int x;

        Point(int x) { this.x = x; }

        boolean isInline(Line line) {
            return line.start <= x && x <= line.finish;
        }
    }

    void run() {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        Line[] lines = new Line[n];
        for (int i = 0; i<n; i++) {
            lines[i] = new Line(in.nextInt(), in.nextInt());
        }

        Arrays.sort(lines);

        for (int i = 0; i<m; i++) {
            Point point = new Point(in.nextInt());
            int result = 0;
            for (Line line : lines) {
                if (point.isInline(line))
                    result++;
            }
            System.out.print(result + " ");
        }
    }
}
