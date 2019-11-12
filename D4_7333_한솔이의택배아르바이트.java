package swea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class D4_7333_한솔이의택배아르바이트 {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();	// (1 ≤ N ≤ 100)
			ArrayList<Integer> arr = new ArrayList<>();	// Wi (1 ≤ Wi ≤ 100)
			
			for(int i=0; i<N; i++) {
				arr.add(sc.nextInt());	// 상자 입력
			}
			
			// 상자 내림차순 정렬
			Collections.sort(arr, new Comparator<Integer>(){
				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2 - o1;
				}
			});
			
			int cnt = 0;
			int sum = 0;
			while(!arr.isEmpty()) {
				if(arr.size() > 1) {
					// 가장 무거운 상자가 50kg 이상일 때
					if(arr.get(0) >= 50) {
						arr.remove(0);
						cnt++;
					}
					
					// 50kg 미만일 때
					else {
						sum = arr.get(0);
						int size = sum;
						arr.remove(0);
						while(true) {
							if(arr.isEmpty()) {
								break;
							}
							sum += size;
							arr.remove(arr.size()-1);
							if(sum >= 50) {
								cnt++;
								sum = 0;
								break;
							}
						}
					}
				}
				if(arr.size() == 1) {
					if(arr.get(0) >= 50) {
						cnt++;
					}
					arr.remove(0);
				}
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
