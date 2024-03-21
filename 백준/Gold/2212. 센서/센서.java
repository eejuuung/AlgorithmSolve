import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N-1];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        if(K>=N){
            System.out.println("0");
            return;
        }

        Arrays.sort(arr);
        for(int i=1;i<N;i++){
            dp[i-1] = arr[i]-arr[i-1];
        }
        Arrays.sort(dp);

        int answer =0;
        for(int i=N-K-1;i>=0;i--){
            answer+=dp[i];
        }
        System.out.println(answer);
    }
}
