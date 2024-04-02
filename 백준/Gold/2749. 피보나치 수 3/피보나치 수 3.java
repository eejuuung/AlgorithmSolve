import java.io.*;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String str = br.readLine();
        BigInteger bigN = new BigInteger(br.readLine());
        bigN = bigN.remainder(BigInteger.valueOf(1500000));
        long N = Long.parseLong(bigN.toString());
        long P = 1000000;

        if(N == 0){
            System.out.println("0");
            return;
        }else if(N == 1){
            System.out.println("1");
            return;
        }

        long before0 = 0;
        long before1 = 1;

        long before2 =1;
        for(int i=2;i<=N;i++){
            before2 = (before0 + before1)%P;
            before0 = before1;
            before1 = before2;
        }

        System.out.println(before2);
    }
}