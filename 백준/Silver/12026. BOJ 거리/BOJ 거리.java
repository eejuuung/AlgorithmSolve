import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        char[] charr = new char[N + 1];
        int[] inarr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            charr[i] = str.charAt(i - 1);
        }

        inarr[1] = 1;

        for (int i = 1; i <= N; i++) {
            char nowStart = charr[i];

            if (inarr[i] == 0)
                continue;

            for (int j = i + 1; j <= N; j++) {
                char moveStart = charr[j];

                if (nowStart == 'B') {
                    if (moveStart != 'O')
                        continue;

                } else if (nowStart == 'O') {
                    if (moveStart != 'J')
                        continue;
                } else if (nowStart == 'J') {
                    if (moveStart != 'B')
                        continue;
                }

                int distance = j - i;
                int cal = inarr[i] + (distance * distance);
                if (inarr[j] > cal || inarr[j] == 0)
                    inarr[j] = cal;

            }
        }

        if (inarr[N] == 0)
            System.out.println("-1");
        else
            System.out.println(inarr[N] - 1);


    }
}