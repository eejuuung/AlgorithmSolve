import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int[][] arr = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int num = Integer.parseInt(stz.nextToken());
                arr[i][j] = arr[i][j - 1] + num;
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            stz = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(stz.nextToken());
            int sx = Integer.parseInt(stz.nextToken());
            int ey = Integer.parseInt(stz.nextToken());
            int ex = Integer.parseInt(stz.nextToken());
            int answer = 0;
            for (int j = sy; j <= ey; j++) {
                answer += (arr[j][ex] - arr[j][sx - 1]);
            }
            sb.append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}