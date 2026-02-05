import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        HashMap<String, String> hMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            String site = stz.nextToken();
            String password = stz.nextToken();
            hMap.put(site, password);
        }
        for (int i = 0; i < M; i++) {
            sb.append(hMap.get(br.readLine())).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}