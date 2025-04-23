import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] answer = new int[N];
        boolean[] barr = new boolean[1000001];
        StringTokenizer stz = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        int size = 1;
        answer[0] = arr[0];
        barr[arr[0]] = true;

        for (int i = 1; i < N; i++) {

            // 값 추가해주는 경우
            if (arr[i] > answer[size - 1]) {
                answer[size] = arr[i];
                barr[arr[i]] = true;
                size++;
            }
            // 다른값으로 대치해주는 경우
            else if (arr[i] < answer[size - 1]) {

                // 동일한 값이 이미 들어가 있다면
                if (barr[arr[i]])
                    continue;

                // 이전에 들어가있는 값들 중에서 이분탐색을 활용해 적절한 값으로 대치함.
                int left = 0;
                int right = size;
                int mid = 0;

                while (left < right) {
                    mid = (left + right) / 2;

                    if (answer[mid] < arr[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                barr[answer[left]] = false;
                barr[arr[i]] = true;
                answer[left] = arr[i];
            }
        }

        System.out.println(size);

    }
}
