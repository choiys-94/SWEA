package swea;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SWEA_2477_차량정비소 {
	static class Counter{
		int idx;
		int cur;
		int time;
		int left;
		int recept;
		Counter(int idx, int time){
			this.idx = idx;
			this.time = time;
			cur = -1;
			left = -1;
		}
	}
	
	static class Customer implements Comparable<Customer>{
		int idx;
		int time;
		int recept;
		int repair;
		Customer(int idx, int time, int recept, int repair){
			this.idx = idx;
			this.time = time;
			this.recept = recept;
			this.repair = repair;
		}
		@Override
		public int compareTo(Customer o) {
			if(this.time == o.time)
				return this.idx - o.idx;
			return this.time - o.time;
		}
	}
	
	static int N, M, K, A, B;
	static ArrayList<Counter> recept, repair;
	static PriorityQueue<Customer> receptWait, repairWait;
	static PriorityQueue<Customer> customer;
	static Customer[] finished;
	static int time, finish;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 접수 창구 개수
			M = sc.nextInt(); // 정비 창구 개수
			K = sc.nextInt(); // 방문 고객 수
			A = sc.nextInt(); // 지갑 접수 번호
			B = sc.nextInt(); // 지갑 창구 번호
			
			recept = new ArrayList<>();
			repair = new ArrayList<>();
			customer = new PriorityQueue<>();
			finished = new Customer[K+1];
			for(int i=1; i<=N; i++) {
				recept.add(new Counter(i, sc.nextInt()));
			}
			
			for(int i=1; i<=M; i++) {
				repair.add(new Counter(i, sc.nextInt()));
			}
			
			for(int i=1; i<=K; i++) {
				customer.add(new Customer(i, sc.nextInt(), 987654321, 987654321));
			}
			
			time = 0;
			finish = 0;
			Comparator<Customer> receptComp = new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					return o1.idx-o2.idx;
				}
			};
			
			Comparator<Customer> repairComp = new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					if(o1.time == o2.time) {
						return o1.recept-o2.recept;
					}
					return o1.time-o2.time;
				}
			};
			
			receptWait = new PriorityQueue<>(receptComp);
			repairWait = new PriorityQueue<>(repairComp);
			
			while(finish < K) {
				while(!customer.isEmpty() && customer.peek().time <= time) {
					Customer cur = customer.poll();
					receptWait.add(new Customer(cur.idx, cur.time, 987654321, 987654321));
				}
				receptFunc();
				repairFunc();
				
				for(int i=0; i<N; i++) {
					if(recept.get(i).left != 0)
						recept.get(i).left--;
				}
				
				for(int i=0; i<M; i++) {
					if(repair.get(i).left != 0)
						repair.get(i).left--;
				}
				
				time++;
			}
			
			int ans = 0;
			for(int i=1; i<=K; i++) {
				Customer f = finished[i];
				if(f == null)
					continue;
				if(f.recept == A && f.repair == B) {
					ans += f.idx;
				}
			}
			ans = (ans == 0) ? -1 : ans;
			System.out.println("#" + tc + " " + ans);
		}
		
	}
	
	static void receptFunc() {
		// 접수 창구 체크
		for(int i=0; i<N; i++) {
			// 볼일 다 봤으면 정비로 보내자
			if(recept.get(i).left == 0) {
				repairWait.add(new Customer(recept.get(i).cur, time, i+1, 987654321));
				recept.get(i).cur = -1;
				recept.get(i).left = -1;
			}
		}
		
		// 접수 창구에 사람 넣자
		while(!receptWait.isEmpty()) {
			boolean check = true;
			for(int i=0; i<N; i++) {
				// 자리 비었으면
				if(recept.get(i).cur == -1) {
					recept.get(i).cur = receptWait.poll().idx;
					recept.get(i).left = recept.get(i).time;
					check = false;
					break;
				}
			}
			if(check)
				break;
		}
	}
	
	static void repairFunc() {
		for(int i=0; i<M; i++) {
			// 볼일 다 봤으면 없애자
			if(repair.get(i).left == 0) {
				finished[repair.get(i).cur] = new Customer(repair.get(i).cur, -1, repair.get(i).recept, i+1);
				finish++;
				repair.get(i).recept = -1;
				repair.get(i).cur = -1;
				repair.get(i).left = -1;
			}
		}
		
		// 정비 창구에 사람 넣자
		while(!repairWait.isEmpty()) {
			boolean check = true;
			for(int i=0; i<M; i++) {
				// 자리 비었으면
				if(repair.get(i).cur == -1) {
					Customer cur = repairWait.poll();
					repair.get(i).cur = cur.idx;
					repair.get(i).recept = cur.recept;
					repair.get(i).left = repair.get(i).time;
					check = false;
					break;
				}
			}
			if(check)
				break;
		}
	}
}
