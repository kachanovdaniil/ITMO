package receivers;

import common.builders.MusicBandBuilder;
import common.descriptions.LoadDescription;
import main.Main;
import managers.file.*;
import common.Album;
import common.Collection;
import common.MusicBand;
import common.descriptions.MusicBandDescription;
import managers.file.decorators.DataBase.DBReader;
import managers.file.decorators.DataBase.DBWriter;
import managers.user.User;
import result.Result;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, хранящий коллекцию и выполняющий все операции с ней. Принимает запросы на выполнение команды. Реализует паттерн одиночка.
 *
 * @author Фролов К.Д.
 */
public class MusicReceiver extends Receiver<MusicBand> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    /**
     * Ссылка на текущий {@link Receiver}. Класс реализует паттерн одиночка.
     */
    private static MusicReceiver instance;

    /**
     * Одиночка. Если объекта нет - создадим, иначе вернем тот, что есть.
     *
     * @return ссылка на существующий объект {@link Receiver}
     */
    public static MusicReceiver GetInstance() throws Exception {
        if (instance == null) {
            instance = new MusicReceiver();
        }
        return instance;
    }

    /**
     * Здесь происходит вся инициализация: инициализация коллекции, подключение к файлу, его чтение и запись данных в коллекцию. Происходит одна один раз - спасибо паттерну одиночка.
     */
    private MusicReceiver() throws Exception{

        String fileName = System.getenv("FILE_NAME");
        try {
            Collection<MusicBand> tmp = new Collection<>();

            //, new LoadDescription<>("YourLogin", "OwnerLogin", musicBandBuilder::setName, null, String.class) - добавить это и id в MusicBandDescription

            collection_to_file_writer = new AbstractWriter<>("MusicBands") {
                @Override
                public void write() throws Exception {

                }

                @Override
                public Result<Boolean> insert(MusicBand musicBand) {
                    return null;
                }

                @Override
                public Result<Boolean> update(MusicBand musicBand, int i) {
                    return null;
                }

                @Override
                public Result<Boolean> remove(long l) {
                    return null;
                }

                @Override
                public Result<Boolean> remove(String col, String val) {
                    return null;
                }
            };
            AbstractReader<MusicBand> Collection_from_file_loader = new AbstractReader<>("MusicBands", new MusicBandDescription(), tmp) {
                @Override
                public Result<Collection<MusicBand>> read() {
                    return null;
                }
            };

            collection_to_file_writer = new DBWriter<>("MusicBands", collection_to_file_writer, new MusicBandDescription());
            MusicBandDescription mbd = new MusicBandDescription();
            ArrayList<LoadDescription<?>> fields = mbd.getFields();
            fields.add(0, new LoadDescription<Integer>("ID", "id", ((MusicBandBuilder) mbd.getBuilder())::setId, null, Integer.class));
            fields.add(new LoadDescription<String>("Your Login", "OwnerLogin", ((MusicBandBuilder) mbd.getBuilder())::setOwnerLogin, null, String.class));
            mbd.setFieldsOfObject(fields);
            Collection_from_file_loader = new DBReader<>("MusicBands", mbd, Collection_from_file_loader, tmp);


            collection = new common.Collection<>(Collection_from_file_loader, collection_to_file_writer);
        } catch (NullPointerException e){
            Main.logger.error(e.getMessage(), e);
            throw new NullPointerException("FILE_NAME is not set");
        }
    }

    /**
     * Возвращает все элементы коллекции в массиве
     *
     * @return объект {@link Result} с ответом
     */
    public Result<Collection<MusicBand>> showElementsOfCollection() {
        return Result.success(collection);
    }

    /**
     * Method to add an element to the collection if it is greater than all elements of the collection
     *
     * @return the execution result of the command (success/failure)
     */
    public Result<Void> addIfMax(MusicBand obj) {
        MusicBand newBand, maxBand;
        newBand = obj;
        Result<MusicBand> maxBandResult = collection.getMax();
        if (maxBandResult.isSuccess() && maxBandResult.getValue().isPresent()) {
            maxBand = maxBandResult.getValue().get();
        } else {
            return Result.failure(maxBandResult.getError().orElse(null), maxBandResult.getMessage());
        }
        if (newBand.compareTo(maxBand) > 0) {
            return this.add(obj);
        } else {
            return Result.success(null, "New band is not the greatest element of collection, element is not added to collection");
        }
    }

    /*public Result<List<Result<?>>> executeQueue(List<CommandDescription> queue, Invoker invoker) {
        Collection<MusicBand> backUp = collection.clone();
        List<Result<?>> results = new ArrayList<>();
        for (CommandDescription cd : queue) {
            Result<?> tmp_res = invoker.executeCommand(cd.getName(), cd);
            if (tmp_res.isSuccess()) {
                results.add(tmp_res);
            } else {
                collection = backUp.clone();
                return Result.failure(tmp_res.getError().get(), tmp_res.getMessage()+"\nСкрипт не исполнен. Загружено состояние коллекции, которое было до начла исполнения.");
            }
        }
        return Result.success(results);
    }*/

    /**
     * Method for filtering collection by best album
     *
     * @param album best album
     * @return result of executing command (success/error)
     */
    public Result<TreeSet<MusicBand>> filterByBestAlbum(Album album) {
        try {
            return Result.success(
                    collection.getCollection()
                            .stream()
                            .filter(
                                    (MusicBand band) -> band.getBestAlbum().equals(album))
                            .collect(Collectors.toCollection(TreeSet::new)));
        } catch (Exception e) {
            return Result.failure(e, "Error with filtering by best album");
        }
    }

    /**
     * Method for printing info about max element of collection by best album
     *
     * @return result of executing command (success/error)
     */
    public Result<MusicBand> maxByBestAlbum() {
        if (collection.getSize() == 0) {
            return Result.failure(new Exception("Max element of collection does not exist"), "Collection is empty");
        }
        try {
            MusicBand maxAlbumBand = collection.getCollection()
                    .stream()
                    .max(Comparator.comparing(band -> band.getBestAlbum().getSales()))
                    .orElse(null);
            if (maxAlbumBand == null) {
                return Result.failure(new Exception("Max element of collection does not exist"), "Max element of collection does not exist");
            }
            return Result.success(maxAlbumBand);
        } catch (Exception e) {
            return Result.failure(e, "Error with finding the maximum by best album");
        }
    }

    /**
     * Method to count the elements in the collection whose bestAlbum field is equal to the specified value
     *
     * @param bestAlbum
     * @return the execution result of the command (success/failure) with the number of elements
     */
    public Result<Long> countByBestAlbum(Album bestAlbum) {
        try {
            long count = collection.getCollection().stream()
                    .filter((MusicBand band) -> band.getBestAlbum().equals(bestAlbum)).count();
            return Result.success(count, "Number of elements with best album equal to " + bestAlbum + " is " + count);
        } catch (Exception e) {
            return Result.failure(e, "Error with executing countByBestAlbum command");
        }
    }

    public Result<Void> clear(String userLogin){
        Result<Boolean> result = collection_to_file_writer.remove("OwnerLogin", userLogin);
        if (result.isSuccess()) {
            collection.setCollection(
                    collection.getCollection().stream()
                    .filter((i)-> !i.getOwnerLogin().equals(userLogin))
                    .collect(Collectors.toCollection(TreeSet::new))
            );
            Main.logger.info("User " + userLogin + " elements of collection cleared");
            return Result.success(null, "Your elements of collection successfully cleared");
        } else {
            Main.logger.error("Collection wasn't cleared. " + result.getMessage());
            return Result.failure(result.getError().orElse(null), result.getMessage());
        }
    }
}
