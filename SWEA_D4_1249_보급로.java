package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_D4_1249_보급로 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static class Pos{
		int r;
		int c;
		int sum;
		Pos(int r, int c, int sum){
			this.r = r;
			this.c = c;
			this.sum = sum;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				String in = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = in.charAt(j)-'0';
				}
			}
			visited = new boolean[N][N];
			visited[0][0] = true;
			memo = new int[N][N];
			for(int i=0; i<N; i++) {
				Arrays.fill(memo[i], 9999);
			}
			result = Integer.MAX_VALUE;
			bfs(0, 0, 0);
//			dfs(0, 0, 0);
			System.out.println("#" + tc + " " + result);
		}
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static long result = Integer.MAX_VALUE;
	static int[][] memo;
	
	static void bfs(int r, int c, int sum) {
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(0, 0, 0));
		while(!queue.isEmpty()) {
			Pos q = queue.poll();
			if(q.r == N-1 && q.c == N-1) {
				result = Math.min(result, q.sum);
				continue;
			}
			for(int i=0; i<4; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(check(nr, nc) && visited[nr][nc] == false) {
					if(memo[nr][nc] > q.sum + map[nr][nc]) {
						memo[nr][nc] = q.sum + map[nr][nc];
						visited[nr][nc] = true;
						queue.add(new Pos(nr, nc, q.sum + map[nr][nc]));
					}
				}
			}	
		}
	}
	
//	static void dfs(int r, int c, int sum) {
//		if(r == N-1 && c == N-1) {
//			result = Math.min(result, sum);
//			return;
//		}
//		
//		if(memo[r][c] < sum)
//			return;
//		for(int i=0; i<4; i++) {
//			int nr = r + dr[i];
//			int nc = c + dc[i];
//			if(check(nr, nc)) {
//				if(memo[nr][nc] > sum + map[nr][nc] && visited[nr][nc] == false) {
//					visited[nr][nc] = true;
//					memo[nr][nc] = sum + map[nr][nc];
//					dfs(nr, nc, sum + map[nr][nc]);
//					visited[nr][nc] = false;
//				}
//			}
//		}
//	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
}
