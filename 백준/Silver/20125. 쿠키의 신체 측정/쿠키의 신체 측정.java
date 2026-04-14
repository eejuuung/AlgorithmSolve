import java.io.*;

public class Main {

    static char[][] arr;
    static int N;
    static StringBuilder sb;

    static void cookie(int y, int x) {
        sb = new StringBuilder();
        sb.append(++y + 1).append(" ").append(x + 1).append("\n");

        // 왼팔
        int i = y;
        int j = x;
        int count = 0;
        while (i >= 0 && i < N && j - 1 >= 0 && j - 1 < N && arr[i][--j] == '*') {
            count++;
        }
        sb.append(count).append(" ");

        // 오른팔
        i = y;
        j = x;
        count = 0;
        while (i >= 0 && i < N && j + 1 >= 0 && j + 1 < N && arr[i][++j] == '*') {
            count++;
        }
        sb.append(count).append(" ");

        // 허리
        i = y;
        j = x;
        count = 0;
        while (i + 1 >= 0 && i + 1 < N && j >= 0 && j < N && arr[++i][j] == '*') {
            count++;
        }
        sb.append(count).append(" ");
        y = i;

        // 왼다리
        i = y - 1;
        j = x - 1;
        count = 0;
        while (i + 1 >= 0 && i + 1 < N && j >= 0 && j < N && arr[++i][j] == '*') {
            count++;
        }
        sb.append(count).append(" ");

        // 오른다리
        i = y - 1;
        j = x + 1;
        count = 0;
        while (i + 1 >= 0 && i + 1 < N && j >= 0 && j < N && arr[++i][j] == '*') {
            count++;
        }
        sb.append(count);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];

        int y = -1, x = -1;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
                if (y == -1 && arr[i][j] == '*') {
                    y = i;
                    x = j;
                }
            }
        }
        cookie(y, x);
        System.out.println(sb);
    }
}