package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WEA_1953_탈주범검거 {
	static class Pos{
		int r, c, time;
		Pos(int r, int c, int time){
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int i=1; i<=7; i++) {
			dr[i] = new ArrayList<>();
			dc[i] = new ArrayList<>();
		}
		
		dr[1].addAll(Arrays.asList(new Integer[] {0, 1, 0, -1}));
		dc[1].addAll(Arrays.asList(new Integer[] {1, 0, -1, 0}));
		dr[2].addAll(Arrays.asList(new Integer[] {1, -1}));
		dc[2].addAll(Arrays.asList(new Integer[] {0, 0}));
		dr[3].addAll(Arrays.asList(new Integer[] {0, 0}));
		dc[3].addAll(Arrays.asList(new Integer[] {1, -1}));
		dr[4].addAll(Arrays.asList(new Integer[] {-1, 0}));
		dc[4].addAll(Arrays.asList(new Integer[] {0, 1}));
		dr[5].addAll(Arrays.asList(new Integer[] {1, 0}));
		dc[5].addAll(Arrays.asList(new Integer[] {0, 1}));
		dr[6].addAll(Arrays.asList(new Integer[] {1, 0}));
		dc[6].addAll(Arrays.asList(new Integer[] {0, -1}));
		dr[7].addAll(Arrays.asList(new Integer[] {-1, 0}));
		dc[7].addAll(Arrays.asList(new Integer[] {0, -1}));
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 1;
			
			Queue<Pos> queue = new LinkedList<>();
			queue.add(new Pos(R, C, 1));
			visited[R][C] = true;
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				int type = map[q.r][q.c];
				
				if(type == 0)
					continue;
				if(q.time >= L)
					continue;
				for(int d=0; d<dr[type].size(); d++) {
					int nr = q.r + dr[type].get(d);
					int nc = q.c + dc[type].get(d);
					if(!check(nr, nc))
						continue;
					if(visited[nr][nc])
						continue;
					if(map[nr][nc] == 0)
						continue;
					int ntype = map[nr][nc];
					boolean flag = true;
					for(int nd=0; nd<dr[ntype].size(); nd++) {
						int nnr = nr + dr[ntype].get(nd);
						int nnc = nc + dc[ntype].get(nd);
						if(nnr == q.r && nnc == q.c)
							flag = false;
					}
					if(flag)
						continue;
					visited[nr][nc] = true;
					queue.add(new Pos(nr, nc, q.time+1));
					ans++;
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static ArrayList<Integer>[] dr = new ArrayList[8];
	static ArrayList<Integer>[] dc = new ArrayList[8];
}
