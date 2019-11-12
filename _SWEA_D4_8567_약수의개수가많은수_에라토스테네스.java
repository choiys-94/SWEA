package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _SWEA_D4_8567_약수의개수가많은수_에라토스테네스 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] arr = new int[100001];
		int[] count = new int[100001];
		int[] res = new int[100001];
		int max = Integer.MIN_VALUE;
		int idx = 1;
		
		for(int i=2; i<=100000; i++)
			arr[i] = i;
		
		res[1] = 1;
		for(int i=2; i<=100000; i++) {
			for(int j=i; j<=100000; j+=i) {
				if(j%arr[i]==0) {
					count[j]++;
					arr[j] /= arr[i];
				}
			}
			if(max <= count[i]) {
				max = count[i];
				idx = i;
			}
			res[i] = idx;
		}
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ").append(res[Integer.parseInt(br.readLine())]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
