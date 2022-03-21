package data_structure.q1655.solution1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * pq를 인덱스 순서대로 접근한다면, 내가 원하는 결과가 안나온다.
 * 내가 원하는 결과를 보려면, pq.poll() 을 해서 가져와야 한다.!!!
 * pq 는 인덱스 순서대로 정렬되어 있는 것이 아니기 때문이다.
 * 
 * 시간 초과
 * */
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        List<Integer> candi = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());        // 입력
            pq.addAll(candi);                               // candi 에 있던 원소 전부 추가
            pq.add(Integer.parseInt(st.nextToken()));       // candi + 새로운 입력 추가
            candi.clear();                                  // candi 는 초기화 시킴
            while (!pq.isEmpty()) {
                Integer p = pq.poll();
                candi.add(p);
            }
            sb.append(candi.get((candi.size() - 1) / 2))    // 가운데 값 출력
                    .append("\n");
        }
        System.out.println(sb);
    }
}
