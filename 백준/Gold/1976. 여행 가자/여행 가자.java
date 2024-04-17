import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int[] parent;

    public static int getParent(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = getParent(parent[x]);
    }

    public static void unionParent(int a, int b){
        a = getParent(a);
        b = getParent(b);
        if(a<b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N+1];

        for(int i=1;i<=N;i++)
            parent[i] = i;

        for(int k=1;k<=N;k++){
            StringTokenizer stz = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++){
                int con = Integer.parseInt(stz.nextToken());
                if(con == 1){
                    unionParent(k,i);
                }
            }
        }

        StringTokenizer stz = new StringTokenizer(br.readLine());
        int root = getParent(Integer.parseInt(stz.nextToken()));
        for(int i=1;i<M;i++){
            int k = Integer.parseInt(stz.nextToken());
            if(getParent(k)!=getParent(root)){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}