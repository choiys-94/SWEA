package swea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class SWEA_D4_7701_염라대왕의이름정렬 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			HashSet<String> arr = new HashSet<>();
			for(int i=0; i<N; i++) {
				arr.add(sc.next());
			}
			ArrayList<String> res = new ArrayList<>(arr);
			Collections.sort(res, new Comparator<String>(){

				@Override
				public int compare(String o1, String o2) {
					if(o1.length() == o2.length())
						return o1.compareTo(o2);
					return o1.length() - o2.length();
				}
				
			});
			System.out.println("#" + tc);
			for(String r: res) {
				System.out.println(r);
			}
		}
		
	}
}
