package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _SWEA_1767_프로세서연결하기_중복조합 {
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static class Res implements Comparable<Res>{
		int cnt;
		int num;
		Res(int cnt, int num){
			this.cnt = cnt;
			this.num = num;
		}
		@Override
		public int compareTo(Res o) {
			if(o.num == this.num)
				return this.cnt - o.cnt;
			return o.num - this.num;
		}
	}
	static int N;
	static int[][] map;
	static int[][] tmap;
	static boolean[][] memo;
	static ArrayList<Pos> core;
	static PriorityQueue<Res> res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		core = new ArrayList<>();
		res = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			tmap = new int[N][N];
			core.clear();
			res.clear();
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						// 코어이면서 가장자리가 아닌놈들 리스트에 추가
						if(i==0 || j==0 || i==N-1 || j==N-1)
							continue;
						core.add(new Pos(i, j));
					}
				}
			}
			memo = new boolean[core.size()][4];
			comb(new int[core.size()], 0, 0);
			sb.append("#").append(tc).append(" ");
			if(res.isEmpty())
				sb.append(0);
			else
				sb.append(res.poll().cnt);
			sb.append("\n");
		}
		System.out.println(sb.toString());
//		System.out.println(aaa);
	}
	
	static void stretch(int[] sel) {
		int total = 0;
		int idx = 0;
		// 코어 순서대로 돌아가면서 방향 쭉쭉 뻗어나가기
		for(int i=0; i<core.size(); i++) {
			if(!res.isEmpty() && (core.size() - i + idx) < res.peek().num) {
				return;
			}
			int cnt = 0;
			Pos cur = core.get(i);
			int dir = sel[i];
			if(dir == 4)
				continue;
			int nr = cur.r;
			int nc = cur.c;
			boolean flag = false;
			while(true) {
				nr += dr[dir];
				nc += dc[dir];
				if(!check(nr, nc)) {
					break;
				}
				if(tmap[nr][nc] == 1) {
					flag = true;
//					if(!res.isEmpty()) {
//						if(res.peek().num < idx && res.peek().cnt < cnt) {
//							res.add(new Res(cnt, idx));
//						}
//					}
//					else
//						res.add(new Res(cnt, idx));
					return;
				}
				tmap[nr][nc] = 1;
				cnt++;
			}
			if(!flag) {
				idx++;
				total += cnt;
			}
		}
//		if(!res.isEmpty()) {
//			Res tmp = res.peek();
//			if(tmp.num < idx) {
//				res.clear();
//				res.add(new Res(total, idx));
//				return;
//			}
//		}
		res.add(new Res(total, idx));
	}
//	static int aaa;
	static void comb(int[] sel, int idx, int k) {
		if(sel.length == k) {
			for(int i=0; i<N; i++)
				System.arraycopy(map[i], 0, tmap[i], 0, map[i].length);
			stretch(sel);
//			aaa++;
			return;
		}
		if(5 == idx) {
			return;
		}
		
		sel[k] = idx;
		comb(sel, 0, k+1);
		comb(sel, idx+1, k);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
