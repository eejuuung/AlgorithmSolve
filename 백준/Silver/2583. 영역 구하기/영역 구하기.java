import java.io.*;
import java.util.*;

public class Main {

    public static class Pair {
        int y;
        int x;

        Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean[][] arr;
    static int N, M;

    public static void drawSquare(Pair p1, Pair p2) {

        for (int y = p1.y; y < p2.y; y++) {
            for (int x = p1.x; x < p2.x; x++) {
                arr[y][x] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        M = Integer.parseInt(stz.nextToken());
        N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());
        arr = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());
            int d = Integer.parseInt(stz.nextToken());
            drawSquare(new Pair(b, a), new Pair(d, c));
        }

        int count = 0;
        List<Integer> list = new ArrayList<>();
        // 오, 아, 왼, 위
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!arr[i][j]) {
                    Queue<Pair> que = new ArrayDeque<>();
                    que.offer(new Pair(i, j));
                    arr[i][j] = true;
                    count++;
                    int extent = 1;

                    while (!que.isEmpty()) {
                        Pair now = que.poll();
                        for (int k = 0; k < 4; k++) {
                            int fy = now.y + dy[k];
                            int fx = now.x + dx[k];

                            if (fy < 0 || fx < 0 || fy >= M || fx >= N || arr[fy][fx])
                                continue;

                            extent++;
                            arr[fy][fx] = true;
                            que.offer(new Pair(fy, fx));
                        }
                    }
                    list.add(extent);
                }
            }
        }

        System.out.println(count);
        StringBuilder sb = new StringBuilder();
        Collections.sort(list);
        for (int num : list) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}