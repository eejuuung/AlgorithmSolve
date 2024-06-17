import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int R,C;

        R = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        boolean[][] bmap = new boolean[R][C];

        stz = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++){
            int height = Integer.parseInt(stz.nextToken());

            for(int j=R-1,k=0;k<height;j--,k++){
                bmap[j][i] = true;
            }
        }

        int total = 0;
        for(int i=0;i<R;i++) {

            // 맨앞 시작점 체크
            int startJ = 0;
            for (int j = 0; j < C; j++) {
                if (bmap[i][j]) {
                    startJ = j;
                    break;
                }
            }

            // 맨 뒤 끝점 체크
            int endJ = 0;
            for (int j = C - 1; j >= 0; j--) {
                if (bmap[i][j]) {
                    endJ = j;
                    break;
                }
            }

            //시작점 끝점이 같은 위치라면 물이 고이지 않으니 체크 X
            if (startJ == endJ) {
                continue;
            } else { // 다르다면 그 사이의 벽이 아닌곳에 모두 물이채워짐
                for (int j = startJ; j <= endJ; j++) {
                    if (!bmap[i][j]) {
                        total++;
                    }
                }
            }
        }
        System.out.println(total);
    }
}