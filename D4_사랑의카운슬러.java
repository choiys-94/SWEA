package swea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class D4_사랑의카운슬러 {
	static int N;
	static class Coord{
		int x;
		int y;
		Coord(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int T = 1; T <= tc; T++) {
			N = sc.nextInt();
			ArrayList<Coord> worms = new ArrayList<>();
			for(int i=0; i<N; i++) {
				worms.add(new Coord(sc.nextInt(), sc.nextInt()));
			}
			MIN = Long.MAX_VALUE;
			solve(worms, new Coord[N/2], 0, 0);
			System.out.println("#" + T + " " + MIN);
		}
	}
	
	static long MIN = Long.MAX_VALUE;
	
	static void solve(ArrayList<Coord> worms, Coord[] sel, int n, int r) {
		if(r == sel.length) {
			long sum_x = 0;
			long sum_y = 0;
			for(int i=0; i<worms.size(); i++) {
				sum_x += worms.get(i).x;
				sum_y += worms.get(i).y;
			}
			for(int i=0; i<sel.length; i++) {
				sum_x -= sel[i].x*2;
				sum_y -= sel[i].y*2;
			}
			
			MIN = Math.min(MIN, (long)(Math.pow(sum_x, 2) + Math.pow(sum_y, 2)));
			
			return;
		}

		if(n == worms.size()) {
			return;
		}
		
		sel[r] = worms.get(n);
		solve(worms, sel, n+1, r+1);
		solve(worms, sel, n+1, r);
	}
}