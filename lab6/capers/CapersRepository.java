package capers;

import java.io.File;
import java.io.IOException;

import static capers.Utils.*;

/** A repository for Capers 
 * @author Irene Jiaxin Fan
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all the persistent data for dogs
 *    - story -- file containing the current story
 *
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(CWD, ".capers");

    /**
     * Does the required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     *
     */
    public static void setupPersistence() {
        if (!CAPERS_FOLDER.exists()) {
            CAPERS_FOLDER.mkdir();
        }
        if (!Dog.DOG_FOLDER.exists()) {
            Dog.DOG_FOLDER.mkdir();
        }
        File STORY_FILE = Utils.join(CAPERS_FOLDER, "story.txt");
        try {
            STORY_FILE.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        File STORY_FILE = Utils.join(CAPERS_FOLDER, "story.txt");
        String prevStory = Utils.readContentsAsString(STORY_FILE);
        String curStory = prevStory + text + "\n";
        Utils.writeContents(STORY_FILE, curStory);
        String story = Utils.readContentsAsString(STORY_FILE);
        System.out.print(story);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog dog = new Dog(name, breed, age);
        dog.saveDog();
        System.out.println(dog);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        File dogFile = Utils.join(Dog.DOG_FOLDER, name);
        if (dogFile.exists()) {
            Dog dog = Dog.fromFile(name);
            dog.haveBirthday();
            dog.saveDog();
        }
    }
}
