package programmers.study.q1_2;

import java.util.*;

public class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        ArrayList<Integer> beforeAnswer = new ArrayList<>();
        int[] personOne = {1, 2, 3, 4, 5};
        int[] personTwo = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] personThree = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int collectOne = 0, collectTwo = 0, collectThree = 0;

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] == personOne[i % personOne.length]) collectOne++;
            if(answers[i] == personTwo[i % personTwo.length]) collectTwo++;
            if(answers[i] == personThree[i % personThree.length]) collectThree++;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, collectOne);
        map.put(2, collectTwo);
        map.put(3, collectThree);

        ArrayList<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if(Integer.compare(o1.getValue(), o2.getValue()) == 0) {
                    return o1.getKey() - o2.getKey();
                }
                return o2.getValue() - o1.getValue();
            }
        });
        int max = entries.get(0).getValue();
        for (int i = 0; i < entries.size(); i++) {
            if (max == entries.get(i).getValue()) {
                beforeAnswer.add(entries.get(i).getKey());
            }
        }

        answer = new int[beforeAnswer.size()];
        for (int i = 0; i < beforeAnswer.size(); i++) {
            answer[i] = beforeAnswer.get(i);
        }
        return answer;
    }
}
