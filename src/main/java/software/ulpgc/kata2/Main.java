package software.ulpgc.kata2;

import javax.imageio.IIOException;
import java.io.*;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    static void main() throws IOException {

        String rawData = "Name\tYear\tDirector\tDuration\n" +
                "Matrix\t1999\tWachowski\t136\n" +
                "Interstellar\t2014\tNolan\t169\n" +
                "Inception\t2010\tNolan\t148";

        try (InputStream is = new ByteArrayInputStream(rawData.getBytes())) {
            List<Movie> movies = toMovies(is);

            Map<String, Long> summary = movies.stream()
                    .collect(Collectors.groupingBy(Movie::director, Collectors.counting()));

            writeSummary(summary, System.out);
        }
    }

    private static void writeSummary(Map<String, Long> summary, OutputStream os) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
            writer.write("--- MOVIE SUMMARY (MOVIES PER DIRECTOR) ---\n");
            for (Map.Entry<String, Long> entry : summary.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + " movie(s)\n");
            }
            writer.flush();
        }
    }

    private static List<Movie> toMovies(InputStream is) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            List<String> strings = reader.readAllLines();
            List<Movie> result = new ArrayList<>();
            for (int i=1; i<strings.size(); i++){
                String line = strings.get(i).trim();
                if  (line.isEmpty()) continue;
                result.add(toMovie(line));

            }
            return result;
        }


    }

    private static Movie toMovie(String string) {
        String[] fields = string.split("\\s{2,}|\t");
        return toMovie(fields);
    }

    private static Movie toMovie(String[] fields) {
        return new Movie(fields[0], toInt(fields[1]), fields[2], toInt(fields[3]));
    }

    private static int toInt(String field) {
        return Integer.parseInt(field.trim());
    }
}
