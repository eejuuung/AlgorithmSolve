import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void movieCube(char order, char dir) {

        copycube = new char[6][3][3];

        for(int k=0;k<6;k++){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    copycube[k][i][j] = cube[k][i][j];
                }
            }
        }

        // 시계방향
        if(dir == '+') {
            int color = 0;
            switch (order) {
                case 'U': //윗면
                    color = 0;
                    //상단
                    frontMoveCubeR(color);
                    //옆
                    for(int i=0,j=2;i<3;i++,j--) {
                        copycube[1][0][i] = cube[5][0][i];
                        copycube[4][0][i] = cube[1][0][i];
                        copycube[3][2][i] = cube[4][0][j];
                        copycube[5][0][i] = cube[3][2][j];
                    }
                    break;
                case 'D': //아랫 면
                    color = 2;
                    //상단
                    frontMoveCubeR(color);
                    //옆
                    for(int i=0,j=2;i<3;i++,j--) {
                        copycube[1][2][i] = cube[4][2][i];
                        copycube[4][2][i] = cube[3][0][j];
                        copycube[3][0][i] = cube[5][2][j];
                        copycube[5][2][i] = cube[1][2][i];
                    }
                    break;
                case 'F': //앞 면
                    color = 1;
                    //상단
                    frontMoveCubeR(color);
                    //옆
                    for(int i=0,j=2;i<3;i++,j--) {
                        copycube[0][2][i] = cube[4][j][2];
                        copycube[4][i][2] = cube[2][0][i];
                        copycube[2][0][i] = cube[5][j][0];
                        copycube[5][i][0] = cube[0][2][i];
                    }
                    break;
                case 'B': //뒷 면
                    color = 3;
                    //상단
                    frontMoveCubeR(color);
                    for(int i=0,j=2;i<3;i++,j--) {
                        copycube[0][0][i] = cube[5][i][2];
                        copycube[5][i][2] = cube[2][2][j];
                        copycube[2][2][i] = cube[4][i][0];
                        copycube[4][i][0] = cube[0][0][j];
                    }
                    break;
                case 'L': //왼쪽 면
                    color = 4;
                    //상단
                    frontMoveCubeR(color);
                    //옆
                    for(int i=0;i<3;i++) {
                        copycube[1][i][0] = cube[0][i][0];
                        copycube[2][i][0] = cube[1][i][0];
                        copycube[3][i][0] = cube[2][i][0];
                        copycube[0][i][0] = cube[3][i][0];
                    }
                    break;
                case 'R': //오른쪽 면
                    color = 5;
                    //상단
                    frontMoveCubeR(color);
                    //옆
                    for(int i=0;i<3;i++) {
                        copycube[0][i][2] = cube[1][i][2];
                        copycube[1][i][2] = cube[2][i][2];
                        copycube[2][i][2] = cube[3][i][2];
                        copycube[3][i][2] = cube[0][i][2];

                    }
                    break;
            }
        }
        // 반시계 방향
        else if(dir == '-'){
            int color = 0;
            switch (order) {
                case 'U': //윗면
                    color = 0;
                    //상단
                    frontMoveCubeL(color);
                    //옆
                    for(int i=0,j=2;i<3;i++,j--) {
                        copycube[1][0][i] = cube[4][0][i];
                        copycube[4][0][i] = cube[3][2][j];
                        copycube[3][2][i] = cube[5][0][j];
                        copycube[5][0][i] = cube[1][0][i];
                    }
                    break;
                case 'D': //아랫 면
                    color = 2;
                    //상단
                    frontMoveCubeL(color);
                    //옆
                    for(int i=0,j=2;i<3;i++,j--) {
                        copycube[1][2][i] = cube[5][2][i];
                        copycube[4][2][i] = cube[1][2][i];
                        copycube[3][0][i] = cube[4][2][j];
                        copycube[5][2][i] = cube[3][0][j];
                    }
                    break;
                case 'F': //앞 면
                    color = 1;
                    //상단
                    frontMoveCubeL(color);
                    //옆
                    for(int i=0,j=2;i<3;i++,j--) {
                        copycube[0][2][i] = cube[5][i][0];
                        copycube[4][i][2] = cube[0][2][j];
                        copycube[2][0][i] = cube[4][i][2];
                        copycube[5][i][0] = cube[2][0][j];
                    }
                    break;
                case 'B': //뒷 면
                    color = 3;
                    //상단
                    frontMoveCubeL(color);
                    //옆
                    for(int i=0,j=2;i<3;i++,j--) {
                        copycube[0][0][i] = cube[4][j][0];
                        copycube[4][i][0] = cube[2][2][i];
                        copycube[2][2][i] = cube[5][j][2];
                        copycube[5][i][2] = cube[0][0][i];
                    }
                    break;
                case 'L': //왼쪽 면
                    color = 4;
                    //상단
                    frontMoveCubeL(color);
                    for(int i=0;i<3;i++) {
                        copycube[0][i][0] = cube[1][i][0];
                        copycube[1][i][0] = cube[2][i][0];
                        copycube[2][i][0] = cube[3][i][0];
                        copycube[3][i][0] = cube[0][i][0];
                    }
                    break;
                case 'R': //오른쪽 면
                    color = 5;
                    //상단
                    frontMoveCubeL(color);
                    for(int i=0;i<3;i++) {
                        copycube[1][i][2] = cube[0][i][2];
                        copycube[2][i][2] = cube[1][i][2];
                        copycube[3][i][2] = cube[2][i][2];
                        copycube[0][i][2] = cube[3][i][2];
                    }
                    break;
            }
        }

        for(int k=0;k<6;k++){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    cube[k][i][j] = copycube[k][i][j];
                }
            }
        }
    }



    public static void frontMoveCubeR(int color) {
        //상단
        for(int i=0;i<3;i++) {
            for(int j=0,k=2;j<3;j++,k--) {
                copycube[color][i][j] = cube[color][k][i];
            }
        }
    }

    public static void frontMoveCubeL(int color) {
        //상단
        for(int i=0,k=2;i<3;i++,k--) {
            for(int j=0;j<3;j++) {
                copycube[color][i][j] = cube[color][j][k];
            }
        }
    }



    public static char[][][] cube = new char[6][3][3];
    public static char[][][] copycube = new char[6][3][3];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int rtc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1;tc<=rtc; tc++) {
            int N = Integer.parseInt(br.readLine());

            cube = new char[6][3][3];

            char[] cdraw = {'w','r','y','o','g','b'};
            for(int i=0;i<6;i++ ) {
                for(int j=0;j<3;j++) {
                    for(int k=0;k<3;k++) {
                        cube[i][j][k] = cdraw[i];
                    }
                }
            }

            StringTokenizer stz = new StringTokenizer(br.readLine());
            for(int k=0;k<N;k++) {
                String str = stz.nextToken();
                char order = str.charAt(0);
                char dir = str.charAt(1);
                movieCube(order, dir);
            }
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    sb.append(cube[0][i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }

}