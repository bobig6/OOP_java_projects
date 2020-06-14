import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        try {
            InputStream peaksInputStream = Files.newInputStream(
                    Path.of("vgsales.csv")
            );
            var videoGamesExplorer = new VideoGamesExplorer(peaksInputStream);
            System.out.println(videoGamesExplorer.findNameOfLeastSoldVideoGameInJapan());
            System.out.println(videoGamesExplorer.findNameOfVideoGameWithLargestSaleDifference());
            System.out.println(videoGamesExplorer.findNamesOfTopNVideoGamesBySalesInEurope(10).get(0));
            System.out.println(videoGamesExplorer.findAverageSoldCopiesByPublisher("Nintendo"));
            System.out.println(videoGamesExplorer.findRankOfMostSoldVideoGameByPlatformAndGenre("Wii", "Sports"));
            System.out.println(videoGamesExplorer.findPublisherWithMostVideoGamesInYear(2006));


        } catch (IOException exception) {
            System.out.println("Could not load video game dataset.");
        }


    }
}
