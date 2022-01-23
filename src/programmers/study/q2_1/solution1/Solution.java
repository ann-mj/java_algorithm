package programmers.study.q2_1.solution1;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }

            if (count < 0) {
                return false;
            }
        }
        if (count > 0) {
            answer = false;
        }
        return answer;
    }
}
