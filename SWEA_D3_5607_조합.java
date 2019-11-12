package swea;

import java.util.Scanner;

public class SWEA_D3_5607_조합 {
	static long N, R;
	static final long p = 1234567891;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextLong();
			R = sc.nextLong();
			long tn = N;
			long n = 1;
			long r = 1;
			
			for(int i=1; i<=R; i++) {
				n *= tn;
				tn--;
				n %= p;
				r *= i;
				r %= p;
			}
			
			long res = (n*(pow(r, p-2)%p))%p;
			System.out.println("#" + tc + " " + res);
		}
	}
	
	static long pow(long a, long b) {
		if(b == 1L)
			return a;
		
		long token = pow(a, b/2)%p;
		if(b%2 == 1L) {
			token = (token*token)%p;
			token = (token*a)%p;
		}
		else {
			token = (token*token)%p;
		}
		
		return token % p;
	}
}
