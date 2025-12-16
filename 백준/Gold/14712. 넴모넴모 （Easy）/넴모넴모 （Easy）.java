import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long answer = 0;
    static boolean[][] map;

    public static void dfs(int y, int x) {

        // y 끝인경우
        if (y == N) {
            answer++;
            return;
        }

        // x 끝인경우
        if (x == M) {
            dfs(y + 1, 0);
            return;
        }

        // 현재칸 비우는 경우
        dfs(y, x + 1);

        // 현재칸 채우는 경우
        if (nemoCheck(y, x)) {
            map[y][x] = true;
            dfs(y, x + 1);
            map[y][x] = false;
        }
    }

    public static boolean nemoCheck(int y, int x) {
        if (y == 0 || x == 0)
            return true;

        return !(map[y - 1][x - 1] && map[y - 1][x] && map[y][x - 1]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        map = new boolean[N][M];

        dfs(0, 0);

        System.out.println(answer);

    }
}