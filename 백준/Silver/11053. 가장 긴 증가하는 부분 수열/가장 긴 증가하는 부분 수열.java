import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(stz.nextToken());
            dp[i] =1;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<i;j++){
                if(arr[j]<arr[i]){ // 증가하는 값이라면
                    if(dp[j]+1>dp[i]) { // 현재 저장된 값보다 더 큰 값이 만들어진다면 바꿔줘기
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }

        int maxi = 0;
        for(int j=0;j<N;j++){
            if(dp[j]>maxi)
                maxi = dp[j];
        }

        System.out.println(maxi);


    }
}
