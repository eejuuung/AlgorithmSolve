import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        int[] arr = new int[str.length()];
        int count0 = 0;
        int count1 = 0;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            arr[i] = str.charAt(i) - '0';
            if (arr[i] == 0)
                count0++;
            else
                count1++;
        }

        // 0delete
        count0 /= 2;
        for (int i = length - 1; i >= 0; i--) {
            if (count0 <= 0)
                break;
            if (arr[i] == 0) {
                arr[i] = -1;
                count0--;
            }
        }

        // 1delete
        count1 /= 2;
        for (int i = 0; i < length; i++) {
            if (count1 <= 0)
                break;
            if (arr[i] == 1) {
                arr[i] = -1;
                count1--;
            }
        }

        for (int i = 0; i < length; i++) {
            if (arr[i] != -1)
                sb.append(arr[i]);
        }
        System.out.println(sb);
    }
}