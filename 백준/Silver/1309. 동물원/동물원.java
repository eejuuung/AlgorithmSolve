import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N==1){
            System.out.println("3\n");
            return;
        }

        int[][] dp = new int[N+1][2];
        dp[2][0] = 2;
        dp[2][1] = 1;
        for(int i=3;i<=N;i++){
            dp[i][0] = (dp[i-1][0] + (2*dp[i-1][1]))%9901;
            dp[i][1] = (dp[i-1][0] + dp[i-1][1])%9901;
        }
        long answer = (dp[N][0]* 2L) + (dp[N][1]* 3L);
        answer %=9901;
        System.out.println(answer);
    }
}