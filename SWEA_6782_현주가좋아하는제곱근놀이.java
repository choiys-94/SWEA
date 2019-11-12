package swea;

import java.util.Scanner;

public class SWEA_6782_현주가좋아하는제곱근놀이 {
	static long N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextLong();
			int cnt = 0;
			while(N != 2) {
				if(Math.sqrt(N)-(long)Math.sqrt(N) != 0 || Math.sqrt(N) < 2) {
					long tmp = (long)(Math.pow((long)Math.sqrt(N)+1, 2));
					cnt += tmp-N+1;
					N = (long)Math.sqrt(tmp);
				}
				else {
					cnt++;
					N = (long)Math.sqrt(N);
				}
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
