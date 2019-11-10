package com.company.snakeLadder;

public class NQueen {

    int count = 0;

    public static void main(String[] args) {
        int n = 8;
        NQueen nQueen = new NQueen();
        nQueen.solveNQueens(n);
        System.out.println(" Total Solutions :" + nQueen.count );
    }

    public void solveNQueens(int n) {
        boolean board[][] = new boolean[n][n];
        getNQueenSolution(board, 0, 0);
    }

    void getNQueenSolution(boolean board[][], int row, int column) {
        final int length = board[0].length;
        for (int j = 0; j < length; j++) {
            boolean newBoard[][] = copyArray(board);
            
            if (isValid(board, row, j)) {
                newBoard[row][j] = true;
                if (row == length - 1) {
                    count++;
                    printBoard(newBoard);
                } else {
                    getNQueenSolution(newBoard, row + 1, 0);
                }
            }
        }
    }

    private void printBoard(boolean[][] newBoard) {
        int length = newBoard[0].length;
        System.out.println("Soution : ");

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {

                System.out.print(newBoard[i][j] == true ? 'Q' + " " : 0 + " ");
            }
            System.out.println();
        }
    }

    private boolean isValid(boolean[][] board, final int row, final int column) {
        int length = board[0].length;

        for (int k = 0; k < row; k++) {
            if (board[k][column])
                return false;
        }

        int quantum = 1;
        while (row - quantum >= 0 && column - quantum >= 0) {
            if (board[row - quantum][column - quantum])
                return false;
            quantum++;
        }

        quantum = 1;
        while (row - quantum >= 0 && column + quantum < length) {
            if (board[row - quantum][column + quantum])
                return false;
            quantum++;
        }

        return true;
    }


    boolean[][] copyArray(boolean[][] old) {
        boolean[][] destination = new boolean[old.length][];
        for (int i = 0; i < old.length; ++i) {
            destination[i] = new boolean[old[i].length];
            System.arraycopy(old[i], 0, destination[i], 0, destination[i].length);
        }
        return destination;

    }
}
