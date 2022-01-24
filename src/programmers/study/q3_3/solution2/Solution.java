package programmers.study.q3_3.solution2;

class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, 0, target);
    }

    private int dfs(int[] numbers, int count, int sum, int target) {
        if (count == numbers.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }

        return dfs(numbers, count + 1, sum + numbers[count], target)
                + dfs(numbers, count + 1, sum - numbers[count], target);
    }
}