import java.io.*;
import java.util.*;

public class Main {
    public static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        boolean[][] bmap = new boolean[N][N];
        StringTokenizer stz;

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        // 오, 아
        int[] dy = {0, 1};
        int[] dx = {1, 0};

        Queue<Pair> que = new ArrayDeque<>();
        que.offer(new Pair(0, 0));
        bmap[0][0] = true;

        while (!que.isEmpty()) {
            Pair nowP = que.poll();
            int distance = map[nowP.y][nowP.x];

            for (int i = 0; i < 2; i++) {
                int fy = nowP.y + (dy[i] * distance);
                int fx = nowP.x + (dx[i] * distance);

                if (fy < 0 || fx < 0 || fy >= N || fx >= N || bmap[fy][fx]) {
                    continue;
                }

                if (fy == N - 1 && fx == N - 1) {
                    System.out.println("HaruHaru");
                    return;
                }

                que.offer(new Pair(fy, fx));
                bmap[fy][fx] = true;
            }

        }
        System.out.println("Hing");
    }
}
