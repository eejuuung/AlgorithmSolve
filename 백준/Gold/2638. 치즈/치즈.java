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

    static int N, M;
    static int[][] map;
    // 오, 아, 왼, 위
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int totalCheese;

    public static boolean meltingCheese() {

        //0. 체크할 맵 복사하기
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        // 1. 치즈 블럭 그리기
        Queue<Pair> que = new ArrayDeque<>();
        que.offer(new Pair(0, 0));
        copyMap[0][0] = -1;
        while (!que.isEmpty()) {
            Pair now = que.poll();

            for (int i = 0; i < 4; i++) {
                int y = now.y + dy[i];
                int x = now.x + dx[i];

                if (y < 0 || x < 0 || y >= N || x >= M || copyMap[y][x] != 0)
                    continue;

                que.offer(new Pair(y, x));
                copyMap[y][x] = -1;
            }
        }


        //2. 녹일 치즈 정하기 (공기 -1이 2개이상 접촉시)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (copyMap[i][j] != 1)
                    continue;

                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int y = i + dy[k];
                    int x = j + dx[k];

                    if (y < 0 || x < 0 || y >= N || x >= M || copyMap[y][x] != -1)
                        continue;
                    
                    count++;
                }
                if (count >= 2) {
                    map[i][j] = 0;
                    totalCheese--;
                    copyMap[i][j] = 0;
                }
            }
        }

        //3. 치즈 다 녺았는지 확인하기
        return totalCheese == 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        map = new int[N][M];
        totalCheese = 0;

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
                totalCheese = map[i][j] == 1 ? totalCheese + 1 : totalCheese;
            }
        }

        boolean melt = false;
        int time = 0;
        while (!melt) {
            melt = meltingCheese();
            time++;
        }

        System.out.println(time);

    }
}