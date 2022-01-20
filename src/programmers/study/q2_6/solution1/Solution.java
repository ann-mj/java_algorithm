package programmers.study.q2_6.solution1;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[][] a = {{"yellowhat", "a"}, {"bluesunglasses", "a"},{"green_turban", "a"}};
        solution.solution(a);
    }

    static int answerCount = 0;
    static List<String> types = new ArrayList<>();
    static boolean[] visit;
    static List<List<String>> clothesList = new ArrayList<>();

    public int solution(String[][] clothes) {
        int answer = 0;
        for (int i = 0; i < clothes.length; i++) {
            if (types.contains(clothes[i][1])) {
                continue;
            }
            types.add(clothes[i][1]);
        }

        visit = new boolean[types.size()];
        for (int i = 0; i < types.size(); i++) {
            clothesList.add(new ArrayList<>());
        }

        for (int i = 0; i < types.size(); i++) {
            for (int j = 0; j < clothes.length; j++) {
                if (clothes[j][1].equals(types.get(i))) {
                    clothesList.get(i).add(clothes[j][0]);
                }
            }
        }

        for (int i = 1; i <= clothesList.size(); i++) {
            combination(0, 0, 0, i);
        }
        answer = answerCount;
        System.out.println(answer);
        return answer;
    }

    private void combination(int start, int startForCloth, int count, int maxCount) {
        if (count == maxCount) {
            answerCount++;
            return;
        }
        if (start == clothesList.size()) {
            return;
        }
        visit[start] = true;
        for (int i = 0; i < clothesList.get(start).size(); i++) {
            combination(start + 1, 0, count+1, maxCount);
        }
        visit[start] = false;
        combination(start + 1, 0, count, maxCount);
    }
}