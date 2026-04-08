import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int parent;
        int count;
        ArrayList<Integer> list;

        Node(int parent) {
            this.parent = parent;
            this.count = 0;
            list = new ArrayList<>();
        }
    }

    static int N;
    static int find1, find2;
    static Node[] people;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new Node[N + 1];
        for (int i = 0; i <= N; i++) {
            people[i] = new Node(i);
        }

        StringTokenizer stz = new StringTokenizer(br.readLine());
        find1 = Integer.parseInt(stz.nextToken());
        find2 = Integer.parseInt(stz.nextToken());

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            stz = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stz.nextToken());
            int y = Integer.parseInt(stz.nextToken());
            people[x].list.add(y);
            people[y].parent = x;
        }

        boolean[] check = new boolean[N + 1];
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(find1);
        check[find1] = true;

        int answer = -1;
        while (!que.isEmpty()) {
            Integer now = que.poll();

            if (now == find2) {
                answer = people[find2].count;
                break;
            }

            if (!check[people[now].parent] && people[now].parent != now) {
                que.offer(people[now].parent);

                people[people[now].parent].count = people[now].count + 1;
            }

            for (int num : people[now].list) {
                if (check[num])
                    continue;

                check[num] = true;
                que.offer(num);
                people[num].count = people[now].count + 1;
            }
        }
        System.out.println(answer);
    }
}