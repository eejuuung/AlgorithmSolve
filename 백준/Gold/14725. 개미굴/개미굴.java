import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static class Room implements Comparable<Room> {
        String name;
        List<Room> rooms;

        public Room(String name) {
            this.name = name;
            rooms = new ArrayList<>();
        }

        @Override
        public int compareTo(Room o) {
            return this.name.compareTo(o.name);
        }
    }

    public static int N;
    public static Room ant;
    public static StringBuilder sb;

    public static void inputSeq() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ant = new Room("start");
        StringTokenizer stz;

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(stz.nextToken());

            Room nowRoom = ant;
            for (int j = 0; j < M; j++) {
                String readRoomName = stz.nextToken();
                int roomCount = nowRoom.rooms.size();

                boolean isin = false;
                for (int k = 0; k < roomCount; k++) {
                    if (nowRoom.rooms.get(k).name.equals(readRoomName)) {
                        isin = true;
                        nowRoom = nowRoom.rooms.get(k);
                        break;
                    }
                }
                if (!isin) {
                    nowRoom.rooms.add(new Room(readRoomName));
                    nowRoom = nowRoom.rooms.get(roomCount);
                }
            }
        }
    }

    public static void DFS(Room nowRoom, String addBar) {
        Collections.sort(nowRoom.rooms);
        sb.append(addBar).append(nowRoom.name).append("\n");
        addBar += "--";

        int roomSize = nowRoom.rooms.size();
        for (int i = 0; i < roomSize; i++) {
            DFS(nowRoom.rooms.get(i), addBar);
        }

    }

    public static void main(String[] args) throws Exception {

        sb = new StringBuilder();
        inputSeq();

        Collections.sort(ant.rooms);
        for (int i = 0; i < ant.rooms.size(); i++) {
            DFS(ant.rooms.get(i), "");
        }

        System.out.print(sb);
    }
}