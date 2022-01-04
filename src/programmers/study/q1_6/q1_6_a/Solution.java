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