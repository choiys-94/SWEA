package swea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class D4_3459_승자예측하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int T = 1; T <= tc; T++) {
			long in = sc.nextLong();
			
			System.out.print("#" + T + " ");
			long sum = 1;
			if(in == 1) {
				System.out.println("BoB");
			}
			else {
				long token = 1;
				boolean flag = false;
				while(true) {
					for(int i=0; i<2; i++) {
						sum += Math.pow(4, token);
						if(in <= sum) {
							if(i == 0) {
								System.out.println("Alice");
								flag = true;
								break;
							}
							else {
								System.out.println("BoB");
								flag = true;
								break;
							}
						}
					}
					if(flag)
						break;
					token++;
				}
			}
		}
	}
}