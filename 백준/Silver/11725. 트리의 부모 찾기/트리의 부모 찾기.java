import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Node{
        ArrayList<Integer> alist;

        public Node() {
            this.alist = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int rN = Integer.parseInt(br.readLine());
        Node[] map = new Node[rN+1];
        for(int i=0;i<=rN;i++)
            map[i] = new Node();

        String str;
        StringTokenizer stz;
        int node1, node2;
        for(int i=1;i<rN;i++){
            str = br.readLine();
            stz = new StringTokenizer(str);
            node1 = Integer.parseInt(stz.nextToken());
            node2 = Integer.parseInt(stz.nextToken());
            map[node1].alist.add(node2);
            map[node2].alist.add(node1);
        }

        boolean[] mapcheck = new boolean[rN+1];
        int[] answer = new int[rN+1];
        answer[1] = 1;
        mapcheck[1] = true;
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(1);
        while (!que.isEmpty()){
            int getnum = que.peek();
            for(int i=0;i<map[getnum].alist.size();i++){
                int num = map[getnum].alist.get(i);
                if(answer[num]!=0)
                    continue;
                answer[num] = getnum;
                if(!mapcheck[num]){
                    que.offer(num);
                    mapcheck[num] = true;
                }
            }

            que.poll();
        }

        for(int i=2;i<rN+1;i++){
            sb.append(answer[i]).append("\n");
        }
        System.out.print(sb);
    }
}
