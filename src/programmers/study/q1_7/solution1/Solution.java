package programmers.study.q1_7.solution1;

import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] sortedAnswer = new int[array.length];

        for (int i = 0; i < commands.length; i++) {

            /* 원래 배열 깊은 복사 */
            for (int j = 0; j < array.length; j++) {
                sortedAnswer[j] = array[j];
            }

            int startIndex = commands[i][0] - 1;
            int endIndex = commands[i][1] - 1;
            Arrays.sort(sortedAnswer, startIndex, endIndex + 1); /* i 부터 j 까지 정렬 */
            answer[i] = sortedAnswer[startIndex + commands[i][2] - 1];
        }

        return answer;
    }
}
