import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class Room implements Comparable<Room> {
        int startTime;
        int endTime;

        @Override
        public int compareTo(Room o) {

            if (this.startTime != o.startTime)
                return this.startTime - o.startTime;
            else
                return this.endTime - o.endTime;
        }

        Room(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Room[] rooms = new Room[N];

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            int st = Integer.parseInt(stz.nextToken());
            int et = Integer.parseInt(stz.nextToken());

            rooms[i] = new Room(st, et);
        }
        Arrays.sort(rooms);

        pq.offer(rooms[0].endTime);
        for (int i = 1; i < N; i++) {
            if (pq.peek() <= rooms[i].startTime) {
                pq.poll();
            }
            pq.offer(rooms[i].endTime);
        }
        System.out.println(pq.size());
        
    }
}
