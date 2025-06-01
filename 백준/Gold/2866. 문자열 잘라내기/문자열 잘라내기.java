import java.io.*;
import java.util.*;

public class Main {

    public static char[][] map;
    public static int R, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        int left = 0;
        int right = R - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        System.out.println(left);
    }

    public static boolean check(int mid) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < C; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = mid + 1; j < R; j++) {
                str.append(map[j][i]);
            }
            if (set.contains(str.toString()))
                return false;

            set.add(str.toString());
        }
        return true;
    }
}
