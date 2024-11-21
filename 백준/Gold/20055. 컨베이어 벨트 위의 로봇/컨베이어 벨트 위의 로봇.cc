#include<iostream>
using namespace std;
int RN, RK;

typedef struct node {
	int Hp;
	bool robotHere;
	node* Next;
	node* Prev;
}Node;

typedef struct list {
	int size;
	//올리는위치
	Node* Head;
	//내리는위치
	Node* Tail;
	//확인용
	Node* Check;
}List;

List* CreateList() {
	List* list = new List;
	list->size = 0;
	list->Head = NULL;
	list->Tail = NULL;
	list->Check = NULL;
	return list;
}

void PushList(List* list, int Hp, bool rhere) {
	Node* NewNode = new Node;
	NewNode->Hp = Hp;
	NewNode->robotHere = rhere;
	NewNode->Next = NULL;
	NewNode->Prev = NULL;
	if (list->size == 0) {
		list->Head = NewNode;
		list->Tail = NewNode;
		list->Check = NULL;
	}
	else {
		NewNode->Prev = list->Tail;
		list->Tail->Next = NewNode;
		list->Tail = NewNode;
	}
	list->size++;
}

int PopList(List* list) {
	if (list->size == 0) {
		return -1;
	}
	else {
		Node* temp = list->Head;
		list->Head = temp->Next;
		list->Head->Prev = temp->Prev;
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
}


int main(void) {
	int TotalK = 0;
	//처음에 입력받은 후 tail이랑 head랑 붙여주기 그런 후 tail 내리는 위치로 이동시켜주기
	List* list = CreateList();
	cin >> RN >> RK;
	for (int i = 0; i < RN * 2; i++) {
		int Rhp = 0;
		cin >> Rhp;
		PushList(list, Rhp, false);
	}
	//0. 끝 tail과 head 연결해주기
	list->Head->Prev = list->Tail;
	list->Tail->Next = list->Head;

	//0-1. 전부 push된 상태에서 이제 tail을 내리는 위치로 이동시켜주기
	list->Tail = list->Head;
	for (int i = 1; i <= RN-1; i++) {
		list->Tail = list->Tail->Next;
	}

	int GetTime = 0;
	while (TotalK < RK)
	{
		//2-1. 내리는 위치에 로봇이 위치해 있다면 내리기
		if (list->Tail->robotHere == true)
			list->Tail->robotHere = false;
		//1. 벨트를 한칸씩 이동시키기 (헤드, 테일이동)
		list->Head = list->Head->Prev;
		list->Tail = list->Tail->Prev;
		//1-1. 내리는 위치에 로봇이 위치해 있다면 내리기
		if (list->Tail->robotHere == true)
			list->Tail->robotHere = false;

		//2. 로봇 이동시키기(먼저 올라간 로봇부터 이동)
		list->Check = list->Tail->Prev;
		while (list->Check != list->Head->Prev)
		{
			if (list->Check->robotHere) {
				if (list->Check->Next->Hp > 0 && list->Check->Next->robotHere == false) {
					list->Check->Next->Hp--;
					list->Check->Next->robotHere = true;
					list->Check->robotHere = false;
					if (list->Check->Next->Hp == 0)
						TotalK++;
				}
			}
			list->Check = list->Check->Prev;
		}
		//1-1. 내리는 위치에 로봇이 위치해 있다면 내리기
		if (list->Tail->robotHere == true)
			list->Tail->robotHere = false;
		//2. 로봇을 head로 올려주기 // 올릴때 내구도 체크하고 0이면 올리지 않기.
		if (list->Head->Hp > 0) {
			list->Head->Hp--;
			list->Head->robotHere = true;
			if (list->Head->Hp == 0) {
				TotalK++;
			}
		}
		GetTime++;
	}
	cout << GetTime << endl;

	DeleteList(list);

	return 0;
}