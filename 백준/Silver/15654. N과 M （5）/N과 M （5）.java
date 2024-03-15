import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static ArrayList<Integer> list;
    static int[] imap;
    static boolean[] bmap;
    static StringBuilder sb;

    public static void dfs(int depth){
        if(depth>=M){
            for(int j=0;j<depth;j++){
                sb.append(imap[j]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int j=0;j<list.size();j++){
            if(bmap[j])
                continue;
            bmap[j] = true;
            imap[depth] = list.get(j);
            dfs(depth+1);
            bmap[j] = false;
        }


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        bmap = new boolean[N];
        imap = new int[M];
        list = new ArrayList<>();
        stz = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(stz.nextToken()));
        }
        Collections.sort(list);
        dfs(0);
        System.out.print(sb);
    }
}
