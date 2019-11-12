package swea;

import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_2117_홈방범서비스 {
	static class Pos{
		int r, c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			ArrayList<Pos> home = new ArrayList<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] == 1) {
						home.add(new Pos(i, j));
					}
				}
			}
			int ans = 0;
			int size = home.size();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					for(int k=1; k<=2*N; k++) {
						int cnt = 0;
						for(int h=0; h<size; h++) {
							Pos cur = home.get(h);
							int dist = Math.abs(i - cur.r) + Math.abs(j - cur.c);
							if(dist <= k-1) {
								cnt++;
							}
						}
						int sum = cnt*M;
						int cost = 1;
						if(k > 1) {
							cost = (int)(Math.pow(k, 2) + Math.pow(k-1, 2));
						}
						if(cost <= sum) {
							ans = Math.max(ans, cnt);
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
