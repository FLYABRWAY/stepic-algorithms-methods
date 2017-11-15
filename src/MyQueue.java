import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class MyQueue {

    class MyInteger implements Comparable {
        Integer number;

        MyInteger(Integer integer) {
            number = integer;
        }

        @Override
        public int compareTo(Object o) {
            return Comparator.comparingInt((MyInteger e) -> e.number).reversed().compare(this, (MyInteger) o);
        }

        @Override
        public String toString() {
            return number.toString();
        }
    }

    void run() {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();

        PriorityQueue<MyInteger> myQueue = new PriorityQueue<>(6);
        for (int i = 0; i<number; i++) {

            String line = in.next();

            if (line.equals("ExtractMax"))
                System.out.println(myQueue.remove());
            else {
                Integer insertValue = in.nextInt();
                myQueue.add(new MyInteger(insertValue));
            }
        }
    }
}