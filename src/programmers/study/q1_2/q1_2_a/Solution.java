package programmers.study.q1_2.q1_2_a;

import java.util.*;

public class Solution {
    public int[] solution(int[] answers) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] answerCount = {};

        // 찍기 배열
        ArrayList<int[]> persons = new ArrayList<>();
        persons.add(new int[]{1, 2, 3, 4, 5});
        persons.add(new int[]{2, 1, 2, 3, 2, 4, 2, 5});
        persons.add(new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5});

        int numOfStudents = persons.size();
        answerCount = new int[numOfStudents];

        for (int i = 0; i < answers.length; i++) {
            int ans = answers[i];
            for (int j = 0; j < numOfStudents; j++) {
                if (Integer.compare(ans, persons.get(j)[i % persons.get(j).length]) == 0) {
                    answerCount[j]++;
                }
            }
        }

        int max = findMax(answerCount);

        for (int i = 0; i < numOfStudents; i++) {
            if (max == answerCount[i]) {
                answer.add(i + 1);
            }
        }

        int[] result = answer.stream().mapToInt(a -> a).toArray();
        return result;
    }

    private int findMax(int[] arr) {
        int max = 0;
        // find min
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= max) {
                max = arr[i];
            }
        }
        return max;
    }
}
