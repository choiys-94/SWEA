package swea;

import java.util.ArrayList;
import java.util.Scanner;

public class 모의_2382_미생물격리 {
	static int N, M, K;
	static class Map{
		int x;
		int y;
		int num;
		int dir;
		int idx;
		int origin;
		Map(int x, int y, int num, int dir){
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();	// 한 변의 길이 (5 ≤ N ≤ 100)
			M = sc.nextInt(); 	// 시간 (1 ≤ M ≤ 1,000)
			K = sc.nextInt(); 	// 군집의 개수 (5 ≤ K ≤ 1,000)
			ArrayList<Map> list = new ArrayList<>();
			for(int i=0; i<K; i++) {
				int ix = sc.nextInt();
				int iy = sc.nextInt();
				int ic = sc.nextInt();
				int dir = sc.nextInt();
				list.add(new Map(ix, iy, ic, dir));	// 미생물 리스트
			}
			solve(list);
			System.out.println("#" + tc + " " + sum);
		}
	}
	
	//				       상  하  좌  우  
	static int[] dx = {0, -1, 1,  0, 0};
	static int[] dy = {0,  0, 0, -1, 1};
	static int sum = 0;
	static Map[][] tmp;
	
	static void solve(ArrayList<Map> list) {
		for(int t=0; t<M; t++) {
			sum = 0;
			tmp = new Map[N][N];	// 비어있는 맵 확보
			for(int i=0; i<list.size(); i++) {
				// 리스트 내의 군집 하나 찝어옴
				Map cur = list.get(i);
				// 죽은놈이면 패스
				if(cur.dir == 0)
					continue;
				
				int nx = cur.x + dx[cur.dir];
				int ny = cur.y + dy[cur.dir];
				// 약품 구간이 아닐 경우
				if(check(nx, ny)) {
					// 비어있으면!
					if(tmp[nx][ny] == null) {
						// 내 위치 비우고 이동!
						tmp[nx][ny] = new Map(nx, ny, cur.num, cur.dir);
						tmp[nx][ny].idx = i;
						cur.x = nx;
						cur.y = ny;
						tmp[nx][ny].origin = cur.num;
						sum += cur.num;
					}
					// 이동할 위치에 군집이 있을 경우
					else {
						// 원래 있는 놈이 나보다 많을 경우
						if(tmp[nx][ny].origin > cur.num) {
							//원래 있는 놈에다 개수 더하고 나를 지움
							sum += cur.num;
							list.get(tmp[nx][ny].idx).num += cur.num;
							tmp[nx][ny].num += cur.num;
							cur.dir = 0;
							
						}
						// 내가 더 크면
						else {
							//원래 있는 놈 리스트 지우고 맵도 내 방향으로 바꿔줌
							list.get(tmp[nx][ny].idx).dir = 0;
							tmp[nx][ny].idx = i;
							tmp[nx][ny].origin = cur.num;
							sum += cur.num;
							cur.num += tmp[nx][ny].num;
							tmp[nx][ny].num = cur.num;
							tmp[nx][ny].dir = cur.dir;
							cur.x = nx;
							cur.y = ny;
						}
					}
				}
				// 약품 샤워 후
				else {
					// 군집 개수가 1일 경우
					if(cur.num == 1) {
						// 쥬금
						cur.dir = 0;
					}
					// 아닐 경우
					else {
						// 수를 반토막 내고 방향 전환
						cur.num /= 2;
						cur.x = nx;
						cur.y = ny;
						switch(cur.dir) {
						case 1:
							cur.dir = 2;
							break;
						case 2:
							cur.dir = 1;
							break;
						case 3:
							cur.dir = 4;
							break;
						case 4:
							cur.dir = 3;
							break;
						}
						
						tmp[nx][ny] = new Map(nx, ny, cur.num, cur.dir);
						tmp[nx][ny].origin = cur.num;
						tmp[nx][ny].idx = i;
						sum += tmp[nx][ny].num;
					}
				}
			}
			System.out.println(sum);
		}
	}
	
	static boolean check(int x, int y) {
		return x != 0 && y != 0 && x != N-1 && y != N-1;
	}
}
