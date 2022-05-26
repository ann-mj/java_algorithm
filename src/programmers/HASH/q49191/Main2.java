package programmers.HASH.q49191;

import java.util.*;

public class Main2 {

    public static void main(String[] args) {
        Solution so = new Solution();
        int[][] result = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        int n = 5;
        System.out.println(so.solution(n, result));
    }

    static class Solution {
        static Set<Integer> goals = new HashSet<>();

        public int solution(int n, int[][] results) {
            int answer = 0;

            int[][] graphs = new int[n + 1][n + 1];
            // 0 은 무시
            for (int i = 0; i < results.length; i++) {
                graphs[results[i][0]][results[i][1]] = 1; // A,B 에서 A가 B를 이겼으므로 1로 표시
                graphs[results[i][1]][results[i][0]] = -1; // A,B 에서 B가 A에게 졌으므로 -1로 표시
            }
            // 순위가 정해진 사람 찾기
            // 자기 자신 빼고 다 방문을 할 수 있어야 한다.

            for (int i = 1; i <= n; i++) {
                boolean isGoal = true;
                Queue<Integer> loseQueue = new LinkedList<>();
                boolean[] vis = new boolean[n + 1];
                loseQueue.add(i);
                vis[i] = true; // 자기 자신도 졌다고 치자.
                while (!loseQueue.isEmpty()) {
                    Integer poll = loseQueue.poll();
                    for (int j = 1; j <= n; j++) {
                        if(poll == j) continue;
                        if(vis[j]) continue;
                        if (graphs[poll][j] == 1) { // poll 은 j 를 계속 이겨야 한다.
                            loseQueue.add(j);
                            vis[j] = true;
                        }
                    }
                }

                Queue<Integer> winQueue = new LinkedList<>();
                winQueue.add(i);
                while (!winQueue.isEmpty()) {
                    Integer poll = winQueue.poll();
                    for (int j = 1; j <= n; j++) {
                        if(poll == j) continue;
                        if(vis[j]) continue;
                        if (graphs[poll][j] == -1) { // poll 은 j 를 계속 이겨야 한다.
                            winQueue.add(j);
                            vis[j] = true;
                        }
                    }
                }

                for (int j = 1; j <= n; j++) {
                    if (!vis[j]) {
                        isGoal = false;
                        break;
                    }
                }
                if(isGoal) {

                    answer++;
                }
            }
            return answer;
        }
    }
}


