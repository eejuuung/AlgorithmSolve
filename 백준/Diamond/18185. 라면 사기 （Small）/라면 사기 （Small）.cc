
#include <iostream>
using namespace std;

int noodle[1010000] = { 0, };
int RN;

int NoodleChecking() {
	int totalcost = 0;

	for (int i = 0; i < RN; i++) {
		if (noodle[i + 2] < noodle[i + 1]) {

			int ca = 0;
			if (noodle[i] < (noodle[i + 1] - noodle[i + 2])) 
				ca = noodle[i];
			else
				ca = (noodle[i + 1] - noodle[i + 2]);

			noodle[i] -= ca;
			noodle[i + 1] -= ca;

			totalcost += ca * 5;

			if (noodle[i] < noodle[i + 1]) {
				if (noodle[i] < noodle[i + 2])
					ca = noodle[i];
				else
					ca = noodle[i + 2];
			}
			else {
				if (noodle[i + 1] < noodle[i + 2])
					ca = noodle[i + 1];
				else
					ca = noodle[i + 2];
			}

			noodle[i] -= ca;
			noodle[i + 1] -= ca;
			noodle[i + 2] -= ca;

			totalcost += ca * 7;
		}
		else {
			int ca = 0;
			if (noodle[i] < noodle[i + 1]) {
				if (noodle[i] < noodle[i + 2])
					ca = noodle[i];
				else
					ca = noodle[i + 2];
			}
			else {
				if (noodle[i + 1] < noodle[i + 2])
					ca = noodle[i + 1];
				else
					ca = noodle[i + 2];
			}

			noodle[i] -= ca;
			noodle[i + 1] -= ca;
			noodle[i + 2] -= ca;

			totalcost += ca * 7;

			if (noodle[i] < noodle[i + 1])
				ca = noodle[i];
			else
				ca = noodle[i + 1];

			noodle[i] -= ca;
			noodle[i + 1] -= ca;

			totalcost += ca * 5;
		}
		totalcost += noodle[i] * 3;
		noodle[i] = 0;
	}

	return totalcost;
}

int main(void) {

	cin >> RN;

	for (int i = 0; i < RN; i++) {
		cin >> noodle[i];
	}

	int cost = NoodleChecking();

	cout << cost << "\n";

	return 0;
}