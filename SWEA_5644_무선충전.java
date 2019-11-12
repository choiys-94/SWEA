package swea;

import java.util.Scanner;

public class SWEA_5644_무선충전 {
	static class BC{
		int r;
		int c;
		int cover;
		int perform;
		BC(int r, int c, int cover, int perform){
			this.r = r;
			this.c = c;
			this.cover = cover;
			this.perform = perform;
		}
	}
	static final int N = 10;
	static int M, K;
	static int[][] move;
	static BC[] bc;
	static int r1, r2, c1, c2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			M = sc.nextInt(); // 이동 시간
			K = sc.nextInt(); // BC 개수
			move = new int[2][M];
			// A, B 이동 경로
			for(int i=0; i<2; i++) {
				for(int j=0; j<M; j++)
					move[i][j] = sc.nextInt();
			}
			
			bc = new BC[K];
			for(int i=0; i<K; i++) {
				int c = sc.nextInt();
				int r = sc.nextInt();
				int cover = sc.nextInt();
				int perform = sc.nextInt();
				bc[i] = new BC(r, c, cover, perform);
			}
			
			r1 = 1;
			c1 = 1;
			r2 = 10;
			c2 = 10;
			
			int cnt = -1;
			long sum = 0;
			while(cnt < M) {
				// 위치 옮기기
				if(cnt != -1) {
					int dir1 = move[0][cnt];
					int dir2 = move[1][cnt];
					r1 += dr[dir1];
					c1 += dc[dir1];
					r2 += dr[dir2];
					c2 += dc[dir2];
				}
				cnt++;
				// BC에서 탐색 시작
				int[] infoA = new int[K];
				int[] infoB = new int[K];
				
				for(int i=0; i<K; i++) {
					int dist1 = Math.abs(r1 - bc[i].r) + Math.abs(c1 - bc[i].c);
					int dist2 = Math.abs(r2 - bc[i].r) + Math.abs(c2 - bc[i].c);
					if(dist1 <= bc[i].cover)
						infoA[i]++;
					if(dist2 <= bc[i].cover)
						infoB[i]++;
				}
				
				int max = 0;
				for(int i=0; i<K; i++) {
					for(int j=0; j<K; j++) {
						int tmp = 0;
						if(infoA[i] != 0)
							tmp += bc[i].perform;
						if(infoB[j] != 0)
							tmp += bc[j].perform;
						if(infoA[i] != 0 && infoB[i] != 0 && i == j)
							tmp -= bc[i].perform;
						max = Math.max(max, tmp);
					}
				}
				sum += max;
			}
			System.out.println("#" + tc + " " + sum);
		}
	}
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 1, 0, -1};
}
