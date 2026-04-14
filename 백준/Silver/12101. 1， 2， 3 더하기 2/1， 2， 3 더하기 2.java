import java.io.*;
import java.util.*;

public class Main {

    static int N, K, count;
    static String answer;

    static void dfs(int total, String str) {
        if (!answer.equals(""))
            return;

        if (total == N) {
            if (++count == K) {
                answer = str;
            }
            return;
        }

        if (total + 1 <= N)
            dfs(total + 1, str + "1+");
        if (total + 2 <= N)
            dfs(total + 2, str + "2+");
        if (total + 3 <= N)
            dfs(total + 3, str + "3+");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());
        answer = "";
        dfs(0, "");
        answer = (answer.equals("") ? "-1" : answer.substring(0, answer.length() - 1));
        System.out.println(answer);
    }
}