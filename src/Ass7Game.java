import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import megirai.game.HighScoresTable;
import megirai.game.AnimationRunner;
import megirai.game.MenuAnimation;
import megirai.game.LevelSpecificationReader;
import megirai.game.GameFlow;
import megirai.game.ScoreInfo;
import megirai.game.HighScoresAnimation;
import megirai.game.KeyPressStoppableAnimation;
import megirai.interfaces.LevelInformation;
import megirai.interfaces.Menu;
import megirai.interfaces.Task;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Ass7Game - run the program.
 */
public class Ass7Game {
    /**
     * Main function - play the game.
     *
     * @param args - gets inputs from the user.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        KeyboardSensor ks = gui.getKeyboardSensor();

        //high scores
        HighScoresTable highScoresTable = new HighScoresTable(5);

        //create a file
        File fileName = new File("highscores.txt");
        try {
            if (!fileName.createNewFile()) {
                highScoresTable = HighScoresTable.loadFromFile(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        String path;
        if (args.length == 1) {
            path = args[0];
        } else {
            path = "level_sets.txt";
            //path = "resources/level_sets.txt";
        }

        Map<String, String> levelSetMap = new LinkedHashMap<>();
        LineNumberReader levelSet;
        int size = 1;
        Menu<Task<Void>> subMenu = new MenuAnimation<>("Arkanoid", ks, ar);
        try {
            levelSet = new LineNumberReader(new InputStreamReader(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(path)), 2);
            //levelSet = new LineNumberReader(new FileReader(new File(path)), 2);
            String line;
            while ((line = levelSet.readLine()) != null) {
                levelSetMap.put(line, levelSet.readLine());
            }

            for (String num : levelSetMap.keySet()) {
                System.out.println("lala" + num + levelSetMap.get(num));
                HighScoresTable finalHighScoresTable = highScoresTable;
                subMenu.addSelection(num.split(":")[0], num, new Task<Void>() {
                    @Override
                    public Void run() {

                        LevelSpecificationReader reader = new LevelSpecificationReader();
                        List<LevelInformation> levelInformations = new LinkedList<>();
                        try {
                            /*levelInformations = reader.fromReader(new FileReader(
                                    new File("resources/" + levelSetMap.get(num))));*/
                            levelInformations = reader.fromReader(new InputStreamReader(
                                    ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetMap.get(num))));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        GameFlow game = new GameFlow(ar, ks);
                        System.out.println("Size = " + levelInformations.size());
                        game.runLevels(levelInformations);
                        if (finalHighScoresTable.getRank(game.getScore().getValue())
                                < finalHighScoresTable.size()) {
                            DialogManager dialog = gui.getDialogManager();
                            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                            finalHighScoresTable.add(new ScoreInfo(name, game.getScore().getValue()));
                            try {
                                finalHighScoresTable.save(fileName);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        ar.run(new KeyPressStoppableAnimation(ks, "space",
                                new HighScoresAnimation(finalHighScoresTable)));
                        return null;
                    }
                });

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* LevelSpecificationReader reader = new LevelSpecificationReader();
        List<LevelInformation> levelInformations = new LinkedList<>();
        try {
            levelInformations = reader.fromReader(new FileReader(new File("definitions/level_definitions.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //run the game
        GameFlow game = new GameFlow(ar, ks);
        Menu<Task<Void>> menu = new MenuAnimation<>("Arkanoid", ks, ar);
        HighScoresTable finalHighScoresTable = highScoresTable;
        //List<LevelInformation> finalLevelInformations = levelInformations;
        ((MenuAnimation<Task<Void>>) menu).addSubMenu("s", "(s)Start Game.", subMenu);
          /*      menu.addSelection("s", "(s)Start Game.", new Task<Void>() {
            @Override
            public Void run() {
                GameFlow game = new GameFlow(ar, ks);
                System.out.println("Size = " + finalLevelInformations.size());
                game.runLevels(finalLevelInformations);
                if(finalHighScoresTable.getRank(game.getScore().getValue()) < finalHighScoresTable.size()){
                    DialogManager dialog = gui.getDialogManager();
                    String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                    finalHighScoresTable.add(new ScoreInfo(name,game.getScore().getValue()));
                    try {
                        finalHighScoresTable.save(fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                ar.run(new KeyPressStoppableAnimation(ks,"space", new HighScoresAnimation(finalHighScoresTable)));
                return null;
            }
        });*/
        menu.addSelection("h", "(h)High Scores.", new Task<Void>() {
            @Override
            public Void run() {
                ar.run(new KeyPressStoppableAnimation(ks, "space", new HighScoresAnimation(finalHighScoresTable)));
                return null;
            }
        });
        menu.addSelection("q", "(q)Quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        });

        while (!menu.shouldStop()) {
            ar.run(menu);
            menu.getStatus().run();
            ((MenuAnimation) menu).dontStop();
            ((MenuAnimation<Task<Void>>) subMenu).dontStop();
        }

    }
}
