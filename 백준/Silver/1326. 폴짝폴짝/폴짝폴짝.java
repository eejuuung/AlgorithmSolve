import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static class Pair{
        int num;
        int time;
        public Pair(int num, int time){
            this.num = num;
            this.time = time;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] map = new int[N+1];
        boolean[] bmap = new boolean[N+1];
        int sNum,eNum;

        StringTokenizer stz = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            map[i] = Integer.parseInt(stz.nextToken());
        }
        stz = new StringTokenizer(br.readLine());
        sNum = Integer.parseInt(stz.nextToken());
        eNum = Integer.parseInt(stz.nextToken());

        Queue<Pair> que = new ArrayDeque<>();
        bmap[sNum] = true;
        que.offer(new Pair(sNum,0));

        int ans = -1;

        while (!que.isEmpty()){
            Pair nowP = que.poll();
            int nowNum = nowP.num;
            int time = nowP.time;
            int jump = map[nowNum];

            if(nowNum == eNum){
                ans = time;
                break;
            }
            
            //배수를 이용해서 이동가능한 모든 방 체크하기
            //오른쪽
            for(int i=1;(nowNum + (jump * i)) <= N ;i++){
                int cal = nowNum + (jump * i);
                if(!bmap[cal]){
                    bmap[cal] = true;
                    que.offer(new Pair(cal,time+1));
                }
            }
            //왼쪽
            for(int i=1;(nowNum - (jump * i))>=1;i++){
                int cal = nowNum - (jump * i);
                if(!bmap[cal]){
                    bmap[cal] = true;
                    que.offer(new Pair(cal,time+1));
                }
            }
        }

        System.out.println(ans);

    }
}