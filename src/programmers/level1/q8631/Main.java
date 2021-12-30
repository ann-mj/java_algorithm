package programmers.level1.q8631;

public class Main {
    public long solution(int price, int money, int count) {
        long answer = -1;
        long total = 0;
        for (int i = 1; i <= count; i++) {
            total += i * price;
        }

        return (total - money > 0) ? total - money : 0;
    }
}

