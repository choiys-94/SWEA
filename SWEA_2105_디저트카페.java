package swea;

import java.util.HashSet;
import java.util.Scanner;

public class SWEA_2105_디저트카페 {
	static class Pos{
		int r;
		int c;
		int sum;
		int dir;
		Pos(int r, int c, int sum, int dir){
			this.r = r;
			this.c = c;
			this.sum = sum;
			this.dir = dir;
		}
	}
	static int N;
	static int[][] map;
	static int ans = Integer.MIN_VALUE;
	static int str, stc;
	static boolean flag = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			ans = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			HashSet<Integer> eated = new HashSet<>();
			for(int i=0; i<N; i++) {
				for(int j=1; j<N-1; j++) {
					str = i;
					stc = j;
					flag = false;
					eated.clear();
					dfs(i, j, 0, 0, new boolean[N][N], eated);
				}
			}
			
			ans = (ans == Integer.MIN_VALUE) ? -1 : ans;
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void dfs(int r, int c, int sum, int dir, boolean[][] visited, HashSet<Integer> eated) {
		if(r < str)
			return;
		
		if(flag && r == str && c == stc) {
//			if(sum == 6)
//				System.out.println(1234);
			ans = Math.max(ans, sum);
			return;
		}
		flag = true;
		
		HashSet<Integer> teated = new HashSet<>(eated);
		
		
		for(int d=dir; d<4; d++) {
			if((dir == 0 && d == 2))
				continue;
			if((dir == 1 && d == 3))
				continue;
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(!check(nr, nc))
				continue;
			
			if(visited[nr][nc])
				continue;
			
			int size = teated.size();
			teated.add(map[nr][nc]);
			if(teated.size() == size) {
				continue;
			}
			visited[nr][nc] = true;
			dfs(nr, nc, sum+1, d, visited, teated);
			teated.clear();
			teated = new HashSet<>(eated);
			visited[nr][nc] = false;
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	// 우하 좌하 좌상 우상
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};
}
