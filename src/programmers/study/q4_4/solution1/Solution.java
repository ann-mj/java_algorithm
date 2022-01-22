package programmers.study.q4_4.solution1;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] money = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(solution.solution(money));
    }

    public int solution(int[] money) {
        int answer = 0;
        int[][] dp = new int[money.length][2]; // 0 : 첫번째꺼 포함 , 1 : 첫번째꺼 미포함
        dp[0][0] = money[0];
        dp[0][1] = 0;
        dp[1][0] = dp[0][0];
        dp[1][1] = money[1];

        for (int i = 2; i < money.length; i++) {
            dp[i][0] = Math.max(dp[i - 2][0] + money[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 2][1] + money[i], dp[i - 1][1]);
        }
        answer = Math.max(dp[money.length - 2][0], dp[money.length - 1][1]);
        return answer;
    }
}