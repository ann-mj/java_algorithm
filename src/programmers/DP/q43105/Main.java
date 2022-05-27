package programmers.DP.q43105;

public class Main {

    public static void main(String[] args) {
        Solution so = new Solution();
        int[][] tri = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(so.solution(tri));
    }

    static class Solution {
        public int solution(int[][] triangle) {
            int answer = 0;
            int[][] dp = new int[501][501];

            // init
            int height = triangle.length;
            if (height == 1) {
                answer = triangle[0][0];
                return answer;
            }

            if (height == 2) {
                answer = Math.max(triangle[0][0] + triangle[1][0], triangle[0][0] + triangle[1][1]);
                return answer;
            }
            init(dp, triangle);

            for (int i = 3; i <= height; i++) {
                int width = triangle[i - 1].length; // 가로의 길이
                for (int j = 1; j <= width; j++) {
                    if (j == 1) {
                        dp[i][j] = dp[i - 1][j] + triangle[i - 1][j - 1];
                    } else if (j == width) {
                        dp[i][j] = dp[i - 1][j - 1] + triangle[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i - 1][j - 1];
                    }
                }
            }

            int max = 0;
            for (int i = 1; i < dp[height].length; i++) {
                max = Math.max(max, dp[height][i]);
            }

            answer = max;
            return answer;
        }

        private void init(int[][] dp, int[][] triangle) {
            dp[1][1] = triangle[0][0];

            if (triangle.length > 1) {
                dp[2][1] = dp[1][1] + triangle[1][0];
                dp[2][2] = dp[1][1] + triangle[1][1];
            }
        }
    }
}


