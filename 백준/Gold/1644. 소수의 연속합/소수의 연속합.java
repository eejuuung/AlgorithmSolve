import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static boolean[] bmap;
    public static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        bmap = new boolean[N+1];
        bmap[0]  = true;
        bmap[1] = true;

        //소수 구하기
        for (int i = 2; i * i <= N; i++) {
            if (!bmap[i]) {
                for (int j = i * i; j <= N; j += i) {
                    bmap[j] = true;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!bmap[i]) {
                list.add(i);
            }
        }

        int start = 0, end = 0, sum = 0, answer = 0;
        while (true) {

            if (sum >= N) 
                sum -= list.get(start++);
            else if (end == list.size())
                break;
            else
                sum += list.get(end++);

            if (N == sum) answer++;
        }
        System.out.println(answer);

    }
}