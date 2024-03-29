#include<iostream>
using namespace std;

typedef struct mp {
	int mapD;
	int islandNum;
}MAP;

typedef struct bg {
	int Sisland;
	int Eisland;
	int MinBridgeLength;
}Bridge;

int totalb = 0;
int RN, RM, RI;
int Bridgelength[15][15] = { 0, };
MAP map[15][15] = { 0, };
Bridge ConBridge[20] = { 0, };
bool b_check[20] = { 0, };
int MinBridge = 10005;
int totalislandNum = 0;

typedef struct node {
	int nY;
	int nX;
	node* Next;
}Node;

typedef struct queue {
	int Size;
	Node* Head;
	Node* Tail;
}Queue;

Queue* CreateQueue() {
	Queue* Que = new Queue;
	Que->Size = 0;
	Que->Head = NULL;
	Que->Tail = NULL;
	return Que;
}

void PushQueue(Queue* Que,int qY,int qX) {
	Node* NewNode = new Node;
	NewNode->nY = qY;
	NewNode->nX = qX;
	NewNode->Next = NULL;
	if (Que->Size == 0) {
		Que->Head = NewNode;
		Que->Tail = NewNode;
	}
	else {
		Que->Tail->Next = NewNode;
		Que->Tail = NewNode;
	}
	Que->Size++;
}

void PopQueue(Queue* Que) {
	if (Que->Size != 0) {
		Node* temp = Que->Head;
		Que->Head = temp->Next;
		delete temp;
		Que->Size--;
	}
}

void DeleteQueue(Queue* Que) {
	while (Que->Size != 0)
	{
		PopQueue(Que);
	}
	delete Que;
}

void islandChecking() {
	bool mapchecking[15][15] = { 0, };
	int NowislandNum = 1;
	for (int i = 0; i < RN; i++) {
		for (int j = 0; j < RM; j++) {
			if (mapchecking[i][j] == false && map[i][j].mapD == 1) {
				Queue* Que = CreateQueue();
				map[i][j].islandNum = NowislandNum;
				PushQueue(Que, i, j);
				mapchecking[i][j] = true;
				int sy = Que->Head->nY;
				int sx = Que->Head->nX;
				int ey = sy;
				int ex = sx;
				while (Que->Size!=0)
				{
					sy = Que->Head->nY;
					sx = Que->Head->nX;
					//오
					if (sx + 1 < RM) {
						if (mapchecking[sy][sx + 1] == false && map[sy][sx + 1].mapD == 1) {
							map[sy][sx+1].islandNum = NowislandNum;
							PushQueue(Que, sy, sx+1);
							mapchecking[sy][sx+1] = true;
						}
					}
					//왼
					if (sx - 1 >= 0) {
						if (mapchecking[sy][sx - 1] == false && map[sy][sx - 1].mapD == 1) {
							map[sy][sx - 1].islandNum = NowislandNum;
							PushQueue(Que, sy, sx - 1);
							mapchecking[sy][sx - 1] = true;
						}
					}
					//아
					if (sy + 1 < RN) {
						if (mapchecking[sy + 1][sx] == false && map[sy + 1][sx].mapD == 1) {
							map[sy+1][sx].islandNum = NowislandNum;
							PushQueue(Que, sy + 1, sx);
							mapchecking[sy + 1][sx] = true;
						}
					}
					//위
					if (sy - 1 >= 0) {
						if (mapchecking[sy - 1][sx] == false && map[sy - 1][sx].mapD == 1) {
							map[sy - 1][sx].islandNum = NowislandNum;
							PushQueue(Que, sy - 1, sx);
							mapchecking[sy - 1][sx] = true;
						}
					}
					PopQueue(Que);
				}
				NowislandNum++;
				DeleteQueue(Que);
			}
		}
	}
	totalislandNum = NowislandNum - 1;
}

void BridgeChecking() {
	//다리 경우의 수 체크
	int g = 0;
	for (int i = 0; i < totalislandNum - 1; i++) {
		for (int j = i + 1; j < totalislandNum; j++) {
			ConBridge[g].Sisland = i + 1;
			ConBridge[g].Eisland = j + 1;
			totalb++;
			g++;
		}
	}

	//다리 연결될 수 있는 최소 값
	for (int i = 0; i < RN; i++) {
		for (int j = 0; j < RM; j++) {
			if (map[i][j].islandNum != 0) {
				//오
				if (j + 3 < RM) {
					if (map[i][j + 1].islandNum == 0 && map[i][j + 2].islandNum == 0) {
						int bleng = 0;
						for (int k = j + 1; k < RM; k++) {
							if (map[i][j].islandNum == map[i][k].islandNum)
								break;
							if (map[i][k].islandNum != 0) {
								if (Bridgelength[map[i][j].islandNum][map[i][k].islandNum] > bleng || Bridgelength[map[i][j].islandNum][map[i][k].islandNum] == 0) {
									Bridgelength[map[i][j].islandNum][map[i][k].islandNum] = bleng;
									Bridgelength[map[i][k].islandNum][map[i][j].islandNum] = bleng;
									break;
								}
							}
							bleng++;
						}
					}
				}

				//아
				if (i + 3 < RN) {
					if (map[i+1][j].islandNum == 0 && map[i+2][j].islandNum == 0) {
						int bleng = 0;
						for (int k = i + 1; k < RN; k++) {
							if (map[i][j].islandNum == map[k][j].islandNum)
								break;
							if (map[k][j].islandNum != 0) {
								if (Bridgelength[map[i][j].islandNum][map[k][j].islandNum] > bleng || Bridgelength[map[i][j].islandNum][map[k][j].islandNum] == 0) {
									Bridgelength[map[i][j].islandNum][map[k][j].islandNum] = bleng;
									Bridgelength[map[k][j].islandNum][map[i][j].islandNum] = bleng;
									break;
								}
							}
							bleng++;
						}
					}
				}

			}
		}
	}
}

void ConnectBridge() {
	int mincheckD = 0;
	int conisland = 0;

	//넘어온 경우의 수 다리 연결 가능 확인
	for (int i = 0; i < totalb; i++) {
		if (b_check[i] == true) {
			int Sisland = ConBridge[i].Sisland;
			int Eisland = ConBridge[i].Eisland;

			if (Bridgelength[Sisland][Eisland] == 0)
				return;
			else 
				mincheckD += Bridgelength[Sisland][Eisland];
		}
	}

	bool CheckingM[10] = { 0, };
	int recheckingnum = 0;
	Queue* Que = CreateQueue();
	for (int i = 0; i < totalb; i++) {
		if (b_check[i] == true) {
			int Sisland = ConBridge[i].Sisland;
			int Eisland = ConBridge[i].Eisland;
			PushQueue(Que, Sisland, Eisland);
		}
	}

	int sl = Que->Head->nY;
	int el = Que->Head->nX;

	CheckingM[sl] = true;
	CheckingM[el] = true;
	PopQueue(Que);
	while (Que->Size != 0)
	{
		sl = Que->Head->nY;
		el = Que->Head->nX;

		if (CheckingM[sl] == true)
			CheckingM[el] = true;
		else if (CheckingM[el] == true)
			CheckingM[sl] = true;
		else {
			//리체킹
			if (recheckingnum < 30) {
				PushQueue(Que, sl, el);
				recheckingnum++;
			}

		}
		PopQueue(Que);
	}
	DeleteQueue(Que);

	bool bout = true;
	for (int i = 1; i <= totalislandNum; i++) {
		if (CheckingM[i] == false) {
			bout = false;
			break;
		}
	}

	if (bout) {
		if (MinBridge > mincheckD)
			MinBridge = mincheckD;
	}
}

void DFS(int seat,int prev,int check) {
	if (check == seat) {
		ConnectBridge();

		return;
	}

	for (int i = prev; i < totalb; i++) {
		if (b_check[i] == false) {
			b_check[i] = true;
			DFS(seat + 1, i + 1, check);
			b_check[i] = false;
		}
	}
}

int main(void) {
	cin >> RN >> RM;

	for (int i = 0; i < RN; i++) {
		for (int j = 0; j < RM; j++) {
			cin >> map[i][j].mapD;
		}
	}

	//섬구분
	islandChecking();
	//다리 경우의 수, 다리길이 체크
	BridgeChecking();

	//다리 모든경우의 수 돌리기
	DFS(0, 0, totalislandNum - 1);

	if(MinBridge == 10005)
		cout << "-1" << "\n";
	else
		cout << MinBridge << "\n";

	return 0;
}