import java.io.*;
import java.nio.file.Files;
import java.util.StringTokenizer;

/**
 * @author mbpdslab01
 * 14:04 6/12/23
 */
public class A_CAM {

    /**
     * Ensure: IS_LOCAL = false before submit
     */
    static boolean IS_LOCAL = false;
    static boolean DEBUG = IS_LOCAL & true;
    static String INPUT_FILE = "input/A_CAM.inp";

    public static void main(String[] args) {
        try {
            InputStream is = IS_LOCAL ? Files.newInputStream(new File(INPUT_FILE).toPath()) : System.in;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            InputReader ir = new InputReader(is);

            // Process here!
            int n = ir.nextInt();
            boolean isOK = true;
            if (n % 2 == 1) {
                isOK = false;
            } else {
                // n % 2 == 0;
                // Sum of even number >= 2
                isOK = n > 2;
            }

            bw.write(isOK ? "YES" : "NO");

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
