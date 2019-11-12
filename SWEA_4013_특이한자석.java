package swea;

import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_4013_특이한자석 {
	static int N = 4;
	static int K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
	
		for(int tc = 1; tc <= T; tc++) {
			ArrayList<Integer>[] arr = new ArrayList[4];
			tarr = new ArrayList[4];
			K = sc.nextInt();
			for(int i=0; i<N; i++) {
				arr[i] = new ArrayList<>();
				tarr[i] = new ArrayList<>();
				// 톱니바퀴 list에 넣음 
				for(int j=0; j<8; j++) {
					arr[i].add(sc.nextInt());
				}
			}
			
			for(int i=0; i<K; i++) {
				int idx = sc.nextInt()-1;
				int rot = sc.nextInt();
				for(int j=0; j<N; j++) {
					tarr[j].clear();
					tarr[j].addAll(arr[j]);
				}
				dfs(arr, idx, rot, 0);
				for(int j=0; j<N; j++) {
					arr[j].clear();
					arr[j].addAll(tarr[j]);
				}
			}
			
			int result = 0;
			if(arr[0].get(0) == 1)
				result += 1;
			if(arr[1].get(0) == 1)
				result += 2;
			if(arr[2].get(0) == 1)
				result += 4;
			if(arr[3].get(0) == 1)
				result += 8;
			
			System.out.println("#" + tc + " " + result);
		}
	}
	
	static ArrayList<Integer>[] tarr;
	
	static void dfs(ArrayList<Integer>[] arr, int idx, int rot, int dir) {
		if(idx == -1 || idx == N) {
			return;
		}

		if(rot == 1) {
			tarr[idx].clear();
			tarr[idx].add(arr[idx].get(arr[idx].size()-1));
			tarr[idx].addAll(arr[idx].subList(0, arr[idx].size()-1));
		}
		else {
			tarr[idx].clear();
			tarr[idx].addAll(arr[idx].subList(1, arr[idx].size()));
			tarr[idx].add(arr[idx].get(0));
		}
		
		if((dir == 0 || dir == 1) && idx != N-1 && arr[idx].get(2) != arr[idx+1].get(6)) {
			dfs(arr, idx+1, ((rot == 1) ? -1 : 1), 1);
		}
		if((dir == 0 || dir == 2) && idx != 0 && arr[idx].get(6) != arr[idx-1].get(2)) {
			dfs(arr, idx-1, ((rot == 1) ? -1 : 1), 2);
		}
	}
}
