package programmers.study.q4_3.solution2;

/**
 * 방향을 아래에서부터 위로 올라가면서 계산하는 방법
 */
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][triangle.length];
        int height = triangle.length;
        for (int i = 0; i < height; i++) {
            dp[height - 1][i] = triangle[height - 1][i];
        }

        for (int i = height-2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j+1]) + triangle[i][j];
            }
        }

        answer = dp[0][0];

        return answer;
    }
}