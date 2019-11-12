package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _SWEA_5672_올해의조련사 {
	static class Bird implements Comparable<Bird>{
		int sidx;
		int eidx;
		String name;
		Bird(int sidx, int eidx, String name){
			this.sidx = sidx;
			this.eidx = eidx;
			this.name = name;
		}
		@Override
		public int compareTo(Bird o) {
			return this.name.compareTo(o.name);
		}
		
	}
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		String ans = "";
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			char[] psi = new char[N];
			for(int i=0; i<N; i++)
				psi[i] = br.readLine().trim().charAt(0);
			
			ans = "";
			
			int sidx = 0;
			int eidx = psi.length-1;
			while(sidx <= eidx) {
//				System.out.println(ans + " " + sidx + " " + eidx);
				if(psi[sidx] < psi[eidx]) {
					ans += psi[sidx];
					sidx++;
				}
				else if(psi[sidx] > psi[eidx]) {
					ans += psi[eidx];
					eidx--;
				}
				else {
					int cnt1 = 0, cnt2 = 0, idx1 = 0, idx2 = 0;
					char ch = psi[sidx];
					for(int i=sidx; i<=eidx; i++) {
						if(psi[i] != ch) {
							idx1 = i;
							break;
						}
						cnt1++;
					}
					for(int i=eidx; i>=sidx; i--) {
						if(psi[i] != ch) {
							idx2 = i;
							break;
						}
						cnt2++;
					}
					if(cnt1 > cnt2) {
						for(int i=0; i<cnt2; i++) {
							ans += ch;
						}
						eidx -= cnt2;
					}
					else if(cnt1 < cnt2) {
						for(int i=0; i<cnt1; i++) {
							ans += ch;
						}
						sidx += cnt1;
					}
					else {
						if(psi[idx1] < psi[idx2]) {
							for(int i=0; i<cnt1; i++) {
								ans += ch;
							}
							sidx += cnt1;
						}
						else {
							for(int i=0; i<cnt2; i++) {
								ans += ch;
							}
							eidx -= cnt2;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
