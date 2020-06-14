
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class VideoGamesExplorer {
    /**
     * Loads the dataset from the given {@code dataInput} stream.
     */
    private final List<VideoGame> videoGames;


    public VideoGamesExplorer(InputStream dataInput) {
        try (
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(dataInput))
        ) {
            reader.readLine();
            videoGames = reader.lines()
                    .map(VideoGame::createVideoGame)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new IllegalArgumentException(
                    "Could not load dataset",
                    exception
            );
        }
    }

    /**
     * Returns all video games loaded from the dataset.
     **/
    public Collection<VideoGame> getVideoGames() {
        return Collections.unmodifiableList(videoGames);
    }

    public String findNameOfLeastSoldVideoGameInJapan(){
        return videoGames.stream()
                .min(Comparator.comparingInt(VideoGame::getJpSales))
                .get()
                .getName();
    }

    public String findNameOfVideoGameWithLargestSaleDifference(){
        return videoGames.stream()
                .max(Comparator.comparingInt(
                        videoGame->
                                (videoGame.getNaSales() + videoGame.getJpSales() + videoGame.getEuSales())
                                        - videoGame.getOtherSales()
                    )
                )
                .get()
                .getName();
    }

    public List<String> findNamesOfTopNVideoGamesBySalesInEurope(int n){
        return videoGames.stream()
                .sorted(Comparator.comparingInt(VideoGame::getEuSales).reversed())
                .limit(n)
                .map(VideoGame::getName)
                .collect(Collectors.toList());
    }

    public double findAverageSoldCopiesByPublisher(String publisher){
        return videoGames.stream()
                .filter(videoGame -> videoGame.getPublisher().equals(publisher))
                .mapToInt(VideoGame::getGlobalSales)
                .average()
                .orElse(0);
    }

    public int findRankOfMostSoldVideoGameByPlatformAndGenre(String platform, String genre){
        if(videoGames.stream()
                .map(VideoGame::getPlatform)
                .noneMatch(platform::equals)
                || videoGames.stream()
                        .map(VideoGame::getGenre)
                        .noneMatch(genre::equals)
        ){
            throw new IllegalArgumentException();
        }

        return videoGames.stream()
                .filter(videoGame -> videoGame.getPlatform().equals(platform))
                .filter(videoGame -> videoGame.getGenre().equals(genre))
                .max(Comparator.comparingInt(VideoGame::getNaSales))
                .orElseThrow(IllegalArgumentException::new)
                .getRank();


    }


    public String findPublisherWithMostVideoGamesInYear(int year){
        Map<String, Long> gamesCountByPublisher = videoGames.stream()
                .filter(videoGame -> videoGame.getReleaseYear() == year)
                .collect(
                        Collectors.groupingBy(
                                VideoGame::getPublisher,
                                Collectors.counting()
                        )
                );
        return gamesCountByPublisher.entrySet().stream()
                .filter(games-> !games.getKey().equals("Unknown") && !games.getKey().equals("N/A"))
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .orElseThrow(IllegalArgumentException::new)
                .getKey();
    }


}
