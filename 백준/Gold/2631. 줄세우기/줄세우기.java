import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
            dp[i]=1;
        }
        int maxi = 0;
       for(int i=0;i<N;i++){
           for(int j=0;j<i;j++){
               if(arr[i]>arr[j]){   // 이후값이 더 큰값이라면
                   if(dp[j]+1>dp[i]){ // 저장값 바꿔주기
                       dp[i] = dp[j]+1;
                       if(dp[i]>maxi)
                           maxi = dp[i];
                   }
               }
           }
       }
       int answer = N - maxi;
        System.out.println(answer);
    }
}
