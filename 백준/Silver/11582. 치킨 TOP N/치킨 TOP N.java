import java.io.*;
import java.util.*;

public class Main {

    static int N, size;
    static StringBuilder sb;
    static int[] arr;

    public static void mergeSort(int left, int right) {
        if (left >= right)
            return;

        int mid = (left + right) / 2;
        // 분할
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        // 병합
        if (right - left + 1 <= size)
            merge(left, mid, right);
    }

    public static void merge(int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int c = 0;

        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                temp[c++] = arr[l++];
            } else {
                temp[c++] = arr[r++];
            }
        }

        while (l <= mid)
            temp[c++] = arr[l++];
        while (r <= right)
            temp[c++] = arr[r++];

        for (int i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sb = new StringBuilder();

        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }
        int K = Integer.parseInt(br.readLine());
        size = N / K;
        mergeSort(0, N - 1);
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }
}