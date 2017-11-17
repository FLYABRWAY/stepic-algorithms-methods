import java.util.*;

class Coordinate_ {

    static class Point {
        enum Type {LEFT, POINT, RIGHT}

        int x;
        Type type;

        Point(int x, Type type) {
            this.x = x;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;

            Point point = (Point) o;

            return x == point.x;
        }

        @Override
        public int hashCode() {
            return x;
        }
    }

    void run() {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // number of lines
        int m = in.nextInt(); // number of points

        List<Point> points = new ArrayList<>(2 * n + m);
        for (int i = 0; i<n; i++) {
            points.add(new Point(in.nextInt(), Point.Type.LEFT));
            points.add(new Point(in.nextInt(), Point.Type.RIGHT));
        }

        Point[] onlyPoints = new Point[m];
        for (int i = 0; i<m; i++) {
            Point newPoint = new Point(in.nextInt(), Point.Type.POINT);
            onlyPoints[i] = newPoint;
            points.add(newPoint);
        }
        points.sort(Comparator.comparingInt((Point p) -> p.x).thenComparing((Point p) -> p.type));

        long result = 0;
        Map<Point, Long> pointToAmount = new HashMap<>(m);
        for (int i = 0; i<points.size(); i++) {
            Point point = points.get(i);
            if (point.type == Point.Type.LEFT)
                result++;
            else if (point.type == Point.Type.RIGHT)
                result--;
            else if (point.type == Point.Type.POINT) {
                pointToAmount.put(point, result);
            }
        }

        for (Point point : onlyPoints) {
            System.out.print(pointToAmount.get(point) + " ");
        }
    }

}
