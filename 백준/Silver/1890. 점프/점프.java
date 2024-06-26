import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        long[][] calMap = new long[N][N];
        StringTokenizer stz;

        for(int i=0;i<N;i++){
            stz = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }
        calMap[0][0] = 1;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){

                if(calMap[i][j]==0 || map[i][j]==0)
                    continue;

                int right = j + map[i][j];
                if(right<N)
                    calMap[i][right] = calMap[i][j] + calMap[i][right];

                int down = i + map[i][j];
                if(down<N)
                    calMap[down][j] = calMap[i][j] + calMap[down][j];

            }
        }

        System.out.println(calMap[N-1][N-1]);

    }
}