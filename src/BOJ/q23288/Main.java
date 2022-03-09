package BOJ.q23288;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static FastReader scan = new FastReader();

    static int N, M, K;
    static int[][] a; // 전체 그래프

    static int[] square = {0,1,3,4,5,2,6}; // 주사위 전개도
    static int[] temp = {0,1,2,3,4,5,6}; // 주사위 전개도에서 동,서,남,북 바뀔 때 임시로 저장할 배열

    static int square_index; // 밑면에 있는 눈 점수
    static int score; // 1번 굴렸을 때 점수
    static int sum_score; // 총 점수

    static int direction = 1;
    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}}; // 동 서 남 북 ( dfs 시 탐색할 때 필요 )
    static boolean[][] visit;

    static void roll(int dir) {
        for(int i=0; i<=6; i++) {
            temp[i] = square[i]; // 복사 해둠.
        }
        // 동쪽
        if(dir == 1) {
            square[2] = temp[1];
            square[6] = temp[2];
            square[1] = temp[3];
            square[3] = temp[6];
        } else if(dir == 2) {
            square[3] = temp[1];
            square[1] = temp[2];
            square[6] = temp[3];
            square[2] = temp[6];
        } else if(dir == 3) {
            square[4] = temp[1];
            square[6] = temp[4];
            square[1] = temp[5];
            square[5] = temp[6];
        } else if(dir == 4) {
            square[4] = temp[6];
            square[1] = temp[4];
            square[6] = temp[5];
            square[5] = temp[1];
        }
    }

    static int rotate(int dir, int amount) {
        // 반대 방향
        if(amount == -2) {
            switch (dir){
                case 1:
                    return 2;
                case 2:
                    return 1;
                case 3:
                    return 4;
                case 4:
                    return 3;
            }
        }

        // 시계 방향
        if(amount == 1) {
            switch (dir){
                case 1:
                    return 3;
                case 2:
                    return 4;
                case 3:
                    return 2;
                case 4:
                    return 1;
            }
        }

        // 반시계 방향
        if(amount == -1) {
            switch (dir){
                case 1:
                    return 4;
                case 2:
                    return 3;
                case 3:
                    return 1;
                case 4:
                    return 2;
            }
        }

        return 1000;
    }

    static void get_score(int i, int j, int dir) {
        // n을 dir 방향으로 굴린다.
        score = 0;
        for (int o=1; o<=N; o++) {
            for(int p=1; p<=M; p++) {
                visit[o][p] = false;
            }
        }
        dfs(i,j); // score 계산도 됨.
        sum_score += a[i][j] * score;
    }

    static void dfs(int r, int c) {
        score++;
        visit[r][c] = true;

        // 값이 같으면 탐색
        for(int i=0; i<4; i++) {
            int nx = r + dir[i][0], ny = c + dir[i][1];
            if(nx < 1 || ny < 1 || nx > N || ny > M) continue;
            if(a[nx][ny] != a[r][c] || visit[nx][ny]) continue;
            dfs(nx,ny);
        }
    }


    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        K = scan.nextInt();
        visit = new boolean[N+1][M+1];
        a = new int[N+1][M+1]; // 1,1 부터 시작
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                a[i][j] = scan.nextInt();
            }
        }
    }


    static void pro() {
        sum_score = 0;
        int ni = 1, nj = 1;
        int r = 1, c = 1;
        while (K > 0) {
            r = ni;
            c = nj;
            switch (direction) {
                case 1:
                    ni = ni;
                    nj = nj + 1;
                    break;
                case 2:
                    ni = ni;
                    nj = nj - 1;
                    break;
                case 3:
                    ni = ni + 1;
                    nj = nj;
                    break;
                case 4:
                    ni = ni - 1;
                    nj = nj;
                    break;
            }

            if(ni < 1 || nj < 1 || ni > N || nj > M) {
                direction = rotate(direction,-2);
                switch (direction) {
                    case 1:
                        ni = r;
                        nj = c + 1;
                        break;
                    case 2:
                        ni = r;
                        nj = c - 1;
                        break;
                    case 3:
                        ni = r + 1;
                        nj = c;
                        break;
                    case 4:
                        ni = r - 1;
                        nj = c;
                        break;
                }
            }
            roll(direction); // 주사위를 굴림.
            get_score(ni,nj,direction);
            if(square[6] > a[ni][nj]) {
                direction = rotate(direction,1);
            } else if(square[6] < a[ni][nj]) {
                direction = rotate(direction,-1);
            }
            // 방향에 따라 다음 좌표 계산
            K--;
        }
        System.out.println(sum_score);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }
    }
}
