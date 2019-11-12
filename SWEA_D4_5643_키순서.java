package swea;

import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_D4_5643_키순서 {
	static int N, M;
	static int cnt;
	static int result;
	static boolean[] visited;
	static ArrayList<Integer>[] light;
	static ArrayList<Integer>[] heavy;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			light = new ArrayList[N+1];
			heavy = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				light[i] = new ArrayList<>();
				heavy[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				light[a].add(b);
				heavy[b].add(a);
			}
			
			result = 0;
			
			for(int i=1; i<=N; i++) {
				cnt = 0;
				visited = new boolean[N+1];
				if(!light[i].isEmpty()) {
					visited[i] = true;
					dfs(light, light[i]);
				}
				visited = new boolean[N+1];
				if(!heavy[i].isEmpty()) {
					visited[i] = true;
					dfs(heavy, heavy[i]);
				}
				if(cnt == N-1)
					result++;
			}
			System.out.println("#" + tc + " " + result);
		}
	}
	
	static void dfs(ArrayList<Integer>[] arr, ArrayList<Integer> list) {
		for(int data: list) {
			if(visited[data] == false) {
				cnt++;
				visited[data] = true;
				if(!arr[data].isEmpty()) {
					dfs(arr, arr[data]);
				}
			}
		}
	}
}
