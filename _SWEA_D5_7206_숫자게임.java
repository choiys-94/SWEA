package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _SWEA_D5_7206_숫자게임 {
	static String N;
	static int max = Integer.MIN_VALUE;
	static HashMap<String, Integer> memo;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); 
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			N = br.readLine();
			memo = new HashMap<>();
			sb.append("#").append(tc).append(" ");
			if(N.length() == 1) {
				sb.append("0\n");
				continue;
			}
			max = Integer.MIN_VALUE;
			play();
			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static String calc(boolean[] sel, String str) {
		int start = 0;
		int end = str.length();
		int res = 1;
		for(int i=0; i<sel.length; i++) {
			if(sel[i]) {
				res *= Integer.parseInt(str.substring(start, i+1));
				start = i+1;
			}
		}
		res *= Integer.parseInt(str.substring(start, end));
		return Integer.toString(res);
	}
	
	static void powerSet(boolean[] sel, int idx, String str, int turn) {
		if(sel.length == idx) {
			if(memo.containsKey(str)) {
				if(memo.get(str) > turn)
					return;
			}
			memo.put(str, turn);
			boolean flag = true;
			for(int i=0; i<sel.length; i++) {
				if(sel[i])
					flag = false;
			}
			if(flag) {
				return;
			}
			String res = calc(sel, str);
			max = Math.max(max, turn);
			
			if(res.length() == 1) {
				return;
			}
			
			powerSet(new boolean[res.length()-1], 0, res, turn+1);
			return;
		}
		sel[idx] = true;
		powerSet(sel, idx+1, str, turn);
		sel[idx] = false;
		powerSet(sel, idx+1, str, turn);
	}
	
	static void play() {
		powerSet(new boolean[N.length()-1], 0, N, 1);
	}
}
