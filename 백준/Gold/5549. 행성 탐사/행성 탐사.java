import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static class Land {
        char ground;
        int jungleCount;
        int oceanCount;
        int iceCount;

        public Land(char ground, int jungleCount, int oceanCount, int iceCount) {
            this.ground = ground;
            this.jungleCount = jungleCount;
            this.oceanCount = oceanCount;
            this.iceCount = iceCount;
        }
    }

    public static int M, N;
    public static Land[][] map;
    public static StringBuilder sb;

    public static void initAndChecking() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        M = Integer.parseInt(stz.nextToken());
        N = Integer.parseInt(stz.nextToken());
        int checkArea = Integer.parseInt(br.readLine());

        map = new Land[M + 1][N + 1];
        for (int i = 0; i <= M; i++) {
            String str = "";
            if (i != 0)
                str = br.readLine();
            for (int j = 0; j <= N; j++) {

                if (i == 0 || j == 0) {
                    map[i][j] = new Land('A', 0, 0, 0);
                    continue;
                }


                map[i][j] = new Land(str.charAt(j - 1),
                        map[i - 1][j].jungleCount + map[i][j - 1].jungleCount - map[i - 1][j - 1].jungleCount,
                        map[i - 1][j].oceanCount + map[i][j - 1].oceanCount - map[i - 1][j - 1].oceanCount,
                        map[i - 1][j].iceCount + map[i][j - 1].iceCount - map[i - 1][j - 1].iceCount);

                if (map[i][j].ground == 'J')
                    map[i][j].jungleCount++;
                else if (map[i][j].ground == 'O')
                    map[i][j].oceanCount++;
                else if (map[i][j].ground == 'I')
                    map[i][j].iceCount++;
            }
        }

        sb = new StringBuilder();
        for (int k = 0; k < checkArea; k++) {
            stz = new StringTokenizer(br.readLine());
            int leftY, leftX, rightY, rightX;
            leftY = Integer.parseInt(stz.nextToken());
            leftX = Integer.parseInt(stz.nextToken());
            rightY = Integer.parseInt(stz.nextToken());
            rightX = Integer.parseInt(stz.nextToken());

            sb.append(map[rightY][rightX].jungleCount - map[rightY][leftX - 1].jungleCount - map[leftY - 1][rightX].jungleCount + map[leftY - 1][leftX - 1].jungleCount).append(" ")
                    .append(map[rightY][rightX].oceanCount - map[rightY][leftX - 1].oceanCount - map[leftY - 1][rightX].oceanCount + map[leftY - 1][leftX - 1].oceanCount).append(" ")
                    .append(map[rightY][rightX].iceCount - map[rightY][leftX - 1].iceCount - map[leftY - 1][rightX].iceCount + map[leftY - 1][leftX - 1].iceCount).append("\n");
        }


    }

    public static void main(String[] args) throws Exception {
        initAndChecking();
        System.out.print(sb);
    }
}