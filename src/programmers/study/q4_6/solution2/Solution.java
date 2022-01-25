package programmers.study.q4_6.solution2;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] pe = {50, 50, 50,50, 40,30};
        System.out.println(solution.solution(pe,90));
    }
    public int solution(int[] people, int limit) {
        int answer = 0;
        int left = 0;
        int right = people.length - 1;

        Arrays.sort(people);

        while (left <= right) {
            if (left != right && people[left] + people[right] > limit) {
                right--;
                answer++;
                continue;
            }
            answer++;
            left++;
            right--;
        }

        return answer;
    }
}
