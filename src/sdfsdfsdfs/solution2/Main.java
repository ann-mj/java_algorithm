package sdfsdfsdfs.solution2;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    final int READ = 0;
    final int WRITE = 1;
    static String[] ar; // 인덱스 0 부터 시작
    static class Solution {
        public String[] solution(String[] arr, String[] processes) {
            String[] answer = {};
            List<String> goal = new ArrayList<>();
            
            ar = new String[arr.length];
            for(int i=0; i<arr.length; i++) {
                ar[i] = arr[i];
            }
            PriorityQueue<Msg> pq = new PriorityQueue<>();
            for(String p : processes) {
                StringTokenizer st = new StringTokenizer(p);
                String type = st.nextToken();
                int t = 0;
                if(type.charAt(0) == 'w') t =1;
                Msg msg;
                if(t == 0) {
                    msg = new Msg(t, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
                } else {
                    msg = new Msg(t, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }
                pq.add(msg);
            }

            for(Msg q: pq) {
                System.out.println(q);
            }
            List<Msg> readQueue = new ArrayList<>();
            List<Msg> writeQueue = new ArrayList<>();
            int time = 0;
            while (!pq.isEmpty()) {
                time++;
                // 매 초마다 확인
                Msg cur = pq.peek();

                if(cur.t1 == time) {
                    // 시간이 되었으니 수행한다.
                    // 수행 하기 전 체크
                    // readQueue
                    if(writeQueue.size() > 0) {
                        //  쓰기 진행 중일 때는 새로운 읽기, 쓰기 요청 프로세스는 모두 대기
                        cur.t1++; //
                        continue;
                    }
                    if(readQueue.size() == 0 && cur.type == 0) {
                        cur = pq.poll();
                        readQueue.add(cur);
                        continue;
                    }
                    if(readQueue.size() > 0 && cur.type == 1) {
                        //  읽기 진행 중일 때는 새로운 쓰기 요청 프로세스는 모두 대기
                        cur.t1++;
//                        cur = pq.poll();
//                        writeQueue.add(cur);
                        continue;
                    }
                    if(readQueue.size() > 0 && cur.type == 0) {
                        //  읽기 진행 중일 때는 새로운 읽기 요청 프로세스는 실행 가능
                        cur = pq.poll();
                        readQueue.add(cur);
                    }
                }

            }

            // 쓰기는 한 번에 하나만 가능
            // 쓰기가 대기 중인 경우 새로운 읽기 요청 대기
            // 읽기 작업보다 쓰기 작업을 먼저 수행한다.
            // 쓰기 작업이 여러개라면 먼저 요청된 쓰기 작업을 먼저 수행한다.

            // 끝남과 동시에 새로운 요청이 들어오면, 새 작업요청을 포함시킨 다음에 이 중에서 작업을 선택한다.


            // 출력 : 읽기, 쓰기 작업에서 읽은 내용


            return answer;
        }
    }

    static class Msg implements Comparable<Msg>{
        @Override
        public String toString() {
            return "Msg{" +
                    "type=" + type +
                    ", t1=" + t1 +
                    ", t2=" + t2 +
                    ", from=" + from +
                    ", to=" + to +
                    ", data=" + data +
                    ", status=" + status +
                    '}';
        }

        int type; // read , write
        int t1; // 시작
        int t2; // 남은
        int from;  // array
        int to; // array
        int data; // 있거나 없거나
        int status = 1; // 시작, 대기 0 또는 1

        public Msg(int type, int t1, int t2, int from, int to, int data) {
            this.type = type;
            this.t1 = t1;
            this.t2 = t2;
            this.from = from;
            this.to = to;
            this.data = data;
        }

        void stop(int time) {
            // time 기준으로 잔여시간 계산하자.
            status = 1;
        }

        void start(int time) {
            // time 기준으로 잔여시간 계산하자.
            status = 0;
        }

        boolean doing() {
            // 반환값이 true 이면 큐에서 뺌.
            if(status == 0) {
                t2--;
                if(t2 > 0) return false;
                return true; // true 이면 끝남
            } else {
                return false;
            }
        }

        @Override
        public int compareTo(Msg o) {
            if (this.t1 != o.t1) {
                return this.t1 - o.t1;
            } else {
                return o.type - this.type;
            }
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] arr = {"1","2","4","3","3","4","1","5"};
        String[] pro = {"read 1 3 1 2","read 2 6 4 7","write 4 3 3 5 2","read 5 2 2 5","write 6 1 3 3 9", "read 9 1 0 7"};

        String[] ans = sol.solution(arr,pro);
        for(String a : ans) {
            System.out.println(a);
        }
    }
}
