package semantic.file;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author liuxiaodong02
 */
public class Grep {

    // Charset and decoder for ISO-8859-15
    private static final Charset charset = StandardCharsets.UTF_8;
    private static final CharsetDecoder decoder = charset.newDecoder();

    // Pattern used to parse lines
    private static final Pattern linePattern = Pattern.compile(".*\r?\n");

    // The input pattern that we're looking for
    private static Pattern pattern;

    // Compile the pattern from the command line
    private static void compile(String pat) {
        try {
            pattern = Pattern.compile(pat);
        } catch (PatternSyntaxException x) {
            System.err.println(x.getMessage());
            System.exit(1);
        }
    }

    // Use the linePattern to break the given CharBuffer into lines, applying
    // the input pattern to each line to see if we have a match
    private static void grep(Path path, CharBuffer cb) {
        Matcher lm = linePattern.matcher(cb); // Line matcher
        Matcher pm = null; // Pattern matcher
        int lines = 0;
        while (lm.find()) {
            lines++;
            CharSequence cs = lm.group(); // The current line
            if (pm == null)
                pm = pattern.matcher(cs);
            else
                pm.reset(cs);
            if (pm.find())
                System.out.print(path + ":" + lines + ":" + cs);
            if (lm.end() == cb.limit())
                break;
        }
    }

    // Search for occurrences of the input pattern in the given file
    private static void grep(Path path) throws IOException {

        // Open the file and then get a channel from the stream
        try (FileChannel fc = FileChannel.open(path, StandardOpenOption.READ)) {

            // Get the file's size and then map it into memory
            int sz = (int) fc.size();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, sz);

            // Decode the file into a char buffer
            CharBuffer cb = decoder.decode(bb);

            // Perform the search
            grep(path, cb);
        }
    }

    static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Grep pattern file...");
            return;
        }
        compile(args[0]);
        for (int i = 1; i < args.length; i++) {
            Path path = Path.of(args[i]);
            try {
                grep(path);
            } catch (IOException x) {
                System.err.println(path + ": " + x);
            }
        }
    }
}