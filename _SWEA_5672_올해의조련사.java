package swea;

import java.util.Scanner;

public class _SWEA_5672_올해의조련사 {
	static char[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			
			arr = new char[N];
			for(int i=0; i<N; i++) {
				arr[i] = sc.next().charAt(0);
			}
			
			int left = 0;
			int right = arr.length-1;
			String ans = "";
			while(left <= right) {
				if(arr[left] < arr[right]) {
					ans += arr[left];
					left++;
				}
				else if(arr[left] > arr[right]) {
					ans += arr[right];
					right--;
				}
				else {
					int res = isOk(left, right);
					if(res < 0) {
						ans += arr[left];
						left++;
					}
					else if(res > 0) {
						ans += arr[right];
						right--;
					}
					else {
						while(left <= right) {
							ans += arr[left];
							left++;
						}
					}
				}
				
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static int isOk(int left, int right) {
		int l = left;
		int r = right;
		while(l < r) {
			if(arr[l] == arr[r]) {
				l++;
				r--;
			}
			else if(arr[l] < arr[r])
				return -1;
			else if(arr[l] > arr[r])
				return 1;
		}
		return 0;
	}
}
