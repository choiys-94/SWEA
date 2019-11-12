package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {
	static int K, N, M;
	static int[][] map;
	static int remain, tRemain, ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			remain = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] != 0)
						remain++;
				}
			}
			
			ans = 987654321;
			comb(new int[K], 0, 0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void down(int[][] map) {
		Stack<Integer> stack = new Stack<>();
		for(int j=0; j<M; j++) {
			for(int i=0; i<N; i++) {
				if(map[i][j] != 0) {
					stack.add(map[i][j]);
					map[i][j] = 0;
				}
			}
			int cnt = N-1;
			while(!stack.isEmpty()) {
				map[cnt][j] = stack.pop();
				cnt--;
			}
		}
	}
	
	static void bomb(int[][] map, int r, int c, int cnt) {
		while(cnt >= 0) {
			for(int d=0; d<4; d++) {
				int nr = r + dr[d]*cnt;
				int nc = c + dc[d]*cnt;
				if(!check(nr, nc))
					continue;
				if(map[nr][nc] == 0)
					continue;
				tRemain--;
				int tmp = map[nr][nc];
				map[nr][nc] = 0;
				bomb(map, nr, nc, tmp-1);
			}
			cnt--;
		}
	}
	
	static void search(int[][] map, int j) {
		for(int i=0; i<N; i++) {
			if(map[i][j] == 0)
				continue;
			int cnt = map[i][j]-1;
			bomb(map, i, j, cnt);
			break;
		}
	}
	
	static void comb(int[] sel, int idx, int k) {
		if(sel.length == k) {
			tRemain = remain;
			int[][] tmap = new int[N][M];
			for(int i=0; i<N; i++)
				System.arraycopy(map[i], 0, tmap[i], 0, map[i].length);
			
			for(int i=0; i<sel.length; i++) {
				search(tmap, sel[i]);
				down(tmap);
			}
			ans = Math.min(ans, tRemain);
			return;
		}
		
		if(idx == M) {
			return;
		}
		
		sel[k] = idx;
		comb(sel, 0, k+1);
		comb(sel, idx+1, k);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
