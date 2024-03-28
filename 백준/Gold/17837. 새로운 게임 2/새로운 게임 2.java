import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static class Map{
        ArrayList<Integer> list;
        int color;

        Map(int color){
            list = new ArrayList<>();
            this.color = color;
        }
    }

    public static class Horse{
        int y;
        int x;
        int d;

        public Horse(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    public static Map[][] map;
    public static int N;
    public static int K;
    public static ArrayList<Horse> horses;

    // 오, 왼, 위, 아
    public static int[] dy = {0, 0, 0, -1, 1};
    public static int[] dx = {0, 1, -1, 0, 0};

    public static int moveHorse() {

        for(int k=1;k<=1000;k++) {
           // System.out.println("time : "+k);
            // 말의 순서대로 차례대로 이동
            for(int i=0;i<K;i++) {
                Horse horse = horses.get(i);
                int fy = horse.y + dy[horse.d];
                int fx = horse.x + dx[horse.d];
                int color = 0;
                // 이동하려는 말의 위치가 넘어가면 파랑
                if(fy<0 || fx<0 || fy>=N || fx>=N) {
                    color = 2;
                }
                else{
                    color = map[fy][fx].color;
                }

                //System.out.print("horse "+(i+1) + "start : y = "+fy+", x = "+fx+", color = "+color);

                // 이동하려는 위치의 말의 갯수 + 현재 이동되는 말의 갯수 >= 4 -> 종료조건
                // 현재 이동하려는 말의 이동되는 말의 번호와 함께 이동될 말 체크
                ArrayList<Integer> list = new ArrayList<>();
                boolean bfind = false;
                for(int j=0;j<map[horse.y][horse.x].list.size() ;) {
                    if(map[horse.y][horse.x].list.get(j) == i) {
                        bfind = true;
                    }

                    if(bfind) {
                        list.add(map[horse.y][horse.x].list.get(j));
                        map[horse.y][horse.x].list.remove(j);
                    }
                    else {
                        j++;
                    }
                }
                // color 처리
                if(color == 0) { // 흰색
                    // 그 칸으로 이동, 이동하려는 칸에 말이 이미 있는경우 가장위에 올려놓음.
                    for(int j=0;j<list.size();j++) {
                        map[fy][fx].list.add(list.get(j));
                        // 해당 말 위치값도 바꿔주기
                        horses.get(list.get(j)).y = fy;
                        horses.get(list.get(j)).x = fx;
                    }
                } else if(color == 1) { // 빨간색
                    // 그 칸으로이동, 이동하려는 칸에 말이 이미 있는경우 역순으로 위로 올리기
                    for(int j=list.size()-1;j>=0;j--) {
                        map[fy][fx].list.add(list.get(j));
                        // 해당 말 위치값도 바꿔주기
                        horses.get(list.get(j)).y = fy;
                        horses.get(list.get(j)).x = fx;
                    }

                } else if(color == 2) { // 파란색
                    // 방향을 반대로 바꾸고
                    if( horses.get(i).d == 1)
                        horses.get(i).d =2;
                    else if( horses.get(i).d == 2)
                        horses.get(i).d =1;
                    else if( horses.get(i).d == 3)
                        horses.get(i).d =4;
                    else if( horses.get(i).d == 4)
                        horses.get(i).d =3;
                    // 한칸 이동하는데
                    fy = horse.y + dy[horses.get(i).d];
                    fx = horse.x + dx[horses.get(i).d];

                    //범위넘거나 이동하려는 곳이 파랑이라면 이동하지 않는다.
                    if(fy<0 || fx<0 || fy>=N || fx>=N || map[fy][fx].color==2){
                        fy = horse.y;
                        fx = horse.x;
                        for(int j=0;j<list.size();j++){
                            map[fy][fx].list.add(list.get(j));
                            // 해당 말 위치값도 바꿔주기
                            horses.get(list.get(j)).y = fy;
                            horses.get(list.get(j)).x = fx;
                        }
                    } else{
                        color = map[fy][fx].color;
                        if(color == 0) { // 흰색
                            // 그 칸으로 이동, 이동하려는 칸에 말이 이미 있는경우 가장위에 올려놓음.
                            for(int j=0;j<list.size();j++) {
                                map[fy][fx].list.add(list.get(j));
                                // 해당 말 위치값도 바꿔주기
                                horses.get(list.get(j)).y = fy;
                                horses.get(list.get(j)).x = fx;
                            }
                        } else if(color == 1) { // 빨간색
                            // 그 칸으로이동, 이동하려는 칸에 말이 이미 있는경우 역순으로 위로 올리기
                            for(int j=list.size()-1;j>=0;j--) {
                                map[fy][fx].list.add(list.get(j));
                                // 해당 말 위치값도 바꿔주기
                                horses.get(list.get(j)).y = fy;
                                horses.get(list.get(j)).x = fx;
                            }

                        }
                    }
                }

                // 종료조건
                if(map[fy][fx].list.size()>=4) {
                    return k;
                }
//
//                // 각 색깔별로 처리끝나면 해당 말 위치값도 바꿔주기
//                horses.get(i).y = fy;
//                horses.get(i).x = fx;

                //System.out.println("\t horse "+(i+1) + "end : y = "+fy+"x = "+fx+" color = "+color);
            }

        }
        return -1;
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());

        map = new Map[N][N];
        horses = new ArrayList<>();

        for(int i=0;i<N;i++) {
            stz = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = new Map(Integer.parseInt(stz.nextToken()));
            }
        }

        for(int i=0;i<K;i++) {
            stz = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(stz.nextToken());
            int x = Integer.parseInt(stz.nextToken());
            int d = Integer.parseInt(stz.nextToken());
            horses.add(new Horse(y-1, x-1, d));
            map[y-1][x-1].list.add(i);
        }

        //말이동
        int answer = moveHorse();

        System.out.println(answer);


    }

}