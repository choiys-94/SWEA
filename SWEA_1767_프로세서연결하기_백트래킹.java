package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서연결하기_백트래킹 {
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N;
	static ArrayList<Pos> core;
	static int maxLen;
	static int maxCore;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		core = new ArrayList<>();
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			boolean[][] visited = new boolean[N][N];
			map = new int[N][N];
			core.clear();
			maxCore = 0;
			maxLen = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						// 코어이면서 가장자리가 아니면 리스트에 추가
						if(i==0 || j==0 || i==N-1 || j==N-1) {
							visited[i][j] = true;
							continue;
						}
						core.add(new Pos(i, j));
					}
				}
			}
			
			dfs(visited, 0, 0, 0);
			System.out.println("#" + tc + " " + maxLen);
		}
	}
	
	static void dfs(boolean[][] visited, int len, int coreCnt, int idx) {
		if(coreCnt + (core.size() - idx + 1) < maxCore)
			return;
		if(coreCnt > maxCore) {
			maxCore = coreCnt;
			maxLen = len;
		}
		else if(coreCnt == maxCore) {
			if(len < maxLen)
				maxLen = len;
		}
//		System.out.println(maxCore + " " + maxLen);
//		for(int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
//		System.out.println();
//		System.out.println(len + " " + coreCnt);
		for(int i=0; i<core.size(); i++) {
			Pos p = core.get(i);
			// 이미 방문한 코어이면 패스
			if(visited[p.r][p.c])
				continue;
			visited[p.r][p.c] = true;

			// 이전 상태 기억
			boolean[][] tvisited = new boolean[N][N];
			for(int j=0; j<N; j++)
				System.arraycopy(visited[j], 0, tvisited[j], 0, visited[j].length);
			
			// 사방탐색, d가 4일 경우 안거치고 패스
			for(int d=0; d<5; d++) {
				if(d == 4) {
					dfs(tvisited, len, coreCnt, i);
					break;
				}
				int nr = p.r;
				int nc = p.c;
				int cnt = 0;
				// 현재 위치(코어)부터 맵의 끝까지 직선으로 탐색
				while(true) {
					nr += dr[d];
					nc += dc[d];
//					System.out.println(p.r + " " + p.c + " " + cnt);
					// 끝까지 도달함(완료)
					if(!check(nr, nc)) {
						dfs(tvisited, len+cnt, coreCnt+1, i);
						break;
					}
					// 만약 지나가는 길에 코어나 전선이 있을 경우 break
					if(tvisited[nr][nc] || map[nr][nc] == 1) {
						break;
					}
					cnt++; // 전선 길이 늘려감
					tvisited[nr][nc] = true; // 전선 연결됐으니 방문 체크
				}
				// 이전 상태로 돌아가기
				for(int j=0; j<N; j++)
					System.arraycopy(visited[j], 0, tvisited[j], 0, visited[j].length);
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
