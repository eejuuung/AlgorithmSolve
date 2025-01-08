import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static class Pair implements Comparable<Pair> {
        int num;
        int first;

        public Pair(int num, int first) {
            this.num = num;
            this.first = first;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.num, o.num);
        }
    }

    public static Pair[] A;
    public static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new Pair[N];

        for (int i = 0; i < N; i++) {
            A[i] = new Pair(Integer.parseInt(br.readLine()), i);
        }

        Arrays.sort(A);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, A[i].first - i);
        }
        System.out.println(answer + 1);
    }
}