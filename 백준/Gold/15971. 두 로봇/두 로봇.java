import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int endRoom;
        int distance;
        int beforeMax;

        Node(int endRoom, int distance, int beforeMax) {
            this.endRoom = endRoom;
            this.distance = distance;
            this.beforeMax = beforeMax;
        }
    }

    static class Room {
        ArrayList<Node> list;

        public Room() {
            this.list = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int startRoom = Integer.parseInt(stz.nextToken());
        int endRoom = Integer.parseInt(stz.nextToken());
        boolean[] visit = new boolean[N + 1];
        Room[] rooms = new Room[N + 1];

        for (int i = 0; i < N - 1; i++) {
            stz = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stz.nextToken());
            int e = Integer.parseInt(stz.nextToken());
            int d = Integer.parseInt(stz.nextToken());

            if (rooms[s] == null)
                rooms[s] = new Room();
            if (rooms[e] == null)
                rooms[e] = new Room();

            rooms[s].list.add(new Node(e, d, 0));
            rooms[e].list.add(new Node(s, d, 0));
        }
        
        if (startRoom == endRoom) {
            System.out.println(0);
            return;
        }

        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(startRoom, 0, 0));
        visit[startRoom] = true;

        while (!que.isEmpty()) {
            Node nowRoom = que.poll();
            int s = nowRoom.endRoom;
            int beforeD = nowRoom.distance;
            int maxD = nowRoom.beforeMax;

            for (int i = 0; i < rooms[s].list.size(); i++) {
                int e = rooms[s].list.get(i).endRoom;
                int d = rooms[s].list.get(i).distance;

                if (visit[e])
                    continue;

                que.offer(new Node(e, beforeD + d, Math.max(maxD, d)));
                visit[e] = true;

                if (e == endRoom) {
                    int answer = d + beforeD - Math.max(maxD, d);
                    System.out.println(answer);
                    return;
                }
            }
        }


    }
}
