package programmers.study.q1_5.solution3;

class Solution {
    static int answer = 0;
    static int NUM_OF_CHOICE = 3;
    static int sum = 0;

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println(solution.solution(nums));
    }

    public int solution(int[] nums) {
        choose(nums, 0, 0);
        return answer;
    }

    private void choose(int[] nums, int start, int count) {
        // 숫자 3개 고르기
        if (count == NUM_OF_CHOICE) {
            // 소수인지 확인하기
            if (isPrime(sum)) answer++;
            return;
        }
        for (int i = start; i < nums.length; i++) {
            sum += nums[i];
            choose(nums, i + 1, count + 1);
            sum -= nums[i];
        }
    }

    private boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}