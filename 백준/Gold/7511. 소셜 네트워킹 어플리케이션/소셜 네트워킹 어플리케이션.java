import java.io.*;
import java.util.*;

public class Main {

    static int[] parent, rank;

    public static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return;

        if (rank[a] < rank[b]) {
            parent[a] = b;
        } else if (rank[a] > rank[b]) {
            parent[b] = a;
        } else {
            parent[b] = a;
            rank[a]++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("Scenario ").append(tc).append(":\n");
            int N = Integer.parseInt(br.readLine());
            int K = Integer.parseInt(br.readLine());
            parent = new int[N];
            rank = new int[N];

            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < K; i++) {
                StringTokenizer stz = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stz.nextToken());
                int b = Integer.parseInt(stz.nextToken());
                union(a, b);
            }

            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                StringTokenizer stz = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stz.nextToken());
                int b = Integer.parseInt(stz.nextToken());

                sb.append(find(a) == find(b) ? "1\n" : "0\n");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}