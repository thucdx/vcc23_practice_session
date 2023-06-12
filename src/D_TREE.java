import java.io.*;
import java.nio.file.Files;
import java.util.StringTokenizer;

/**
 * @author mbpdslab01
 * 15:31 6/12/23
 */
public class D_TREE {

    /**
     * Ensure: IS_LOCAL = false before submit
     */
    static boolean IS_LOCAL = false;
    static boolean DEBUG = IS_LOCAL & true;
    static String INPUT_FILE = "input/D_Tree.inp";

    public static void main(String[] args) {
        try {
            InputStream is = IS_LOCAL ? Files.newInputStream(new File(INPUT_FILE).toPath()) : System.in;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            InputReader ir = new InputReader(is);

            // Process here!
            int ntest = ir.nextInt();
            for (int i = 0; i < ntest; i++) {
                int n = ir.nextInt(), m = ir.nextInt(), k = ir.nextInt();

                int maxDeg[] = new int[n];
                for (int j = 0; j < n; j++) {
                    maxDeg[j] = n-1;
                }

                for (int j = 0; j < m; j++) {
                    int u = ir.nextInt() - 1, v = ir.nextInt() - 1;
                    maxDeg[u]--;
                    maxDeg[v]--;
                }

                boolean isOK = maxDeg[0] >= k;
                for (int t = 1; t < n; t++) {
                    if (maxDeg[t] < 0) {
                        isOK = false;
                        break;
                    }
                }
                bw.write(isOK ? "Yes " : "No ");


            }

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

