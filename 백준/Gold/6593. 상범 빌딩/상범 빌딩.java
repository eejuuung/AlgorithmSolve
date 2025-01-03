import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Pair {
        int z;
        int y;
        int x;

        int time;

        public Pair(int z, int y, int x, int time) {
            this.z = z;
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    public static int L, R, C;  // 층, 행, 열

    // 동, 서, 남, 북, 상, 하
    public static int[] dz = {0, 0, 0, 0, 1, -1};
    public static int[] dy = {0, 0, 1, -1, 0, 0};
    public static int[] dx = {1, -1, 0, 0, 0, 0};

    public static char[][][] building;
    public static boolean[][][] visit;

    public static Pair starter;
    public static Pair goal;

    public static int escapeBuilding() {
        int escapeTime = -1;

        Queue<Pair> que = new ArrayDeque<>();
        que.offer(starter);

        while (!que.isEmpty()) {
            Pair nowP = que.poll();

            if (nowP.z == goal.z && nowP.y == goal.y && nowP.x == goal.x) {
                escapeTime = nowP.time;
                break;
            }

            for (int i = 0; i < 6; i++) {
                int fz = nowP.z + dz[i];
                int fy = nowP.y + dy[i];
                int fx = nowP.x + dx[i];

                if (fz < 0 || fz >= L || fy < 0 || fy >= R || fx < 0 || fx >= C || visit[fz][fy][fx] || building[fz][fy][fx] == '#')
                    continue;

                visit[fz][fy][fx] = true;
                que.offer(new Pair(fz, fy, fx, nowP.time + 1));
            }

        }


        return escapeTime;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        StringBuilder sb = new StringBuilder();

        while (true) {
            stz = new StringTokenizer(br.readLine());
            L = Integer.parseInt(stz.nextToken());
            R = Integer.parseInt(stz.nextToken());
            C = Integer.parseInt(stz.nextToken());

            if (L == 0 && R == 0 && C == 0)
                break;

            building = new char[L][R][C];
            visit = new boolean[L][R][C];
            String str;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    str = br.readLine();
                    for (int k = 0; k < C; k++) {
                        building[i][j][k] = str.charAt(k);
                        if (building[i][j][k] == 'S') {
                            visit[i][j][k] = true;
                            starter = new Pair(i, j, k, 0);
                        } else if (building[i][j][k] == 'E') {
                            goal = new Pair(i, j, k, 0);
                        }
                    }
                }
                str = br.readLine();
            }

            int escapeTime = escapeBuilding();
            if (escapeTime == -1)
                sb.append("Trapped!").append("\n");
            else
                sb.append("Escaped in ").append(escapeTime).append(" minute(s).").append("\n");

        }

        System.out.print(sb);

    }
}