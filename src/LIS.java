import java.util.Scanner;

class LIS {

    void run() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] array = new int[n];
        int[] amounts = new int[n];

        for (int i = 0; i<n; i++) {
            array[i] = in.nextInt();
            amounts[i] = 1;
        }

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {
                if (array[j] % array[i] == 0
                        && amounts[i] + 1 > amounts[j]) {
                    amounts[j] += 1;
                }
            }
        }

        int max = 1;
        for (int i = 0; i<n; i++)
            if (amounts[i] > max)
                max = amounts[i];
        System.out.println(max);
    }

}
