package data_structure.q1655.solution2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> bigger = new PriorityQueue<>(); // 작은 것 우선
        PriorityQueue<Integer> equalOrSmaller = new PriorityQueue<>(new Comparator<Integer>() {   // 큰 것 우선
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        st = new StringTokenizer(br.readLine());
        int mid = Integer.parseInt(st.nextToken());
        sb.append(mid).append('\n');                                // 초기값을 넣어준다.

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int candi = Integer.parseInt(st.nextToken());           // 중간값이 될 수 있는 후보

            if(candi > mid) {
                bigger.add(candi);                                  // 현재 중간값 보다 크다면, bigger 에 넣는다.

                if(bigger.size() > equalOrSmaller.size() + 1) {
                    equalOrSmaller.add(mid);                        // 현재 중간값을 넣고
                    mid = bigger.poll();                            // bigger 에서 제일 작은 값이 중간이 된다.
                }
            } else {
                equalOrSmaller.add(candi);                          // 현재 중간값 보다 같거나 작다면, equal or smaller 에 넣는다.

                if(equalOrSmaller.size() > bigger.size()) {         // 작거나 같은 수가 큰 개수보다 더 많다면
                    bigger.add(mid);                                // 현재 중간값을 큰 수로 넣고
                    mid = equalOrSmaller.poll();                    // 작거나 같은 수 중에서 가장 큰 수를 중간값으로 업데이트한다.
                }
            }
            sb.append(mid).append('\n');
        }
        System.out.println(sb);
    }
}
