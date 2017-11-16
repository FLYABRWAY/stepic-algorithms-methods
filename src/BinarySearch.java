import java.util.Scanner;

class BinarySearch {

    void run() {
        Scanner in = new Scanner(System.in);

        int amountOne = in.nextInt();
        long[] arrayOne = new long[amountOne];
        for (int i = 0; i<amountOne; i++) {
            arrayOne[i] = in.nextLong();
        }

        int amountSecond = in.nextInt();
        long[] arraySecond = new long[amountSecond];
        for (int i = 0; i<amountSecond; i++) {
            arraySecond[i] = in.nextLong();
        }

        for (int i = 0; i<amountSecond; i++) {
            int result = find(arraySecond[i], arrayOne);
            if (result != -1)
                result += 1;
            System.out.print(result + " ");
        }
    }

    int find(long searchKey, long[] array) {
        int lowerBound = 0;
        int upperBound = array.length-1;
        int curIn;
        while(true) {
            curIn = (lowerBound + upperBound ) / 2;
            if(array[curIn]==searchKey)
                return curIn; // Элемент найден
            else if(lowerBound > upperBound)
                return -1; // Элемент не найден
            else // Деление диапазона
            {
                if(array[curIn] < searchKey)
                    lowerBound = curIn + 1; // В верхней половине
                else
                    upperBound = curIn - 1; // В нижней половине
            }
        }
    }

}
