import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] cal = new int[N+1];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(stz.nextToken());
        for(int i=1;i<N;i++){
            arr[i] = Integer.parseInt(stz.nextToken());
            cal[i] = (arr[i-1]>arr[i]?cal[i-1]+1:cal[i-1]);
        }
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            stz = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stz.nextToken());
            int y = Integer.parseInt(stz.nextToken());

            if(x==y)
                sb.append("0\n");
            else{
                sb.append(cal[y-1]-cal[x-1]).append("\n");
            }
        }
        System.out.print(sb);
    }
}