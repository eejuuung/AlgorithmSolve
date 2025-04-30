import java.io.*;
import java.util.*;

public class Main {

    static int endN;
    static int N;
    static List<Integer> list;
    static int maxEnergy;

    public static void dfs(int num, int energy) {

        if (num >= endN) {
            if (energy > maxEnergy)
                maxEnergy = energy;
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            N--;
            int val = list.get(i);
            int total = energy + (list.get(i + 1) * list.get(i - 1));
            list.remove(i);
            dfs(num + 1, total);
            list.add(i, val);
            N++;
        }


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        endN = N - 2;
        maxEnergy = 0;
        list = new ArrayList<>();
        StringTokenizer stz = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(stz.nextToken()));
        }

        dfs(0, 0);

        System.out.println(maxEnergy);
    }
}
