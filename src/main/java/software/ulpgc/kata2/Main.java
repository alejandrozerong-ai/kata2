package software.ulpgc.kata2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    static void main() throws IOException {

        String rawData = "Name:Year:Director:Duration\n" +
                "El Padrino:1972:Coppola:132\n" +
                "Avatar:2011:Cameron:182\n" +
                "La Odisea:2026:Nolan:178\n" +
                "Other Mommy:2026:Savage:94\n" +
                "Resident Evil:2026:Cregger:100\n" +
                "Planet of the Apes:2014:Reeves:131\n" +
                "Spiderman:2002:Raimi:121\n" +
                "Interstellar:2014:Nolan:169";

        try (InputStream is = new ByteArrayInputStream(rawData.getBytes())) {
            List<Movie> movies = toMovies(is);

            Map<String, Long> summary = movies.stream()
                    .collect(Collectors.groupingBy(Movie::director, Collectors.counting()));

            writeSummary(summary, System.out);

            }

        }

    private static void writeSummary(Map<String, Long> summary, OutputStream os) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {

            for (Map.Entry<String, Long> entry : summary.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "movie(s)\n");
            }
            writer.flush();
        }
    }


    private static List<Movie> toMovies(InputStream is) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            List<String> strings = reader.readAllLines();
            List<Movie> result = new ArrayList<>();
            for (int i = 1; i < strings.size(); i++) {
                String line = strings.get(i).trim();
                if (line.isEmpty()) continue;
                result.add(toMovie(line));

            }
            return result;
        }

        }


    private static Movie toMovie(String string) {
        String[] fields = string.split(":");
        return toMovie(fields);
    }

    private static Movie toMovie(String[] fields) {
        return new Movie(fields[0], toInt(fields[1]), fields[2], toInt(fields[3]));
    }

    private static int toInt(String field) {
        return Integer.parseInt(field.trim());
    }

}
