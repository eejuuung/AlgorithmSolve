import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static class Pair{
        int y;
        int x;
        Pair(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    // 위, 오위, 오, 오아, 아, 아왼, 왼, 왼위
    public static int[] qdy = {-1,-1,0,1,1,1,0,-1};
    public static int[] qdx = {0,1,1,1,0,-1,-1,-1};

    // 오위1, 오위2, 오아1, 오아2, 왼아1, 왼아2, 왼위1, 왼위2'
    public static int[] kdy = {-2,-1,1,2,2,1,-1,-2};
    public static int[] kdx = {1,2,2,1,-1,-2,-2,-1};

    public static int N,M;
    public static int[][] map;

    public static void queenCheck(ArrayList<Pair> list){

        //Queen먼저 위험구역 체크
        for(int i=0;i<list.size();i++){
            Pair nowPair = list.get(i);

            for(int j=0;j<8;j++){
                int fy = nowPair.y;
                int fx = nowPair.x;

                while (true){
                    int ffy = fy + qdy[j];
                    int ffx = fx + qdx[j];

                    if(ffy<0 || ffx<0 || ffy>=N || ffx >= M || map[ffy][ffx]==1){
                        break;
                    }
                    fy = ffy;
                    fx = ffx;
                    map[fy][fx] = 2;
                }
            }

        }
    }

    public static void kingCheck(ArrayList<Pair> list){

        //Queen먼저 위험구역 체크
        for(int i=0;i<list.size();i++){
            Pair nowPair = list.get(i);

            for(int j=0;j<8;j++){
                int fy = nowPair.y + kdy[j];
                int fx = nowPair.x + kdx[j];

                if(fy<0 || fx<0 || fy>=N || fx >= M || map[fy][fx]==1){
                    continue;
                }

                map[fy][fx] = 2;
            }

        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        map = new int[N][M];
        ArrayList<Pair> queenList = new ArrayList<>();
        ArrayList<Pair> kingList = new ArrayList<>();

        stz = new StringTokenizer(br.readLine());
        int queenSize = Integer.parseInt(stz.nextToken());
        for(int i=0;i<queenSize;i++){
            int y = Integer.parseInt(stz.nextToken())-1;
            int x = Integer.parseInt(stz.nextToken())-1;

            queenList.add(new Pair(y,x));
            map[y][x] = 1;
        }

        stz = new StringTokenizer(br.readLine());
        int kingSize = Integer.parseInt(stz.nextToken());
        for(int i=0;i<kingSize;i++){
            int y = Integer.parseInt(stz.nextToken())-1;
            int x = Integer.parseInt(stz.nextToken())-1;

            kingList.add(new Pair(y,x));
            map[y][x] = 1;
        }

        stz = new StringTokenizer(br.readLine());
        int pawnSize = Integer.parseInt(stz.nextToken());
        for(int i=0;i<pawnSize;i++){
            int y = Integer.parseInt(stz.nextToken())-1;
            int x = Integer.parseInt(stz.nextToken())-1;

            map[y][x] = 1;
        }

        //Queen 위험구역 체크
        queenCheck(queenList);

        //King 위험구역 체크
        kingCheck(kingList);

        int answer = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == 0)
                    answer++;
            }
        }

        System.out.println(answer);

    }
}