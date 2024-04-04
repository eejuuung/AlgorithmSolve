#include <iostream>
using namespace std;

int map[12][12] = { 0, };
int paper[5] = { 5,5,5,5,5 };


int maxpaper(int y, int x) {
	int maxd = 0;
	bool checkout = true;
	for (maxd = 4; maxd >= 0; maxd--) {
		if (maxd + y < 10 && maxd + x < 10) {
			for (int i = y; i <= maxd + y; i++) {
				for (int j = x; j <= maxd + x; j++) {
					if (map[i][j] != 1) {
						checkout = false;
						break;
					}
				}
				if (!checkout)
					break;
			}
			if (checkout) {
				break;
			}
			else {
				checkout = true;
			}
		}
	}


	return maxd;
}

void attach(int y, int x, int size, int state) {
	for (int i = y; i <= y + size; i++) {
		for (int j = x; j <= x + size; j++) {
			map[i][j] = state;
		}
	}

	if (state == 0) {
		//붙이기
		paper[size]--;
	}
	else {
		//떼기
		paper[size]++;
	}
}
int minp = 26;
void dfs(int y,int x,int totalp) {
	//색종이는 무조건 붙여야함 하지만 갯수제한 있음.

	if (y == 10 && x == 10) {
		if (minp > totalp)
			minp = totalp;
		return;
	}
	if (x == 10) {
		y++;
		x = 0;
	}

	if (map[y][x] == 1) {
		int maxd = maxpaper(y, x);
		for (int cnt = maxd; cnt >= 0; cnt--) {
			//색종이 남아있다면 붙이기
			if (paper[cnt] > 0) {
				attach(y, x, cnt, 0);
				//붙인 후 다음 붙일자리로 이동
				dfs(y, x + 1, totalp + 1);
				//이동 끝나고 돌아오면 떼고 다음 색종이 붙일준비
				attach(y, x, cnt, 1);
			}
			//남아있지 않다면 작은색종이 만약 계속해서 작은색종이도 없다면 return;
			else if (cnt == 0) {
				return;
			}
		}
	}
	else {
		dfs(y, x + 1, totalp);
	}
}

int main(void) {

	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			cin >> map[i][j];
		}
	}
	dfs(0, 0, 0);
	
	if (minp == 26)
		minp = -1;

	cout << minp << "\n";

	return 0;
}