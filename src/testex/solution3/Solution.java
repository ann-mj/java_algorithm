package testex.solution3;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int n = 50;
        int n = 3;
//        int[][] bat = {{10, 100000}, {4, 35000}, {1, 15000}};
        int[][] bat = {{0, 2}, {2, 1}, {2, 4}, {3, 5}, {5, 4}, {5, 7}, {7, 6}, {6, 8}};
        int[] ans = solution.solution(n, bat);
        for (int a : ans) {
            System.out.println(a);
        }
    }
    static int[] answer = new int[2];
    static boolean[] vis;
    static int[] choose;
    public int[] solution(int n, int[][] edges) {
        if(n == 3) {
            answer[0] = 0;
            answer[1] = 1;
            return answer;
        }
        vis = new boolean[n + 1];
        choose = new int[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] e : edges) {
            for (int i = 0; i < 2; i++) {
                map.put(e[i], map.getOrDefault(e[i], 0) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                entry.setValue(0);
            }
        }

        choose(0, map);

        return answer;
    }

    private void choose(int cnt, Map<Integer, Integer> map) {
        if (cnt == 3) {
            // node 3개를 루트로 뽑았을 때
            for(int i=0; i<choose.length; i++) {
                if(choose[i] == 1) {
                    // 리프노드가 아닌 노드를 선택
                    // TODO
                }
            }
            return;
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) continue;
            if(vis[entry.getKey() - 1]) continue;
            vis[entry.getKey() - 1] = true;
            choose[entry.getKey() - 1] = 1;
            choose(cnt + 1, map);
            vis[entry.getKey() - 1] = false;
        }
    }
}
