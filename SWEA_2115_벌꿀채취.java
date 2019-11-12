package swea;

import java.util.Scanner;

public class SWEA_2115_벌꿀채취 {
	static int N, M, C;
	static int[][] map;
	static int ans, sum, tttsum;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			C = sc.nextInt();
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			ans = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N-M+1; j++) {
					solve(i, j);
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void powerSet(boolean[] sel, int idx, int r, int c) {
		if(idx == M) {
			int tsum = 0;
			int ttsum = 0;
			for(int i=0; i<M; i++) {
				if(!sel[i])
					continue;
				tsum += map[r][c+i];
				ttsum += Math.pow(map[r][c+i], 2);
			}
			if(tsum <= C)
				tttsum = Math.max(tttsum, ttsum);
			return;
		}
		sel[idx] = true;
		powerSet(sel, idx+1, r, c);
		sel[idx] = false;
		powerSet(sel, idx+1, r, c);
	}
	
	static void solve(int str, int stc) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N-M+1; j++) {
				if(str == i && Math.abs(j-stc) <= M)
					continue;
				sum = 0;
				tttsum = 0;
				powerSet(new boolean[M], 0, str, stc);
				sum += tttsum;
				
				tttsum = 0;
				powerSet(new boolean[M], 0, i, j);
				sum += tttsum;
				ans = Math.max(ans, sum);
			}
		}
	}
}
