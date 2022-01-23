package programmers.study.q4_5.solution1;

import javax.sound.midi.Soundbank;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] lost = {1,2,3};
        int[] re = {1};
        System.out.println(solution.solution(4, lost, re));
    }

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] students = new int[n + 1];  /* 1번 부터 n번 까지 */
        for (int i = 1; i <= n; i++) {
            students[i] = 1; // 체육복 개수를 1로 초기화

            /* 여벌 옷 가져온 사람 */
            for (int j = 0; j < reserve.length; j++) {
                if (i == reserve[j]) {
                    students[i]++;
                    break;
                }
            }

            /* 분실한 사람 */
            for (int k = 0; k < lost.length; k++) {
                if (i == lost[k]) {
                    students[i]--;
                    break;
                }
            }
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