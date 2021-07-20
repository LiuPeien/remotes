package com.leetcode.problems.array;

import java.util.concurrent.locks.ReentrantLock;

public class KthLargestValue {
    public static int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, res = matrix[0][0];
        for(int j = 1; j < n; j++)
            matrix[0][j] = matrix[0][j] ^ matrix[0][j-1];
        for(int i = 1; i < m; i++)
            matrix[i][0] = matrix[i][0] ^ matrix[i-1][0];

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i][j] ^ matrix[i-1][j] ^ matrix[i][j-1] ^ matrix[i-1][j-1];

                if(matrix[i][j] > res)
                    res = matrix[i][j];
            }
        }
        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {{5,2}, {1,6}};
        KthLargestValue.kthLargestValue(matrix, 1);
    }
}
