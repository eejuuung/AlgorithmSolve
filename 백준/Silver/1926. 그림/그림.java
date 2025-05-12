import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int y;
        int x;

        Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N;
    static int M;
    static int[][] map;
    static int[][] checkMap;

    // 오, 아, 왼, 위
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        map = new int[N][M];
        checkMap = new int[N][M];
        Queue<Pair> que;


        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        int count = 0;
        int maxDraw = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && checkMap[i][j] == 0) {
                    que = new ArrayDeque<>();
                    int drawCount = 1;

                    que.offer(new Pair(i, j));
                    checkMap[i][j] = 1;
                    count++;

                    while (!que.isEmpty()) {
                        Pair nowP = que.poll();

                        if (drawCount > maxDraw)
                            maxDraw = drawCount;

                        for (int k = 0; k < 4; k++) {
                            int fy = nowP.y + dy[k];
                            int fx = nowP.x + dx[k];

                            if (fy < 0 || fx < 0 || fy >= N || fx >= M || map[fy][fx] == 0 || checkMap[fy][fx] != 0) {
                                continue;
                            }

                            checkMap[fy][fx] = ++drawCount;
                            que.offer(new Pair(fy, fx));
                        }
                    }

                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n").append(maxDraw).append("\n");

        System.out.print(sb);

    }
}
