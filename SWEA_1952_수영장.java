package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1952_수영장 {
	static final int N = 12;
	static int ans;
	static int[] pay, info;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			info = new int[N+1];
			pay = new int[5];
			
			st = new StringTokenizer(br.readLine().trim());
			// 1일, 1달, 3달, 1년 요금
			for(int i=1; i<=4; i++) {
				pay[i] = Integer.parseInt(st.nextToken()); 
			}
			
			ans = pay[4];
			
			// 수영장 이용 계획
			st = new StringTokenizer(br.readLine().trim());
			for(int i=1; i<=N; i++) {
				info[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1; i<=3; i++) {
				dfs(info, 1, i, 0);
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void dfs(int[] tinfo, int idx, int type, int sum) {
		// 12월 넘어가면 패스 
		if(idx > N) {
			if(sum != 0)
				ans = Math.min(ans, sum);
			return;
		}
		// 3개월 이용권 넘어가면 패스 
		if(type > 3) {
			return;
		}
		// 이용계획 없으면 다음달로 이동 
		if(tinfo[idx] == 0) {
			dfs(tinfo, idx+1, type, sum);
			return;
		}

		// 이용계획 이전 상태 저장
		int[] tmp = new int[N+1];
		System.arraycopy(tinfo, 0, tmp, 0, tinfo.length);
		
		int token = 0; // idx 늘려줄 값
		int stoken = 0; // sum 늘려줄 값
		switch(type) {
		// 1일권 쓸 때 
		case 1:
			stoken = tmp[idx]*pay[type];
			token = 1;
			tmp[idx] = 0;
			break;
		// 1달권 쓸 때
		case 2:
			stoken = pay[type];
			token = 1;
			tmp[idx] = 0;
			break;
		// 3달권 쓸 때
		case 3:
			stoken = pay[type];
			try {
				for(int i=0; i<3; i++)
					tmp[idx+i] = 0;
			} catch(Exception e){
				
			}
			token = 3;
			break;
		}
		
		// 1일 ~ 3달 다 돌려봄
		for(int i=1; i<=3; i++) {
			dfs(tinfo, idx+token, i, sum+stoken);
			// 이전 상태로 계획 돌려줌
			System.arraycopy(tinfo, 0, tmp, 0, tinfo.length);
		}
	}
}
