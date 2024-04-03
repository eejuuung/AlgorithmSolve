#include<iostream>

using namespace std;

char readC[25] = { 0, };
int RN;
int answer = -9999999;

int Calculate(int a, int b, char p) {
	int calD = 0;
	switch (p)
	{
		case '*':
			calD = a * b;
			break;
		case '+':
			calD = a + b;
			break;
		case '-':
			calD = a - b;
			break;
	}
	return calD;
}

void plusCC(int a,int totalD) {

	if (a + 2 > RN) {
		if (answer < totalD)
			answer = totalD;
		return;
	}

	//묶는다면 (묶일수 있다면)
	if (a + 3 < RN) {
		int ccD = Calculate(readC[a + 1] - '0', readC[a + 3] - '0', readC[a + 2]);
		int calD = Calculate(totalD, ccD, readC[a]);
		plusCC(a + 4, calD);
	}

	//안묶는다면
	if (a + 1 < RN) {
		int calD = Calculate(totalD, readC[a + 1] - '0', readC[a]);
		plusCC(a + 2, calD);
	}
}

int main(void) {
	RN = 0;
	cin >> RN;
	for (int i = 0; i < RN; i++) {
		cin >> readC[i];
	}
	plusCC(1,readC[0]-'0');
	cout << answer << "\n";


	return 0;
}