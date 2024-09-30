import java.util.*;
import java.io.*;

public class USACO2023SILVER2 {
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

    static int ans = 0;
    static int[] leafs;
    static int traversals = 0;
    static Queue<Integer>[] adj;
    static int[] convert;
    static int[] potions;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = nextInt();
        convert = new int[n+1];
        int[] time = new int[n+1];
        Arrays.fill(convert,n+2);
        for(int i=1; i<=n; i++){
            int temp = nextInt();
            convert[temp]=Math.min(convert[temp],i);
            time[i]=temp;
        }
        /*
        5
        5 4 3 2 1
        1 2 3 4 5
        traversals=3
         */

        adj = new LinkedList[n+1];
        for(int i=1; i<=n; i++){
            adj[i]=new LinkedList<>();
        }
        for(int i=1; i<n; i++){
            int a = nextInt();
            int b = nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        leafs = new int[n+1];
        for(int i=2; i<=n; i++){
            if(adj[i].size()==1){
                traversals++;
                leafs[i]=1;  //array showing which indexes are leafs
            }
        }
        potions = new int[n+1];

        for(int i=1; i<=traversals; i++){
            potions[time[i]]++;
        }
        search(-1,1);
        out.print(ans);
        out.close();
    }
    static int search(int prev, int idx){
        if(leafs[idx]==1){//if leaf
            if(convert[idx]<=traversals){//if its within the time
                ans++;
                return 0;//picked up potion, parent nodes cannot pick up anymore
            }
            return 1;//other nodes still able to pick up

        }
        else{
            int howMuchRoom = 0;
            while(!adj[idx].isEmpty()){
                int currStem = adj[idx].remove();//access each stem
                if(currStem!=prev){//can't go backwards
                    howMuchRoom+=search(idx,currStem);//go to child nodes, ask for the amount of room
                }
            }
            while(howMuchRoom>0&&potions[idx]>0) {
                ans++;
                howMuchRoom--;
                potions[idx]--;
            }
            return howMuchRoom;
        }
    }
}
