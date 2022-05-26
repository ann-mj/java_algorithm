package programmers.HASH.q49191;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution so = new Solution();
        int[][] result = {{2,1}, {1,3}, {3,4}};
        int n = 4;
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
//            for (int i = 1; i <= n; i++) {
//                for (int j = 1; j <= n; j++) {
//                    System.out.print(graphs[i][j] + " ");
//                }
//                System.out.println();
//            }

            // 순위가 정해진 사람 찾기
            for (int i = 1; i <= n; i++) {
                boolean goal = true;
                goal = isGoal(n, graphs, i, goal); // i 가 goal 인지 아닌지. (순위가 정해지는지 아닌지)
                if (goal) {
                    goals.add(i); // i 번 사람은 순위가 정해진 사람이다.
                }
            }

            // 순위가 정해진 사람한테 지고, 순위가 정해진 사람한테 진 모든 사람과 경기를 한 사람
            for (Integer g : goals) {
                List<Integer> losers = new ArrayList<>();
                List<Integer> winners = new ArrayList<>();
                for (int i = 1; i <= n; i++) {
                    if (g == i) continue;
                    if (graphs[g][i] == 1) {
                        // i 는 g 한테 졌다.
                        losers.add(i);
                    } else if (graphs[g][i] == -1) {
                        winners.add(i);
                    }
                }
//                System.out.println("g1 : " + g);
                findGoal(n, losers, graphs);
//                System.out.println("g2 : " + g);
                findGoal(n, winners, graphs);
            }

            return goals.size();
        }

        private boolean isGoal(int n, int[][] graphs, int target, boolean goal) {
            for (int j = 1; j <= n; j++) {
                if (target == j) continue;
                if (graphs[target][j] == 0) {
                    goal = false;
                }
            }

            // i 가 1등 이거나
            boolean isFirst = true;
            Queue<Integer> queue = new LinkedList<>();
            boolean[] isWin = new boolean[n + 1];
            isWin[target] = true; // 자기를 이겼다고 치자.
            for (int j = 1; j <= n; j++) {
                if (target == j) continue;
                if (graphs[target][j] == 1) {
                    queue.add(j); // i 는 j 를 이겼다.
                    isWin[j] = true;
                }
            }
            while (!queue.isEmpty()) {
                Integer poll = queue.poll();
                for (int j = 1; j <= n; j++) {
                    if (poll == j) continue;
                    if (graphs[poll][j] == 1) {
                        queue.add(j); // poll 은 j 를 이겼다.
                        isWin[j] = true; // 그러면 i 도 j 를 이긴것이다.
                    }
                }
            }
            for (int i = 1; i <= n; i++) {
                if (isWin[i] == false) {
                    isFirst = false;
                }
            }



            // i 가 꼴찌 이거나
            boolean isLast = true;
            Queue<Integer> loseQueue = new LinkedList<>();
            boolean[] isLose = new boolean[n + 1];
            isLose[target] = true; // 자기를 이겼다고 치자.
            for (int j = 1; j <= n; j++) {
                if (target == j) continue;
                if (graphs[target][j] == -1) {
                    loseQueue.add(j); // i 는 j 에게 졌다.
                    isLose[j] = true;
                }
            }
            while (!loseQueue.isEmpty()) {
                Integer poll = loseQueue.poll();
                for (int j = 1; j <= n; j++) {
                    if (poll == j) continue;
                    if (graphs[poll][j] == -1) {
                        queue.add(j); // poll 은 j 를 이겼다.
                        isLose[j] = true; // 그러면 i 도 j 를 이긴것이다.
                    }
                }
            }
            for (int i = 1; i <= n; i++) {
                if (isLose[i] == false) {
                    isLast = false;
                }
            }

//            System.out.println("goal : " + goal + " isFirst : " + isFirst + " isLast : " + isLast);
            return goal || isFirst || isLast;
        }

        private void findGoal(int n, List<Integer> targets, int[][] graphs) {
//            System.out.println("targets");
//            for (Integer t : targets) {
//                System.out.print(t + " ");
//            }
//            System.out.println("====");

            for (Integer l : targets) {
                boolean isGoal = true; // 1 은 2 한테 진사람이다.


                for (Integer t : targets) {
                    if (targets.size() == 1 && l.equals(t)) {
                        isGoal = false;
                        continue;
                    }
                    if(l.equals(t)) continue;
                    if (graphs[l][t] == 0) {
                        // 경기를 안했다는 뜻이니까 false
                        isGoal = false;
                        break;
                    }
                }
                if (isGoal) {
                    goals.add(l);
                }
            }
        }
    }
}


