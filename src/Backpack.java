import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Backpack {

    private class Item implements Comparable {
        int cost;
        int volume;
        double average;

        Item(int cost, int volume) {
            this.cost = cost;
            this.volume = volume;
            this.average = (double) cost / (double) volume;
        }

        @Override
        public int compareTo(Object o) {
            Item oo = (Item) o;
            return Comparator.comparingDouble((Item ooo) -> ooo.average).reversed().compare(this, oo);
        }
    }

    void run() {
        Scanner in = new Scanner(System.in);

        int countItems = in.nextInt();
        Item[] items = new Item[countItems];

        int capacity = in.nextInt();

        for (int i = 0; i<countItems; i++) {
            int cost = in.nextInt();
            int volume = in.nextInt();

            items[i] = new Item(cost, volume);
        }
        Arrays.sort(items);

        double totalSum = 0.0;
        double currentCapacity = 0;

            for (int i = 0; i<items.length; i++) {
                double freeCapacity = capacity - currentCapacity;

                if (freeCapacity >= items[i].volume) {
                    currentCapacity += items[i].volume;
                    totalSum += items[i].cost;
                }
                else {
                    totalSum += items[i].average * freeCapacity;
                    break;
                }
            }

        System.out.printf("%.3f",totalSum);
    }

}
