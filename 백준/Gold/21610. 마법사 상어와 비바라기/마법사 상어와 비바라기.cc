#include<iostream>
using namespace std;
//40

typedef struct map {
	int GetWater;
	bool Cloutvisit;
}Map;

int RN, RM;
int MagicDirect[100] = { 0, };
int MagicSpeed[100] = { 0, };
Map MagicMap[55][55] = { 0, };
//왼,왼대,위,위대,오,오대,아,아대
int dy[8] = { 0,-1,-1,-1,0,1,1,1 };
int dx[8] = { -1,-1,0,1,1,1,0,-1 };

typedef struct node {
	int nx;
	int ny;
	node* Next;
}Node;

typedef struct list {
	int size = 0;
	Node* Head;
	Node* Tail;
	Node* Check;
}List;

List* CreateList() {
	List* list = new List;
	list->size = 0;
	list->Head = NULL;
	list->Check = NULL;
	list->Tail = NULL;
	return list;
}

void PushList(List* list,int ny, int nx) {
	Node* NewNode = new Node;
	NewNode->Next = NULL;
	NewNode->nx = nx;
	NewNode->ny = ny;
	if (list->size == 0) {
		list->Head = NewNode;
		list->Tail = NewNode;
	}
	else {
		list->Tail->Next = NewNode;
		list->Tail = NewNode;
	}
	list->size++;
}

int PopList(List* list) {
	if (list->size == 0)
		return -1;
	else {
		Node* temp = list->Head;
		list->Head = temp->Next;
		delete temp;
		list->size--;
		return 0;
	}
}

void DeleteList(List* list) {
	while (list->size!=0)
	{
		PopList(list);
	}
	delete list;
}

int movexy(int xy) {
	if (xy < 0) {
		xy = abs(xy) % RN;
		xy = RN - xy;
	}
	if (xy >= RN) {
		xy = xy % RN;
	}
	return xy;
}

int movewater(int py, int px) {
	int totalwater = 0;
	if (py - 1 >= 0 && px - 1 >= 0 && MagicMap[py - 1][px - 1].GetWater > 0)
		totalwater++;
	if (py + 1 < RN && px - 1 >= 0 && MagicMap[py + 1][px - 1].GetWater > 0)
		totalwater++;
	if (py + 1 < RN && px + 1 < RN && MagicMap[py + 1][px + 1].GetWater > 0)
		totalwater++;
	if (py - 1 >= 0 && px + 1 < RN && MagicMap[py - 1][px + 1].GetWater > 0)
		totalwater++;
	return totalwater;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> RN >> RM;
	for (int i = 0; i < RN; i++) {
		for (int j = 0; j < RN; j++) {
			cin >> MagicMap[i][j].GetWater;
		}
	}
	for (int i = 0; i < RM; i++) {
		cin >> MagicDirect[i] >> MagicSpeed[i];
	}
	//첫 구름 list에 넣어주기
	List* list = new List;
	PushList(list, RN - 1, 0);
	PushList(list, RN - 1, 1);
	PushList(list, RN - 2, 0);
	PushList(list, RN - 2, 1);

	for (int k = 0; k < RM; k++) {
		//1. 구름 첫번째 마법 방향대로 이동후 물양+1, 구름지우기
		list->Check = list->Head;
		for (int i = 0; i < list->size; i++)
		{
			int px = list->Check->nx;
			int py = list->Check->ny;

			py = py + (dy[MagicDirect[k]-1] * MagicSpeed[k]);
			py = movexy(py);
			px = px + (dx[MagicDirect[k]-1] * MagicSpeed[k]);
			px = movexy(px);

			MagicMap[py][px].Cloutvisit = true;
			MagicMap[py][px].GetWater += 1;
			list->Check->nx = px;
			list->Check->ny = py;
			list->Check = list->Check->Next;
		}
		//2. 물이 늘어난 곳에 대각선대로 체크해서 물증가
		list->Check = NULL;
		while (list->size != 0)
		{
			int px = list->Head->nx;
			int py = list->Head->ny;
			int pw = movewater(py, px);
			MagicMap[py][px].GetWater += pw;
			PopList(list);
		}
		//3. 맵 돌면서 물양 2이상인곳에 구름리스트에 넣기, 이전구름지역은 지나가고 방문 초기화
		for (int i = 0; i < RN; i++) {
			for (int j = 0; j < RN; j++) {
				if (MagicMap[i][j].Cloutvisit == false && MagicMap[i][j].GetWater >= 2) {
					MagicMap[i][j].GetWater -= 2;
					PushList(list, i, j);
				}
				if (MagicMap[i][j].Cloutvisit)
					MagicMap[i][j].Cloutvisit = false;
			}
		}
		int tototo = 0;
	}

	int totalWaterp = 0;
	for (int i = 0; i < RN; i++) {
		for (int j = 0; j < RN; j++) {
			totalWaterp += MagicMap[i][j].GetWater;
		}
	}

	cout << totalWaterp << "\n";

	return 0;
}