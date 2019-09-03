package swea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class D4_1258_행렬찾기 {
	static int N;
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	static int mx, my;
	static boolean[][] visited;
	
	static class Coord implements Comparable<Coord>{
		int x;
		int y;
		int area;
		Coord(int x, int y){
			this.x = x;
			this.y = y;
			this.area = x*y;
		}
		@Override
		public int compareTo(Coord o) {
			// TODO Auto-generated method stub
			if(this.area == o.area) {
				return this.x - o.x;
			}
			return this.area - o.area;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int T = 1; T <= tc; T++) {
			N = sc.nextInt();
			int[][] map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			ArrayList<Coord> result = new ArrayList<>();
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j] == false && map[i][j] != 0) {
						mx = 0;
						my = 0;
						int sx = i;
						int sy = j;
						visited[i][j] = true;
						solve(map, i, j);
						result.add(new Coord(mx-sx+1, my-sy+1));
					}
				}
			}
			
			Collections.sort(result);
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(T).append(" ").append(result.size()).append(" ");
			for(Coord c: result) {
				sb.append(c.x).append(" ").append(c.y).append(" ");
			}
			System.out.println(sb.toString());
		}
	}
	
	static void solve(int[][] map, int x, int y) {
		for(int i=0; i<2; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(check(nx, ny) && visited[nx][ny] == false && map[nx][ny] != 0) {
				visited[nx][ny] = true;
				mx = Math.max(mx, nx);
				my = Math.max(my, ny);
				solve(map, nx, ny);
			}
		}
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
}
