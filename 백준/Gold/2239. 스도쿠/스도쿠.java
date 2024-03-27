import java.io.*;
import java.util.*;

public class Main {

    public static class Pair {
        int y;
        int x;
        int blockNum;

        Pair(int y, int x, int blockNum) {
            this.y = y;
            this.x = x;
            this.blockNum = blockNum;
        }
    }

    public static boolean[][] garo;
    public static boolean[][] sero;
    public static boolean[][] blockmap;
    public static int[][] map;
    public static ArrayList<Pair> list;
    public static boolean answer;

    public static void bCheck() {
        // 블록체크
        for (int i = 1, k = 1; i <= 9; i += 3) {

            for (int l = i; l < i + 3; l++){
                for (int j = 1; j <= 3; j++) {
                    if (map[l][j] == 0) { // 해당자리 구해야하는 자리라면 list에 추가
                        list.add(new Pair(l, j, k));
                    } else {
                        blockmap[k][map[l][j]] = true;
                    }
                }
                k++;
                for (int j = 4; j <= 6; j++) {
                    if (map[l][j] == 0) { // 해당자리 구해야하는 자리라면 list에 추가
                        list.add(new Pair(l, j, k));
                    } else {
                        blockmap[k][map[l][j]] = true;
                    }
                }
                k++;
                for (int j = 7; j <= 9; j++) {
                    if (map[l][j] == 0) { // 해당자리 구해야하는 자리라면 list에 추가
                        list.add(new Pair(l, j, k));
                    } else {
                        blockmap[k][map[l][j]] = true;
                    }
                }
                k-=2;
            }
            k+=3;
        }

        // 가로,세로 체크
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                garo[i][map[i][j]] = (map[i][j] != 0 ? true : false);
                sero[j][map[i][j]] = (map[i][j] != 0 ? true : false);
            }
        }
    }

    public static void dfs(int depth) {
        if (answer)
            return;

        if (depth >= list.size()) {
            answer = true;

            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (!garo[list.get(depth).y][i] && !sero[list.get(depth).x][i] && !blockmap[list.get(depth).blockNum][i]) {

                garo[list.get(depth).y][i] = true;
                sero[list.get(depth).x][i] = true;
                blockmap[list.get(depth).blockNum][i] = true;
                map[list.get(depth).y][list.get(depth).x] = i;
                dfs(depth + 1);
                if (answer)
                    return;
                garo[list.get(depth).y][i] = false;
                sero[list.get(depth).x][i] = false;
                blockmap[list.get(depth).blockNum][i] = false;
                map[list.get(depth).y][list.get(depth).x] = 0;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        garo = new boolean[10][10];
        sero = new boolean[10][10];
        blockmap = new boolean[10][10];
        map = new int[10][10];
        list = new ArrayList<>();
        answer = false;

        for (int i = 1; i <= 9; i++) {
            String str = br.readLine();
            for (int j = 1; j <= 9; j++) {
                map[i][j] = str.charAt(j - 1) - '0';
            }
        }

        // 각 구역 boolean 체크
        bCheck();

        // dfs돌리기 list의 갯수만큼
        dfs(0);

        // 출력
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

}
