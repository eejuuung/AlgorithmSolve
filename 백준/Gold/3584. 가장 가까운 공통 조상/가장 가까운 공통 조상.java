import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for (int rtc = 0; rtc < tc; rtc++) {
            int N = Integer.parseInt(br.readLine());
            int[] map = new int[10005];
            StringTokenizer stz;

            for (int i = 1; i < N; i++) {
                stz = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(stz.nextToken());
                int child = Integer.parseInt(stz.nextToken());
                map[child] = parent;
            }
            stz = new StringTokenizer(br.readLine());

            boolean[] bmap = new boolean[10005];
            int node1 = Integer.parseInt(stz.nextToken());
            int node2 = Integer.parseInt(stz.nextToken());
            bmap[node1] = true;

            int nowNode = -1;
            while (nowNode != 0) {
                nowNode = map[node1];
                node1 = nowNode;
                bmap[nowNode] = true;
            }

            nowNode = node2;
            while (!bmap[nowNode]) {
                nowNode = map[node2];
                node2 = nowNode;
            }
            sb.append(nowNode).append("\n");
        }

        System.out.print(sb);
    }
}