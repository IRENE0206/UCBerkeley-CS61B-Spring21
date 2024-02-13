package gitlet;

import java.io.File;
import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  The structure of a gitlet Repository is as follows:
 *  .gitlet/ -- top level folder for all persistent data
 *      HEAD -- file containing the head pointer
 *      refs/ --
 *      objects/ --
 *
 *
 *  @author Irene Jiaxin Fan
 */
public class Repository {

    // The current working directory.
    public static final File CWD = new File(System.getProperty("user.dir"));
    // The .gitlet directory.
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File HEAD = join(GITLET_DIR, "HEAD");
    public static final File refs = join(GITLET_DIR, "refs");
    public static final File logs = join(GITLET_DIR, "logs");
    // The staging directory.
    public static final File index = join(GITLET_DIR, "index");
    public static final File COMMIT_EDITMSG = join(GITLET_DIR, "COMMIT_EDITMSG.txt");
    public static final File objects = join(GITLET_DIR, "objects");

    // Create an initial branch without any commit
    public static void init() {
        if (GITLET_DIR.exists()) {
            Utils.error("A Gitlet version-control system already exists in the current directory.");
        } else {
            Commit initialCommit = new Commit();
            GITLET_DIR.mkdir();
            HEAD.mkdir();
            logs.mkdir();
            index.mkdir();
            COMMIT_EDITMSG.mkdir();
            objects.mkdir();
            refs.mkdir();
        }
    }
}
