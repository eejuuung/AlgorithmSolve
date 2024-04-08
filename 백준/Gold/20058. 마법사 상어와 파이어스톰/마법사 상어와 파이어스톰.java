import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Pair{
        int y;
        int x;
        Pair(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static int N;   // map의 크기
    public static int M;   // map의 실제크기
    public static int Q;  // 마법 횟수

    public static int[][] map;
    public static int[][] copymap;
    public static int[] dp; // 맵크기
    public static ArrayList<Integer> levelList;

    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};

    public static void magic(int L) {
        //실행전 복사배열 만들어놓기
        copymap = new int[M][M];

        // L사이즈만큼 쪼개서 파이어스톰 실행
        if (L != 0) {   // 0일때는 1개씩 쪼개지므로 회전할 필요 없음.
            for (int y = 0; y < dp[N]; y += dp[L]) {
                for (int x = 0; x < dp[N]; x += dp[L]) {
                    rotation(y, x, dp[L]);
                }
            }

            // map 전부 범위끼리 회전했다면 배열 바꿔주기
            for(int i=0;i<M;i++){
                for(int j=0;j<M;j++){
                    map[i][j] = copymap[i][j];
                }
            }
        }

        // 얼음이 있는 칸 3개 or 그이상 인접해있지 않은칸 체크
        ArrayList<Pair> list = new ArrayList<>();    // 그전에 전체적으로 감소해야하므로 배열에 복사해놓ㄱ
        for(int i=0;i<M;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]<=0)
                    continue;

                int cnt = 0;
                for(int k=0;k<4;k++){
                    int fy = i + dy[k];
                    int fx = j + dx[k];
                    if(fy<0 || fx<0 || fy>=M || fx>=M || map[fy][fx]<=0)
                        continue;

                    cnt++;
                }
                if(cnt<3)
                    list.add(new Pair(i,j));
            }
        }

        // 얼음 -1
        while (!list.isEmpty()){
            Pair nowP = list.remove(0);
            map[nowP.y][nowP.x]--;
        }

    }

    public static void rotation(int rsy, int rsx, int leng) {
        for (int lengy = 0, sy = rsy,msx = rsx + leng -1; lengy < leng; lengy++,sy++,msx--) {
            for (int lengx = 0,sx = rsx, msy = rsy; lengx < leng; lengx++,sx++,msy++) {
                copymap[msy][msx] = map[sy][sx];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        Q = Integer.parseInt(stz.nextToken());
        levelList = new ArrayList<>();
        dp = new int[N+1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] * 2;
        }
        M = dp[N];
        map = new int[M][M];

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            levelList.add(Integer.parseInt(stz.nextToken()));
        }

        // 마법 Q번 실행
        for (int i = 0; i < Q; i++) {
            magic(levelList.remove(0));
        }
        
        // 마법이 끝난 후 계산
        int total = 0;
        int totalcnt = 0;
        Queue<Pair> que = new ArrayDeque<>();
        boolean[][] bmap = new boolean[M][M];
        for(int i=0;i<M;i++){
            for(int j=0;j<M;j++){
                total += map[i][j];

                if(bmap[i][j] || map[i][j]<=0)
                    continue;
                bmap[i][j] = true;
                que.offer(new Pair(i,j));
                int cnt = 0;
                while (!que.isEmpty()){
                    Pair nowP = que.poll();
                    cnt++;

                    for(int k=0;k<4;k++){
                        int fy = nowP.y + dy[k];
                        int fx = nowP.x + dx[k];
                        if(fy<0 || fx<0 || fy>= M || fx>=M || map[fy][fx]==0 || bmap[fy][fx])
                            continue;
                        bmap[fy][fx] = true;
                        que.offer(new Pair(fy,fx));
                    }

                }
                if(cnt > totalcnt)
                    totalcnt = cnt;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(total).append("\n").append(totalcnt).append("\n");
        System.out.print(sb);
    }
}