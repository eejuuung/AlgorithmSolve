import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void popQueue(Queue<Integer> pq, Map<Integer, Integer> map) {
        while (!pq.isEmpty()) {
            int num = pq.poll();

            if (map.containsKey(num)) {
                if (map.get(num) == 1) {
                    map.remove(num);
                } else {
                    map.replace(num, map.get(num) - 1);
                }
                return;
            }
        }
    }

    public static String queCheck(Queue<Integer> pq, Map<Integer, Integer> map) {
        String str = "";

        while (!pq.isEmpty()) {
            int num = pq.poll();
            if (map.containsKey(num)) {
                return String.valueOf(num);
            }
        }

        return str;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer stz;
        StringBuilder sb = new StringBuilder();

        for (int rtc = 0; rtc < tc; rtc++) {
            Queue<Integer> minQue = new PriorityQueue<>();
            Queue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> map = new HashMap<>();

            int k = Integer.parseInt(br.readLine());

            for (int i = 0; i < k; i++) {
                stz = new StringTokenizer(br.readLine());

                String str = stz.nextToken();
                int num = Integer.parseInt(stz.nextToken());

                if (str.equals("I")) {
                    minQue.offer(num);
                    maxQue.offer(num);
                    map.put(num, map.getOrDefault(num, 0) + 1);

                } else if (!map.isEmpty()) {
                    if (num == 1) {
                        popQueue(maxQue, map);
                    } else {
                        popQueue(minQue, map);
                    }
                }
            }

            if (map.isEmpty())
                sb.append("EMPTY").append("\n");
            else
                sb.append(queCheck(maxQue, map)).append(" ").append(queCheck(minQue, map)).append("\n");

        }

        System.out.print(sb);
    }
}