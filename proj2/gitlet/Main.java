package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Irene Jiaxin Fan
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ...
     *
     * Runs one of these commands:
     * init
     * add [file name]
     * commit [message]
     * rm [file name]
     * log
     * global-log
     * find [commit message]
     * status
     * checkout -- [file name]
     * checkout [commit id] -- [file name]
     * checkout [branch name]
     * branch [branch name]
     * rm-branch [branch name]
     * reset [commit id]
     * merge [branch name]
     *
     * All persistent data should be stored in a ".gitlet" directory in the current working directory.
     * .gitlet/ -- top level folder
     *
     *
     * @param args arguments from the command line
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        if (args.length == 0) {
            Utils.error("Please enter a command.");
        }
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // TODO
                Repository.init();
                break;
            case "add":
                // TODO
                break;
            case "commit":
                // TODO
                break;
            case "rm":
                // TODO
                break;
            case "log":
                // TODO
                break;
            case "global-log":
                // TODO
                break;
            case "find":
                // TODO
                break;
            case "status":
                // TODO
                break;
            case "checkout":
                // TODO
                break;
            case "branch":
                // TODO
                break;
            case "rm-branch":
                // TODO
                break;
            case "reset":
                // TODO
                break;
            case "merge":
                // TODO
                break;
            default:
                Utils.error("No command with that name exists.");
        }
    }
}
