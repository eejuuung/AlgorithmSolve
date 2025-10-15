import java.io.*;
import java.util.*;

public class Main {

    // 오, 아, 왼, 위
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static int N;
    static int[][] mapGold;
    static boolean[][] map;

    static int answer;

    static void maxFlower(int flower, int total) {
        if (flower >= 3) {
            answer = Math.min(answer, total);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!map[i][j]) {
                    boolean isokay = true;

                    for (int k = 0; k < 4; k++) {
                        int fy = i + dy[k];
                        int fx = j + dx[k];
                        if (fy < 0 || fx < 0 || fy >= N || fx >= N || map[fy][fx]) {
                            isokay = false;
                            break;
                        }
                    }
                    if (isokay) {

                        int gold = mapGold[i][j];
                        map[i][j] = true;
                        for (int k = 0; k < 4; k++) {
                            map[i + dy[k]][j + dx[k]] = true;
                            gold += mapGold[i + dy[k]][j + dx[k]];
                        }

                        maxFlower(flower + 1, total + gold);

                        map[i][j] = false;
                        for (int k = 0; k < 4; k++) {
                            map[i + dy[k]][j + dx[k]] = false;
                        }
                    }
                }
            }
        }


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        mapGold = new int[N][N];
        map = new boolean[N][N];

        StringTokenizer stz;
        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mapGold[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        maxFlower(0, 0);
        System.out.println(answer);
    }
}
