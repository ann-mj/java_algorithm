package programmers.study.q1_6.q1_6_c;

public class Solution {
    public int solution(int n) {
        int bitCount = getBitCount(n);
        while (bitCount != getBitCount(++n));
        return n;
    }

    private int getBitCount(int n) {
        int result = 0;
        while (n != 0) {
            if ((n & 1) == 1) result++;
            n >>= 1;
        }
        return result;
    }
}