package commands;

import receivers.MusicReceiver;
import result.Result;

/**
 * Class InfoCommand for displaying information about the collection.
 */
public class InfoCommand extends Command<MusicReceiver> {

    public InfoCommand() {
        super(MusicReceiver.GetInstance());
    }

    /**
     * Method execute calls the info() method of the receiver object.
     * @return result of executing the command (the result of the info() method of the receiver object)
     */
    @Override
    public Result<String> execute() {
        return receiver.info();
    }
}