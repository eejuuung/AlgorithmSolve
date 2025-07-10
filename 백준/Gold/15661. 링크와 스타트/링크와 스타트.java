import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer stz;

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        int endNum = (1 << N) - 1; // 1048575

        for (int i = 1; i < endNum; i++) {
            int temp = i;
            List<Integer> team1 = new ArrayList<>();
            List<Integer> team2 = new ArrayList<>();
            int team1Ability = 0;
            int team2Ability = 0;

            for (int j = 0; j < N; j++) {
                if ((temp & 1) != 0) {
                    // 하위비트 1
                    team1.add(j);
                } else {
                    // 하위비트 0
                    team2.add(j);
                }
                temp >>= 1;
            }

            for (int j = 0; j < team1.size(); j++) {
                for (int k = 0; k < team1.size(); k++) {
                    if (j == k)
                        continue;

                    team1Ability += map[team1.get(j)][team1.get(k)];
                }
            }

            for (int j = 0; j < team2.size(); j++) {
                for (int k = 0; k < team2.size(); k++) {
                    if (j == k)
                        continue;

                    team2Ability += map[team2.get(j)][team2.get(k)];
                }
            }

            answer = Math.min(Math.abs(team1Ability - team2Ability), answer);
        }

        System.out.println(answer);

    }

}
