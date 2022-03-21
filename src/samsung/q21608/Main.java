package samsung.q21608;

//4
//1 2 5 1 7
//2 2 5 1 7
//3 2 5 1 7
//4 2 9 3 4
//5 5 2 3 4
//6 5 2 3 4
//7 5 2 3 4
//8 5 2 3 4
//9 4 9 2 1
//10 4 9 2 1
//11 4 9 2 1
//12 4 9 2 1
//13 4 9 2 1
//14 4 9 2 1
//15 4 9 2 1
//16 4 9 2 1


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;                   // 학생 수
    static int[][] board;           // 전체 교실
    static int[] dy = {-1, 1, 0, 0}; // 상하좌우
    static int[] dx = {0, 0, -1, 1};
    static List<Student> students;

    static class Student {
        int num;            // 학생 번호
        List<Integer> likeStudent = new ArrayList<>();      // 좋아하는 학생 번호

        public Student(int num) {
            this.num = num;
        }

        void addLikeStudent(int studentNumber) {    // 좋아하는 학생 추가
            likeStudent.add(studentNumber);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1]; // y , x 순
        for(int i=1; i<=N; i++) {
            Arrays.fill(board[i], 0);
        }
        students = new ArrayList<>();
        int maxN = N * N;
        for (int i = 0; i < maxN; i++) {
            st = new StringTokenizer(br.readLine());
            Student s = new Student(Integer.parseInt(st.nextToken()));
            for (int j = 0; j < 4; j++) {
                s.addLikeStudent(Integer.parseInt(st.nextToken()));
            }
            students.add(s);
        }

        // 입력 끝

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            int maxLikeCount = -1;  // 학생 별 좋아하는 사람의 최대 수
            int maxBlankCount = -1;  // 비어있는 좌석의 개수
            int targetY = N+1;  // 자리에 앉기 위한 좌표
            int targetX = N+1;
            for (int y = 1; y <= N; y++) {
                for (int x = 1; x <= N; x++) {
                    if (board[y][x] > 0) continue;      // 이미 학생이 앉아있으면 패스
                    int likeCount = 0;
                    int emptyCount = 0;
                    for (int d = 0; d < 4; d++) {      //상하좌우 탐색하며 찾는다.
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (ny < 1 || ny > N || nx < 1 || nx > N) continue;     // 범위 벗어나는지 체크
                        if (board[ny][nx] == 0) emptyCount++;
                        for (Integer likeStudent : student.likeStudent) {
                            if (board[ny][nx] == likeStudent) likeCount++;
                        }
                    }
                    if (maxLikeCount < likeCount) {                 // 좋아하는 수가 더 많으면 무조건 업데이트
                        targetY = y;
                        targetX = x;
                        maxLikeCount = likeCount;                   // 좋아하는 수의 최대 값 갱신
                        maxBlankCount = emptyCount;                 // 비어있는 칸 수를 갱신 -> 그래야 좋아하는 수의 최대값과 같더라도 그때의 비어있는 칸을 비교하여 비어있는 칸이 많으면 업데이트가 가능하다.
                    } else if (maxLikeCount == likeCount) {         // 좋아하는 수가 같다면
                        if (maxBlankCount < emptyCount) {           // 좋아하는 수가 같을 때 비어있는 칸이 더 많으면 앉을 자리 변경
                            targetY = y;
                            targetX = x;
                            maxBlankCount = emptyCount;             // 비어있는 칸의 최대값 갱신
                        } else if (maxBlankCount == emptyCount) {   // 비어있는 칸이 같다면
                            if (targetY > y) {                      // 더 위에 있는 좌표 순서
                                targetY = y;
                                targetX = x;
                            } else if(targetY == y) {
                                if (targetX > x) {                  // 행이 같다면, 열이 더 작은 순서
                                    targetX = x;
                                }
                            }
                        }
                    }
                }
            }
            board[targetY][targetX] = student.num;
        }

        int ret = 0;
        for (Student s : students) {
            int count = 0;                      // 인접한 친구들의 개수
            for (int y = 1; y <= N; y++) {
                for (int x = 1; x <= N; x++) {
                    if (board[y][x] != s.num) continue;
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (ny < 1 || ny > N || nx < 1 || nx > N) continue;
                        for (Integer likeStudent : s.likeStudent) {
                            if (board[ny][nx] == likeStudent) count++;
                        }
                    }
                }
            }

            // 만족도 계산
            if (count == 1) {
                ret += 1;
            } else if (count == 2) {
                ret += 10;
            } else if (count == 3) {
                ret += 100;
            } else if (count == 4) {
                ret += 1000;
            }
        }
        System.out.println(ret);
    }
}
