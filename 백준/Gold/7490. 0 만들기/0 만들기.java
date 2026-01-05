import java.io.*;

public class Main {

    public static StringBuilder out;
    public static int N;

    public static void dfs(int num, int sum, int last, String str) {
        if (num > N) {
            if (sum + last == 0) {
                out.append(str).append('\n');
            }
            return;
        }
        
        // ' '
        int newLast;
        if (last >= 0) {
            newLast = last * 10 + num;
        } else {
            newLast = last * 10 - num;
        }
        dfs(num + 1, sum, newLast, str + " " + num);

        // +
        dfs(num + 1, sum + last, +num, str + "+" + num);

        // -
        dfs(num + 1, sum + last, -num, str + "-" + num);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        out = new StringBuilder();

        int rtc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < rtc; tc++) {
            N = Integer.parseInt(br.readLine());
            dfs(2, 0, 1, "1");

            if (tc != rtc - 1) out.append("\n");
        }

        bw.write(out.toString());
        bw.flush();
    }
}