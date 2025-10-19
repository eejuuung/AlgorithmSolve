import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        Integer[] arr = new Integer[M];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            que.offer(Integer.parseInt(stz.nextToken()));
        }
        stz = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++) {
            arr[j] = Integer.parseInt(stz.nextToken());
        }

        boolean isokay = true;
        for (int i = 0; i < M; i++) {
            if (que.isEmpty()) {
                isokay = false;
                break;
            }
            int num = que.poll();
            if (num >= arr[i]) {
                num -= arr[i];
                if (num > 0)
                    que.offer(num);
            } else {
                isokay = false;
                break;
            }
        }

        System.out.println((isokay ? "1" : "0"));


    }
}
