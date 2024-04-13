import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[][] dp = new int[N][2];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        if(N==0){
            System.out.println("0");
            return;
        }
        else if(N==1){
            System.out.println(arr[0]);
            return;
        }
        dp[0][0] = arr[0];
        dp[0][1] = arr[0];
        dp[1][0] = arr[1];
        dp[1][1] = arr[0] + arr[1];

        for(int i=2;i<N;i++){
            dp[i][0] = Math.max(dp[i-2][0],dp[i-2][1]) + arr[i];
            dp[i][1] = dp[i-1][0] + arr[i];
        }
        dp[N-2][1] = 0;

        int maxanswer = 0;

        for(int i=N-1;i>N-3;i--){
            maxanswer = Math.max(dp[i][0],maxanswer);
            maxanswer = Math.max(dp[i][1],maxanswer);
        }
        System.out.println(maxanswer);
    }
}
