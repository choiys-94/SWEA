#coding: utf-8
T = int(input())

for i in range(1, T+1):
	a = input().split()
	N, M = int(a[0]), int(a[1])
	b = input()
	c = set()
	t = int(N/4)
	for k in range(t):
		for j in range(4):
			c.add(b[j*t: (j+1)*t])
		b = b[-1] + b[:-1]
	print("#{} {}".format(i,int(sorted(c)[::-1][M-1],16)))