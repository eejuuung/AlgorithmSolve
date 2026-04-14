import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        int count0 = 0;
        int count1 = 0;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) - '0' == 0)
                count0++;
            else
                count1++;
        }
        count0 /= 2;
        count1 /= 2;
        int total = count0 + count1;

        while (total > 0) {
            if (count0 > 0) {
                sb.append('0');
                count0--;
            } else {
                sb.append('1');
                count1--;
            }
            total--;
        }
        System.out.println(sb);
    }
}