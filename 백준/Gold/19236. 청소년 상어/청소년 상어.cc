//https://www.acmicpc.net/problem/19236


#include <iostream>
using namespace std;

#define MAX 4

int map[4][4] = { 0, };

typedef struct rfish {
	int rX;
	int rY;
	int rDirec;
	bool rLive;
}Fish;

int dy[8] = { -1,-1,0,1,1,1,0,-1 };
int dx[8] = { 0,-1,-1,-1,0,1,1,1 };

Fish fish[16];
Fish shark;
int TotalEat = 0;

void MoveFish() {
	for (int i = 0; i < 16; i++) {
		int x = fish[i].rX;
		int y = fish[i].rY;
		int d = fish[i].rDirec;
		bool l = fish[i].rLive;

		//먹힌 물고기는 건너뜀
		if (fish[i].rLive)
			continue;

		//상어 위치면 건너뜀
		if (x == shark.rX && y == shark.rY)
			continue;

		//바라보는 방향 갈수있는지 체크
		for (int j = 0; j < 8; j++) {
			int pd = (d - 1 + j) % 8;
			int py = y + dy[pd];
			int px = x + dx[pd];

			//이동위치가 상어 위치면 건너뜀
			if (px == shark.rX && py == shark.rY)
				continue;

			//범위 넘지않고 상어위치도 아니면 해당자리랑 자리바꿈
			if (py >= 0 && px >= 0 && py < MAX && px < MAX) {
				int Cxy = (map[py][px]);
				if (Cxy == 0) {
					//바꿀위치 비어있음!
					map[fish[i].rY][fish[i].rX] = 0;
					fish[i].rX = px;
					fish[i].rY = py;
					fish[i].rDirec = pd + 1;
					map[py][px] = i + 1;
				}
				else {
					//바꿀위치에 물고기 있움! 위치 바꿔줘야할때
					Fish CFish = fish[Cxy - 1];
					fish[Cxy - 1].rX = fish[i].rX;
					fish[Cxy - 1].rY = fish[i].rY;
					fish[i].rX = CFish.rX;
					fish[i].rY = CFish.rY;
					fish[i].rDirec = pd + 1;
					map[fish[i].rY][fish[i].rX] = i + 1;
					map[fish[Cxy - 1].rY][fish[Cxy - 1].rX] = Cxy;
				}
				break;
			}
		}
	}
}

void BackFish(int sx, int sy, int sd, int eatF) {
	int Cmap[4][4];
	Fish CShark;
	Fish CFish[16];

	//식사갯수MAX 변경해주기
	if (TotalEat < eatF)
		TotalEat = eatF;
	
	//물고기 이동
	MoveFish();
	//이동 후 배열 복사
	for (int i = 0; i < MAX; i++) {
		for (int j = 0; j < MAX; j++) {
			Cmap[i][j] = map[i][j];
		}
	}
	//샤크정보복사
	CShark = shark;
	//Fish 정보복사
	for (int i = 0; i < 16; i++) {
		CFish[i].rX = fish[i].rX;
		CFish[i].rY = fish[i].rY;
		CFish[i].rDirec = fish[i].rDirec;
		CFish[i].rLive = fish[i].rLive;
	}

	//*3만큼 이동범위 확인해서 백트래킹
	for (int i = 1; i <= 3; i++) {
		//상어 이동위치 상어는 회전하지 않는다. 즉, D고정
		int pd = sd - 1;
		int py = sy + (dy[pd] * i);
		int px = sx + (dx[pd] * i);

		//이동위치가 비어있으면 들어가지 않음.
		if (map[py][px] == 0)
			continue;
		
		//범위 넘지않고 먹힌물고기도 아니면 상어가 먹음
		if (py >= 0 && px >= 0 && py < MAX && px < MAX) {
			//상어가 먹고 먹은 물고기의 위치랑 방향을 가져감.
			int eatyou = map[py][px];
			map[py][px] = 0;
			shark.rX = px;
			shark.rY = py;
			shark.rDirec = fish[eatyou - 1].rDirec;
			fish[eatyou - 1].rLive = true;
			BackFish(shark.rX, shark.rY, shark.rDirec, eatF + eatyou);

			//백트래킹 돌아올때 배열복사한거 돌려주기, 상어위치 돌려주기, 물고기정보 돌려주기
			for (int i = 0; i < MAX; i++) {
				for (int j = 0; j < MAX; j++) {
					map[i][j] = Cmap[i][j];
				}
			}
			shark = CShark;
			for (int i = 0; i < 16; i++) {
				fish[i].rX = CFish[i].rX;
				fish[i].rY = CFish[i].rY;
				fish[i].rDirec = CFish[i].rDirec;
				fish[i].rLive = CFish[i].rLive;
			}
		}
	}
}

int main(void) {
	int FirstEat = 0;
	for (int i = 0; i < MAX; i++) {
		for (int j = 0; j < MAX; j++) {
			int a = 0, b = 0;
			cin >> a >> b;

			fish[a - 1] = { j,i,b,false };
			map[i][j] = a;
		}
	}

	shark = { 0,0,fish[map[0][0] - 1].rDirec,true };
	fish[map[0][0] - 1].rLive = true;
	FirstEat = map[0][0];
	map[0][0] = 0;

	BackFish(0, 0, shark.rDirec, 0);

	cout << TotalEat+ FirstEat << endl;


	return 0;
}