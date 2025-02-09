import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 두 지붕을 잇는 선분이 A와 B를 제외한 다른 고층빌딩을 지나거나 접하지 않아야 한다.
 * 즉, 건물 A에서 건물 B를 봣을때 볼 수잇는지에 대한 기울기를 구하는 문제.
 * 건물 A->B 까지의 기울기  = (건물 A의 높이 - 건물 B의 높이) / 건물A-B와의 거리
 * <p>
 * 건물 A를 기준으로 왼쪽으로 가면 기울기가 감소하고 오른쪽으로가면 기울기가 증가한다.
 * 바로 왼쪽 그리고 오른쪽은 무조건 보인다.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] map = new int[N];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(stz.nextToken());
        }

        int answer = 0;
        int cnt;
        double beforeGradi;
        double gradient;

        //건물A
        for (int i = 0; i < N; i++) {
            cnt = 0;
            beforeGradi = 0;

            //왼쪽 확인
            for (int j = i - 1; j >= 0; j--) {
                gradient = (double) (map[i] - map[j]) / (i - j);

                if (j == i - 1 || beforeGradi > gradient) {
                    cnt++;
                    beforeGradi = gradient;
                }

            }
            //오른쪽 확인
            for (int j = i + 1; j < N; j++) {
                gradient = (double) (map[i] - map[j]) / (i - j);

                if (j == i + 1 || beforeGradi < gradient) {
                    cnt++;
                    beforeGradi = gradient;
                }
            }

            answer = Math.max(cnt, answer);
        }

        System.out.println(answer);

    }
}