import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] total = new int[N + 1];
        int[] arr = new int[N + 1];
        StringTokenizer stz = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
            total[i] = total[i - 1] + arr[i];
        }

        int answer = 0;
        int honey = 0;
        // 1. 벌-통-벌 = 벌은 1,N에 위치하고 꿀통을 옮겨가며 검사
        for (int i = 2; i < N; i++) {
            honey = (total[i] - arr[1]) + (total[N] - total[i - 1] - arr[N]);
            answer = Math.max(answer, honey);
        }

        // 2. 통-벌-벌 = 통은1 벌은N에 위치하고 나머지 별을 옮겨가며 검사
        for (int i = 2; i < N; i++) {
            honey = (total[N] - arr[N] - arr[i]) + (total[i - 1]);
            answer = Math.max(answer, honey);
        }

        // 3. 벌-벌-통 = 벌은1 통은N에 위치하고 나머지 벌을 옮겨가며 검사
        for (int i = 2; i < N; i++) {
            honey = (total[N] - arr[1] - arr[i]) + (total[N] - total[i]);
            answer = Math.max(answer, honey);
        }

        System.out.println(answer);
    }
}
