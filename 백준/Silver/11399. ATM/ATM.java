import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        Arrays.sort(arr);

        int total = 0;
        int val = 0;
        for(int i=0;i<N;i++){
            total =  total + arr[i] + val;
            val += arr[i];
        }
        System.out.println(total);
    }
}
