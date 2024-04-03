import java.io.*;
import java.util.*;

public class Main {
    public static class Node{
        int data;
        Node next;
        Node prev;
        Node(int data){
            this.data = data;
            next = null;
            prev = null;
        }
    }

    public static class List{
        int size;
        Node head;
        Node tail;
        List(){
            this.size =0;
            head = null;
            tail = null;
        }
    }

    public static void PushList(List list, int data){
        Node NewNode = new Node(data);
        if(list.size == 0){
            list.head = NewNode;
            list.tail = NewNode;
        }
        else{
            list.tail.next = NewNode;
            NewNode.prev = list.tail;
            list.tail = NewNode;
            list.tail.next = list.head;
            list.head.prev = list.tail;
        }
        list.size++;
    }

    public static List[] list;
    public static Node[] left;
    public static Node[] right;
    public static Node[] score;
    public static boolean[] bcheck;

    public static void moveTobni(int tobni, int direc){
        // 방문체크
        if(bcheck[tobni])
            return;
        bcheck[tobni] = true;

        //방향먼저 체크
        int reverseDirec = (direc==1?-1:1);

        // 붙어있는 자석은 붙어잇는 날의 자성과 다른경우에만 회전됨.
        if(tobni-1>=0 && !bcheck[tobni-1]){ // 왼쪽자석
            if(left[tobni].data != right[tobni-1].data){ //자성다름
                moveTobni(tobni-1,reverseDirec);
            }
        }
        if(tobni+1<4 && !bcheck[tobni+1]){  // 오른쪽자석
            if(right[tobni].data != left[tobni+1].data) { // 자성다름
                moveTobni(tobni+1,reverseDirec);
            }
        }

        // 자석돌려주기
        if(direc == 1){ // 시계방향
            left[tobni] = left[tobni].prev;
            right[tobni] = right[tobni].prev;
            score[tobni] = score[tobni].prev;
        }
        else {   // 반시계방향
            left[tobni] = left[tobni].next;
            right[tobni] = right[tobni].next;
            score[tobni] = score[tobni].next;
        }
    }



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int answer = 0;
        list = new List[4];
        left = new Node[4];
        right = new Node[4];
        score = new Node[4];

        for(int i=0;i<4;i++){
            list[i] = new List();
            String str = br.readLine();
            for(int j=0;j<8;j++){
                PushList(list[i],str.charAt(j)-'0');

                // 위치체크
                if(j==0)
                    score[i] = list[i].tail;
                else if(j==2)
                    right[i] = list[i].tail;
                else if(j==6)
                    left[i] = list[i].tail;
            }
        }
        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int tobni = Integer.parseInt(stz.nextToken());
            int direc = Integer.parseInt(stz.nextToken());
            bcheck = new boolean[4];
            moveTobni(tobni-1, direc);
        }
        // 마지막 score 구하기
        answer += (score[0].data == 1?1:0);
        answer += (score[1].data == 1?2:0);
        answer += (score[2].data == 1?4:0);
        answer += (score[3].data == 1?8:0);

        sb.append(answer).append("\n");
        System.out.print(sb);
    }
}