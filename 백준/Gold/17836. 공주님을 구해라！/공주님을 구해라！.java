import java.io.*;
import java.util.*;

public class Main {

    public static class State {
        int y;
        int x;
        boolean sord;
        int time;

        public State(int y, int x, boolean sord, int time) {
            this.y = y;
            this.x = x;
            this.sord = sord;
            this.time = time;
        }
    }

    static int[][] map;
    // 오, 아, 왼, 위
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int N, M, T;

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        T = Integer.parseInt(stz.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

    }

    static int savePrincess() {

        boolean[][] bMap = new boolean[N][M];
        boolean[][] sordMap = new boolean[N][M];
        Queue<State> que = new ArrayDeque<>();

        que.offer(new State(0, 0, false, 0));

        while (!que.isEmpty()) {
            State nowS = que.poll();

            if (nowS.time > T)
                break;

            if (nowS.y == N - 1 && nowS.x == M - 1) {
                return nowS.time;
            }

            for (int i = 0; i < 4; i++) {
                int fy = nowS.y + dy[i];
                int fx = nowS.x + dx[i];

                if (fy < 0 || fx < 0 || fy >= N || fx >= M)
                    continue;

                // 현재위치에 칼 잇는지 확인
                if (map[fy][fx] == 2) {
                    nowS.sord = true;
                }

                // 칼이 있는경우 벽도 이동 가능.
                if (nowS.sord) {
                    if (sordMap[fy][fx])
                        continue;

                    sordMap[fy][fx] = true;
                    que.offer(new State(fy, fx, true, nowS.time + 1));
                } else {
                    if (map[fy][fx] == 1 || bMap[fy][fx])
                        continue;

                    bMap[fy][fx] = true;
                    que.offer(new State(fy, fx, false, nowS.time + 1));
                }

            }

        }


        return -1;
    }

    public static void main(String[] args) throws Exception {
        init();

        int answer = savePrincess();
        System.out.println(answer != -1 ? answer : "Fail");
    }
}
