import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static class Node{
        int nd;
        Node Next;

        public Node(int nd){
            this.nd = nd;
            this.Next = null;
        }
    }

    public static class List{
        int size;
        Node Head;
        Node Tail;

        public List(){
            this.size =0;
            Head = null;
            Tail = null;
        }

        public void pushList(int nd){
            Node NewNode = new Node(nd);

            if(this.size==0){
                this.Head = NewNode;
                this.Tail = NewNode;
            }
            else{
                this.Tail.Next = NewNode;
                this.Tail = NewNode;
            }
            this.size++;
        }

        void PushListAtoB(int Ad, int Bd, int[] ns) {

            if(Ad == this.size){
                PushListBack(Bd, ns);
                return;
            }

            if(Ad == 0){
                PushListFront(Bd,ns);
                return;
            }

            //노드 Ad위치까지 이동
            Node temp = this.Head;
            for (int cnt = 1; cnt < Ad; cnt++)
                temp = temp.Next;

            //Bd위치만큼 끼워넣기 그전에
            Node Backtemp = temp.Next;	// 기존에 현위치 뒤에 이어져있던 노드값 back에 저장해놓기
            for (int cnt = 0; cnt < Bd; cnt++) {
                //Node 생성하고 값넣기
                Node NewNode = new Node(ns[cnt]);
                NewNode.Next = null;

                temp.Next = NewNode;
                temp = NewNode;
                this.size++;
            }
            temp.Next = Backtemp;
        }

        void PushListBack(int Yd, int[] ns) {
            //노드 테일로 이동
            Node temp = this.Tail;

            //Yd갯수 만큼 ns값 집어넣기
            for (int cnt = 0; cnt < Yd; cnt++) {
                pushList(ns[cnt]);
            }
        }
        void PushListFront(int Yd, int[] ns) {
            Node start = new Node(0);
            Node temp = new Node(0);

            for(int cnt = 0;cnt<Yd;cnt++){
                Node NewNode = new Node(ns[cnt]);
                if(cnt == 0){
                    start = NewNode;
                    temp = NewNode;
                }
                else{
                    temp.Next = NewNode;
                    temp = NewNode;
                }
            }

            temp.Next = this.Head;
            this.Head = start;
            this.size += Yd;

        }

        void PopList() {
            if (this.size == 0)
                return;

            Node temp = this.Head;
            this.Head = temp.Next;
            temp = null;
            this.size--;
        }

        void PopListAtoB(int Xd, int Yd) {

            if(Xd == 0){
                for(int i=0;i<Yd;i++)
                    PopList();
                return;
            }

            boolean ischeck = false;
            if(Xd + Yd == this.size){
                ischeck = true;
            }

            //노드 Xd위치까지 이동
            Node temp = this.Head;
            for (int cnt = 0; cnt < Xd-1; cnt++)
                temp = temp.Next;

            //Bd위치만큼 빼기 그전에
            Node fronttemp = temp;	// 기존에 현위치 앞에 이어져있던 노드값 front에 저장해놓기
            temp = temp.Next;
            for (int cnt = 0; cnt < Yd; cnt++) {
                //Node 생성하고 값넣기
                Node deletetemp = temp;
                temp = temp.Next;
                deletetemp = null;
                this.size--;
            }
            fronttemp.Next = temp;
            if(ischeck)
                this.Tail = fronttemp;
        }
    }


    public static void main(String[] args)throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	for(int tc = 1;tc<=10;tc++) {
    		List list = new List();
    		int rN = Integer.parseInt(br.readLine());
    		String str = br.readLine();
    		StringTokenizer stz = new StringTokenizer(str);
    		for(int i=0;i<rN;i++) {
    			list.pushList(Integer.parseInt(stz.nextToken()));
    		}
    		int rM = Integer.parseInt(br.readLine());
    		str = br.readLine();
    		stz = new StringTokenizer(str);
            for(int i=0;i<rM;i++) {
                String readS = stz.nextToken();
                if(readS.equals("I")) {
                    //x, y, s[] -> else
                    int x = Integer.parseInt(stz.nextToken());
                    int y = Integer.parseInt(stz.nextToken());
                    int[] s = new int[y];
                    for(int j=0;j<y;j++) {
                        s[j] = Integer.parseInt(stz.nextToken());
                    }
                    list.PushListAtoB(x,y,s);
                }
                else if(readS.equals("D")) {
                    //x, y
                    int x = Integer.parseInt(stz.nextToken());
                    int y = Integer.parseInt(stz.nextToken());
                    list.PopListAtoB(x,y);
                }
                else if(readS.equals("A")) {
                    //y, s
                    int y = Integer.parseInt(stz.nextToken());
                    int[] s = new int[y];
                    for(int j=0;j<y;j++) {
                        s[j] = Integer.parseInt(stz.nextToken());
                    }
                    list.PushListBack(y,s);
                }
            }
            sb.append("#").append(tc).append(" ");
            Node temp = list.Head;
            for(int i=0;i<10;i++){
                sb.append(temp.nd).append(" ");
                temp = temp.Next;
            }
            sb.append("\n");
    	}
    	System.out.print(sb);
    }
}