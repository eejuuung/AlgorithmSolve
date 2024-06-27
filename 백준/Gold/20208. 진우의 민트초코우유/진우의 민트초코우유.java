import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static class Pair {
        int y;
        int x;
        boolean isEat;

        public Pair(int y, int x, boolean isEat) {
            this.y = y;
            this.x = x;
            this.isEat = isEat;
        }
    }

    public static int N, M, H;
    public static int maxChoco;
    public static int[][] map;
    public static Pair home;
    public static ArrayList<Pair> chocoList;

    public static void dfs(int nowHp, int chocoCnt, Pair nowP) {

        //체력이 0이되면 이동할 수 없음
        if (nowHp <= 0) {
            return;
        }

        //민트초코 체크하는 기준은 다시 집에 돌아가는 기준
        if(!(nowP.y == home.y && nowP.x == home.x)){
            // 집에 돌아갈 수 있는지 여부 확인
            int distance = Math.abs(nowP.y - home.y) + Math.abs(nowP.x - home.x);
            if (distance <= nowHp) {
                if(maxChoco<chocoCnt)
                    maxChoco = chocoCnt;
            }
        }

        // 현재 위치에서 이동할 수 있는 초코의 위치 체크하고 이동!
        for (int i = 0; i < chocoList.size(); i++) {
            if (chocoList.get(i).isEat)
                continue;

            //만약 먹을 수 있는 초코 위치이고 먹어도 체력이 남는다면
            int distance = Math.abs(nowP.y - chocoList.get(i).y) + Math.abs(nowP.x - chocoList.get(i).x);
            if (distance <= nowHp) {
                chocoList.get(i).isEat = true;
                dfs(nowHp-distance+H,chocoCnt+1,chocoList.get(i));
                chocoList.get(i).isEat = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        H = Integer.parseInt(stz.nextToken());
        map = new int[N][N];
        chocoList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
                if (map[i][j] == 1) {
                    home = new Pair(i, j, false);
                } else if (map[i][j] == 2) {
                    chocoList.add(new Pair(i, j, false));
                }
            }
        }
        maxChoco = 0;
        dfs(M,0,home);
        System.out.println(maxChoco);
    }
}