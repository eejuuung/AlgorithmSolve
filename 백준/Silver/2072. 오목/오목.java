import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;

    static boolean doingOmok() {
        // 연속된 돌의 갯수를 체크해서 5개가 된다면 true return
        int countBlack = 0;
        int countWhite = 0;
        int maxBlack = 0;
        int maxWhite = 0;

        // →
        for (int i = 0; i < 19; i++) {
            countBlack = 0;
            countWhite = 0;
            maxBlack = 0;
            maxWhite = 0;
            for (int j = 0; j < 19; j++) {
                if (map[i][j] == 1) {
                    countBlack++;
                    maxWhite = Math.max(maxWhite, countWhite);
                    countWhite = 0;
                } else if (map[i][j] == 2) {
                    countWhite++;
                    maxBlack = Math.max(maxBlack, countBlack);
                    countBlack = 0;
                } else {
                    maxWhite = Math.max(maxWhite, countWhite);
                    maxBlack = Math.max(maxBlack, countBlack);
                    countBlack = 0;
                    countWhite = 0;
                }
            }
            maxBlack = Math.max(maxBlack, countBlack);
            maxWhite = Math.max(maxWhite, countWhite);
            if (maxBlack == 5 || maxWhite == 5)
                return true;
        }

        // ↓
        for (int i = 0; i < 19; i++) {
            countBlack = 0;
            countWhite = 0;
            maxBlack = 0;
            maxWhite = 0;
            for (int j = 0; j < 19; j++) {
                if (map[j][i] == 1) {
                    countBlack++;
                    maxWhite = Math.max(maxWhite, countWhite);
                    countWhite = 0;
                } else if (map[j][i] == 2) {
                    countWhite++;
                    maxBlack = Math.max(maxBlack, countBlack);
                    countBlack = 0;
                } else {
                    maxWhite = Math.max(maxWhite, countWhite);
                    maxBlack = Math.max(maxBlack, countBlack);
                    countBlack = 0;
                    countWhite = 0;
                }
            }
            maxWhite = Math.max(maxWhite, countWhite);
            maxBlack = Math.max(maxBlack, countBlack);
            if (maxBlack == 5 || maxWhite == 5)
                return true;
        }

        // ↗(세로)
        for (int i = 4; i < 19; i++) {
            countBlack = 0;
            countWhite = 0;
            maxBlack = 0;
            maxWhite = 0;
            int y = i;
            int x = 0;
            int k = 0;
            while (true) {
                int fy = y + (k * -1);
                int fx = x + k;
                k++;

                if (fy < 0 || fx < 0 || fy >= 19 || fx >= 19)
                    break;

                if (map[fy][fx] == 1) {
                    countBlack++;
                    maxWhite = Math.max(maxWhite, countWhite);
                    countWhite = 0;
                } else if (map[fy][fx] == 2) {
                    countWhite++;
                    maxBlack = Math.max(maxBlack, countBlack);
                    countBlack = 0;
                } else {
                    maxBlack = Math.max(maxBlack, countBlack);
                    maxWhite = Math.max(maxWhite, countWhite);
                    countBlack = 0;
                    countWhite = 0;
                }
            }
            maxBlack = Math.max(maxBlack, countBlack);
            maxWhite = Math.max(maxWhite, countWhite);
            if (maxBlack == 5 || maxWhite == 5)
                return true;
        }

        // ↗(가로)
        for (int i = 1; i < 15; i++) {
            countBlack = 0;
            countWhite = 0;
            maxBlack = 0;
            maxWhite = 0;
            int y = 18;
            int x = i;
            int k = 0;
            while (true) {
                int fy = y + (k * -1);
                int fx = x + k;
                k++;

                if (fy < 0 || fx < 0 || fy >= 19 || fx >= 19)
                    break;

                if (map[fy][fx] == 1) {
                    countBlack++;
                    maxWhite = Math.max(maxWhite, countWhite);
                    countWhite = 0;
                } else if (map[fy][fx] == 2) {
                    countWhite++;
                    maxBlack = Math.max(maxBlack, countBlack);
                    countBlack = 0;
                } else {
                    maxBlack = Math.max(maxBlack, countBlack);
                    maxWhite = Math.max(maxWhite, countWhite);
                    countBlack = 0;
                    countWhite = 0;
                }
            }
            maxBlack = Math.max(maxBlack, countBlack);
            maxWhite = Math.max(maxWhite, countWhite);
            if (maxBlack == 5 || maxWhite == 5)
                return true;
        }

        // ↘ (가로)
        for (int i = 0; i < 15; i++) {
            countBlack = 0;
            countWhite = 0;
            maxBlack = 0;
            maxWhite = 0;
            int y = 0;
            int x = i;
            int k = 0;
            while (true) {
                int fy = y + k;
                int fx = x + k;
                k++;

                if (fy >= 19 || fx >= 19)
                    break;

                if (map[fy][fx] == 1) {
                    countBlack++;
                    maxWhite = Math.max(maxWhite, countWhite);
                    countWhite = 0;
                } else if (map[fy][fx] == 2) {
                    countWhite++;
                    maxBlack = Math.max(maxBlack, countBlack);
                    countBlack = 0;
                } else {
                    maxBlack = Math.max(maxBlack, countBlack);
                    maxWhite = Math.max(maxWhite, countWhite);
                    countBlack = 0;
                    countWhite = 0;
                }
            }
            maxBlack = Math.max(maxBlack, countBlack);
            maxWhite = Math.max(maxWhite, countWhite);
            if (maxBlack == 5 || maxWhite == 5)
                return true;
        }

        // ↘ (세로)
        for (int i = 1; i < 15; i++) {
            countBlack = 0;
            countWhite = 0;
            maxBlack = 0;
            maxWhite = 0;
            int y = i;
            int x = 0;
            int k = 0;
            while (true) {
                int fy = y + k;
                int fx = x + k;
                k++;

                if (fy >= 19 || fx >= 19)
                    break;

                if (map[fy][fx] == 1) {
                    countBlack++;
                    maxWhite = Math.max(maxWhite, countWhite);
                    countWhite = 0;
                } else if (map[fy][fx] == 2) {
                    countWhite++;
                    maxBlack = Math.max(maxBlack, countBlack);
                    countBlack = 0;
                } else {
                    maxBlack = Math.max(maxBlack, countBlack);
                    maxWhite = Math.max(maxWhite, countWhite);
                    countBlack = 0;
                    countWhite = 0;
                }
            }
            maxBlack = Math.max(maxBlack, countBlack);
            maxWhite = Math.max(maxWhite, countWhite);
            if (maxBlack == 5 || maxWhite == 5)
                return true;
        }


        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[19][19];
        for (int i = 1; i <= N; i++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(stz.nextToken()) - 1;
            int x = Integer.parseInt(stz.nextToken()) - 1;
            if (i % 2 == 0)
                map[y][x] = 2;
            else
                map[y][x] = 1;

            if (doingOmok()) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);

    }
}