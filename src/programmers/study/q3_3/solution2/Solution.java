package programmers.study.q3_3.solution2;

class Solution {
    static int answer = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, 0, target);
        return answer;
    }

    private void dfs(int[] numbers, int count, int sum, int target) {
        if (count == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        dfs(numbers, count + 1, sum + numbers[count], target);
        dfs(numbers, count + 1, sum - numbers[count], target);
    }
}