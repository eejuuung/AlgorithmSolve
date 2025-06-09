import java.io.*;
import java.util.*;

public class Main{

    static int S, C;
    static ArrayList<Long> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        S = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        list = new ArrayList<>();
        long maxPar = 0;
        long total = 0;

        for (int i = 0; i < S; i++) {
            list.add(Long.parseLong(br.readLine()));
            maxPar = Math.max(list.get(i), maxPar);
            total += list.get(i);
        }

        long left = 1;
        long right = maxPar;
        long best = 0;
        while (left <= right) {
            long mid = (left + right) / 2;

            if (parCount(mid)) {
                best = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        long answer = total - best * C;
        System.out.println(answer);
    }

    public static boolean parCount(long val) {

        if (val == 0)
            return false;

        long count = 0;
        for (long i : list) {
            count += (i / val);
        }

        return (count >= C);
    }


}
