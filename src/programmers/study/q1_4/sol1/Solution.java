package programmers.study.q1_4.sol1;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int person = 0;
        int sequence = 1;
        // 1번째 사람은 무조건 통과 이므로 index 는 1부터 시작
        for (int i = 1; i < words.length; i++) {
            person = ((i) % n) + 1; // 사람 번호는 index + 1
            if (i % n == 0) {
                sequence++;
            }
            // 앞사람이 말한 문자의 마지막 문자를 처음으로 말했는가?
            // 이전에 문자가 반복되진 않았는가?
            if (!checkValidate(words,i)) {
                answer[0] = person;
                answer[1] = sequence;
                return answer;
            }
        }

        answer[0] = 0;
        answer[1] = 0;
        return answer;
    }

    private boolean checkValidate(String[] words, int i) {
        return !isDuplicated(words, i) && checkToldLastCharacter(words, i);
    }

    private boolean isDuplicated(String[] words, int sequence) {
        for (int i = 0; i < sequence; i++) {
            if (words[sequence].equals(words[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkToldLastCharacter(String[] words, int index) {
        return words[index-1].charAt(words[index-1].length() - 1) == words[index].charAt(0);
    }
}