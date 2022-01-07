package programmers.study.q1_6.q1_6_b;

public class Solution {
    public int solution(int n) {
        int answer = 0;
//        int bitCount = Integer.bitCount(n);
        int bitCount = getBitCount(n);
        for (int i = n + 1; ; i++) {
            if (bitCount == Integer.bitCount(i)) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    // bit Count 구현한다면 ?
    public int getBitCount(int n) {
        int result = 0;
        while (n != 0) {
            if (((n) & 1) == 1) {
                result++;
            }
            n >>= 1;
        }
        return result;
    }
}
