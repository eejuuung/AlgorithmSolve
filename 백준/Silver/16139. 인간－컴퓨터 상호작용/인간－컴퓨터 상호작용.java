import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int Q = Integer.parseInt(br.readLine());

        int[][] map = new int[26][S.length()];
        for(int i=0;i<S.length();i++) {
            int a = S.charAt(i) - 'a';
            map[a][i]++;

            if (i == 0)
                continue;
            for (int j = 0; j < 26; j++) {
                map[j][i] += map[j][i - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer stz;
        for(int i=0;i<Q;i++){
            stz = new StringTokenizer(br.readLine());
            int a = stz.nextToken().charAt(0)-'a';
            int l = Integer.parseInt(stz.nextToken());
            int r = Integer.parseInt(stz.nextToken());

            if(l == 0){
                sb.append(map[a][r]).append("\n");
            }else{
                sb.append(map[a][r]-map[a][l-1]).append("\n");
            }
        }

        System.out.print(sb);
    }
}