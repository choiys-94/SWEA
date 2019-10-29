package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA_5658_보물상자비밀번호 {
	static int N, K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			String in = br.readLine();
			ArrayList<Character> arr = new ArrayList<>();
			for(int i=0; i<N; i++) {
				arr.add(in.charAt(i));
			}
			TreeSet<Integer> res = new TreeSet<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o2, o1);
				}
			});
			
			for(int i=0; i<N/4; i++) {
				String token = "";
				for(int j=0; j<N; j++) {
					token += arr.get(j);
					if((j+1)%(N/4) == 0) {
						res.add(Integer.parseInt(token, 16));
						token = "";
					}
				}
				char tmp = arr.get(arr.size()-1);
				arr.remove(arr.size()-1);
				arr.add(0, (char)tmp);
			}
			
			for(int i=0; i<K-1; i++)
				res.pollFirst();
			
			sb.append("#").append(tc).append(" ").append(res.pollFirst()).append("\n");
		}
		System.out.println(sb.toString());
	}
}
