import java.util.*;

class Solution {
    
    public static class Node {
        int score;
        int count;
        int bullSingle;

        Node(int score, int count, int bullSingle) {
            this.score = score;
            this.count = count;
            this.bullSingle = bullSingle;
        }
    }

    static final int INF = 100_001;
    static Queue<Node> que;
    static boolean[] visit;
    static int[] visitCount;
    static int[] visitBSCount;
    static int answer;
    static int answerBullSingle;
    static int target;

    public int[] solution(int t) {
        target = t;
        answer = INF;
        answerBullSingle = 0;

        visit = new boolean[INF];
        visitCount = new int[INF];
        visitBSCount = new int[INF];
        que = new ArrayDeque<>();

        for (int i = 1; i <= 20; i++) {
            type(i, 0, 0, 0);
        }

        if (answer == INF) {
            while (!que.isEmpty()) {
                Node nowNode = que.poll();

                if (answer != INF && nowNode.count > answer) {
                    break;
                }

                for (int i = 1; i <= 20; i++) {
                    type(i, nowNode.score, nowNode.count, nowNode.bullSingle);
                }
            }
        }

        return new int[]{answer, answerBullSingle};
    }

    private static void type(int dart, int score, int count, int bullSingle) {
        int[] scores = {dart, dart * 2, dart * 3, 50};
        boolean[] isBullSingle = {true, false, false, true};

        for (int i = 0; i < 4; i++) {
            int num = score + scores[i];
            int newCount = count + 1;
            int newBS = bullSingle + (isBullSingle[i] ? 1 : 0);

            if (num >= INF) continue;

            if (num == target && newCount <= answer) {
                if (answer == INF) {
                    answer = newCount;
                    answerBullSingle = newBS;
                } else if (newCount == answer) {
                    answerBullSingle = Math.max(answerBullSingle, newBS);
                }
                continue;
            }

            if (visit[num]) {
                if (visitCount[num] == newCount && visitBSCount[num] < newBS) {
                    que.offer(new Node(num, newCount, newBS));
                    visitCount[num] = newCount;
                    visitBSCount[num] = newBS;
                }
            } else {
                que.offer(new Node(num, newCount, newBS));
                visit[num] = true;
                visitCount[num] = newCount;
                visitBSCount[num] = newBS;
            }
        }
    }
}
