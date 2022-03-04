package boj.samsung.boj21610.solution1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 비바라기 시전 시 : (N, 1), (N, 2), (N-1, 1), (N-1, 2) 에 비구름 생김
 * 구름에 이동을 M번 명령
 * i 번째 이동명령은 방향 d i 와 거리 s i 로 이루어져 있음
 * 방향은 총 8개  좌, 좌상, 상, 우상, 우, 우하, 하, 좌하 이동이 가능
 *
 * 이동명령시
 * - 모든 구름이 di방향으로 si칸 이동
 * 각 구름에서 비가 내려 구름있는 칸의 바구니에 물의 양 1 증가
 * 구름 모두 사라짐
 * 2에서 물이 증가한 칸에 (r,c) 에 물복사버그 마법을 시전 -> 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니 수 만큼 (r,c) 에 있는 바구니 물 증가
 *  주의 : 이동과 다르게 경계를 넘어간 칸은 대각선 방향으로 거리가 1인칸이 아님
 *  N,2 에서 인접한 대각선 칸은 N-1, 1 이고,
 *  N,N 에서 인접한 대각선 칸은 N-1, N-1 뿐이다.
 * 바구니에 저장된 물의 양이 2인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
 * 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
 *
 * M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구하자.
 */
public class Main {
    static int N;
    static int M;
    static int[][] A;
    static int[] D;
    static int[] Dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] Dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] S;

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        A = new int[N + 1][N + 1];
        S = new int[M];
        D = new int[M];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < M; i++) {
            D[i] = sc.nextInt();
            S[i] = sc.nextInt();
        }
    }

    static void solution() {
        List<Space> spaces = new ArrayList<>();
        spaces.add(new Space(N-1, 1, A[N-1][1]));
        spaces.add(new Space(N-1, 2, A[N-1][2]));
        spaces.add(new Space(N, 1, A[N][1]));
        spaces.add(new Space(N, 2, A[N][2]));
        RainCloud rainCloud = new RainCloud(spaces);

        for (int k = 0; k < M; k++) {
            rainCloud.move(Dx[D[k]-1], Dy[D[k]-1], S[k]);
            rainCloud.rain();
            rainCloud.magic();
            List<Space> alreadyCloud = new ArrayList<>();
            for (int i = 0; i < rainCloud.spaces.size(); i++) {
                alreadyCloud.add(new Space(rainCloud.spaces.get(i).x, rainCloud.spaces.get(i).y, rainCloud.spaces.get(i).water));
            }
            rainCloud.spaces.clear();

            // 2 이상인 칸에 구름 생김 , 구름 사라진 칸 제외
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(A[i][j] < 2) continue;
                    if(isCloud(alreadyCloud,i, j)) continue;
                    A[i][j] -= 2;
                    rainCloud.spaces.add(new Space(i, j, A[i][j]));
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                answer += A[i][j];
            }
        }
        System.out.println(answer);
    }

    private static boolean isCloud(List<Space> clouds, int i, int j) {
        for (Space space : clouds) {
            if(space.x == i && space.y == j) {
                return true;
            }
        }
        return false;
    }

    static class RainCloud {
        List<Space> spaces;

        // 크기가 4인 배열만 받는다.
        public RainCloud(List<Space> spaces) {
            this.spaces = spaces;
        }

        public void move(int dx, int dy, int s) {
            for (int i=0; i<spaces.size(); i++) {
                int nx = spaces.get(i).x;
                int ny = spaces.get(i).y;
                for(int j=1; j<=s; j++){
                    nx = spaces.get(i).x + (dx);
                    ny = spaces.get(i).y + (dy);

                    // 벗어나는 경우
                    if(nx < 1) {
                        nx = N;
                    } else if(nx > N) {
                        nx = 1;
                    }

                    if(ny < 1) {
                        ny = N;
                    } else if(ny > N) {
                        ny = 1;
                    }
                spaces.get(i).x = nx;
                spaces.get(i).y = ny;
                spaces.get(i).water = A[nx][ny];
                }

            }
        }

        public void rain() {
            for (Space space : spaces) {
                A[space.x][space.y]++;
                space.water++;
            }
        }

        public void magic() {
            int[] ax = {-1, -1, 1, 1};
            int[] ay = {-1, 1, 1, -1};
            for (Space space : spaces) {
                int amount = 0;
                for (int i = 0; i < 4; i++) {
                    // 대각선 방향 1인 칸에 물이 있는 바구니의 수만큼 물의 양이 증가
                    int nx = space.x + ax[i];
                    int ny = space.y + ay[i];
                    if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
                    if(A[nx][ny] > 0) {
                        amount++;
                    }
                }
                A[space.x][space.y] += amount;
                space.water += amount;
            }
        }
    }

    static class Space {
        int x;
        int y;
        int water;

        public Space(int x, int y, int water) {
            this.x = x;
            this.y = y;
            this.water = water;
        }
    }

}
