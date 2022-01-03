package programmers.study.q1_6;
/*
자연수 n이 주어졌을 때, n의 다음 큰 숫자는 다음과 같이 정의 합니다.

조건 1. n의 다음 큰 숫자는 n보다 큰 자연수 입니다.
조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같습니다.
조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수 입니다.
예를 들어서 78(1001110)의 다음 큰 숫자는 83(1010011)입니다.

자연수 n이 매개변수로 주어질 때, n의 다음 큰 숫자를 return 하는 solution 함수를 완성해주세요.

제한 사항
n은 1,000,000 이하의 자연수 입니다.
 */
public class Solution {
    public int solution(int n) {
        int answer = getBiggerNumber(n);
        return answer;
    }

    private int getBiggerNumber(int n) {
        int length = Integer.toBinaryString(n).length();
        for (int i = 0; i < length-1; ++i) {
            int j = i + 1;
            if ((((n >>> i) & 1) ^ ((n >>> j) & 1)) == 1) {
                if (((n >>> i) & 1) == 1) {
                    int bitMask = (int) ((1L << i)|(1L << j));
                    int swapNum = bitMask^n;
                    int bitCount = Integer.bitCount(swapNum);
                    for (int k = swapNum; k>n; k--) {
                        if (bitCount == Integer.bitCount(k)) {
                            swapNum = k;
                        }
                    }
                    if (swapNum > n) {
                        return swapNum;
                    }
                }
            }
        }
        if (Integer.bitCount(n) < length) {
            return (int) (n + Math.pow(2, length) - Math.pow(2, length-1) - 1);
        } else {
            return (int) (n + Math.pow(2, length-1));
        }
    }
}
