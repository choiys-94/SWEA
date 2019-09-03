package swea;

import java.util.PriorityQueue;
import java.util.Scanner;

public class D4_1861_정사각형방 {
	static int N;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] map;
	static PriorityQueue<Room> result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int T = 1; T <= tc; T++) {
			N = sc.nextInt();
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			result = new PriorityQueue<>();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					result.add(new Room(map[i][j], 1, map[i][j]));
					solve(i, j, map[i][j], 1, map[i][j]);
				}
			}
			
			System.out.print("#" + T + " ");
			Room tmp = result.poll();
			System.out.println(tmp.sVal + " " + tmp.cnt);
			
		}
	}
	static class Room implements Comparable<Room>{
		int val;
		int cnt;
		int sVal;
		Room(int val, int cnt, int sVal){
			this.val = val;
			this.cnt = cnt;
			this.sVal = sVal;
		}
		@Override
		public int compareTo(Room o) {
			// TODO Auto-generated method stub
			if(o.cnt == this.cnt) {
				return this.val - o.val; 
			}
			return o.cnt - this.cnt;
		}
	}
	
	static void solve(int x, int y, int Val, int cnt, int sVal) {
		if(!result.isEmpty() && result.peek().cnt <= cnt) {
			result.add(new Room(map[x][y], cnt, sVal));
		}
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(check(nx, ny) && map[nx][ny] == Val+1) {
				x = nx;
				y = ny;
				solve(nx, ny, map[nx][ny], cnt+1, sVal);
			}
		}
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
}
