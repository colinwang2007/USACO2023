import java.util.*;
import java.io.*;

public class USACOSILVER1REDO {
    static StringTokenizer st;
    static BufferedReader br;
    static PrintWriter out;

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int aa = nextInt();
        for (int ii = 0; ii < aa; ii++) {
            int[] ans = compute();
            if (ans[0]==-1) {
                out.println(-1);
            }
            else{
                for (int i=1; i<ans.length-1; i++) {
                    if (ans[i]==0) out.print(1 + " ");
                    else out.print(ans[i] + " ");
                }
                if (ans[ans.length-1]==0) out.println(1);
                else out.println(ans[ans.length-1]);
            }
        }
        out.close();
    }

    static int[] compute() throws IOException {
        int cows = nextInt();
        int queries = nextInt();
        int max = nextInt();
        int[] cowLine = new int[cows + 1];
        int[] zero = new int[cows + 1];
        int[] maxArr = new int[cows + 1];
        for (int i = 1; i <= cows; i++) {
            cowLine[i] = nextInt();
            if (cowLine[i] == 0) {
                zero[i] = 1;
                cowLine[i] = 1;
            }
            maxArr[i] = Math.max(maxArr[i - 1], cowLine[i]);
        }
        int[] minForCow = new int[cows + 1];
        for (int i = 0; i < queries; i++) {
            int l = nextInt();
            int r = nextInt();
            if (minForCow[r] == 0) {
                minForCow[r] = l;
            } else {
                minForCow[r] = Math.min(minForCow[r], l);
            }
        }
        int[] bad = {-1};
        for (int i = 1; i <= cows; i++) {
            if (minForCow[i]!=0) {
                int max1a = cowLine[1];
                for (int j = 2; j <= minForCow[i]; j++) {
                    max1a = Math.max(cowLine[j], max1a);
                }
                int maxPrev = cowLine[1];
                for (int j = 2; j <= i - 1; j++) {
                    maxPrev = Math.max(cowLine[j], maxPrev);
                }
                if(maxPrev+1>max)  return bad;
                if (zero[i]>0) {
                    cowLine[i] = maxPrev + 1;
                }
                else if (cowLine[i]<=maxPrev)  return bad;
                if (max1a<maxPrev) {
                    for (int j = minForCow[i]; j >= 1; j--) {
                        if (zero[j]>0) {
                            cowLine[j] = maxPrev;
                            break;
                        }
                        if(j==1) return bad;
                    }
                }
            }
        }
        for (int i = 1; i <= cows; i++) {
            maxArr[i]=Math.max(maxArr[i-1],cowLine[i]);
        }
        for (int i = 1; i <= cows; i++) {//Go through each case
            if (minForCow[i]!=0) {
                if (cowLine[i]<=maxArr[i-1]||maxArr[minForCow[i]]<maxArr[i-1]) return bad;
            }
        }
        return cowLine;
    }

}
