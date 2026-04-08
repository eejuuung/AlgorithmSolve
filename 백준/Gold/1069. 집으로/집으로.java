import java.io.*;
import java.util.*;

public class Main {

    // 직선거리 D = sqrt(x^2 + y^2)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        double X = Double.parseDouble(stz.nextToken());
        double Y = Double.parseDouble(stz.nextToken());
        double D = Double.parseDouble(stz.nextToken());
        double T = Double.parseDouble(stz.nextToken());

        double dist = Math.sqrt(X * X + Y * Y);
        // 걷기
        double answer = dist;

        int jump = (int) (dist / D);

        // 점프+걷기
        answer = Math.min(answer, jump * T + (dist - jump * D));

        // 점프 후 되돌아오기
        answer = Math.min(answer, (jump + 1) * T + ((jump + 1) * D - dist));

        // 점프로만 
        if (dist >= D) {
            answer = Math.min(answer, Math.ceil(dist / D) * T);
        }
        if (dist < D) {
    answer = Math.min(answer, 2 * T);
}

        System.out.println(answer);
    }
}