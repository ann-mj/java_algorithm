package interview.chapter5.q5_0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input.add(3);
        input.add(2);
        input.add(5);
        input.add(6);
        input.add(7);
        input.add(1);
        input.add(8);
        evenOdd(input);
    }

    static void evenOdd(List<Integer> A) {
        int nextEven = 0, nextOdd = A.size() - 1;
        while (nextEven < nextOdd) {
            if (A.get(nextEven) % 2 == 0) {
                nextEven++;
            } else {
                Collections.swap(A,nextEven, nextOdd--);
            }
        }
    }
}
