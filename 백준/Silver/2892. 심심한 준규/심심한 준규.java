import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine());

        for (int i = 0, num = 0; i < N; i++) {
            num = Integer.parseInt(stz.nextToken(), 16);
            if (num <= 31)
                sb.append(".");
            else
                sb.append("-");
        }
        System.out.println(sb);

        // 확인용
//        boolean[] check = new boolean[100000];
//        List<Integer> list = new ArrayList<>();
//        int a = 'a';
//        for (int i = '0'; i <= '9'; i++) {

//            ['0'~'9'] ^ [' ', '.']
//            int num = ' ' ^ i;
//            list.add(num);
//            num = '.' ^ i;
//            list.add(num);

//            ['0'~'9'] ^ ['a'~'z']
//            for (int j = a; j < 'z'; j++) {
//                int num = i ^ j;
//                if (!check[num]) {
//                    list.add(num);
//                    check[num] = true;
//                }
//            }
//        }
//
//        Collections.sort(list);
//        for (int node : list) {
//            sb.append(node).append(" ");
//        }
//
//        System.out.println(sb);
    }
}
