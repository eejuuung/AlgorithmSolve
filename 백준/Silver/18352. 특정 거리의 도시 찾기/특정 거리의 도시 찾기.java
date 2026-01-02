import java.io.*;
import java.util.*;

public class Main {

    public static class City {
        List<Integer> list;

        City() {
            list = new ArrayList<>();
        }
    }

    public static class Node {
        int cityNumber;
        int length;

        Node(int cityNumber, int length) {
            this.cityNumber = cityNumber;
            this.length = length;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());
        int X = Integer.parseInt(stz.nextToken());
        City[] cities = new City[N + 1];
        for (int i = 0; i <= N; i++) {
            cities[i] = new City();
        }
        int[] cityDistance = new int[N + 1];

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());

            cities[a].list.add(b);
        }

        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(X, 0));
        cityDistance[X] = -1;
        List<Integer> answer = new ArrayList<>();

        while (!que.isEmpty() && que.peek().length <= K) {
            Node nowCity = que.poll();
            if (nowCity.length == K)
                break;

            for (int i = 0; i < cities[nowCity.cityNumber].list.size(); i++) {
                int num = cities[nowCity.cityNumber].list.get(i);
                if (cityDistance[num] != 0)
                    continue;
                que.offer(new Node(num, nowCity.length + 1));
                cityDistance[num] = nowCity.length + 1;

                if (cityDistance[num] == K)
                    answer.add(num);
            }
        }
        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.size(); i++) {
            sb.append(answer.get(i)).append("\n");
        }

        bw.write(answer.isEmpty() ? "-1" : sb.toString());
        bw.flush();
    }
}
