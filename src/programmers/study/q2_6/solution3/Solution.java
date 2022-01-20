package programmers.study.q2_6.solution3;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[][] a = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        solution.solution(a);
    }

    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> pairs = new HashMap<>();
        for (String[] a : clothes) {
            pairs.put(a[1], pairs.getOrDefault(a[1], 1) + 1);
        }

        for (Integer count : pairs.values()) {
            answer *= count;
        }
        answer -= 1;
        return answer;
    }
}