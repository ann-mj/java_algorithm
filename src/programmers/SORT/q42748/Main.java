package programmers.SORT.q42748;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution so = new Solution();
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        for (int a : so.solution(array, commands)) {
            System.out.println(a);
        }
    }

    static class Solution {
        public int[] solution(int[] array, int[][] commands) {
            int[] answer = new int[commands.length];

            for (int n = 0; n < commands.length; n++) {
                int candi = 0;

                // i , j , k 로 자르자.
                int i = commands[n][0];
                int j = commands[n][1];
                int k = commands[n][2];

                int[] sortedArray = new int[array.length];
                System.arraycopy(array, 0, sortedArray, 0, array.length);

                Arrays.sort(sortedArray, i-1, j);
//                System.out.println("======");
//                for (int a : sortedArray) {
//                    System.out.println(a);
//                }
//                System.out.println("======");
                candi = sortedArray[i + k - 2];
                answer[n] = candi;
            }
            return answer;
        }
    }
}


