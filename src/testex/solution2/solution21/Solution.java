package testex.solution2.solution21;

/*
* 보조 배터리 n개를 구입하려면 최소 얼마가 필요한지 구하려 합니다.
* 보조 배터리 제조사로부터 다양한 제품들에 대한 견적을 받았고,
* 그 견적에는 '판매 단위'와 '단위 가격'(판매 단위만큼 구입할 때의 가격)이 적혀있습니다.

예를 들어 판매 단위가 4이고,
* 단위 가격이 35,000원인 제품이 있습니다.
* 이 배터리는 4의 배수 개(4개, 8개, 12개...등)씩 주문해야 하며,
* 3세트(12개)를 주문하면 105,000원을 내야 합니다.

구입할 배터리 수 n, 보조 배터리 제조사가 보내온 견적이 담긴 배열 battery가 매개변수로 주어질 때,
* 배터리를 n개 이상 구매하기 위해서 필요한 최소 비용을 return 하도록 solution함수를 완성해주세요.
*
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 50;
//        int n = 20;
        int[][] bat = {{10, 100000}, {4, 35000}, {1, 15000}};
//        int[][] bat = {{6,30000},{3,18000},{4,28000},{1,9500}};
        System.out.println(solution.solution(n, bat));
    }

    static int minCost = Integer.MAX_VALUE;
    static List<Battery> batteryList;
    static int[] dp;
    static class Battery implements Comparable<Battery>{
        int set; // 몇개 단위
        int cost; // set 당 가격

        public Battery(int set, int cost) {
            this.set = set;
            this.cost = cost;
        }

        @Override
        public int compareTo(Battery o) {
            return cost/set - o.cost/o.set;
        }
    }

    public int solution(int n, int[][] battery) {
        int answer = 0;
        batteryList = new ArrayList<>();
        for (int i = 0; i < battery.length; i++) {
            Battery b = new Battery(battery[i][0], battery[i][1]);
            batteryList.add(b);
        }
        Collections.sort(batteryList);

        int max = 0;
        int cost = 0;
        Battery best = batteryList.get(0);
        while (max + best.set < n) {
            max += best.set;
            cost += best.cost;
        }


        int remain = n - max;
        // 50개이하로 선택한 이후에 하나씩 더해보면서 값 비교
        dfs(remain, 0, 0, 0);
        answer = minCost + cost;
        return answer;
    }

    private void dfs(int remain, int size, int cost, int index) {
        if (size >= remain) {
            // min Cost 업데이트
            minCost = Math.min(minCost, cost);
            return;
        }

        // 넣거나
        dfs(remain, size + batteryList.get(index).set, cost + batteryList.get(index).cost, index);

        // 안넣거나
        if(index + 1 < batteryList.size()) {
            dfs(remain, size, cost, index+1);
        }
    }
}
