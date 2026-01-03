import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int S = Integer.parseInt(stz.nextToken());
        int R = Integer.parseInt(stz.nextToken());
        boolean[] kayak = new boolean[N + 1];

        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int num = Integer.parseInt(stz.nextToken());
            kayak[num] = true;
        }

        List<Integer> list = new ArrayList<>();
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int num = Integer.parseInt(stz.nextToken());
            if (!kayak[num])
                list.add(num);
            else
                kayak[num] = false;
        }
        Collections.sort(list, Collections.reverseOrder());

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) + 1 <= N && kayak[list.get(i) + 1])
                kayak[list.get(i) + 1] = false;
            else if (list.get(i) - 1 >= 0 && kayak[list.get(i) - 1])
                kayak[list.get(i) - 1] = false;
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (kayak[i])
                answer++;
        }
        System.out.println(answer);
    }
}