package boj.q5052;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        input();
    }

    static int t;
    static int n;
    static ArrayList<String> inputs = new ArrayList<>();

    public static void input() {
        Scanner scan = new Scanner(System.in);
        t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            n = scan.nextInt();
            inputs.clear();
            for (int j = 0; j < n; j++) {
                inputs.add(scan.next());
            }
            process(inputs);
        }
    }

    // startsWith 함수
    // String 을 sort 하면?
    public static void process(ArrayList<String> numbers) {
        Collections.sort(numbers);
        System.out.println(Arrays.deepToString(numbers.toArray()));
        boolean isConsistent = true;

        for (int i = 0; i < numbers.size() - 1; i++) {
            if(numbers.get(i+1).startsWith(numbers.get(i))) {
                isConsistent = false;
            }
        }

        System.out.println(isConsistent ? "YES" : "NO");
    }
}
