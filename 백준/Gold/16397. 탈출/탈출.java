import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Exit{
        int value;
        int time;
        public Exit(int value, int time){
            this.value = value;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int T = Integer.parseInt(stz.nextToken());
        int G = Integer.parseInt(stz.nextToken());
        boolean[] bMap = new boolean[100000];
        bMap[N] = true;
        int answer = 0;

        Queue<Exit> que = new ArrayDeque<>();
        que.offer(new Exit(N,0));

        boolean isout = false;
        while (!que.isEmpty()) {
            Exit nowE = que.poll();

            if(nowE.time>T){
                break;
            }
            
            if(nowE.value == G){
                answer = nowE.time;
                isout = true;
                break;
            }

            if(nowE.value+1<=99999 && !bMap[nowE.value+1]){
                bMap[nowE.value+1] = true;
                que.offer(new Exit(nowE.value+1, nowE.time+1));
            }

            int num = nowE.value*2;
            if(num<=99999){

                String str = String.valueOf(num);
                int check = str.charAt(0)-'0';
                if(check == 0){
                    continue;
                }

                if(str.length() == 1){
                    if(bMap[check-1])
                        continue;
                    bMap[check-1] = true;
                    que.offer(new Exit(check-1, nowE.time+1));

                } else{
                    str = str.substring(1);
                    str = String.valueOf(check-1) + str;
                    check = Integer.parseInt(str);

                    if(bMap[check])
                        continue;
                    bMap[check] = true;
                    que.offer(new Exit(check, nowE.time+1));
                }
            }



        }

        if(!isout){
            System.out.println("ANG");
        }else{
            System.out.println(answer);
        }







    }
}