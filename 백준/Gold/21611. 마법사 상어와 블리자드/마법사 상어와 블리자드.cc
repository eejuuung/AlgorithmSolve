//https://www.acmicpc.net/problem/21611

#include<iostream>
#include<vector>
using namespace std;

typedef struct emap {
	int ey;
	int ex;
}EmptyMap;

EmptyMap Amap[2550] = { 0, };
int map[55][55] = { 0, };
int RN, RM;
int RDS[105][2] = { 0, };
int boomb[3] = { 0, };
void mapclean();
int ddy[4] = { -1,1,0,0 };
int ddx[4] = { 0,0,-1,1 };

void magic(int RD, int RS) {

	//1-1. 블리자드마법 (방향d, 거리s)

	for (int j = 1; j <= RS; j++) {
		int dy = (RN / 2) + ddy[RD - 1] * j;
		int dx = (RN / 2) + ddx[RD - 1] * j;
		if (dy < 0 || dx < 0 || dy >= RN || dx >= RN)
			break;
		map[dy][dx] = 0;
	}
	//1-2. 구슬 안으로 이동
	mapclean();

	//2-0. 2-1~2-2 까지더이상 폭발하지 않을때까지 반복함.
	bool isbomb = true;
	while (isbomb)
	{
		isbomb = false;
		//2-1. 폭발마법 (4개이상 연속일경우)
		int checkc = 0;
		int checkn = map[Amap[0].ey][Amap[0].ex];
		for (int i = 1; i < RN * RN - 1; i++) {
			if (map[Amap[i].ey][Amap[i].ex] == checkn) {
				checkc++;
			}
			else {
				if (checkc >= 3) {
					//역방향으로 폭발시켜주기
					boomb[checkn - 1] += checkc + 1;
					for (int j = i-1, k = 0; k <= checkc; j--, k++) {
						map[Amap[j].ey][Amap[j].ex] = 0;
					}
					isbomb = true;
				}
				checkc = 0;
				checkn = map[Amap[i].ey][Amap[i].ex];
			}
		}
		if (checkc >= 3 && checkn != 0) {
			boomb[checkn - 1] += checkc + 1;
			for (int j = RN * RN - 2, k = 0; k <= checkc; j--, k++) {
				map[Amap[j].ey][Amap[j].ex] = 0;
			}
			isbomb = true;
		}
		//2-2. 구슬 다시 이어주기 (2-1~2-2 까지더이상 폭발하지 않을때까지 반복함.)
		if(isbomb)
			mapclean();
	}

	//3-1. 하나의 구슬은 구슬A와 구슬 B로 변경(구슬 개수와 번호)
	vector<int> Newmap;
	int checkc = 1;
	int checkn = map[Amap[0].ey][Amap[0].ex];
	bool bcheck = false;
	for (int i = 1; i < RN * RN - 1; i++) {

		if (map[Amap[i].ey][Amap[i].ex] == checkn) {
			checkc++;
		}
		else {
			if (i == RN * RN - 2) {
				bcheck = true;
			}
			Newmap.push_back(checkc);
			Newmap.push_back(checkn);
			checkc = 1;
			checkn = map[Amap[i].ey][Amap[i].ex];
		}

	}
	if (bcheck) {
		Newmap.push_back(checkc);
		Newmap.push_back(checkn);
	}
	//3-2. 구슬넣을거 정해졌으면 맵 바꿔주기
	for (int i = 0; i < RN; i++) {
		for (int j = 0; j < RN; j++) {
			map[i][j] = 0;
		}
	}
	int i = 0;
	while (Newmap.size() != 0)
	{
		map[Amap[i].ey][Amap[i].ex] = Newmap[0];
		Newmap.erase(Newmap.begin());
		if (Amap[i].ey == 0 && Amap[i].ex == 0)
			break;
		i++;
	}
}

void Allmap() {
	int sy = RN / 2;
	int sx = (RN / 2) - 1;
	int RunC = 1;
	int RunD = 0;
	for (int runt = 0; runt < RN / 2; runt++) {
		//아
		for (int j = 0; j < RunC; sy++, j++) {
			Amap[RunD].ey = sy;
			Amap[RunD].ex = sx;
			RunD++;
		}
		RunC++;
		//오
		for (int j = 0; j < RunC; sx++, j++) {
			Amap[RunD].ey = sy;
			Amap[RunD].ex = sx;
			RunD++;
		}
		//위
		for (int j = 0; j < RunC; sy--, j++) {
			Amap[RunD].ey = sy;
			Amap[RunD].ex = sx;
			RunD++;
		}
		RunC++;
		if (runt == (RN / 2) - 1)
			RunC--;
		//왼
		for (int j = 0; j < RunC; sx--, j++) {
			Amap[RunD].ey = sy;
			Amap[RunD].ex = sx;
			RunD++;
		}

	}
}

void mapclean() {
	vector<EmptyMap> vmap;
	for (int i = 0; i < RN * RN - 1; i++) {
		if (map[Amap[i].ey][Amap[i].ex] == 0) {
			//빈곳이라면 넣을수있는곳으로 체크해놓기
			EmptyMap emap;
			emap.ey = Amap[i].ey;
			emap.ex = Amap[i].ex;
			vmap.push_back(emap);
		}
		else {
			if (vmap.size() != 0) {
				map[vmap[0].ey][vmap[0].ex] = map[Amap[i].ey][Amap[i].ex];
				vmap.erase(vmap.begin());
				map[Amap[i].ey][Amap[i].ex] = 0;
				EmptyMap emap;
				emap.ey = Amap[i].ey;
				emap.ex = Amap[i].ex;
				vmap.push_back(emap);
			}
		}
	}
}

int main(void) {
	cin >> RN >> RM;
	for (int i = 0; i < RN; i++) {
		for (int j = 0; j < RN; j++) {
			cin >> map[i][j];
		}
	}
	for (int i = 0; i < RM; i++) {
		for (int j = 0; j < 2; j++) {
			cin >> RDS[i][j];
		}
	}

	Allmap();
	for (int i = 0; i < RM; i++) {
		magic(RDS[i][0], RDS[i][1]);
	}

	int answer = 0;
	for (int i = 0; i < 3; i++) {
		answer += (i + 1) * boomb[i];
	}
	cout << answer << "\n";

	return 0;
}