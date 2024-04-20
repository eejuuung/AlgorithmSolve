#include <iostream>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int rTc;
	cin >> rTc;
	for (int tc = 1; tc <= rTc; tc++) {
		
		int runN;
		cin >> runN;

		int calD;
		int bitcheck = 0;
		int cnt = 1;
		while(true){
			calD = runN * cnt;
			int bitD = calD;
			while (bitD != 0) {
				int movebit = bitD % 10;
				bitcheck = bitcheck | (1 << movebit);
				bitD /= 10;
			}

			if ((bitcheck & 1023) == 1023) {
				break;
			}
			cnt++;
		}

		cout << "#" << tc << " " << calD << "\n";



	}

	return 0;
}