package track;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

class App{
  public static void main(String[] args){
    try{
      Scanner scan = new Scanner(System.in);
      int n = scan.nextInt();
      assert 1<=n&&n<=13;
      int m = scan.nextInt();
      assert 1<=m&&m<=20;
      int x = scan.nextInt();
      assert 1<=x&&x<=1000;
      int y = scan.nextInt();
      assert 1<=y&&y<=1000;
      int[] a = new int[n];
      int[] b = new int[m];
      
      for(int i=0;i<n;++i){a[i]=scan.nextInt();assert 1<=a[i]&&a[i]<=1000;}
      for(int i=0;i<m;++i){b[i]=scan.nextInt();assert 1<=b[i]&&b[i]<=1000;}

      int[][] dp = new int[m+1][(1<<n)];
      int[] sum = new int[(1<<n)];
      int[] cnt = new int[(1<<n)];
      
      for(int i=0;i<(1<<n);++i){
        for(int j=0;j<n;++j){
          if(((i>>j)&1)==1){
            sum[i]+=a[j];
            cnt[i]++;
          }
        }
      }

      for(int[]arr : dp)Arrays.fill(arr, Integer.MAX_VALUE/10);
      Arrays.fill(dp[0], 0);
      
      for(int i=0;i<m;++i){
        for(int j=0;j<(1<<n);++j){
          dp[i+1][j] = dp[i][j] + x*b[i];
          for(int k=(1<<n)-1;k>=0;--k){
            k&=j;
            if(sum[k]>=b[i])dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j^k] + y*(cnt[k]-1));
          }
        }
      }

      System.out.println(dp[m][(1<<n)-1]);
    }catch(Exception e){}
  }
}
