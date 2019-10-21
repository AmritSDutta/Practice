/* Tested in LeetCode, performance can be further improved, but algo is correct, based on classical DP.*/

import java.util.Arrays;

public class MaximumProductSubarray {

    public static void main(String[] args) {
       int[] array = new int[]{5, 0, -2, 1, -5, 0, 7};
        maximumProductSubArray(array);
    }

    static void maximumProductSubArray(int[] arr) {
        int[][] intermediateResult = new int[2][arr.length];
        int MAX = arr[0];
        int finalI = 0;
        int finalJ = 0;

        initializeProductSubArrayIntemediate(arr, intermediateResult, MAX, finalI, finalJ);

        for (int k = 1; k <= arr.length; k++) {
            for (int j = k; j < arr.length; j++) {
                intermediateResult[1][j] = intermediateResult[0][j - 1] * arr[j];
                if (MAX < intermediateResult[1][j]) {
                    MAX = intermediateResult[1][j];
                    finalI = j - k;
                    finalJ = j;
                }
            }
            printSubArray(intermediateResult);
            transferContent(intermediateResult);
        }
        System.out.println(" MAX : " + MAX + " ," + finalI + " , " + finalJ);
    }

    private static void initializeProductSubArrayIntemediate(int[] arr, int[][] intermediateResult, int MAX, int finalI, int finalJ) {
        intermediateResult[0][0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            intermediateResult[0][i] = arr[i];
            if (MAX < arr[i]) {
                MAX = arr[i];
                finalI = i;
                finalJ = i;
            }
        }
        printSubArray(intermediateResult);
    }

    private static void printSubArray(int[][] intermediateResult) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < intermediateResult[0].length; j++)
                System.out.print(intermediateResult[i][j] + " ");
            System.out.println();
        }
        System.out.println();
        System.out.println();

    }

    private static void transferContent(int[][] intermediateResult) {
        for (int i = 0; i < intermediateResult[0].length; i++) {
            intermediateResult[0][i] = intermediateResult[1][i];
        }

    }
}
}
