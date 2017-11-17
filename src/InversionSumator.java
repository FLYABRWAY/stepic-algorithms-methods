import java.util.Scanner;

class InversionSummator {

    void run() {
        Scanner in = new Scanner(System.in);

        int size = in.nextInt();
        int[] array = new int[size];

        for (int i = 0; i<size; i++) {
            array[i] = in.nextInt();
        }

        long result = insertionSort(array);
        System.out.println(result);
    }

    private long insertionSort(int[] array) {
        int in, out;

        long result = 0;
        for(out=1; out<array.length; out++)         // out is dividing line
        {
            int temp = array[out];                  // remove marked item
            in = out;                               // start shifts at out
            while(in>0 && array[in-1] > temp)      // until one is smaller,
            {
                array[in] = array[in-1];            // shift item to right
                --in;                               // go left one position
                result++;
            }
            array[in] = temp;                       // insert marked item
        }  // end for
        return result;
    }

    private long merge(int[] array) {
        int[] workspace = new int[array.length];
        return recMerge(0, array, workspace, 0, array.length - 1);
    }

    private long recMerge(long result,
                          int[] array,
                          int[] workspace,
                          int lowerBound, int upperBound) {
        if(lowerBound == upperBound) // Если только один элемент,
            return result;
        else {
            // Поиск середины
            int mid = (lowerBound+upperBound) / 2;
            // Сортировка нижней половины
            result = recMerge(result, array, workspace, lowerBound, mid);
            // Сортировка верхней половины
            result = recMerge(result, array, workspace, mid+1, upperBound);
            // Слияние
            return merge(result, array, workspace, lowerBound, mid+1, upperBound);
        }
    }

    private long merge(long result,
                       int[] array,
                       int[] workSpace,
                       int lowPtr, int highPtr, int upperBound) {
        int j = 0; // Индекс в рабочей области
        int lowerBound = lowPtr;
        int mid = highPtr-1;
        int n = upperBound-lowerBound+1; // Количество элементов
        while(lowPtr <= mid && highPtr <= upperBound)
            if( array[lowPtr] <= array[highPtr] )
                workSpace[j++] = array[lowPtr++];
            else {
                workSpace[j++] = array[highPtr++];

                for (int i = lowPtr; i<=mid; i++)
                    result++;
            }
        while(lowPtr <= mid)
            workSpace[j++] = array[lowPtr++];
        while(highPtr <= upperBound)
            workSpace[j++] = array[highPtr++];
        for(j=0; j<n; j++)
            array[lowerBound+j] = workSpace[j];

        return result;
    }

}
