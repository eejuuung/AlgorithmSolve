import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        HashMap<Long, Integer> cnt = new HashMap<>();
        HashSet<Long> set = new HashSet<>();

        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(stz.nextToken());
            set.add(arr[i]);
            cnt.put(arr[i], cnt.getOrDefault(arr[i], 0) + 1);
        }

        long start = arr[0];
        for (Long x : arr) {
            boolean isNext = false;

            if (set.contains(x * 3))
                isNext = true;
            else if (x % 2 == 0 && set.contains(x / 2))
                isNext = true;

            if (!isNext) {
                start = x;
                break;
            }
        }

        long cur = start;
        for (int i = 0; i < N; i++) {
            sb.append(cur).append(' ');
            int count = cnt.get(cur);
            if (count == 1)
                cnt.remove(cur);
            else
                cnt.put(cur, count - 1);

            long next = Long.MIN_VALUE;

            if (cur % 3 == 0) {
                long num = cur / 3;
                count = cnt.getOrDefault(num, 0);
                if (count > 0)
                    next = num;
            }
            if (next == Long.MIN_VALUE) {
                long num = cur * 2;
                count = cnt.getOrDefault(num, 0);
                if (count > 0)
                    next = num;
            }
            cur = next;
        }
        System.out.println(sb);
    }
}