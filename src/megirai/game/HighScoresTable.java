package megirai.game;

import java.io.Serializable;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HighScoresTable class implements Serializable.
 */
public class HighScoresTable implements Serializable {

    private static HighScoresTable highScoresTable;
    private List<ScoreInfo> highScores;
    private static int size;

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size - gets the size top scores.
     */
    public HighScoresTable(int size) {
        this.highScores = new ArrayList<>();
        this.size = size;
    }

    /**
     * Add a high-score.
     *
     * @param score - gets the score.
     */
    public void add(ScoreInfo score) {
        if (highScores.size() <= size) {
            this.highScores.add(score);
            for (int i = highScores.size() - 1; i >= 0; i--) {
                if (score.getScore() > highScores.get(i).getScore() && highScores.indexOf(score) > i) {
                    Collections.swap(highScores, i, highScores.indexOf(score));
                }
            }
            if (highScores.size() == size + 1) {
                highScores.remove(highScores.get(size));
            }
        }
    }

    /**
     * Return table size.
     *
     * @return the table size.
     */
    public int size() {
        return this.size;
    }

    /**
     * Return the current high scores.
     * The list is sorted such that the highest scores come first.
     *
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * Return the rank of the current score: where will it
     * be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score - gets the player score.
     * @return the rank of the current score.
     */
    public int getRank(int score) {
        int i = 0;
        while (i < highScores.size()) {
            if (highScores.get(i).getScore() < score) {
                System.out.println(i + 1);
                return i;
            }
            i++;
        }
        if (i == this.size()) {
            return size + 1;
        } else {
            return i;
        }
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.highScores.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename - gets the high scores file.
     * @throws IOException - if the loading failed.
     */
    public void load(File filename) throws IOException {
        this.clear();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        try {
            this.highScores = (ArrayList<ScoreInfo>) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        objectInputStream.close();
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename - gets the high scores file.
     * @throws IOException - if the loading failed.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
        objectOutputStream.writeObject(this.getHighScores());
        objectOutputStream.close();

    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename - gets the high scores file.
     * @return an HighScoresTable.
     */
    public static HighScoresTable loadFromFile(File filename) {
        highScoresTable = new HighScoresTable(size);
        try {
            if (filename.length() != 0) {
                highScoresTable.load(filename);
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return highScoresTable;
    }
}

