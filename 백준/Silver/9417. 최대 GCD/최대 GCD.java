import java.io.*;
import java.util.*;

public class Main {

    public static int gcd(int a, int b) {

        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int count = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < count; tc++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int answer = 0;
            List<Integer> list = new ArrayList<>();
            while (stz.countTokens() > 0) {
                list.add(Integer.parseInt(stz.nextToken()));
            }

            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    answer = Math.max(answer, gcd(list.get(i), list.get(j)));
                }
            }
            
            sb.append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}