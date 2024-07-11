import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int[] ground = new int[N];
        stz = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            ground[i] = Integer.parseInt(stz.nextToken());
        }

        int[] imos = new int[N+1];
        for(int i=0;i<M;i++){
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int k = Integer.parseInt(stz.nextToken());

            imos[a-1] += k;
            imos[b] -=k;
        }

        int total = 0;
        for(int i=0;i<N;i++){
            total +=imos[i];
            ground[i] += total;
            sb.append(ground[i]).append(" ");
        }
        System.out.println(sb);
    }
}