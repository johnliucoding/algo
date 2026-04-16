package semantic.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author liuxiaodong02
 */
public class Find {

    private static final Logger log = LoggerFactory.getLogger(Find.class);

    public static class Finder extends SimpleFileVisitor<Path> {
        private final PathMatcher matcher;
        private int numMatches;

        Finder(String pattern) {
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        }

        // compare the glob pattern against the file or directory name
        void find(Path file) {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                numMatches++;
                IO.println(file);
            }
        }
        // prints the total number of matches
        void done() {
            IO.println("Matches found: " + numMatches);
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            log.error("Error visiting file: {}", file, exc);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            find(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            find(dir);
            return FileVisitResult.CONTINUE;
        }
    }

    static void usage() {
        System.err.println("java Find <path> -glob <glob_pattern>");
        System.exit(-1);
    }

    static void main(String[] args) throws IOException {
        if(args.length < 2 || !args[1].equals("-glob")) {
            usage();
        }

        Path starting = Path.of(args[0]);
        String pattern = args[2];

        Finder finder = new Finder(pattern);
        Files.walkFileTree(starting, finder);
        finder.done();
    }
}
