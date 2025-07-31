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
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        char[][] map = new char[N][M];
        String str;
        Pair doyeon = null;
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'I') {
                    doyeon = new Pair(i, j);
                    map[i][j] = 'X';
                }
            }
        }

        int answer = 0;
        // 오, 아, 왼, 위
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        Queue<Pair> que = new ArrayDeque<>();
        que.offer(doyeon);

        while (!que.isEmpty()) {
            Pair nowP = que.poll();

            for (int i = 0; i < 4; i++) {
                int fy = nowP.y + dy[i];
                int fx = nowP.x + dx[i];

                if (fy < 0 || fx < 0 || fy >= N || fx >= M || map[fy][fx] == 'X')
                    continue;
                else if (map[fy][fx] == 'P') {
                    answer++;
                }
                que.offer(new Pair(fy, fx));
                map[fy][fx] = 'X';
            }
        }

        System.out.println((answer == 0 ? "TT" : answer));
    }
}
