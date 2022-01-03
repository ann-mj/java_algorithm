package programmers.study.q1_1;

public class Solution {
        public boolean solution(int x) {
            int sum = getDigitSum(x);
            return (x % sum == 0);
        }

        private int getDigitSum(int x) {
            return Integer.toString(x).chars()
                    .map(ch -> Character.getNumericValue(ch))
                    .sum();
        }
}
