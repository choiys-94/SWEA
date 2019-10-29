package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2105_디저트카페_2 {
	static int N;
	static int[][] map;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					dfs(i, j, i, j, 0, new int[101], 0);
				}
			}
			ans = (ans == Integer.MIN_VALUE) ? -1 : ans;
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void dfs(int str, int stc, int r, int c, int dir, int[] visited, int sum) {
		if(r < str) {
			return;
		}
		if(dir != 0 && str == r && stc == c) {
			ans = Math.max(ans, sum);
			return;
		}
		
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		if(check(nr, nc)) {
			if(!(visited[map[nr][nc]] > 0)) {
			
				visited[map[nr][nc]]++;
				dfs(str, stc, nr, nc, dir, visited, sum+1);
				visited[map[nr][nc]]--;
			}
		}
		if(dir > 2)
			return;
		
		dir++;
		nr = r + dr[dir];
		nc = c + dc[dir];
		if(!check(nr, nc))
			return;
		if(visited[map[nr][nc]] > 0)
			return;
		
		visited[map[nr][nc]]++;
		dfs(str, stc, nr, nc, dir, visited, sum+1);
		visited[map[nr][nc]]--;
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};
}
