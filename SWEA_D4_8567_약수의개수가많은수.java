package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_D4_8567_약수의개수가많은수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] arr = new int[100001];
		int cnt = 0;
		arr[1] = 1;
		int max = Integer.MIN_VALUE;
		int idx = 0;
		for(int i=2; i<=100000; i++) {
			cnt = 0;
			for(int j=1; j<=(int)Math.sqrt(i); j++) {
				if(i%j == 0) {
					if(i/j != j) cnt += 2;
					else cnt++;
				}
			}
			if(max <= cnt) {
				max = cnt;
				idx = i;
			}
			arr[i] = idx;
		}
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++)
			sb.append("#").append(tc).append(" ").append(arr[Integer.parseInt(br.readLine())]).append("\n");
		System.out.println(sb.toString());
	}
}