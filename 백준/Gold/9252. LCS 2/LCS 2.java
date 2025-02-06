import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] map = new int[str2.length() + 1][str1.length() + 1];

        for (int i = 1; i <= str2.length(); i++) {
            for (int j = 1; j <= str1.length(); j++) {
                map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]);
                if (str1.charAt(j - 1) == str2.charAt(i - 1)) {
                    map[i][j] = map[i - 1][j - 1] + 1;
                }
            }
        }
        int length = map[str2.length()][str1.length()];
        sb.append(length).append("\n");
        String sentence = "";
        int i = str2.length();
        int j = str1.length();

        while (length != 0) {
            if (map[i - 1][j] != length && map[i][j - 1] != length) {
                sentence = str2.charAt(i - 1) + sentence;
                length--;
                i--;
                j--;
            } else if (map[i - 1][j] == length && map[i - 1][j - 1] != length) {
                i--;
            } else if (map[i][j - 1] == length && map[i - 1][j - 1] != length) {
                j--;
            } else if (map[i - 1][j] == length && map[i][j - 1] == length) {
                i--;
                j--;
            }
        }
        sb.append(sentence);

        System.out.println(sb);
    }
}