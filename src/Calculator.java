import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Calculator {

    void run() {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        List<Integer> list = new ArrayList<>();
        list.add(n);

        int i = n;
        int count = 0;
        while (i != 1) {
            if ((i % 3 == 0) && (i / 3 > 0)) {
                i = i / 3;
            }
            else if ((i % 2 == 0) && (i / 2 > 0)) {
                i = i / 2;
            }
            else { i = i - 1; }

            list.add(i);
            count++;
        }

        System.out.println(count);
        for (int j = list.size() - 1; j >= 0; j--) {
            System.out.print(list.get(j) + " ");
        }
    }

    void anotherRun() {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        List<Integer> steps = new ArrayList<>(n + 1);
        for (int i = 0; i<n; i++)
            steps.add(Integer.MAX_VALUE);
        steps.add(0);

        List<Integer> next_num = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++)
            next_num.add(-1);

        for (int i = n; i > 1; --i) {
            int s = steps.get(i) + 1;
            if ( (i % 3 == 0) && (steps.get(i/3) > s) ) {
                steps.set(i/3, s);
                next_num.set(i/3, i);
            }
            if ( (i % 2 == 0) && (steps.get(i/2) > s) ) {
                steps.set(i/2, s);
                next_num.set(i/2, i);
            }
            if ( steps.get(i - 1) > s ) {
                steps.set(i - 1, s);
                next_num.set(i - 1, i);
            }
        }

        System.out.println(steps.get(1));
        for (int i = 1; i != -1; i = next_num.get(i)) {
            System.out.print(i + " ");
        }

    }

}
