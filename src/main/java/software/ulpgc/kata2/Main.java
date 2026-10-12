package software.ulpgc.kata2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/movies.tsv")))) {
            List<Movie> movies = toMovies(reader.readAllLines());
            for (Movie movie : movies) {
                System.out.println(movie);
            }

        }
    }

    private static List<Movie> toMovies(List<String> strings) {
        List<Movie> result = new ArrayList<>();
        for (int i = 1; i < strings.size(); i++) {
            String line = strings.get(i).trim();
            if (line.isEmpty()) continue;
            result.add(toMovie(line));
        }
            return result;
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
