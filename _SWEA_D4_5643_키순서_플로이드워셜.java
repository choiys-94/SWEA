package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _SWEA_D4_5643_키순서_플로이드워셜 {
	static final int INF = 987654321;
	
	static int N, M;
	static int[][] adj;
	static int[] res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			adj = new int[N+1][N+1];
			res = new int[N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i==j)
						adj[i][j] = 0;
					else
						adj[i][j] = INF;	
				}
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adj[a][b] = 1;
			}
			
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
					}
				}
			}
			
			int cnt = 0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(adj[i][j] < INF || adj[j][i] < INF) res[i]++;
				}
			}
			
//			for(int i=1; i<=N; i++) {
//				for(int j=1; j<=N; j++) {
//					if(adj[i][j] == INF) System.out.print("x "); 
//					else System.out.print(adj[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			for(int i=1; i<=N; i++) {
				if(res[i] == N)
					cnt++;
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
}
