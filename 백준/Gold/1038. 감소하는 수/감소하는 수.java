import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Long> list;

    public static void bt(long num, int idx) {
        if (idx > 10)
            return;

        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            bt((num * 10) + i, idx + 1);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        if (N <= 10)
            System.out.println(N);
        else if (N > 1022)
            System.out.println("-1");
        else {
            for (int i = 0; i < 10; i++) {
                bt(i, 1);
            }
            Collections.sort(list);
            System.out.println(list.get(N));
        }
    }
}
