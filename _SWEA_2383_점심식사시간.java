package swea;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class _SWEA_2383_점심식사시간 {
	static final int INF = 987654321;
	static class Node {
		int r;
		int c;
		int level;
		Node stair1;
		Node stair2;
		int dist = INF;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			ArrayList<Node> people = new ArrayList<>();
			ArrayList<Node> stair = new ArrayList<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int in = sc.nextInt();
					if(in == 0)
						continue;
					if(in == 1)
						people.add(new Node(i, j));
					else {
						stair.add(new Node(i, j));
						stair.get(stair.size()-1).level = in;
					}
				}
			}
			
			// 사람과 계단 둘 중 가까운 거리를 기준으로 pq 생성
			PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					int dist1 = Math.min(o1.stair1.dist, o1.stair2.dist);
					int dist2 = Math.min(o2.stair1.dist, o2.stair2.dist);
					return Integer.compare(dist1, dist2);
				}
			});
			
			// 사람과 계단들 거리 측정, 큐에 때려박음
			for(Node p: people) {
				p.stair1 = new Node(0, 0);
				p.stair2 = new Node(0, 0);
				p.stair1.dist = Math.abs(p.r - stair.get(0).r) + Math.abs(p.c - stair.get(0).c) + 1;
				p.stair1.level = stair.get(0).level;
				p.stair2.dist = Math.abs(p.r - stair.get(1).r) + Math.abs(p.c - stair.get(1).c) + 1;
				p.stair2.level = stair.get(1).level;
				queue.add(p);
			}
			
			Comparator<Integer> comp = new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1-o2;
				}
			};
			PriorityQueue<Integer> stairQueue1 = new PriorityQueue<>(comp);
			PriorityQueue<Integer> stairQueue2 = new PriorityQueue<>(comp);
			while(!queue.isEmpty()) {
				Node q = queue.poll();
				
				if(q.stair1.dist < q.stair2.dist) {
					if(stairQueue1.size() != 3) {
						stairQueue1.add(q.stair1.dist+stair.get(0).level);
					}
					else {
						boolean flag = true;
						int sub = q.stair1.dist+1 - stairQueue1.peek();
//						if(sub > stair.get(0).level) {
//							int origin = q.stair1.dist+stair.get(0).level+sub;
//							int alter = q.stair2.dist+stair.get(1).level;
//							if(origin > alter) {
//								stairQueue2.add(alter);
//								flag = false;
//							}
//							if(flag) {
//								stairQueue1.add(q.stair1.dist+stair.get(0).level + sub);
//							}
//						}
//						else {
//							stairQueue1.add(q.stair1.dist+stair.get(0).level);
//						}
						if(sub < 0)
							sub = 0;
						stairQueue1.add(q.stair1.dist+stair.get(0).level+sub);
						stairQueue1.poll();
					}
				}
				else {
					if(stairQueue2.size() != 3) {
						stairQueue2.add(q.stair2.dist+stair.get(1).level);
					}
					else {
						boolean flag = true;
						int sub = q.stair2.dist+1 - stairQueue2.peek();
//						System.out.println("Stair2: " + sub);
//						if(sub > stair.get(1).level) {
//							int origin = stairQueue1.peek()+sub;
//							int alter = q.stair1.dist+stair.get(0).level;
//							if(origin > alter) {
//								stairQueue1.add(alter);
//								flag = false;
//							}
//							if(flag) {
//								stairQueue2.add(q.stair2.dist+stair.get(1).level + sub);
//							}
//						}
//						else {
//							stairQueue2.add(q.stair2.dist+stair.get(1).level);
//						}
						if(sub < 0)
							sub = 0;
						stairQueue2.add(q.stair2.dist+stair.get(1).level+sub);
						stairQueue2.poll();
					}
				}
			}
			
			int ans = 0;
			
			while(!stairQueue1.isEmpty()) {
				ans = Math.max(ans, stairQueue1.poll());
			}
			while(!stairQueue2.isEmpty()) {
				ans = Math.max(ans, stairQueue2.poll());
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
