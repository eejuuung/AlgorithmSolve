import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int totalTime = 0;
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(stz.nextToken());
        int b = Integer.parseInt(stz.nextToken());
        totalTime += b;
        totalTime++;

        int cal = 0;

        for (int i = 1; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            a = Integer.parseInt(stz.nextToken());
            b = Integer.parseInt(stz.nextToken());
            cal = totalTime % (a + b);
            if (cal < b)
                totalTime += (b - cal);
            totalTime++;
        }

        System.out.println(totalTime);

    }
}
