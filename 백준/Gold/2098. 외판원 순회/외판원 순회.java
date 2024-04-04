import java.io.*;
import java.util.*;

public class Main {

    public static final int MAX = 17000000;
    public static int[][] dp;
    public static int[][] map;
    public static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][1<<16];
        for(int i=0;i<N;i++){
            StringTokenizer stz = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
            Arrays.fill(dp[i],-1);
        }
        int answer = TSP(0,1);
        System.out.println(answer);
    }

    /**
     * 문제에서 항상 순회할 수 잇는 경우만 입력으로 주어진다고 명시함
     * 현재도시와 지금까지 방문한 도시를 체크하며 DFS를 돌려줌
     */
    public static int TSP(int cur, int visit) {

//        // cur번 도시 방문함 -> 방문체크 이때해주면 시간초과남 미리 걸러야함.
//        visit |= (1 << cur);

        // 모든 도시를 다 방문한 경우 -> 기저조건
        if (visit == (1<<N)-1) {
            //출발도시 = 0 까지의 경로 있는지 체크
            if (map[cur][0] > 0) {
                return map[cur][0];
            }
            //없다면 max값 리턴
            return MAX;
        }

        // 현재도시까지의 이동거리값
        if (dp[cur][visit] != -1) // 이미 해당 거리는 계산함 그대로 돌려주기
            return dp[cur][visit];

        dp[cur][visit] = MAX;   // 이동안한값이면 min구해야하니까 우선 max값 넣기

        for (int i = 0; i < N; i++) {
            // 길이아니라면
            if(map[cur][i]==0)
                continue;
            // 이미 방문한 곳이라면
            if((visit & (1 << i)) != 0)
                continue;
            // 현재에서 i까지 가는 거리비용체크
            dp[cur][visit] = Math.min(dp[cur][visit],map[cur][i] + TSP(i,visit|(1<<i)));
        }
        return dp[cur][visit];
    }
}