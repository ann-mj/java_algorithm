package BOJ.Challenge.q3;

/*
* 가로 1칸, 세로 1칸의 크기를 갖는 정사각형으로 이루어진 가로 width칸, 세로 height칸의 격자가 있습니다. 일부 정사각형에는 "왼쪽 위의 점과 오른쪽 아래점을 잇는" 대각선이 있습니다. 이 격자에서 다음 조건을 만족하는 경로의 개수를 구하고자 합니다.

좌측 하단의 끝점에서 우측 상단의 끝점으로 가는 경로입니다.
대각선을 정확히 1번 이용해야 합니다.
1, 2번 조건을 만족하는 전제 하에서 최단거리 경로여야 합니다.
예를 들어, 다음 그림은 한 격자가 주어졌을 때, 해당 격자에서 1~3번 조건을 만족하는 경로를 모두 나타낸 것입니다.
*
*격자의 가로 길이 width, 세로 길이 height, 대각선이 위치한 정사각형의 정보 diagonals가 매개변수로 주어집니다. 주어진 조건을 모두 만족하는 경로의 개수를 10,000,019로 나눈 나머지를 return 하도록 solution 함수를 완성해주세요.
* */

class Solution {
    public static void main(String[] args) {
        int[][] costs = {{1,1}};
        Solution sol = new Solution();
        System.out.println(sol.solution(1,1,costs));
    }
    public int solution(int width, int height, int[][] diagonals) {
        int[][][] dp = new int[height+1][width+1][diagonals.length+1];  // 0은 대각선 x , 1은 대각선 o
        dp[1][0][0] = 1;
        dp[0][1][0] = 1;
        for(int y=1; y<=height; y++) {
            for(int x=1; x<=width; x++) {
                dp[y][x][0] = dp[y - 1][x][0] + dp[y][x - 1][0];
                for(int d=0; d<diagonals.length; d++) {
                    if(diagonals[d][0] <= height && diagonals[d][1] <= width) {
                        dp[y][x][d+1] = dp[y - 1][x][0] + dp[y][x - 1][0] + dp[y - 1][x-1][0];
                    }
                }
            }
        }
        int sum = 0;
        for(int i=1; i<=diagonals.length; i++) {
            sum += dp[height][width][i];
        }
        return sum;
    }
}