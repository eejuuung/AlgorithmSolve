import java.io.*;
import java.util.*;

// 키순서 1 DFS
public class Solution {

    static int N, M, adj[][], cnt;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int Tc = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= Tc; ++tc) {
            N = Integer.parseInt(br.readLine().trim());
            M = Integer.parseInt(br.readLine().trim());

            adj = new int[N + 1][N + 1];
            StringTokenizer stz = null;
            for (int i = 0; i < M; i++) {
                stz = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stz.nextToken());
                int b = Integer.parseInt(stz.nextToken());
                adj[a][b] = 1; // a보다 b가 키가 크다
            }

            int ans = 0;
            for (int i = 1; i <=N ; i++) {
                cnt=0;
                gtDFS(i,new boolean[N+1]);
                ltDFS(i,new boolean[N+1]);
                if(cnt == N-1)
                    ans++;
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    private static void gtDFS(int cur, boolean[] visited) {
        visited[cur] = true;
        for (int i = 1; i <= N; i++) {
            // 현재 정점보다 상대 정점이 클때 그리고 탐색이 되지 않앗다면
            if (adj[cur][i] == 1 && !visited[i]) {
                cnt++;
                gtDFS(i, visited);
            }
        }
    }

    private static void ltDFS(int cur, boolean[] visited) {
        visited[cur] = true;
        for (int i = 1; i <= N; i++) {
            // 현재 정점보다 상대 정점이 클때 그리고 탐색이 되지 않앗다면
            if (adj[i][cur] == 1 && !visited[i]) {
                cnt++;
                ltDFS(i, visited);
            }
        }
    }

}
