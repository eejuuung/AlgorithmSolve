import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] hamArr = new int[N];
        int sum = 0;
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hamArr[i] = Integer.parseInt(stz.nextToken());
            sum += hamArr[i];
        }

        boolean[][] dp = new boolean[sum + 1][sum + 1];
        dp[0][0] = true;
        for (int i = 0; i < N; i++) {
            for (int x = sum; x >= 0; x--) {
                for (int y = sum; y >= 0; y--) {
                    // x와 y의 햄버거를 먹을 수 있는지 여부 체크
                    if (x - hamArr[i] >= 0) {
                        dp[x][y] |= dp[x - hamArr[i]][y];
                    }

                    if (y - hamArr[i] >= 0) {
                        dp[x][y] |= dp[x][y - hamArr[i]];
                    }
                }
            }
        }

        int answer = 0;

        for (int x = 0; x <= sum; x++) {    // 첫째의 효용
            for (int y = 0; y <= x; y++) {  // 둘째의 효용
                int z = sum - x - y;    // 막내의 효용
                if(dp[x][y] && (y >= z)){
                    answer = Math.max(answer,z);
                }
            }
        }


        System.out.println(answer);

    }
}