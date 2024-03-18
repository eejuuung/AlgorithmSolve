import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Node{
        ArrayList<Integer> list;
        Node(){
            list = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        Node[] arr = new Node[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = new Node();
        }

        for(int i=0;i<M;i++){
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());

            arr[a].list.add(b);
            arr[b].list.add(a);
        }

        Queue<Integer> que = new ArrayDeque<>();
        que.offer(1);
        int answer = 0;
        boolean[] bmap = new boolean[N+1];
        bmap[1]=true;
        while (!que.isEmpty()){
            int virus = que.poll();

            for(int i=0;i<arr[virus].list.size();i++){
                if(!bmap[arr[virus].list.get(i)]){
                    bmap[arr[virus].list.get(i)] = true;
                    answer++;
                    que.offer(arr[virus].list.get(i));
                }
            }
        }
        System.out.println(answer);

    }
}
