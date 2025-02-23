import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static int N;

    public static void dfs(int nowN, String str) {

        if (nowN == N) {
            System.out.println(str);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            if (check(str + String.valueOf(i))) {
                dfs(nowN + 1, str + String.valueOf(i));
            }
        }

    }

    public static boolean check(String cstr) {
        int length = cstr.length() / 2;

        for (int i = 1; i <= length; i++) {
            if (cstr.substring(cstr.length() - i)
                    .equals(cstr.substring(cstr.length() - 2 * i, cstr.length() - i))) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dfs(0, "");

    }
}