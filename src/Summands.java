import java.util.Scanner;

class Summands {

    void run() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        StringBuilder SB = new StringBuilder();

        int current = n;
        int summand = 0;

        while (current - (summand + 1) > summand + 1) {
            summand++;
            current -= summand;
            SB.append(summand + " ");
        }
        SB.append(current);

        System.out.println(summand + 1);
        System.out.println(SB.toString());
    }
}
