import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
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

    public static int R,C;
    public static char[][] cMap;
    // 오, 아, 왼, 위
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};

    public static void brokenMineral(int y, boolean toggle){
        // 해당자리의 미네랄 부수고 해당 미네랄이 아래로 내려갈 수 있는지 여부체크하고 한칸씩 내림
        int x = 0;

        // 1. 우선 해당 미네랄을 부숨
        boolean isbroken = false;
        if(toggle){
            for(int i=0;i<C;i++){
                if(cMap[R-y][i]=='x'){
                    cMap[R-y][i] = '.';
                    y = R-y;
                    x = i;
                    isbroken = true;
                    break;
                }
            }
        }else{
            for(int i=C-1;i>=0;i--){
                if(cMap[R-y][i]=='x'){
                    cMap[R-y][i] = '.';
                    y = R-y;
                    x = i;
                    isbroken = true;
                    break;
                }
            }
        }

        if(!isbroken){
            return;
        }
        // 2. 해당자리에 4방향으로 존재하는 미네랄의 집합을 구함 = 맨처음은 위에있는 집합만

        // 아
        if(y+1<R)
            ddown(y+1,x);

        // 왼또는 오
        if(toggle && x+1<C){
            ddown(y,x+1);
        }else if(!toggle && x-1>=0){
            ddown(y,x-1);
        }

        //위
        if(y-1>=0)
            ddown(y-1,x);


    }

    public static void ddown(int y, int x){
        // 2. 해당자리에 4방향으로 존재하는 미네랄의 집합을 구함 = 맨처음은 위에있는 집합만
        ArrayList<Pair> list = new ArrayList<>();
        Queue<Pair> que = new ArrayDeque<>();
        boolean[][] bMap = new boolean[R][C];
        boolean iscon = false;

        if(cMap[y][x]=='x'){
            que.offer(new Pair(y,x));
            list.add(new Pair(y,x));
            bMap[y][x] = true;
            iscon = true;
        }

        if(!iscon)
            return;


        while (!que.isEmpty()){
            Pair nowP = que.poll();

            for(int i=0;i<4;i++){
                int fy = nowP.y+dy[i];
                int fx = nowP.x +dx[i];

                if(fy<0 || fx<0 || fy>=R || fx>=C|| cMap[fy][fx]=='.' || bMap[fy][fx])
                    continue;
                que.offer(new Pair(fy,fx));
                list.add(new Pair(fy,fx));
                bMap[fy][fx] = true;
            }
        }

        // 여기서 최대 몇칸 내려갈 수 있는지 체크해야함.
        // 아래가 빈칸인 경우는 모드 고려해야함. 만약 아래가 빈칸이 아닌 같은 클러스터라면 고려X
        // 비어있다가 아래 같은 클러스터인경우와 아닌경우도 고려할것.

        ArrayList<Pair> donwlist = new ArrayList<>();   // 일단 아래가 빈칸인 list먼저 체크
        for(int i=0;i<list.size();i++){
            if(list.get(i).y+1<R && cMap[list.get(i).y+1][list.get(i).x]=='.'){
                donwlist.add(new Pair(list.get(i).y,list.get(i).x));
            }
            if(list.get(i).y==R-1)
                return;
        }
        if(donwlist.isEmpty())
            return;

        // 여기서부터 무조건 내릴 수 있는경우임! 그러므로 일단 map에서 해당 값들을 비워주고 내리자!
        for(int i=0;i< list.size();i++){
            cMap[list.get(i).y][list.get(i).x] = '.';
        }


        // 구한 downlist에서 최대 어디까지 내려갈 수 있는지  min값 체크
        int min = Integer.MAX_VALUE;
        int cnt;
        for(int i=0;i< donwlist.size();i++){
            cnt = 0;
            for(int j = donwlist.get(i).y+1;j<R;j++){
                if(cMap[j][donwlist.get(i).x]=='.'){
                    cnt++;
                } else {
                    break;
                }
            }
            if(cnt<min){
                min = cnt;
            }
        }

        // 전체리스트에서 cnt만큼 y값 증가시켜서 넣기
        for(int i=0;i<list.size();i++){
            cMap[list.get(i).y+min][list.get(i).x] = 'x';
        }
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        cMap = new char[R][C];
        String str;
        for(int i=0;i<R;i++){
            str = br.readLine();
            for(int j=0;j<C;j++){
                cMap[i][j] = str.charAt(j);
            }
        }

        int N = Integer.parseInt(br.readLine());
        stz = new StringTokenizer(br.readLine());
        boolean toggle = false;
        for(int i=0;i<N;i++){
            toggle=!toggle;
            brokenMineral(Integer.parseInt(stz.nextToken()),toggle);
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                sb.append(cMap[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }
}