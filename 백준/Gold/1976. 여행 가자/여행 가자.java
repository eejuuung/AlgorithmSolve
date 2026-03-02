import java.io.*;
import java.util.*;

public class Main {

    public static int[] arr, rank;
    static int N, M;

    public static int find(int x) {
        if (arr[x] == x)
            return x;
        else
            return arr[x] = find(arr[x]);
    }

    public static void union(int x, int y) {
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
        StringTokenizer stz;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            arr[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(stz.nextToken());
                if (num == 1) {
                    union(i, j);
                }
            }
        }

        stz = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(stz.nextToken());
        a = find(a);
        for (int i = 1, b = 0; i < M; i++) {
            b = find(Integer.parseInt(stz.nextToken()));

            if (a != b) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}