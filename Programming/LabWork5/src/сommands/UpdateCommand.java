/**

 Команда обновления музыкальной группы в коллекции.

 */
package сommands;

import result.Result;
import managers.Receiver;

public class UpdateCommand implements Command {

    public Result<Void> execute(Receiver receiver) {
       return receiver.updateById();
    }
}