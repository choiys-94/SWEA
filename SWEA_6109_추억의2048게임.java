package swea;

import java.util.Arrays;
import java.util.Scanner;

public class SWEA_6109_추억의2048게임 {
	static int N;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			String in = sc.next();
			int dir = 0;
			if(in.equals("right"))
				dir = 0;
			else if(in.equals("down"))
				dir = 1;
			else if(in.equals("left"))
				dir = 2;
			else if(in.equals("up"))
				dir = 3;
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			//방향 경우의 수
			move(dir);
			
			System.out.println("#" + tc);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
	
	static boolean[][] visited;
	// 우 하 좌 상
	static void move(int dir) {
		for(int i=0; i<N; i++) {
			Arrays.fill(visited[i], false);
		}
		switch(dir) {
		case 0:
			for(int i=0; i<N; i++) {
				for(int j=N-1; j>=0; j--) {
					int token = map[i][j];
					if(map[i][j] == 0)
						continue;
					int nr = i;
					int nc = j;
					while(true) {
						nr += dr[dir];
						nc += dc[dir];
						if(!check(nr, nc)) {
							map[i][j] = 0;
							map[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
						if(map[nr][nc] == 0)
							continue;
						if(map[nr][nc] == token && !visited[nr][nc]) {
							map[i][j] = 0;
							map[nr][nc] = token*2;
							visited[nr][nc] = true;
							break;
						}
						else {
							map[i][j] = 0;
							map[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
					}
				}
			}
			break;
		case 1:
			for(int i=0; i<N; i++) {
				for(int j=N-1; j>=0; j--) {
					int token = map[j][i];
					if(map[j][i] == 0)
						continue;
					int nr = j;
					int nc = i;
					while(true) {
						nr += dr[dir];
						nc += dc[dir];
						if(!check(nr, nc)) {
							map[j][i] = 0;
							map[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
						if(map[nr][nc] == 0)
							continue;
						if(map[nr][nc] == token && !visited[nr][nc]) {
							map[j][i] = 0;
							map[nr][nc] = token*2;
							visited[nr][nc] = true;
							break;
						}
						else {
							map[j][i] = 0;
							map[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
					}
				}
			}
			break;
		case 2:
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int token = map[i][j];
					if(map[i][j] == 0)
						continue;
					int nr = i;
					int nc = j;
					while(true) {
						nr += dr[dir];
						nc += dc[dir];
						if(!check(nr, nc)) {
							map[i][j] = 0;
							map[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
						if(map[nr][nc] == 0)
							continue;
						if(map[nr][nc] == token && !visited[nr][nc]) {
							map[i][j] = 0;
							map[nr][nc] = token*2;
							visited[nr][nc] = true;
							break;
						}
						else {
							map[i][j] = 0;
							map[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
					}
				}
			}
			break;
		case 3:
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int token = map[j][i];
					if(map[j][i] == 0)
						continue;
					int nr = j;
					int nc = i;
					while(true) {
						nr += dr[dir];
						nc += dc[dir];
						if(!check(nr, nc)) {
							map[j][i] = 0;
							map[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
						if(map[nr][nc] == 0)
							continue;
						if(map[nr][nc] == token && !visited[nr][nc]) {
							map[j][i] = 0;
							map[nr][nc] = token*2;
							visited[nr][nc] = true;
							break;
						}
						else {
							map[j][i] = 0;
							map[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
					}
				}
			}
			break;
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}