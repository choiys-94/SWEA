#coding: utf-8
from itertools import combinations

T = int(input())
for tc in range(1, T+1):
	a = input().split()
	N, M = int(a[0]), int(a[1])
	m = [[0 for i in range(M)] for j in range(N)]
	wh, bl, re = [], [], []
	for i in range(N):
		a = input()
		ws = bs = rs = 0
		for j in range(M):
			m[i][j] = a[j]
			if a[j] != 'W':
				ws += 1
			if a[j] != 'B':
				bs += 1
			if a[j] != 'R':
				rs += 1
		wh.append(ws)
		bl.append(bs)
		re.append(rs)

	arr = [i for i in range(1, N)]
	ans = 987654321
	for l in list(combinations(arr, 2)):
		ts = 0
		for w in range(0, l[0]):
			ts += wh[w]
		for b in range(l[0], l[1]):
			ts += bl[b]
		for r in range(l[1], N):
			ts += re[r]
		ans = min(ans, ts)

	print("#{} {}".format(tc, ans))
