package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _SWEA_D5_7396_종구의딸이름짓기 {
	static int N, M;
	static class Name implements Comparable<Name>{
		int r;
		int c;
		char alpha;
		StringBuilder name = new StringBuilder();
		Name(int r, int c, char alpha, StringBuilder name){
			this.r = r;
			this.c = c;
			this.alpha = alpha;
			this.name.append(name.toString()).append(alpha);
		}
		@Override
		public int compareTo(Name o) {
			// TODO Auto-generated method stub
			return Character.compare(this.alpha, o.alpha);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
//		Queue<Name> queue = new LinkedList<>();
		PriorityQueue<Name> queue = new PriorityQueue<>();
		ArrayList<Name> tmp = new ArrayList<>();
		ArrayList<String> res = new ArrayList<>();
		char[][] map;
		boolean[][] visited;
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			queue.clear();
			for(int i=0; i<N; i++) {
				String in = br.readLine();
				map[i] = in.toCharArray();
			}
			visited = new boolean[N][M];
			queue.add(new Name(0, 0, map[0][0], new StringBuilder()));
			visited[0][0] = true;
			
			sb.append("#").append(tc).append(" ");
			res.clear();
			String memo = new String();
			memo = "{";
			while(!queue.isEmpty()) {
				Name q = queue.poll();
				if(q.name.toString().length() != 0 && memo.length() <= q.name.toString().length() && memo.substring(0, memo.length()).compareTo(q.name.toString().substring(0, memo.length())) >= 0) {
					memo = q.name.toString() + "{";
				}
				
				else {
					continue;
				}
				System.out.println(memo);
				if(q.r == N-1 && q.c == M-1) {
//					sb.append(q.name.toString()).append("\n");
//					break;
					res.add(q.name.toString());
				}
				tmp.clear();
				for(int d=0; d<2; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(check(nr, nc) && (visited[nr][nc] == false || (nr == N-1 && nc == M-1))) {
						if(Character.compare(q.alpha, map[nr][nc]) != 0)
							visited[nr][nc] = true;
						tmp.add(new Name(nr, nc, map[nr][nc], q.name));
//						visited[nr][nc] = true;
//						queue.add(new Name(nr, nc, map[nr][nc], q.name));
					}
				}
				if(tmp.size() == 2) {
					if(Character.compare(tmp.get(0).alpha, tmp.get(1).alpha) == 0) {
						queue.add(tmp.get(0));
						queue.add(tmp.get(1));
					}
					else if(Character.compare(tmp.get(0).alpha, tmp.get(1).alpha) < 0)
						queue.add(tmp.get(0));
					else
						queue.add(tmp.get(1));
				}
				else if(tmp.size() == 1)
					queue.add(tmp.get(0));
			}
			Collections.sort(res, new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			sb.append(res.get(0)).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
}
