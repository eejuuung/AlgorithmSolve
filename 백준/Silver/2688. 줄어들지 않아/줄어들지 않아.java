import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static long[] answer;
    public static long[][] arr;

    public static int N, T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T  = Integer.parseInt(br.readLine());

        arr = new long[64][10];
        answer = new long[64];
        long total = 0;
        for(int j=0,i=10;i>0;i--,j++){
            arr[1][j] =i;
            total+=i;
        }
        answer[0] = 10;
        answer[1] = total;

        for(int tc = 2;tc<64;tc++) {
            long cal = answer[tc-1];
            total = 0;
            for(int i=0;i<10;i++){
                total += cal;
                arr[tc][i] = cal;
                cal-=arr[tc-1][i];
            }
            answer[tc] = total;
        }

        while (T-->0){
            N = Integer.parseInt(br.readLine());
            sb.append(answer[N-1]).append("\n");
        }
        System.out.print(sb);
    }
}