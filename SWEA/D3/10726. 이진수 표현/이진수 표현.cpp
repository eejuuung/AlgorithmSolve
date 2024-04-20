#include<iostream>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int rTc;
	cin >> rTc;
	for (int Tc = 1; Tc <= rTc; Tc++) {
		int rN, rM;
		cin >> rN >> rM;

		int CalD = 0;
		for (int i = 0; i < rN; i++) {
			CalD += 1 << i;
		}

		cout << "#" << Tc << " ";
		if ((rM & CalD) == CalD) {
			cout << "ON\n";
		}
		else {
			cout << "OFF\n";
		}



	}

	return 0;
}