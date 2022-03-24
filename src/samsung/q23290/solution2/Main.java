package samsung.q23290.solution2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int S;       // 마법 연습 횟수
    static int M;       // 초기 물고기 수
    static int[][][] board = new int[5][5][9];  // step 1 에서의 물고기들의 위치 y , x , d
    static int[][][] movedBoard = new int[5][5][9];  // 이동한 물고기들의 위치 y , x , d
    static int[][][] copyBoard = new int[5][5][9];  // y , x , d
    static int[][] smell = new int[5][5]; // 물고기의 냄새
    static int sharkY;
    static int sharkX;
    static int[] fishDirY = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] fishDirX = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sharkDirY = {0, -1, 0, 1, 0};
    static int[] sharkDirX = {0, 0, -1, 0, 1};
    static int fishCount = 0;
    static int[][] orders = new int[65][3]; // 상어가 이동할 순서

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        M = nextInt(st.nextToken());
        S = nextInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            board[nextInt(st.nextToken())][nextInt(st.nextToken())][nextInt(st.nextToken())]++;
        }
        st = new StringTokenizer(br.readLine());
        sharkY = nextInt(st.nextToken());
        sharkX = nextInt(st.nextToken());
        makeOrders(); // 상어가 이동할 수 있는 3가지 순서쌍을 미리 만들어 놓자.
    }

    private static void makeOrders() {
        int order = 1;
        for (int a = 1; a <= 4; a++) {
            for (int b = 1; b <= 4; b++) {
                for (int c = 1; c <= 4; c++) {
                    orders[order][0] = a;
                    orders[order][1] = b;
                    orders[order][2] = c;
                    order++;
                }
            }
        }
    }

    public static int nextInt(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws IOException {
        init();

        while (S-- > 0) {
            step1();
            step2();
            step3();
            step4();
            step5();
        }

        countFish();
        System.out.println(fishCount);
    }

    private static void countFish() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 8; k++) {
                    fishCount += board[i][j][k];
                }
            }
        }
    }

    private static void step1() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 8; k++) {
                    copyBoard[i][j][k] = board[i][j][k];
                }
            }
        }
    }

    private static void step2() {
        initMovedBoard();

        for (int y = 1; y <= 4; y++) {
            for (int x = 1; x <= 4; x++) {
                for (int d = 1; d <= 8; d++) {
                    if (board[y][x][d] == 0) continue;
                    // 7번까지 방향 ( 45도 반시계 ) 회전 해본다
                    boolean canGo = false;
                    for (int i = 0; i <= 7; i++) {
                        int nextDir = (d - i <= 0) ? (d - i + 8) : (d - i);
                        int ny = y + fishDirY[nextDir];
                        int nx = x + fishDirX[nextDir];
                        if (fishCanGo(ny, nx)) {
                            canGo = true;
                            movedBoard[ny][nx][nextDir] += board[y][x][d];  // 다른 방향
                            break;
                        }
                    }
                    if(!canGo) {
                        // 제자리
                        movedBoard[y][x][d] += board[y][x][d];
                    }
                }
            }
        }

        updateBoard();
    }

    private static void initMovedBoard() {
        for (int y = 1; y <= 4; y++) {
            for (int x = 1; x <= 4; x++) {
                Arrays.fill(movedBoard[y][x], 0);
            }
        }
    }

    private static void updateBoard() {
        for (int y = 1; y <= 4; y++) {
            for (int x = 1; x <= 4; x++) {
                for (int d = 1; d <= 8; d++) {
                    board[y][x][d] = movedBoard[y][x][d];
                }
            }
        }
    }

    private static boolean fishCanGo(int ny, int nx) {
        if (ny < 1 || ny > 4 || nx < 1 || nx > 4) return false;
        if (smell[ny][nx] > 0) return false;
        if (ny == sharkY && nx == sharkX) return false;

        return true;
    }

    private static void step3() {
        // 상어가 연속 3칸 이동하는 순서쌍들 : orders
        int sy = sharkY;
        int sx = sharkX;
        int[] bestOrder = new int[3];
        int maxEat = -1;
        for (int k = 1; k <= 64; k++) {    // 사전 순 ( 상좌하우 순서로 1234 , 숫자 작은게 우선)
            int[] order = orders[k];
            boolean[][] visit = new boolean[5][5];
            int ny = sy;
            int nx = sx;
            int eat = 0;
            boolean inBoard = true;
            for (int i = 0; i < 3; i++) {
                int dir = order[i];
                ny += sharkDirY[dir];
                nx += sharkDirX[dir];
                if (ny < 1 || ny > 4 || nx < 1 || nx > 4) {
                    inBoard = false;
                    break;
                }
                if (visit[ny][nx]) continue;
                visit[ny][nx] = true;
                for (int d = 1; d <= 8; d++) {
                    eat += board[ny][nx][d];
                }
            }
            if (inBoard) { // 격자 벗어나면 안됨
                if (maxEat < eat) {  // 가장 물고기를 많이 잡아먹을 수 있도록
                    maxEat = eat;
                    bestOrder = order;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            int bo = bestOrder[i];
            sharkY += sharkDirY[bo];
            sharkX += sharkDirX[bo];
            for (int d = 1; d <= 8; d++) {
                if (board[sharkY][sharkX][d] > 0) {
                    board[sharkY][sharkX][d] = 0;
                    smell[sharkY][sharkX] = 3;    // 물고기 냄새를 남긴다
                }
            }
        }
    }


    private static void step4() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (smell[i][j] > 0) {
                    smell[i][j]--;
                }
            }
        }
    }

    private static void step5() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 8; k++) {
                    board[i][j][k] += copyBoard[i][j][k];
                }
            }
        }
    }
}