package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/** Represents a gitlet commit object that can be serialized.
 *  @author Irene Jiaxin Fan
 */
public class Commit implements Serializable {

    // The message of this Commit.
    private String message;

    // The timestamp of this Commit.
    private Date date;

    // The parent of this commit
    private String parentRef;

    // TODO
    public Commit() {
        this.message = "initial commit";
        this.date = new Date(0);
        this.parentRef = null;
    }

    // TODO
    public Commit(String msg) {
        String prev = Utils.readObject(Repository.HEAD, String.class);
        this.parentRef = prev;
        this.message = msg;
    }

    // TODO
    public void toFile() {

        File commitFile = Utils.join(Repository.refs, id);
    }
}
