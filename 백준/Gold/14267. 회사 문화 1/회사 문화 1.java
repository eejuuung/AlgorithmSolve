import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static class Human {
        int score;
        List<Integer> underling;

        public Human() {
            this.score = 0;
            underling = new ArrayList<>();
        }
    }

    public static int N, M;
    public static Human[] employee;
    public static int[] totalScore;

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        employee = new Human[N + 1];
        totalScore = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            employee[i] = new Human();
        }

        stz = new StringTokenizer(br.readLine());
        stz.nextToken();
        for (int i = 2; i <= N; i++) {
            int reporter = Integer.parseInt(stz.nextToken());
            employee[reporter].underling.add(i);
        }

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(stz.nextToken());
            int score = Integer.parseInt(stz.nextToken());
            employee[num].score += score;
        }
    }

    public static void dfs(int nowEmployee, int score) {

        int nowScore = score + employee[nowEmployee].score;
        totalScore[nowEmployee] = nowScore;

        for (int i = 0; i < employee[nowEmployee].underling.size(); i++) {
            dfs(employee[nowEmployee].underling.get(i), nowScore);
        }


    }

    public static void main(String[] args) throws Exception {
        init();

        dfs(1, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(totalScore[i]).append(" ");
        }
        System.out.println(sb);
    }
}