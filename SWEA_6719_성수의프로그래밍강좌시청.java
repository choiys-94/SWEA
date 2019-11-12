package swea;
import java.util.Arrays;
import java.util.Scanner;
 
public class SWEA_6719_성수의프로그래밍강좌시청 {
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
 
        for (int T = 1; T <= tc; T++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[] arr = new int[N];
            for(int i=0; i<N; i++) {
                arr[i] = sc.nextInt();
            }
             
            Arrays.sort(arr);
            double result = 0;
            for(int i=0; i<K; i++) {
                result = (result + arr[N-K+i])/2;
            }
             
            System.out.printf("#" + T + " %.6f\n", result);
        }
    }
}
