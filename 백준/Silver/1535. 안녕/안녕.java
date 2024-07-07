import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] joy = new int[N+1];
        int[] hp = new int[N+1];
        int[] dp = new int[101];    // 체력에 맞는 최소값

        StringTokenizer stz = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            hp[i] = Integer.parseInt(stz.nextToken());
        }
        stz = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            joy[i] = Integer.parseInt(stz.nextToken());
        }

        for(int i=1;i<=N;i++){
            for(int j=100;j-hp[i]>0;j--){
                dp[j] = Math.max(dp[j],dp[j-hp[i]]+joy[i]);
            }
        }

        System.out.println(dp[100]);





    }
}