package programmers.study.q4_2.solution1;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(4));
    }

    public int solution(int n) {
        int answer = 0;
        long[] dp = new long[5001];
        dp[1] = 0;
        dp[2] = 3;
        dp[3] = 0;
        for (int i = 4; i <= n; i+=2) {
            dp[i] = (dp[i - 2] * 3);
            for (int j = i-4; j > 0; j-=2) {
                dp[i] += (dp[j] * 2);
            }
            dp[i] = (dp[i] + 2) % 1000000007;
        }
        answer = (int) dp[n];
        return answer;
    }
}