package programmers.study.q4_5.solution2;

import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] students = new int[n + 1];  /* 1번 부터 n번 까지 */
        Arrays.fill(students, 1); // 체육복 개수를 1로 초기화

        for (int reservedPerson : reserve) {
            students[reservedPerson]++;
        }
        for (int lostPerson : lost) {
            students[lostPerson]--;
        }

        for (int i = 1; i <= n; i++) {
            // 1인 경우 continue
            if (students[i] == 1) continue;

            // 분실한 경우
            if (students[i] == 0) {
                if (students[i - 1] > 1) {
                    students[i - 1]--;
                    students[i]++;
                    continue;
                }

                if ((i+1) <= n && students[i + 1] > 1) {
                    students[i + 1]--;
                    students[i]++;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (students[i] >= 1) {
                answer++;
            }
        }
        return answer;
    }
}