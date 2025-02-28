import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Truck {
        int nowLocation;
        int weight;

        public Truck(int weight) {
            nowLocation = 0;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int answer = 0;
        int N = Integer.parseInt(stz.nextToken());
        int W = Integer.parseInt(stz.nextToken());
        int L = Integer.parseInt(stz.nextToken());
        Truck[] trucks = new Truck[N];

        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trucks[i] = new Truck(Integer.parseInt(stz.nextToken()));
        }

        Queue<Truck> que = new ArrayDeque<>();
        int nowN = 1;
        int nowL = trucks[0].weight;
        trucks[0].nowLocation = 1;
        answer = 1;
        que.offer(trucks[0]);

        while (!que.isEmpty()) {

            Queue<Truck> copyQue = new ArrayDeque<>();

            while (!que.isEmpty()) {
                Truck nowTruck = que.poll();

                if (nowTruck.nowLocation == W) {
                    nowL -= nowTruck.weight;
                } else {
                    nowTruck.nowLocation++;
                    copyQue.offer(nowTruck);
                }
            }

            answer++;
            que = copyQue;

            if (nowN < N && trucks[nowN].weight + nowL <= L) {
                trucks[nowN].nowLocation = 1;
                nowL += trucks[nowN].weight;
                que.offer(trucks[nowN++]);
            }

        }
        System.out.println(answer);
    }
}