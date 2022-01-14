package programmers.study.q1_5.solution1;

class Solution {
    static int answer = 0;
    static int NUM_OF_CHOICE = 3;
    static Integer[] chosenNumbers = new Integer[3];

    static int cnt;
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println(solution.solution(nums));
    }

    public int solution(int[] nums) {
        choose(nums, 0, 0);
        System.out.println("cnt = " + cnt);
        return answer;
    }

    private void choose(int[] nums, int start, int count) {
        cnt++;
        // 숫자 3개 고르기
        if (count == NUM_OF_CHOICE) {
            int sum = 0;
            for (int i = 0; i < NUM_OF_CHOICE; i++) {
                sum += chosenNumbers[i];
            }
            // 소수인지 확인하기
            if (isPrime(sum)) answer++;
            return;
        }
        if (start != nums.length) {
            chosenNumbers[count] = nums[start];
            choose(nums, start + 1, count + 1);

            chosenNumbers[count] = nums[start];
            choose(nums, start + 1, count);
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