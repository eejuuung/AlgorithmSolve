import java.util.*;

class Solution {
    public class Node {
        int time;
        int min;

        Node(int time, int min) {
            this.time = time;
            this.min = min;
        }
    }

    public int solution(String[][] book_time) {

        PriorityQueue<Node> pQue = new PriorityQueue<>(
            (n1, n2) -> {
                if (n1.time == n2.time) {
                    return n1.min - n2.min;
                }
                return n1.time - n2.time;
            }
        );

        Arrays.sort(book_time, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        int answer = 0;

        for (String[] book : book_time) {

            String[] start = book[0].split(":");
            int startHour = Integer.parseInt(start[0]);
            int startMin = Integer.parseInt(start[1]);

            String[] end = book[1].split(":");
            Node node = new Node(
                Integer.parseInt(end[0]),
                Integer.parseInt(end[1])
            );

            node.time += (node.min + 10) / 60;
            node.min = (node.min + 10) % 60;

            if (!pQue.isEmpty()) {
                Node room = pQue.peek();

                if (room.time < startHour ||
                    (room.time == startHour && room.min <= startMin)) {
                    pQue.poll();
                }
            }

            pQue.offer(node);

            answer = Math.max(answer, pQue.size());
        }

        return answer;
    }
}