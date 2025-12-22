import java.io.*;
import java.util.*;

public class Main {
    static long answer = 0;
    static int[] input;
    static int N;

    public static int lowerBound(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (input[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    public static int upperBound(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (input[mid] > target)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new int[N];

        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(stz.nextToken());
            input[i] = num;
        }
        Arrays.sort(input);


        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int num = -(input[i] + input[j]);

                int left = lowerBound(j + 1, N, num);
                int right = upperBound(j + 1, N, num);
                answer += (right - left);
            }
        }
        System.out.println(answer);
    }
}