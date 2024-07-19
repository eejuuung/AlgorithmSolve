import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Room{
        int y;
        int x;
        int time;
        ArrayList<Integer> eatList;

        Room(int y, int x, int time, ArrayList<Integer> list){
            this.y = y;
            this.x = x;
            this.time = time;
            this.eatList = list;
        }
    }

    public static int R,C,T;
    public static int[][] map;

    // 오, 아, 왼, 위
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        T = Integer.parseInt(stz.nextToken());
        Queue<Room> que = new ArrayDeque<>();

        map = new int[R][C];
        int gogumaCnt = 0;
        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                char cdata = str.charAt(j);

                if(cdata == 'G'){
                    que.offer(new Room(i,j,0,new ArrayList<>()));
                } else if(cdata == 'S'){
                    map[i][j] = ++gogumaCnt;
                } else if(cdata == '#'){
                    map[i][j] = -1;
                }

            }
        }

        int maxD = 0;
        while (!que.isEmpty()){

            Room nowQ = que.poll();

            if(nowQ.time>T)
                break;

            if(nowQ.eatList.size()>maxD)
                maxD = nowQ.eatList.size();

            for(int i = 0;i<4;i++){
                int fy = nowQ.y + dy[i];
                int fx = nowQ.x + dx[i];

                if(fy<0 || fx<0 || fy>=R || fx >= C || map[fy][fx]<0)
                    continue;

                ArrayList<Integer> copyList = new ArrayList<>();
                for(int j=0;j<nowQ.eatList.size();j++){
                    copyList.add(nowQ.eatList.get(j));
                }

                //고구마가 있는곳이라면 안먹은 고구마만 먹어주기
                if(map[fy][fx]>0){

                    boolean iseat = false;

                    for(int j=0;j<nowQ.eatList.size();j++){
                        if(nowQ.eatList.get(j) == map[fy][fx]){
                            iseat = true;
                            break;
                        }
                    }

                    if(iseat){
                        que.offer(new Room(fy,fx, nowQ.time+1,copyList));
                    }else{
                        copyList.add(map[fy][fx]);
                        que.offer(new Room(fy,fx, nowQ.time+1,copyList));
                    }
                } else{
                    que.offer(new Room(fy,fx, nowQ.time+1,copyList));
                }

            }

        }

        System.out.println(maxD);
    }
}