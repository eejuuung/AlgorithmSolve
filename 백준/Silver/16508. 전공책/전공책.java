import java.io.*;
import java.util.*;

public class Main {

    public static int answer;
    public static int N;
    public static int[] count;
    public static int[][] book;
    public static int[] price;

    public static void dfs(int now, int cost) {
        if (answer != -1 && cost >= answer) {
            return;
        }

        boolean isOkay = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                isOkay = false;
                break;
            }
        }
        if (isOkay) {
            answer = (answer == -1) ? cost : Math.min(answer, cost);
            return;
        }

        if (now == N)
            return;

        int[] backup = new int[26];
        for (int i = 0; i < 26; i++) {
            backup[i] = count[i];
            count[i] = Math.max(0, count[i] - book[now][i]);
        }
        dfs(now + 1, cost + price[now]);

        for (int i = 0; i < 26; i++)
            count[i] = backup[i];

        dfs(now + 1, cost);


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        count = new int[26];
        answer = -1;
        for (int i = 0; i < word.length(); i++) {
            count[word.charAt(i) - 'A']++;
        }

        N = Integer.parseInt(br.readLine());
        book = new int[N][26];
        price = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            price[i] = Integer.parseInt(stz.nextToken());
            String name = stz.nextToken();
            for (int j = 0; j < name.length(); j++) {
                book[i][name.charAt(j) - 'A']++;
            }
        }
        dfs(0, 0);

        System.out.println(answer);
    }
}