import java.io.*;
import java.util.*;

public class Main {

    static long[] patty;
    static long[] burger;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken()); // 버거레벨
        long X = Long.parseLong(stz.nextToken()); // 먹은 layer수

        patty = new long[N + 1]; // 레벨 별 버거의 총 패티 수
        burger = new long[N + 1]; // 레벨 별 버거의 총 레이어 수

        patty[0] = 1;
        burger[0] = 1;

        for (int i = 1; i <= N; i++) {
            patty[i] = (patty[i - 1] * 2) + 1;
            burger[i] = (burger[i - 1] * 2) + 3;
        }

        long answer = eatPatty(N, X);
        System.out.println(answer);
    }

    static long eatPatty(int level, long num) {
        long cnt = 0;
        long center = (burger[level] / 2) + 1;

        if (level == 0) return 1; // 0레벨은 패티한장만 먹음
        if (num == 1) return 0; // 한장만 먹으면 번만 먹음
        else if (num == burger[level]) // 현재레벨의 모든 레이어를 먹었다면 패티수 반환
            return (patty[level - 1] * 2) + 1;
        else if (num < center) { // 현재레벨의 중간패티보다 아래쪽먹은경우 재귀
            cnt += eatPatty(level - 1, num - 1);
        } else if (num == center) { // 현재레벨의 중간까지 먹엇다면 먹은 패티개수는 레벨-1패티에 +1
            cnt += patty[level - 1] + 1;
        } else { // 현재 레벨의 중간보다 많은 레이어를 먹었다면 재귀
            cnt += patty[level - 1] + 1 + eatPatty(level - 1, num - burger[level - 1] - 2);
        }

        return cnt;
    }
}
