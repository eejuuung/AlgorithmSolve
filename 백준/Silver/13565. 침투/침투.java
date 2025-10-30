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

    // 오, 아, 왼, 위
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(stz.nextToken());
        int N = Integer.parseInt(stz.nextToken());
        int[][] arr = new int[M][N];

        Queue<Pair> que = new ArrayDeque<>();
        boolean floor = true;
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j) - '0';

                if (i == M - 1 && arr[i][j] == 0)
                    floor = false;

                if (i == 0 && arr[i][j] == 0) {
                    que.offer(new Pair(i, j));
                    arr[i][j] = -1;
                }

            }
        }
        if (floor || que.isEmpty()) {
            System.out.println("NO");
            return;
        }

        boolean isout = false;
        while (!que.isEmpty() && !isout) {
            Pair nowP = que.poll();
            int k = 0;
            for (int i = 0; i < 4; i++) {
                int fy = nowP.y + dy[i];
                int fx = nowP.x + dx[i];

                if (fy == M && fx >= 0 && fx < N) {
                    isout = true;
                    break;
                } else if (fy < 0 || fx < 0 || fx >= N || arr[fy][fx] != 0)
                    continue;

                arr[fy][fx] = -1;
                que.offer(new Pair(fy, fx));
            }

        }

        System.out.println(isout ? "YES" : "NO");


    }
}
