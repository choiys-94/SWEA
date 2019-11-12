package swea;

import java.util.Scanner;

public class SWEA_4613_러시아국기같은깃발 {
	static int N, M;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][M];
			for(int i=0; i<N; i++) {
				String in = sc.next();
				for(int j=0; j<M; j++) {
					switch(in.charAt(j)) {
					case 'W':
						map[i][j] = 1;
						break;
					case 'B':
						map[i][j] = 2;
						break;
					case 'R':
						map[i][j] = 3;
						break;
					}
				}
			}
			
			white = new int[N];
			blue = new int[N];
			red = new int[N];
			for(int i=0; i<N; i++) {
				int ws = 0, bs = 0, rs = 0;
				for(int j=0; j<M; j++) {
					if(map[i][j] != 1)
						ws++;
					if(map[i][j] != 2)
						bs++;
					if(map[i][j] != 3)
						rs++;
				}
				white[i] = ws;
				blue[i] = bs;
				red[i] = rs;
			}
			
			ans = Integer.MAX_VALUE;
			
			for(int i=1; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					int sum = 0;
					for(int k=0; k<i; k++) {
						sum += white[k];
					}
					for(int k=i; k<j; k++) {
						sum += blue[k];
					}
					for(int k=j; k<N; k++) {
						sum += red[k];
					}
					ans = Math.min(ans, sum);
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static int ans;
	static int[] blue;
	static int[] white;
	static int[] red;
}
