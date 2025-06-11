import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer> list;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        int answer = 0;
        int total = 0;
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(stz.nextToken()));
            answer = Math.max(answer, list.get(i));
            total += list.get(i);
        }
        M = Integer.parseInt(br.readLine());

        if (total <= M) {
            System.out.println(answer);
            return;
        }
        int left = 1;
        int right = answer;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (checkValue(mid)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);

    }

    public static boolean checkValue(int val) {
        int totalCheck = 0;
        for (int i = 0; i < N; i++) {
            if (list.get(i) <= val)
                totalCheck += list.get(i);
            else
                totalCheck += val;
        }

        return (totalCheck <= M);
    }
}
