import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static class PairRoom {
        List<Pair> pairList;

        public PairRoom() {
            this.pairList = new ArrayList<>();
        }
    }

    public static int N, K, S, X, Y;
    public static int[][] map;
    public static PairRoom[] pairRooms;
    // 오, 아, 왼, 위
    public static int[] dy = {0, 1, 0, -1};
    public static int[] dx = {1, 0, -1, 0};

    public static int runningVirus() {
        int isVirus = 0;

        for (int cnt = 1; cnt <= S; cnt++) {

            for (int k = 1; k <= K; k++) {
                Queue<Pair> que = new ArrayDeque<>();

                int maxSize = pairRooms[k].pairList.size();
                for (int i = 0; i < maxSize; i++) {
                    int y = pairRooms[k].pairList.get(i).y;
                    int x = pairRooms[k].pairList.get(i).x;

                    for (int j = 0; j < 4; j++) {
                        int fy = y + dy[j];
                        int fx = x + dx[j];

                        if (fy >= 0 && fy < N && fx >= 0 && fx < N && map[fy][fx] == 0) {
                            Pair newPair = new Pair(fy, fx);
                            que.offer(newPair);
                            pairRooms[k].pairList.add(newPair);
                            map[fy][fx] = k;
                        }
                    }

                    if (map[Y][X] != 0)
                        break;

                }

                if (map[Y][X] != 0)
                    break;

            }
            if (map[Y][X] != 0)
                break;
        }

        isVirus = map[Y][X];


        return isVirus;
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());

        map = new int[N][N];
        pairRooms = new PairRoom[K + 1];

        for (int i = 1; i <= K; i++) {
            pairRooms[i] = new PairRoom();
        }

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
                if (map[i][j] != 0)
                    pairRooms[map[i][j]].pairList.add(new Pair(i, j));
            }
        }

        stz = new StringTokenizer(br.readLine());
        S = Integer.parseInt(stz.nextToken());
        Y = Integer.parseInt(stz.nextToken()) - 1;
        X = Integer.parseInt(stz.nextToken()) - 1;

        System.out.println(runningVirus());

    }
}