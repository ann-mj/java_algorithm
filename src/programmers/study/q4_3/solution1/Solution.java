package programmers.study.q4_3.solution1;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][triangle.length];
        int height = triangle.length;
        dp[0][0] = triangle[0][0];
        dp[1][0] = dp[0][0] + triangle[1][0];
        dp[1][1] = dp[0][0] + triangle[1][1];
//        dp[2][0] = dp[1][0] + triangle[2][0];
//        dp[2][1] = Math.max(dp[1][0], dp[1][1]) + triangle[2][1];
//        dp[2][2] = dp[1][1] + triangle[2][2];

        for (int i = 2; i < triangle.length; i++) {

            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i-1][i-1] + triangle[i][i];
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }

        for (int i = 0; i < height; i++) {
            answer = Math.max(dp[height - 1][i], answer);
        }

        return answer;
    }
}