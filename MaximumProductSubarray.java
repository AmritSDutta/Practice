/* Tested in LeetCode, performance can be further improved, but algo is correct, based on classical DP.*/


import java.util.concurrent.TimeUnit;

public class MaximumProductSubarray {

    public static void main(String[] args) {
       //int[] array = new int[]{5, 0, -2, 1, -5, 0, 7};
        int[] array = new int[]{1,-5,6,-5,2,-4,-5,0,3,2,-4,0,-5,-3,-1,-4,-1,4,1,-1,-3,
                -1,1,3,-4,-6,-2,5,1,-5,0,-1,-5,0,1,2,6,1,2,-6,5,5,0,1,0,1,1,-1,-1,3,1,0,
                4,-3,0,4,-4,-1,6,5,5,6,-6,1,1,3,4,3,-1,-3,0,-5,-4,1,5,-2,3,-1,2,1,1,6,0,5,-5,6,
                -6,3,0,4,-1,3,6,0,-2,0,-1,6,4,1,-5,1,0,1,-1,-1,3,5,5,4,2,5,0,-1,5,2,2,-3,-1,-1,0,
                -6,-2,-5,1,-2,2,0,0,2,-3,-2,-4,1,1,-4,-3,-1,0,0,1,-3,-2,3,-4,5,2,-1,4,1,5,6,0,1,1,-2,
                -1,0,-1,-5,5,6,6,-1,-1,0,-4,2,1,3,-5,6,-5,-1,-1,-3,-1,-4,-2,-1,-1,1,-3,-4,0,1,-3,4,3,2,-2,
                6,-3,-6,-6,-2,-5,1,2,0,-1,0,0,-2,3,-4,2,4,3,-1,3,1,0,2,1,-1,0,5,-1,-3,-6,-5,0,6,6,-6,
                -5,4,-2,-1,0,4,6,-3,1,-1,0,1,-5,5,-3,-3,-3,-1,-1,4,0,-2,-4,3,5,5,-1,-1,-5,-2,-4,-4,6,0,
                -3,-1,-5,-3,-1,6,1,-5,-1,0,1,-4,-5,0,0,0,-3,-5,-1,-4,-1,5,5,-4,4,-1,6,-1,1,-1,2,-2,-3,0,1,0,0,
                -3,0,2,5,-6,-3,-3,3,-4,-2,-6,-1,1,4,4,0,-6,-5,-6,-3,5,-3,1,-4,6,-2,0,-4,-1,0,-1,0,6,-6,0,5,0,1,
                -3,6,1,-1,1,0,-1,1,-1,-6,-3,4,-1,-4,6,4,-1,-3,2,-6,5,0,4,-2,1,0,4,-2,2,0,0,5,5,-3,4,3,-5,2,2,6,-1,-2,1,-3,1,-1,6,-4,0,0,0,2,-5,-4,2,6,-3,-6,-1,-6,0,0,2,-1,6,-4,-5,-1,0,-3,-3,-1,0,-4,3,1,5,0,2,5,0,4,-5,-1,3,1,-1,-1,1,1,-2,3,5,4,6,2,6,-6,5,2,-3,0,-1,-1,3,1,1,1,-2,-5,3,-1,3,0,-1,3,1,1,-2,6,3,-6,5,-5,-5,0,-2,-3,
                -3,-4,6,-1,-6,6,-3,-5,1,-1,0,0,1,4,-5,0,1,-2,6,1,-3,-5,0,4,-2,1,-5,-4,0,0,-1,-2,0,2,-2,5,6};
        System.out.println(array.length);
        long start = System.nanoTime();
        maximumProductSubArray(array);
        long end = System.nanoTime();
        System.out.println("Time Taken : "+ TimeUnit.NANOSECONDS.toMillis(end-start));

    }

    static void maximumProductSubArray(int[] arr) {
        int[][] intermediateResult = new int[2][arr.length];
        int MAX = arr[0];
        int finalI = 0;
        int finalJ = 0;

        //Initialize
        intermediateResult[0][0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            intermediateResult[0][i] = arr[i];
            if (MAX < arr[i]) {
                MAX = arr[i];
                finalI = i;
                finalJ = i;
            }
        }
        //printSubArray(intermediateResult);

        for (int k = 1; k <= arr.length; k++) {
            for (int j = k; j < arr.length; j++) {
                intermediateResult[1][j] = intermediateResult[0][j - 1] * arr[j];
                if (MAX < intermediateResult[1][j]) {
                    MAX = intermediateResult[1][j];
                    finalI = j - k;
                    finalJ = j;
                }
            }
            //printSubArray(intermediateResult);
            transferContent(intermediateResult);
        }
        //System.out.println(" MAX : " + MAX + " ," + finalI + " , " + finalJ);
    }

    /*private static void printSubArray(int[][] intermediateResult) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < intermediateResult[0].length; j++)
                //System.out.print(intermediateResult[i][j] + " ");
            //System.out.println();
        }
        //System.out.println();
       // System.out.println();

    }*/

    /* This can be avoided, but with this algo uses limited memory */
    private static void transferContent(int[][] intermediateResult) {
        for (int i = 0; i < intermediateResult[0].length; i++) {
            intermediateResult[0][i] = intermediateResult[1][i];
        }

    }
}

