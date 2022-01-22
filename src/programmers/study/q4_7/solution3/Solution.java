package programmers.study.q4_7.solution3;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] t = {7,10};
        System.out.println(solution.solution(6,t));
    }
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 1;
        long right = (long) n * times[times.length - 1];
        while (left <= right) {
            long sum = 0; /* 입국심사를 통과한 총 입국자 수 */
            long mid = (left + right) / 2;
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }

            if (sum < n) { /* 입국자가 적으므로, 시간을 더 늘려서 더 통과해야 한다. 탐색범위를 mid 이후로 다시 탐색 */
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }
}
