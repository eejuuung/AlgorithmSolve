import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] lcs;
        String str1 = br.readLine();
        String str2 = br.readLine();
        int leng = Math.max(str1.length(), str2.length());
        lcs = new char[2][leng + 1];
        for (int i = 0; i < str1.length(); i++) {
            lcs[0][i + 1] = str1.charAt(i);
        }
        for (int i = 0; i < str2.length(); i++) {
            lcs[1][i + 1] = str2.charAt(i);
        }

        int[][] map = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (lcs[0][i] == lcs[1][j])
                    map[i][j] = map[i - 1][j - 1] + 1;
                else
                    map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]);
            }
        }

        System.out.println(map[str1.length()][str2.length()]);

    }
}
