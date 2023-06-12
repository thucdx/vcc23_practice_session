import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * @author mbpdslab01
 * 14:35 6/12/23
 */
public class E_VC {

    /**
     * Ensure: IS_LOCAL = false before submit
     */
    static boolean IS_LOCAL = false;
    static boolean DEBUG = IS_LOCAL & true;
    static String INPUT_FILE = "input/E_VC.inp";

    static class Edge {
        int u, v;
        int d;

        public Edge(int u, int v, int d) {
            this.u = u;
            this.v = v;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", v=" + v +
                    ", d=" + d +
                    '}';
        }
    }

    static class Node implements Comparable<Node> {
        int totalDist;
        int ver;

        @Override
        public int compareTo(Node o) {
            return Integer.compare(totalDist, o.totalDist);
        }

        public Node(int totalDist, int ver) {
            this.totalDist = totalDist;
            this.ver = ver;
        }
    }

    static class Graph {
        List<Edge>[] adj;
        static int INF = (int) 1e9;

        public Graph(List<Edge>[] adj) {
            this.adj = adj;
        }

        int[] getMinDist(int startPoint) {
            int[] res = new int[adj.length] ;
            Arrays.fill(res, INF);

            PriorityQueue<Node> q = new PriorityQueue<>();
            res[startPoint] = 0;
            q.add(new Node(0, startPoint));
            while (!q.isEmpty()) {
                Node cur = q.poll();
                int curVer = cur.ver, curDist = cur.totalDist;

                if (curDist > res[curVer])
                    continue;

                for (Edge nxt: adj[curVer]) {
                    if (res[nxt.v] > curDist + nxt.d) {
                        res[nxt.v] = curDist + nxt.d;
                        q.add(new Node(res[nxt.v], nxt.v));
                    }
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        try {
            InputStream is = IS_LOCAL ? Files.newInputStream(new File(INPUT_FILE).toPath()) : System.in;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            InputReader ir = new InputReader(is);

            // Process here!
            int n = ir.nextInt(), m = ir.nextInt(), k = ir.nextInt();
            int x = ir.nextInt() - 1, y = ir.nextInt() - 1;
            int[] p = new int[k];
            for (int i = 0; i < k; i++) {
                p[i] = ir.nextInt() - 1;
            }
            List<Edge>[] adj = new List[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                int u = ir.nextInt() - 1;
                int v = ir.nextInt() - 1;
                int d = ir.nextInt();
                adj[u].add(new Edge(u, v, d));
                adj[v].add(new Edge(v, u, d));
            }

            Graph g = new Graph(adj);
            int[] minX = g.getMinDist(x);
            int[] minY = g.getMinDist(y);
            int sol = (int) 1e9 + 10;
            for (int i = 0; i < k; i++) {
                int total = minX[p[i]] + minY[p[i]];
                if (total < sol) {
                    sol = total;
                }
            }

            bw.write(sol + "\n");
            bw.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    static void debug(Object... args) {
        if (DEBUG) {
            for (int i = 0; i < args.length; ++i) {
                System.out.print(args[i] + " ");
            }
            System.out.println();
        }
    }
}
