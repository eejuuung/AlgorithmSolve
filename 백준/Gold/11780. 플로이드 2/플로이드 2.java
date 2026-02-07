import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] dist = new int[N][N];
        int[][] next = new int[N][N];   // i->j로 갈때 가야할 첫 정점
        final int INF = 100_000_000;
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
            Arrays.fill(next[i], -1);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken()) - 1;
            int b = Integer.parseInt(stz.nextToken()) - 1;
            int cost = Integer.parseInt(stz.nextToken());

            if (cost < dist[a][b]) {
                dist[a][b] = cost;
                next[a][b] = b;
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (dist[i][k] == INF)
                    continue;
                for (int j = 0; j < N; j++) {
                    if (dist[k][j] == INF)
                        continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k]; // i->j로 갈때의 노드는 i->k로 갈때의 첫 노드
                    }
                }
            }
        }

        StringBuilder sbMin = new StringBuilder();
        StringBuilder sbAll = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sbMin.append(dist[i][j] == INF ? 0 : dist[i][j]).append(" ");

                if (next[i][j] == -1) {
                    sbAll.append("0\n");
                    continue;
                }

                int current = i;
                ArrayList<Integer> path = new ArrayList<>();
                while (current != j) {
                    path.add(current);
                    current = next[current][j];
                }
                path.add(current);
                sbAll.append(path.size()).append(" ");
                for (int now : path)
                    sbAll.append(now + 1).append(" ");
                sbAll.append("\n");
            }
            sbMin.append("\n");
        }

        bw.write(sbMin.toString());
        bw.write(sbAll.toString());
        bw.flush();
    }
}