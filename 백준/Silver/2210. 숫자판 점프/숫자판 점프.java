import java.io.*;
import java.util.*;

public class Main {

    public static class Pair {
        int y;
        int x;
        int num;
        int count;

        public Pair(int y, int x, int num, int count) {
            this.y = y;
            this.x = x;
            this.num = num;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] doubleCheck = new boolean[1000000];
        int[][] map = new int[5][5];
        // 오, 아, 왼, 위
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        for (int i = 0; i < 5; i++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Queue<Pair> que = new ArrayDeque<>();
                que.offer(new Pair(i, j, map[i][j], 1));

                while (!que.isEmpty()) {
                    Pair nowP = que.poll();

                    if (nowP.count > 6)
                        break;

                    for (int k = 0; k < 4; k++) {
                        int fy = nowP.y + dy[k];
                        int fx = nowP.x + dx[k];

                        if (fy < 0 || fx < 0 || fy >= 5 || fx >= 5) {
                            continue;
                        }

                        int val = (nowP.num * 10) + map[fy][fx];
                        que.offer(new Pair(fy, fx, val, nowP.count + 1));

                        if (nowP.count + 1 == 6 && !doubleCheck[val]) {
                            doubleCheck[val] = true;
                            answer++;
                        }
                    }
                }
            }
        }

        System.out.println(answer);

    }
}
