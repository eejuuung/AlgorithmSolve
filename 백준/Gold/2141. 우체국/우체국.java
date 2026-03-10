import java.io.*;
import java.util.*;

/*
 - 우체국을 오른쪽으로 X만큼 움직이면
    + 왼쪽마을들 = 거리증가
        -> 증가량 = 왼쪽 사람 수 * X
    + 오른쪽마을들 = 거리감소
        -> 감소량 = 오른쪽 사람 수 * X
    = 전체 거리 변화
        -> 변화량 = (왼쪽인구 - 오른쪽인구) * X
 */
public class Main {

    public static class Town {
        int x;
        long people;

        Town(int x, long people) {
            this.x = x;
            this.people = people;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Town[] arr = new Town[N];
        long total = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            long b = Long.parseLong(stz.nextToken());
            arr[i] = new Town(a, b);
            total += b;
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a.x));

        long left = 0;
        for (int i = 0; i < N; i++) {
            left += arr[i].people;
            total -= arr[i].people;
            if (left >= total) {
                System.out.println(arr[i].x);
                return;
            }
        }
    }
}
