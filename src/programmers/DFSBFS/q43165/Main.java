package programmers.DFSBFS.q43165;

public class Main {
    static int ans = 0;
    public int solution(int[] numbers, int target) {
        comb(numbers, 0, 0, target);
        return ans;
    }

    public void comb(int[] numbers, int start, int sum, int target) {
        if (start == numbers.length) {
            if (sum == target) {
                ans++;
            }
        }

        if (start == numbers.length) return;
        comb(numbers, start + 1, sum + numbers[start], target);
        comb(numbers, start + 1, sum - numbers[start], target);
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1};
        int target = 4;
        Main solution = new Main();
        System.out.println(solution.solution(nums, target));
    }


}


