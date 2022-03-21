package BOJ.Challenge;

class Solution {
    public static void main(String[] args) {
        int money = 1999;
        int[] costs = {2, 11, 20, 100, 200, 600};
        Solution sol = new Solution();
        System.out.println(sol.solution(money,costs));
    }
    public int solution(int money, int[] costs) {
        int answer = 0;
        int[] dp = new int[money + 1];

        for(int i=1; i<=money; i++) {
            long min1;
            long min5;
            long min10;
            long min50;
            long min100;
            long min500;
            long min = (long)5e9;
            if(i-1 >= 0) {
                min1 = dp[i-1] + costs[0];
                min = min1;
            }

            if (i - 5 >= 0) {
                min5 = dp[i-5] + costs[1];
                min = Math.min(min, min5);
            }
            if (i - 10 >= 0) {
                min10 = dp[i-10] + costs[2];
                min = Math.min(min, min10);
            }

            if (i - 50 >= 0) {
                min50 = dp[i-50] + costs[3];
                min = Math.min(min, min50);
            }

            if (i - 100 >= 0) {
                min100 = dp[i-100] + costs[4];
                min = Math.min(min, min100);
            }
            if (i - 500 >= 0) {
                min500 = dp[i-500] + costs[5];
                min = Math.min(min, min500);
            }
            dp[i] = (int) min;
        }
        return dp[money];
    }
}
