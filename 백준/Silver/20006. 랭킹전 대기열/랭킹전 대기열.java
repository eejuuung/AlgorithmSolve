import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static class User implements Comparable<User> {
        String name;
        int level;

        public User(String name, int level) {
            this.name = name;
            this.level = level;
        }


        @Override
        public int compareTo(User o) {
            return this.name.compareTo(o.name);
        }
    }

    public static class Room {
        List<User> userList;

        int minLevel;
        int maxLevel;

        public Room(int level) {
            this.userList = new ArrayList<>();
            this.minLevel = level - 10;
            this.maxLevel = level + 10;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int playerCount = Integer.parseInt(stz.nextToken());
        int roomMax = Integer.parseInt(stz.nextToken());

        List<Room> roomList = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            stz = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(stz.nextToken());

            boolean isRoom = false;
            for (int j = 0; j < roomList.size(); j++) {
                if (roomList.get(j).userList.size() < roomMax &&
                        roomList.get(j).minLevel <= level &&
                        roomList.get(j).maxLevel >= level) {
                    roomList.get(j).userList.add(new User(stz.nextToken(), level));
                    isRoom = true;
                    break;
                }
            }

            if (!isRoom) {
                roomList.add(new Room(level));
                roomList.get(roomList.size() - 1).userList.add(new User(stz.nextToken(), level));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).userList.size() == roomMax)
                sb.append("Started!").append("\n");
            else
                sb.append("Waiting!").append("\n");

            Collections.sort(roomList.get(i).userList);

            for (int j = 0; j < roomList.get(i).userList.size(); j++) {
                sb.append(roomList.get(i).userList.get(j).level).append(" ").append(roomList.get(i).userList.get(j).name).append("\n");
            }
        }
        System.out.print(sb);

    }
}