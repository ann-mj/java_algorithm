package programmers.level1.q2544;

import javax.swing.border.MatteBorder;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] answers = {1, 2, 3, 4, 5};
        solution(answers);
    }
    static int[] solution(int[] answers) {
        ArrayList<Integer> answer0 = new ArrayList<>();
        int[] answer = {};
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] count = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] == one[i%5]) {
                count[0]++;
            }
            if(answers[i] == two[i%8]) {
                count[1]++;
            }
            if(answers[i] == three[i%10]) {
                count[2]++;
            }
        }

        int max = 0;
        for (int i = 0; i < 3; i++) {
            if(count[i] > max) {
                max = count[i];
            }
        }

        for (int i = 0; i < 3; i++) {
            if(max == count[i]) {
                answer0.add(i);
            }
        }

        answer = new int[answer0.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answer0.get(i) + 1;
        }
        return answer;
    }
}
