package collection_file_managers.decorators;

import common.Collection;
import common.IDAccess;
import collection_file_managers.Abstract_file_reader;
import descriptions.LoadDescription;
import managers.BaseTextReceiver;
import result.Result;

import java.io.FileNotFoundException;

public class CSV_reader<T extends Comparable<T> & IDAccess> extends Reader_decorator<T> {
    public CSV_reader(String fileName, LoadDescription<T> load_description, Abstract_file_reader<T> reader) throws FileNotFoundException, NullPointerException, SecurityException {
        super(fileName, load_description, reader);
    }

    @Override
    public Result<Collection<T>> read() {
        try {
            while (true) {
                if (scanner.hasNextLine()) {
                    CSV_Loader loader = new CSV_Loader(new BaseTextReceiver() {
                        @Override
                        public void print(String message) {
                        }

                        @Override
                        public void println(String message) {
                        }
                    },
                            scanner.nextLine());
                    collection.add(loader.enter(load_description).getValue());
                } else {
                    break;
                }
            }
            return Result.success(collection);
        } catch (IndexOutOfBoundsException e) {
            return Result.failure(e, "Файл с коллекцией не соответствует структуре хранимых объектов");
        } catch (Exception e) {
            return Result.failure(e, "Ошибка валидации объекта");
        }
    }
}