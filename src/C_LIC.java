import java.io.*;
import java.nio.file.Files;
import java.util.StringTokenizer;

/**
 * @author mbpdslab01
 * 14:19 6/12/23
 */
public class C_LIC {

    /**
     * Ensure: IS_LOCAL = false before submit
     */
    static boolean IS_LOCAL = false;
    static boolean DEBUG = IS_LOCAL & true;
    static String INPUT_FILE = "input/C_LIC.inp";

    public static void main(String[] args) {
        try {
            InputStream is = IS_LOCAL ? Files.newInputStream(new File(INPUT_FILE).toPath()) : System.in;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            InputReader ir = new InputReader(is);

            // Process here!
            int n = ir.nextInt(), m = ir.nextInt();
            int[] a = new int[n];
            int[] b = new int[m];
            for (int i = 0; i < n; i++) {
                a[i] = ir.nextInt();
            }

            for (int i = 0; i < m; i++) {
                b[i] = ir.nextInt();
            }

            int[] lcis = new int[m];

            for (int i = 0; i < n; i++) {
                int len = 0;

                for (int j = 0; j < m; j++) {
                    if (a[i] > b[j]) {
                        if (lcis[j] > len) {
                            len = lcis[j];
                        }
                    } else if (a[i] == b[j]) {
                        if (len + 1 > lcis[j]) {
                            lcis[j] = len + 1;
                        }
                    }
                }
            }

            int ans = 0;
            for (int i = 0; i < m; i++) {
                if (lcis[i] > ans) {
                    ans = lcis[i];
                }
            }

            System.out.println(ans);

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
