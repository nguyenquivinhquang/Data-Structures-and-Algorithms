/**
 * Author: emaxx
 * License: CC0
 * Description: Josephus problem solver in 2 versions.
 * Assume start counting at 0, remove every $k$-th number.
 * Status: Tested
 * Time: $O(N)$ or $O(K \log N)$
 */
#pragma once

int josephus1(int n, int k) { // O(N)
	int res = 0;
	rep(i, 1, n+1) res = (res + k) % i;
	return res;
}

int josephus2(int n, int k) { // O(KlogN)
	if (n == 1) return 0;
	if (k == 1) return n-1;
	if (k > n) return (josephus(n-1, k) + k) % n;
	int cnt = n/k, res = josephus(n - cnt, k) - n % k;

	if (res < 0) res += n;
	else res += res/(k-1);

	return res;
}
