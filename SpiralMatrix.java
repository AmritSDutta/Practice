import java.util.LinkedList;
import java.util.List;

/* Tested in  LeetCode*/
public class SpiralMatrix {

    final int GO_LEFT = 0;
    final int GO_DOWN = 1;
    final int GO_UP = 2;
    final int GO_RIGHT = 3;
    int STATE = GO_LEFT;

    int RIGHT_WALL;
    int LEFT_WALL;
    int TOP_WALL;
    int BOTTOM_WALL;

    public static void main(String[] args) {

        int[][] array = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int[][] array1 = {
                {1, 2},
                {5, 6},
                {9, 10},
                {13, 14}};

        int[][] array2 = {
                {1, 2},
                {5, 6}
        };

        int[][] array3 = {
        };
        SpiralMatrix m = new SpiralMatrix();
        m.spiral(array);
        m.spiral(array1);
        m.spiral(array2);
        m.spiral(array3);
    }

    void spiral(int[][] matrix) {

        if (matrix.length == 0 || matrix[0].length == 0)
            return;
        RIGHT_WALL = matrix[0].length - 1;
        LEFT_WALL = 0;
        TOP_WALL = 0;
        BOTTOM_WALL = matrix.length - 1;

        int row = 0;
        int column = 0;
        STATE = GO_RIGHT;

        System.out.println();
        while (!(RIGHT_WALL < LEFT_WALL) && !(TOP_WALL > BOTTOM_WALL)) {
            if (STATE == GO_RIGHT) {
                System.out.print(matrix[row][column] + " ");

                if (column < RIGHT_WALL) {
                    column++;
                } else {
                    STATE = GO_DOWN;
                    TOP_WALL++;
                    row++;
                }
            } else if (STATE == GO_DOWN) {
                System.out.print(matrix[row][column] + " ");
                if (row < BOTTOM_WALL) {
                    row++;
                } else {
                    RIGHT_WALL--;
                    STATE = GO_LEFT;
                    column--;
                }
            } else if (STATE == GO_LEFT) {
                System.out.print(matrix[row][column] + " ");
                if (column > LEFT_WALL) {
                    column--;
                } else {
                    BOTTOM_WALL--;
                    STATE = GO_UP;
                    row--;
                }
            } else if (STATE == GO_UP) {
                System.out.print(matrix[row][column] + " ");
                if (row > TOP_WALL) {
                    row--;
                } else {
                    LEFT_WALL++;
                    STATE = GO_RIGHT;
                    column++;
                }
            }
        }
    }
    
    // for LeetCode
    List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        if (matrix.length == 0 || matrix[0].length == 0)
            return result;
        RIGHT_WALL = matrix[0].length - 1;
        LEFT_WALL = 0;
        TOP_WALL = 0;
        BOTTOM_WALL = matrix.length - 1;

        int row = 0;
        int column = 0;
        STATE = GO_RIGHT;

        while (!(RIGHT_WALL < LEFT_WALL) && !(TOP_WALL > BOTTOM_WALL)) {
            if (STATE == GO_RIGHT) {
                result.add(matrix[row][column]);
                if (column < RIGHT_WALL) {
                    column++;
                } else {
                    STATE = GO_DOWN;
                    TOP_WALL++;
                    row++;
                }
            } else if (STATE == GO_DOWN) {
                result.add(matrix[row][column]);
                if (row < BOTTOM_WALL) {
                    row++;
                } else {
                    RIGHT_WALL--;
                    STATE = GO_LEFT;
                    column--;
                }
            } else if (STATE == GO_LEFT) {
                result.add(matrix[row][column]);
                if (column > LEFT_WALL) {
                    column--;
                } else {
                    BOTTOM_WALL--;
                    STATE = GO_UP;
                    row--;
                }
            } else if (STATE == GO_UP) {
                result.add(matrix[row][column]);
                if (row > TOP_WALL) {
                    row--;
                } else {
                    LEFT_WALL++;
                    STATE = GO_RIGHT;
                    column++;
                }
            }
        }
        return result;
    }
}
