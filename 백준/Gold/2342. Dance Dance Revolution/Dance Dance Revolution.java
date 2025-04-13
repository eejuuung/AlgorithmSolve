import java.io.*;
import java.util.*;

public class Main {

    static int[][] power = {
            {1, 2, 2, 2, 2},
            {0, 1, 3, 4, 3},
            {0, 3, 1, 3, 4},
            {0, 4, 3, 1, 3},
            {0, 3, 4, 3, 1}
    };

    static int[][][] dp;
    static int size;
    static ArrayList<Integer> list;

    public static int dfs(int idx, int l, int r) {
        if (idx == size)
            return 0;

        if (dp[idx][l][r] != 0)
            return dp[idx][l][r];

        int num = list.get(idx);
        dp[idx][l][r] = Math.min(
                dfs(idx + 1, num, r) + power[l][num],
                dfs(idx + 1, l, num) + power[r][num]);

        return dp[idx][l][r];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        list = new ArrayList<>();

        int num = Integer.parseInt(stz.nextToken());

        while (num != 0) {
            list.add(num);
            num = Integer.parseInt(stz.nextToken());
        }

        size = list.size();
        dp = new int[size][5][5];

        System.out.println(dfs(0, 0, 0));

    }
}
