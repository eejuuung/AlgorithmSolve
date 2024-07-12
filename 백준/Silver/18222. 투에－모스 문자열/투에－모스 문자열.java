import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static long dfs(long x){

        if(x==1)
            return 0;

        long i;
        for(i=1;i+i<x;i+=i);
        long j = dfs(x-i);
        j = (j==0? 1 : 0);
        return j;


    }

    public static void main(String[] args) throws Exception {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.println(dfs(N));

    }
}