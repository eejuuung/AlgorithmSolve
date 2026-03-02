import java.io.*;
import java.util.*;

public class Main{

    static int[] arr, rank;
    static int N, M;

    static int find(int x) {
        if (arr[x] == x)
            return x;
        else
            return arr[x] = find(arr[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y)
            return;

        if (rank[x] > rank[y]) {
            arr[y] = x;
        } else if (rank[x] < rank[y]) {
            arr[x] = y;
        } else {
            arr[x] = y;
            rank[y]++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        arr = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 0; i <= N; i++)
            arr[i] = i;

        for (int m = 0; m < M; m++) {
            stz = new StringTokenizer(br.readLine());
            int what = Integer.parseInt(stz.nextToken());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());

            switch (what) {
                case 0:
                    union(a, b);
                    break;
                case 1:
                    sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
                    break;
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }
}