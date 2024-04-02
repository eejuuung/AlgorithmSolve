import java.io.*;
import java.math.BigInteger;

public class Main {
    public static BigInteger[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
//        dp = new BigInteger[N+1];
//
//        dp[0] = new BigInteger("0");
//        dp[1] = new BigInteger("1");
        BigInteger before0 =  new BigInteger("0");
        BigInteger before1 =  new BigInteger("1");
        BigInteger before2 = null;
        if(N == 0){
            System.out.println("0");
            return;
        }
        else if(N == 1){
            System.out.println("1");
            return;
        }
        for(int i=2;i<=N;i++){
            before2 = before0.add(before1);
            before0 = before1;
            before1 = before2;

            //dp[i] = dp[i-1].add(dp[i-2]);
        }
//        StringBuilder sb = new StringBuilder();
//        sb.append(before2.toString()).append("\n");
        System.out.println(before2);
    }
}