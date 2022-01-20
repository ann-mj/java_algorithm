package programmers.study.q3_3.solution1;

/*
* 테스트 코드만 통과한 코드
* */
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nub = {1, 1,1,1,1};
        System.out.println(solution.solution(nub, 3));
    }

    static int count = 0;

    public int solution(int[] numbers, int target) {
        int answer = 0;

        int[] visit = new int[numbers.length];
        dfs(numbers, visit, target);
        answer = count;
        return answer;
    }

    private void dfs(int[] numbers, int[] visit, int target) {
        int sum = getSum(numbers, visit);
        if (sum == target) {
            count++;
            return;
        }

        int[] copyVisit = new int[visit.length];
        System.arraycopy(visit, 0, copyVisit, 0, visit.length);

        for (int i = 0; i < numbers.length; i++) {
            if (copyVisit[i] > 1) continue;
            copyVisit[i]++;
            dfs(numbers, copyVisit, target);
            copyVisit[i]++;
        }
    }

    private int getSum(int[] numbers, int[] visit) {
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            if ((visit[i] % 2) == 0) {
                result += numbers[i];
            } else {
                result -= numbers[i];
            }
        }
        return result;
    }
}
