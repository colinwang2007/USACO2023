import java.util.*;
import java.io.*;

public class USACO2023SILVER3 {
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

    static long[] diffs;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int months = nextInt();
        HashSet<Long> currMonth = new HashSet<>(months);
        long min = Long.MAX_VALUE;
        for (int i = 0; i < months; i++) {
            long curr = Long.parseLong(next());
            currMonth.add(curr);
            min=Math.min(min,curr);
        }
        if (currMonth.size() < 4) {
            long a = min / 4;
            long sum = (1+a)*a/2;
            out.println(sum);
            out.close();
            return;
        }

        diffs = new long[months];
        int idx = 0;
        for (long a : currMonth) {
            diffs[idx] = a - min;
            idx++;
        }
        Arrays.sort(diffs);
        int a = (int) Math.min(min / 4, diffs[3]);
        long sum = 6;
        int[] bad = new int[a + 1];
        for (int day = 4; day <= a ; day++) {
            if (bad[day]==0) {
                int isBad = 0;
                HashSet<Integer> remainders = new HashSet<>();
                for (long diff : diffs)
                {
                    remainders.add((int)(diff % day));
                    if (remainders.size() > 3) {
                        isBad= 1;
                    }
                }
                if (isBad==1) {
                    for (int nextL = day; nextL <= a; nextL += day) {
                        bad[nextL] = 1;
                    }
                } else {
                    sum += day;
                }
            }
        }


        out.println(sum);
        out.close();
    }
}

/*
public class USACO2023SILVER3
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(st.nextToken());
        HashSet<Long> set = new HashSet<>(N);
        long min = Long.MAX_VALUE;
        st = new StringTokenizer(input.readLine());
        for (int i = 1; i <= N; i++)
        {
            long a = Long.parseLong(st.nextToken());
            set.add(a);
            if (min > a) {
                min = a;
            }
        }


        if (set.size() < 4) {
            long K = min / 4;
            long sum = (1 + K) * K / 2;
            out.println(sum);
            out.close();
            input.close();
            return;
        }


        long first = min;
        long[] diffs = new long[set.size()];
        int idx = 0;
        for (long a : set)
        {
            diffs[idx] = a - first;
            idx++;
        }
        Arrays.sort(diffs);


        long diff4 = diffs[3];


        int K = (int)Math.min(first/4, diff4);


        long sum = 1 + 2 + 3;
        boolean[] bad = new boolean[K + 1]; // has >3 distinct remainders for a given [L]
        for (int L = 4; L <= K ; L++)
        {
            if (!bad[L]) {
                boolean isBad = hasMoreThan3Remainders(diffs, L);
                if (isBad) {
                    for (int nextL = L; nextL <= K; nextL += L)
                    {
                        // if given L, its distinct remainders > 3, then any multiple m*L is also > 3
                        // mark all multiples of L to be bad as well
                        bad[nextL] = true;
                    }
                } else {
                    sum += L;
                }
            }
        }


        out.println(sum);
        input.close();
        out.close();
    }


    private static boolean hasMoreThan3Remainders(long[] diffs, int L)
    {
        HashSet<Integer> remainders = new HashSet<>();
        for (long diff : diffs)
        {
            remainders.add((int)(diff % L));
            if (remainders.size() > 3) {
                return true;
            }
        }
        return false;
    }
}

*/