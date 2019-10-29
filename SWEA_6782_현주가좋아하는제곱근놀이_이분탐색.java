package swea;

import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_6782_현주가좋아하는제곱근놀이_이분탐색 {
	static int N;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		arr = new int[T];
		long max = Integer.MIN_VALUE;
		for(int tc = 1; tc <= T; tc++) {
			arr[tc-1] = sc.nextInt();
			max = Math.max(max, arr[tc-1]);
		}
		
		ArrayList<Long> pow = new ArrayList<>();
		long token = 1;
		pow.add(0L);
		while(true) {
			pow.add(token*token);
			if(token*token > max)
				break;
			token++;
		}
		
		for(int i=0; i<T; i++) {
			int cnt = 0;
			while(arr[i] != 2) {
				int left = 1;
				int right = pow.size()-1;
				int idx = -1;
				max = arr[i];
				
				System.out.println(left + " " + right);
				
				while(left <= right) {
					int mid = (left+right)/2;
					if(pow.get(mid) == arr[i]) {
						max = arr[i];
						idx = mid;
						cnt += 1;
						break;
					}
					else if(pow.get(mid) < arr[i]) {
						left = mid+1;
					}
					else {
						if(max == pow.get(mid)) {
							cnt += (int)(max - arr[i]) + 1;
							break;
						}
						max = Math.min(max, pow.get(mid));
						idx = Math.min(idx, mid);
						right = mid-1;
					}
				}
				System.out.println(max);
				System.exit(0);
//				cnt += (int)(max - arr[i]) + 1;
				arr[i] = idx;
			}
			System.out.println(cnt);
		}
	}
}
