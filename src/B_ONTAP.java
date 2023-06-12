import java.io.*;
import java.nio.file.Files;
import java.util.StringTokenizer;

/**
 * @author mbpdslab01
 * 14:09 6/12/23
 */
public class B_ONTAP {

    /**
     * Ensure: IS_LOCAL = false before submit
     */
    static boolean IS_LOCAL = false;
    static boolean DEBUG = IS_LOCAL & false;
    static String INPUT_FILE = "input/B_ONTAP.inp";

    public static void main(String[] args) {
        try {
            InputStream is = IS_LOCAL ? Files.newInputStream(new File(INPUT_FILE).toPath()) : System.in;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            InputReader ir = new InputReader(is);

            // Process here!
            int[] l, h;
            int d, s;
            d = ir.nextInt();
            s = ir.nextInt();

            l = new int[d];
            h = new int[d];

            int sumL = 0, sumR = 0;
            for (int i = 0; i < d; i++) {
                l[i] = ir.nextInt();
                h[i] = ir.nextInt();
                sumL += l[i];
                sumR += h[i];
            }
            boolean isOK = s >= sumL && s <= sumR;
            int[] res = new int[d];
            if (isOK) {
                bw.write("YES\n");
                int remain = s;
                for (int i = 0; i < d; i++) {
                    res[i] = l[i];
                    remain -= res[i];
                }
                debug("After", remain);
                for (int i = 0; i < d && remain > 0; ++i) {
                    int more = Math.min(h[i] - l[i], remain);
                    debug(i, more, remain);
                    res[i] += more;
                    remain -= more;
                }

                for (int i = 0; i < d; i++) {
                    bw.write(res[i] + " ");
                }
                bw.write("\n");
            } else {
                bw.write("NO\n");
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
