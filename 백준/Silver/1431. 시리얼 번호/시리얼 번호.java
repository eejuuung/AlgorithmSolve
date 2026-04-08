import java.io.*;
import java.util.*;

public class Main {
    public static class Serial implements Comparable<Serial> {
        String str;
        int total;

        @Override
        public int compareTo(Serial o) {

            if (this.str.length() != o.str.length())
                return this.str.length() - o.str.length();

            if (this.total != o.total)
                return this.total - o.total;

            return this.str.compareTo(o.str);
        }

        Serial(String str, int total) {
            this.str = str;
            this.total = total;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Serial> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int total = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) >= '0' && str.charAt(j) <= '9')
                    total += str.charAt(j) - '0';
            }
            list.add(new Serial(str, total));
        }

        Collections.sort(list);
        for (Serial serial : list) {
            sb.append(serial.str).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}