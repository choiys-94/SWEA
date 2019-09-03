package swea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class _모의_1952_수영장 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int T = 1; T <= tc; T++) {
			int[] tickets = new int[4]; // 1일, 1개월, 3개월, 1년
			for(int i=0; i<4; i++) {
				tickets[i] = sc.nextInt();
			}
			int[] origin_plan = new int[12]; // 12개월 계
			for(int i=0; i<12; i++) {
				origin_plan[i] = sc.nextInt();
			}
			int min = tickets[3];	// 1년치를 최대값으로 둠
			
			//1. 올3개월.
			//2. 3개월- 부분1개월
			//3. 3개월- 부분1개월 - 부분1일
			//4. 올1개월
			//5. 부분1개월-부분1일
			//6. 올1일
			
			int[] plan = new int[12];
			
			//1. 올3개월
			int money = 0;
			System.arraycopy(origin_plan, 0, plan, 0, origin_plan.length);
			for(int i=0; i<12; i++) {
				if(plan[i] != 0) {
					plan[i] = 0;
					if(check(i+1) && plan[i+1] != 0) {
						i++;
						plan[i+1] = 0;
					}
						
					if(check(i+2) && plan[i+2] != 0) {
						plan[i+2] = 0;
						i++;
					}
					money += tickets[2];
				}
			}
			min = Math.min(min, money);
			
			//2. 3개월 - 부분 1개월
			money = 0;
			System.arraycopy(origin_plan, 0, plan, 0, origin_plan.length);
			for(int i=0; i<12; i++) {
				if(plan[i] != 0 && check(i+1) && check(i+2) && 3*tickets[1] > tickets[2]) {
					plan[i] = 0;
					plan[i+1] = 0;
					plan[i+2] = 0;
					i += 3;
					money += tickets[2];
				}
				else if(plan[i] != 0 && check(i+1) && check(i+2) && 3*tickets[1] <= tickets[2]){
					plan[i] = 0;
					money += tickets[1];
				}
			}
			min = Math.min(min, money);
			
			//3. 3개월 - 부분 1개월 - 부분 1일
			money = 0;
			System.arraycopy(origin_plan, 0, plan, 0, origin_plan.length);
			for(int i=0; i<12; i++) {
				if(plan[i] != 0) {
					if(check(i+1) && plan[i+1] != 0 && check(i+2) && plan[i+2] != 0) {
						if(3*tickets[1] > tickets[2]) {
							i+=2;
							plan[i+1] = 0;
							plan[i+2] = 0;
						}
						else {
							money += tickets[1]*3;
						}
					}
					else if(check(i+1) && plan[i+1] != 0) {
						if(2*tickets[1] > tickets[2]) {
							i++;
							plan[i+1] = 0;
						}
						else {
							money += tickets[1]*2;
						}
					}
					else if(tickets[1] > tickets[2]){
						money += tickets[2];
					}
					else {
						money += tickets[1];
					}
					plan[i] = 0;
				}
				else if(plan[i] != 0 && tickets[0] * plan[i] > tickets[1]) {
					money += tickets[1];
					plan[i] = 0;
				}
				else if(plan[i] != 0 && tickets[0] * plan[i] <= tickets[1]) {
					money += tickets[0] * plan[i];
					plan[i] = 0;
				}
			}
			min = Math.min(min, money);
			
			//4. 올1개월
			money = 0;
			System.arraycopy(origin_plan, 0, plan, 0, origin_plan.length);
			for(int i=0; i<12; i++) {
				if(plan[i] != 0) {
					plan[i] = 0;
					money += tickets[1];
				}
			}
			min = Math.min(min, money);
			
			//5. 부분1개월-부분1일
			money = 0;
			System.arraycopy(origin_plan, 0, plan, 0, origin_plan.length);
			for(int i=0; i<12; i++) {
				if(plan[i] != 0 && plan[i]*tickets[0] > tickets[1]) {
					plan[i] = 0;
					money += tickets[1];
				}
				else if(plan[i] != 0 && plan[i]*tickets[0] <= tickets[1]) {
					money += tickets[0]*plan[i];
					plan[i] = 0;
				}
			}
			min = Math.min(min, money);
			
			//6. 올1일
			money = 0;
			System.arraycopy(origin_plan, 0, plan, 0, origin_plan.length);
			for(int i=0; i<12; i++) {
				if(plan[i] != 0) {
					money += tickets[0] * plan[i];
					plan[i] = 0;
				}
			}
			min = Math.min(min, money);
			
			System.out.println("#" + T + " " + min);
		}
	}
	
	static boolean check(int x) {
		return x<12;
	}
}
