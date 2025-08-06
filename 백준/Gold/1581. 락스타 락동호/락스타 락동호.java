import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int ff = Integer.parseInt(stz.nextToken());
        int fs = Integer.parseInt(stz.nextToken());
        int sf = Integer.parseInt(stz.nextToken());
        int ss = Integer.parseInt(stz.nextToken());

        int answer = 0;
        if (ff == 0 && fs == 0) {
            answer = ss + (sf != 0 ? 1 : 0);
        } else if (ff != 0 && fs == 0) {
            answer = ff;
        } else {
            if (fs > sf) {
                answer = ff + ss + (2 * sf) + 1;
            } else {
                answer = ff + ss + (2 * fs);
            }
        }


        System.out.println(answer);
    }
}
