import java.util.Scanner;

class Stairs {

    void run() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] nums = new int[n+1];
        for (int i = 1; i <= n; i++)
            nums[i] = in.nextInt();

        int[] sums = new int[n+1];
        for (int i = 1; i <= n; i++)
            sums[i] = Integer.MIN_VALUE;
        sums[0] = 0;

        for (int i = 0; i < n; i++) {
            int current_sum = sums[i];

            if (nums[i + 1] + current_sum > sums[i + 1])
                sums[i + 1] = nums[i + 1] + current_sum;
            if ( (i <= n - 2) && nums[i + 2] + current_sum > sums[i + 2])
                sums[i + 2] = nums[i + 2] + current_sum;
        }

        System.out.print(sums[n]);
    }

}
