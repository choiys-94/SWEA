package swea;

import java.util.Scanner;

public class _SWEA_4014_활주로건설 {
	static int N, X;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 맵 크기
			X = sc.nextInt(); // 경사로 길이
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int cnt = 0;
			
			for(int i=0; i<N; i++) {
				boolean flag1 = true;
				boolean flag2 = true;
				boolean[] visited = new boolean[N];
				for(int j=0; j<N; j++) {
					if(!flag1)
						break;
					if(check(i, j+1)) {
						if(Math.abs(map[i][j]-map[i][j+1]) > 1) {
							flag1 = false;
							break;
						}
						// 다음놈이 나보다 작으면
						if(map[i][j] == map[i][j+1]+1 && flag1) {
							for(int k=0; k<X; k++) {
								if(check(i, j+1+k)) {
									if(visited[j+1+k]) {
										flag1 = false;
										break;
									}
									if(map[i][j+1] != map[i][j+1+k]) {
										flag1 = false;
										break;
									}
									visited[j+1+k] = true;
								}
								else {
									flag1 = false;
									break;
								}
							}
						}
						// 다음놈이 나보다 크면
						if(map[i][j] == map[i][j+1]-1 && flag1) {
							for(int k=0; k<X; k++) {
								if(check(i, j-k)) {
									if(visited[j-k]) {
										flag1 = false;
										break;
									}
									if(map[i][j] != map[i][j-k]) {
										flag1 = false;
										break;
									}
									visited[j-k] = true;
								}
								else {
									flag1 = false;
									break;
								}
							}
						}
					}
				}
				if(flag1)
					cnt++;
				visited = new boolean[N];
				for(int j=0; j<N; j++) {
					if(!flag2)
						break;
					if(check(j+1, i)) {
						if(Math.abs(map[j][i]-map[j+1][i]) > 1) {
							flag2 = false;
							break;
						}
						// 다음놈이 나보다 작으면
						if(map[j][i] == map[j+1][i]+1 && flag2) {
							for(int k=0; k<X; k++) {
								if(check(j+1+k, i)) {
									if(visited[j+1+k]) {
										flag2 = false;
										break;
									}
									if(map[j+1][i] != map[j+1+k][i]) {
										flag2 = false;
									}
									visited[j+1+k] = true;
								}
								else {
									flag2 = false;
									break;
								}
							}
						}
					}
					if(check(j+1, i)) {
						if(Math.abs(map[j][i]-map[j+1][i]) > 1) {
							flag2 = false;
							break;
						}
						// 다음놈이 나보다 크면
						if(map[j][i] == map[j+1][i]-1 && flag2) {
							for(int k=0; k<X; k++) {
								if(check(j-k, i)) {
									if(visited[j-k]) {
										flag2 = false;
										break;
									}
									if(map[j][i] != map[j-k][i]) {
										flag2 = false;
										break;
									}
									visited[j-k] = true;
								}
								else {
									flag2 = false;
									break;
								}
							}
						}
					}
				}
				if(flag2)
					cnt++;
			}
			
			System.out.println("#" + tc + " " + cnt);
		}
		
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
}
