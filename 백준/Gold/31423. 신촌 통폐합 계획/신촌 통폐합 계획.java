import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static class Room{

        mList list;
//        ArrayList<Node> list = new ArrayList<>();
        Room(){
            list = new mList();
        }
    }

    public static class Node{
        String data;
        Node Next;
        Node(String data){
            this.data = data;
            this.Next = null;
        }
    }

    public static class mList{
        int size;
        Node Head;
        Node Tail;
        mList(){
            this.size = 0;
            this.Head = null;
            this.Tail = null;
        }
    }

    public static void pushData(mList list, String data){
        Node newNode = new Node(data);

        if(list.size == 0){
            list.Head = newNode;
            list.Tail = newNode;
        } else{
            list.Tail.Next = newNode;
            list.Tail = newNode;
        }
        list.size++;
    }

    public static void pushList(mList list1, mList list2){
        list1.Tail.Next = list2.Head;
        list1.Tail = list2.Tail;
        list1.size += list2.size;
        list2 = null;
    }

    public static Room[] rooms;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        rooms = new Room[N+1];

        for(int i=1;i<=N;i++){
            rooms[i] = new Room();
            pushData(rooms[i].list,br.readLine());
        }

        StringTokenizer stz;
        int start=0,end=0;
        for(int i=1;i<N;i++){
            stz = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stz.nextToken());
            end = Integer.parseInt(stz.nextToken());

            pushList(rooms[start].list,rooms[end].list);
        }

        StringBuilder sb = new StringBuilder();
        Node temp = rooms[start].list.Head;

        while (temp.Next!=null){
            sb.append(temp.data);
            temp = temp.Next;
        }
        sb.append(temp.data);

        System.out.println(sb);
    }

}