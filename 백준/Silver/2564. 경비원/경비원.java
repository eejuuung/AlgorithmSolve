import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static final int INF = 10000;
    static int mapX, mapY;
    static int[][] city;

    //1북,4동,2남,3서

    // 정방향
    static int[] forwardY = {0, -1, 0, 1};
    static int[] forwardX = {1, 0, -1, 0};
    // 역방향
    static int[] reverseY = {0, 1, 0, -1};
    static int[] reverseX = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        mapX = Integer.parseInt(stz.nextToken());
        mapY = Integer.parseInt(stz.nextToken());
        city = new int[mapY + 1][mapX + 1];
        int storeCount = Integer.parseInt(br.readLine());

        for (int i = 0, val1 = 0, val2 = 0; i < storeCount; i++) {
            stz = new StringTokenizer(br.readLine());
            val1 = Integer.parseInt(stz.nextToken());
            val2 = Integer.parseInt(stz.nextToken());
            Node newNode = buildStore(val1, val2);
            city[newNode.y][newNode.x] = INF;
        }

        stz = new StringTokenizer(br.readLine());
        int bouncerDir = Integer.parseInt(stz.nextToken());
        int num = Integer.parseInt(stz.nextToken());
        Node bouncer = buildStore(bouncerDir, num);
        if (bouncerDir == 1)
            bouncerDir = 0;
        else if (bouncerDir == 4)
            bouncerDir = 1;
        else if (bouncerDir == 2)
            bouncerDir = 2;
        else if (bouncerDir == 3)
            bouncerDir = 3;

        running(true, bouncerDir, bouncer);
        running(false, bouncerDir, bouncer);

        int answer = 0;
        for (int y = 0; y <= mapY; y++) {
            for (int x = 0; x <= mapX; x++) {
                if (city[y][x] > 0 && city[y][x] < INF) answer += city[y][x];
            }
        }

        System.out.println(answer);
    }

    public static void running(boolean forward, int bouncerDir, Node bouncer) {
        // forward = true -> 정, false -> 역
        int y = bouncer.y;
        int x = bouncer.x;
        int dy[] = forward ? forwardY : reverseY;
        int dx[] = forward ? forwardX : reverseX;
        int delta = forward ? 1 : -1;
        int count = 0;

        while (true) {

            int ny = y + dy[bouncerDir];
            int nx = x + dx[bouncerDir];

            // 2) 경계 밖이면: 방향만 회전하고, 회전한 방향으로 "다시" 한 칸 이동
            if (nx < 0 || nx > mapX || ny < 0 || ny > mapY) {
                bouncerDir = (bouncerDir + delta + 4) % 4; // 회전
                ny = y + dy[bouncerDir];
                nx = x + dx[bouncerDir];
                // 여기서는 ny,nx가 둘레 안으로 들어오게 됨
            }

            // 3) 실제 이동 적용 (항상 한 칸 이동)
            y = ny;
            x = nx;

            count++;
            if (city[y][x] == INF) {
                city[y][x] = count;
            } else if (city[y][x] != 0) {
                city[y][x] = Math.min(city[y][x], count);
            }

            if (y == bouncer.y && x == bouncer.x) {
                break;
            }
        }
    }

    public static Node buildStore(int dir, int val) {
        Node newNode = null;
        switch (dir) {
            case 1:
                newNode = new Node(mapY, val);
                break;
            case 2:
                newNode = new Node(0, val);
                break;
            case 3:
                newNode = new Node(mapY - val, 0);
                break;
            case 4:
                newNode = new Node(mapY - val, mapX);
                break;
        }
        return newNode;
    }
}
