import java.io.*;
import java.util.*;

public class Main {

    static final int ALPHABET_NUMBER = 26;

    // dictionary = 사전에 갖고있는 수, readNum = 읽은 단어
    public static boolean checkBit(int dictionary, int readNum) {
        return (readNum & dictionary) == readNum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int[] arr = new int[N];
        int dictionary = (1 << ALPHABET_NUMBER) - 1;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                // |=을 하면 기존에 1이면 그대로 유지됨
                arr[i] |= (1 << (str.charAt(j) - 'a'));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(stz.nextToken());
            char x = stz.nextToken().charAt(0);

            if (order == 1) {
                dictionary &= ~(1 << (x - 'a'));
            } else {
                dictionary |= (1 << (x - 'a'));
            }

            int count = 0;
            for (int j = 0; j < N; j++) {
                if (checkBit(dictionary, arr[j]))
                    count++;
            }
            sb.append(count).append("\n");
        }

        System.out.print(sb);

    }
}