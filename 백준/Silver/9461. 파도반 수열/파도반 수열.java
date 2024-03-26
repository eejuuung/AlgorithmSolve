import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long[] arr = new long[101];
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        for(int i=4;i<=100;i++){
            arr[i] = arr[i-3]+arr[i-2];
        }

        int tc = Integer.parseInt(br.readLine());
        for(int i=0;i<tc;i++){
            int N = Integer.parseInt(br.readLine());
            sb.append(arr[N]).append("\n");
        }
        System.out.print(sb);
    }
}
