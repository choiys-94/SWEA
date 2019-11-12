package swea;

import java.util.Scanner;

public class _SWEA_5653_줄기세포배양 {
	static class Node implements Comparable<Node>{
		int r, c, val, time;
		Node(int r, int c, int val, int time){
			this.r = r;
			this.c = c;
			this.val = val;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			if(this.time == o.time)
				return o.val - this.val;
			return this.time - o.time;
		}
	}
	static int N, M, K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 세로
			M = sc.nextInt(); // 가로
			K = sc.nextInt(); // 배양 시간
			
		}
	}
}
