import java.io.*;
import java.util.*;

public class Main {

    public static class Study implements Comparable<Study> {
        int num;
        int start;
        int end;

        Study(int num, int start, int end) {
            this.num = num;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Study o) {
            return this.start - o.start;
        }
    }

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int[] studyNum = new int[N + 1];

        List<Study> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());
            list.add(new Study(a, b, c));
        }
        Collections.sort(list);

        PriorityQueue<Study> que = new PriorityQueue<>(Comparator.comparing(s -> s.end));
        int total = 1;
        for (Study now : list) {
            if (que.isEmpty()) {
                que.add(new Study(total, 0, now.end));
                studyNum[now.num] = total++;
                continue;
            }

            Study room = que.poll();
            if (room.end <= now.start) {
                que.add(new Study(room.num, 0, now.end));
                studyNum[now.num] = room.num;
            } else {
                que.add(room);
                que.add(new Study(total, 0, now.end));
                studyNum[now.num] = total++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(--total).append("\n");
        for (int i = 1; i <= N; i++) {
            sb.append(studyNum[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}