package programmers.HASH.q42578;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution so = new Solution();
        String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(so.solution(clothes));
    }

    static class Solution {
        public int solution(String[][] clothes) {
            int answer = 1;

            Map<String, List<String>> map = new HashMap<>();
            for (String[] a : clothes) {
                map.computeIfAbsent(a[1], k -> new ArrayList<>());
                map.get(a[1]).add(a[0]);
            }

            Set<Map.Entry<String, List<String>>> entries = map.entrySet();
            for (Map.Entry<String, List<String>> a : entries) {
                answer *= (a.getValue().size() + 1);
            }

            return answer - 1;
        }
    }
}


