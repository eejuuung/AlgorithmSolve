import java.io.*;
import java.util.*;

public class Main {

    public static int count;
    public static int startNum;
    public static int endNum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        startNum = Integer.parseInt(stz.nextToken());
        endNum = Integer.parseInt(stz.nextToken());

        int startLeng = String.valueOf(startNum).length();
        int endLeng = String.valueOf(endNum).length();
        endLeng = (endLeng == 10 ? 9 : endLeng);

        for (int i = startLeng; i <= endLeng; i++) {
            dfs(i, "");
        }

        System.out.println(count);
    }

    public static void dfs(int N, String str) {

        if (str.length() >= N) {
            int num = Integer.parseInt(str);
            if (num >= startNum && num <= endNum)
                count++;
            return;
        }

        dfs(N, str + "4");
        dfs(N, str + "7");

    }
}
