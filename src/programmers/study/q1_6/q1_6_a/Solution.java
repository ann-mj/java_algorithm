package programmers.study.q1_6.q1_6_a;

public class Solution {
    public int solution(int n) {
        int answer = getBiggerNumber(n);
        return answer;
    }

    private int getBiggerNumber(int n) {
        int bitCount = Integer.bitCount(n);
        for (int i = n + 1; ; i++) {
            if (bitCount == Integer.bitCount(i)) {
                return i;
            }
        }
    }
}