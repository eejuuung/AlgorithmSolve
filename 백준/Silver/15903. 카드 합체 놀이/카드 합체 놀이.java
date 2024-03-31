import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Long> que = new PriorityQueue<>();

        int N, M;
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        stz = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            que.offer(Long.parseLong(stz.nextToken()));
        }

        for(int j=0;j<M;j++){
            long a = que.poll();
            long b = que.poll();
            que.offer(a+b);
            que.offer(a+b);
        }
        long answer = 0;
        while (!que.isEmpty()){
            answer += que.poll();
        }
        System.out.println(answer);
    }
}
