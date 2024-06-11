import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final long Cal = 1000000007;
    public static int N;

    public static long CalNum(long a){

        if(a==0)
            return 1;
        if(a%2==1)
            return (CalNum(a-1)*2)% Cal;

        long div = CalNum(a/2);
        return (div*div)%Cal;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        long ans = 0;
        long C,K;
        StringTokenizer stz;
        for(int i=N;i>0;i--){
            stz = new StringTokenizer(br.readLine());
            C = Integer.parseInt(stz.nextToken());
            K = Integer.parseInt(stz.nextToken());
            ans = (ans + ((C*K)%Cal) * CalNum(K-1))%Cal;
        }
        System.out.println(ans);

    }
}