import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static Material[][][] materials;
    public static Material[][] GaMa;
    public static int[] pick_GaMa;
    public static int[][] location_GaMa = {{0,0},{0,1},{1,0},{1,1}};  //가마의 위치
    public static int[][] move_GaMa =
                {{0,0},{0,1},{0,2},{0,3},
                          {1,0},{1,1},{1,2},{1,3},
                          {2,0},{2,1},{2,2},{2,3},
                          {3,0},{3,1},{3,2},{3,3},};  //가마의 옮길 위치, 방향
    public static int[] choice;
    public static int answer = 0;
    public static int[] pick_dfs;


    public static class Material {
        int efect; // 효능
        char element; // 원소

        Material(int efect, char element) {
            this.efect = efect;
            this.element = element;
        }

        Material() {
            this.efect = 0;
            this.element = 'W';
        }
    }

    public static boolean np(int[] p) {
        int i = p.length - 1;
        while (i > 0 && p[i - 1] >= p[i])
            i--;

        if (i == 0)
            return false;

        int j = p.length - 1;
        while (p[i - 1] >= p[j])
            j--;
        swap(p, i - 1, j);

        j = p.length - 1;
        while (i < j) {
            swap(p, i++, j--);
        }

        return true;
    }

    public static void swap(int[] p, int a, int b) {
        int c = p[a];
        p[a] = p[b];
        p[b] = c;
    }

    public static void RunningGaMa(){
        /**
         * 1. 선택한 재료에 대해 가마의 어느위치에 고를 것인지를 체크해야함.
         *      -> 이때! 가마는 5*5, 재료는 4*4이므로 위치는 (0,0), (0,1), (1,0), (1,1) 4가지만 가능함!
         *      -> k = 0 ~ 4 / location_GaMa[k][0] = y, location_GaMa[k][1] = x
         * 2. 체크한 후에는 얼만큼 회전해서 넣을 것인지를 체크해야함.
         *      -> 이떄! 방향은 90도 180도 270도로 총 3개임!
         *
         * 3. 3개의 재료를 다 넣었을 때
         *
         * 선택한 가마 = pick_GaMa
         * 가마 별 선택한 위치, 방향 = move_GaMa
         *
         */

        // 가마 초기값 세팅
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                GaMa[j][k] = new Material(0,'W');
            }
        }

        for(int k=0;k<3;k++){ // 가마선택 후 합쳐주기
            int num = pick_GaMa[k];
            int location = move_GaMa[choice[k]][0];
            int direct = move_GaMa[choice[k]][1];

            int start_y = location_GaMa[location][0];
            int start_x = location_GaMa[location][1];
            
            //넣은 재료 회전해준 배열 체크
            Material[][] matearr = new Material[4][4];
            lotation(direct,matearr,num);

            // 시작y, 시작 x부터 가마에 합쳐주기
            //- 격자의 품질은 재료의 효능이 더해짐. 더한 뒤의 값이 음수인 경우 0으로, 초과인 경우 9로 변경
            //- 격자의 색은 재료의 원소가 흰색인 경우 그대로, 아닌 경우 재료의 원소와 같은 색으로 칠해짐
            for(int i = start_y, n = 0 ;n<4; i++,n++){
                for(int j=start_x,m=0;m<4;j++,m++){
                    if ( GaMa[i][j].efect + matearr[n][m].efect <=0){ // 음수인 경우 0
                        GaMa[i][j].efect = 0;
                    } else if ( GaMa[i][j].efect + matearr[n][m].efect >9){ // 초과인 경우 9
                        GaMa[i][j].efect = 9;
                    } else{ // 아니면 더한값으로
                        GaMa[i][j].efect = GaMa[i][j].efect + matearr[n][m].efect;
                    }

                   if( matearr[n][m].element != 'W')
                       GaMa[i][j].element = matearr[n][m].element;
                }
            }
        }
        
        // 폭탄의 품질 체크
        int totalR = 0;
        int totalB = 0;
        int totalG = 0;
        int totalY = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(GaMa[i][j].element == 'R'){
                    totalR += GaMa[i][j].efect;
                } else if(GaMa[i][j].element == 'B'){
                    totalB += GaMa[i][j].efect;
                } else if(GaMa[i][j].element == 'G'){
                    totalG += GaMa[i][j].efect;
                } else if(GaMa[i][j].element == 'Y'){
                    totalY += GaMa[i][j].efect;
                }
            }
        }
        int bomb = (7*totalR) + (5*totalB) + (3*totalG) + (2*totalY);
        if(bomb > answer)
            answer = bomb;
    }

    public static void lotation(int direct, Material[][] matearr , int num ){
        if(direct == 0){
            for(int i=0, m = 3;i<4;i++,m--){
                for(int j=0, n = 0;j<4;j++,n++){
                    int efect = materials[num][i][j].efect;
                    char element = materials[num][i][j].element;
                    matearr[n][m] = new Material(efect,element);
                }
            }
        }
        else if(direct == 1){
            for(int i=0, n = 3;i<4;i++,n--){
                for(int j=0, m = 3;j<4;j++,m--){
                    int efect = materials[num][i][j].efect;
                    char element = materials[num][i][j].element;
                    matearr[n][m] = new Material(efect,element);
                }
            }
        }
        else if(direct == 2){
            for(int i=0, m = 0;i<4 ; i++, m++){
                for(int j=0, n=3;j<4;j++,n--){
                    int efect = materials[num][i][j].efect;
                    char element = materials[num][i][j].element;
                    matearr[n][m] = new Material(efect,element);
                }
            }
        }
        else if(direct == 3){
            for(int i=0;i<4 ; i++){
                for(int j=0;j<4;j++){
                    matearr[i][j] = new Material(materials[num][i][j].efect,materials[num][i][j].element);
                }
            }
        }
    }

    public static void dfs_GaMa(int depth){
        if(depth >= 3){
            RunningGaMa();
            return;
        }

        for(int i=0;i<12;i++){
            choice[depth] = i;
            dfs_GaMa(depth+1);
        }
    }

    public static boolean[] bcheck;
    public static int[] dfschoice;
    public static void dfs_pick(int depth){

        if(depth>=3){
            pick_GaMa[0] = pick_dfs[dfschoice[0]];
            pick_GaMa[1] = pick_dfs[dfschoice[1]];
            pick_GaMa[2] = pick_dfs[dfschoice[2]];
            //가마에 재료넣게 넘겨주기!
            dfs_GaMa(0);
            return;
        }

        for(int i=0;i<3;i++){
            if(bcheck[i])
                continue;
            bcheck[i] = true;
            dfschoice[depth] = i;
            dfs_pick(depth+1);
            bcheck[i] = false;


        }

    }



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        materials = new Material[N][4][4];
        pick_GaMa = new int[3];
        GaMa = new Material[5][5];
        choice = new int[3];
        bcheck = new boolean[3];
        dfschoice = new int[3];
        StringTokenizer stz;

        for (int i = 0; i < N; i++) {

            // 품질
            for (int j = 0; j < 4; j++) {
                stz = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < 4; k++) {
                    materials[i][j][k]  = new Material();
                    materials[i][j][k].efect = Integer.parseInt(stz.nextToken());
                }
            }
            //원소
            for (int j = 0; j < 4; j++) {
                stz = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < 4; k++) {
                    materials[i][j][k].element = stz.nextToken().charAt(0);
                }
            }
        }

        //먼저 할 일은 재료를 3개 고르는 NC3의 조합을 뽑아내야함! -> 넥퍼를 사용할거임!
        int[] p = new int[N];
        p[N - 1] = 1;
        p[N - 2] = 1;
        p[N - 3] = 1;
        do{
            pick_dfs = new int[3];
            int j=0;;
            for(int i=0;i<N;i++){
                if(p[i] == 1){
                    pick_dfs[j++] = i;
                }
            }
            dfs_pick(0);
        }while (np(p));

        System.out.println(answer);
    }
}
