#coding: utf-8
T = int(input())
for tc in range(1, T+1):
	a = input().split()
	N, M = int(a[0]), a[1]
	map = [[0 for y in range(N)] for x in range(N)]
	for i in range(N):
		a = input().split()
		for j in range(N):
			map[i][j] = int(a[j])

	if M=='up':
		for c in range(N):
			idx = 0
			for r in range(1, N):
				if map[r][c] != 0 and r != idx:
					if map[idx][c] == 0:
						map[idx][c], map[r][c] = map[r][c], map[idx][c]
					elif map[idx][c] == map[r][c]:
						map[idx][c] = map[r][c]*2
						map[r][c] = 0
						idx += 1
					else:
						while(map[idx][c] != 0):
							idx += 1
						map[idx][c], map[r][c] = map[r][c], map[idx][c]

	for i in range(N):
		for j in range(N):
			print(map[i][j], end=' ')
		print()