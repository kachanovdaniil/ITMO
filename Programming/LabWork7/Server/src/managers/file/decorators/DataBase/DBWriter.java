package managers.file.decorators.DataBase;

import common.descriptions.LoadDescription;
import main.Main;
import managers.file.AbstractWriter;
import managers.file.DBSavable;
import managers.file.decorators.WriterDecorator;
import common.IDAccess;
import result.Result;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class DBWriter<T extends Comparable<T> & IDAccess & DBSavable> extends WriterDecorator<T> {
    Connection connection;
    LoadDescription<T> descriptionForUpdate;

    public DBWriter(String destination, AbstractWriter<T> writer, LoadDescription<T> descriptionForUpdate) throws SQLException, IOException, NullPointerException, SecurityException {
        super(destination, writer);
        String db_url = System.getenv("DB_URL"); // configure connection params
        Properties info = new Properties();
        info.load(new FileInputStream("db.cfg"));
        Connection conn = DriverManager.getConnection(db_url, info); // starting connection

        this.descriptionForUpdate = descriptionForUpdate;
    }

    @Override
    public void write() throws Exception {
        connection.setAutoCommit(false);
        connection.setSavepoint();

        //clearing
        PreparedStatement stat = connection.prepareStatement("truncate table ?");
        stat.setString(1, destination);
        stat.execute();

        //saving
        stat = connection.prepareStatement("insert into ? values (?);");
        stat.setString(1, destination);

        String collectionRows = "";
        Iterator<T> iter = collection.getCollection().iterator();
        while (iter.hasNext()) {
            Result<List<String>> row = iter.next().toFields();
            if (row.isSuccess()) {
                collectionRows += String.join(", ", row.getValue().get());
                if (iter.hasNext()) {
                    collectionRows += ", ";
                }
            } else {
                Main.logger.error("Error in some element while saving collection. " + row.getMessage());
                connection.rollback();
                connection.setAutoCommit(true);
                throw new Exception(row.getMessage());
            }
        }

        stat.setString(2, collectionRows);
        if (stat.execute()) {
            Main.logger.info("Collection saved");
            connection.commit();
        } else {
            Main.logger.error("Error with data base. Collection not saved");
            connection.rollback();
        }
        connection.setAutoCommit(true);
    }

    public boolean insert(T obj) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement("insert into ? values (?);");
            stat.setString(1, destination);

            Result<List<String>> row = obj.toFields();
            if (row.isSuccess()) {
                stat.setString(2, String.join(", ", row.getValue().get()));
            } else {
                Main.logger.error("Problem with getting field of element. Can not be save in data base. " + row.getMessage());
                return false;
            }

            return stat.execute();
        } catch (SQLException e) {
            Main.logger.error("Error with data base. Element not saved. " + e.getMessage());
            return false;
        }
    }

    private List<String> getFieldsFromDescription(LoadDescription<?> d){
        List<String> res = new ArrayList<>();
        for (LoadDescription<?> i: d.getFields()){
            if (i.getFields().isEmpty())
                res.add(i.getFieldName());
            else
                res.addAll(getFieldsFromDescription(i));
        }
        return res;
    }

    public boolean update(T obj, int id) {
        Statement stat = null;
        try {
            Result<List<String>> row = obj.toFields();
            List<String> columns = getFieldsFromDescription(descriptionForUpdate);
            String setter = "";
            if (row.isSuccess() && columns.size() == row.getValue().get().size()) {
                for (int j=0; j < columns.size(); j+=1){
                    setter += columns.get(j) + " = " + row.getValue().get().get(j);
                    if (j < columns.size()-1)
                        setter += ", ";
                }
            } else {
                Main.logger.error("Problem with getting field of element. Can not be save in data base. " + row.getMessage());
                return false;
            }
            stat = connection.createStatement();
            return stat.execute("update " + destination + " set " + setter + " where id = " + id + ";");
        } catch (SQLException e) {
            Main.logger.error("Error with data base. Element not saved. " + e.getMessage());
            return false;
        }
    }

    public boolean remove(int id){
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement("delete from ? where id = ?;");
            stat.setString(1, destination);
            stat.setInt(2, id);
            return stat.execute();
        } catch (SQLException e) {
            Main.logger.error("Error with data base. Element not saved. " + e.getMessage());
            return false;
        }
    }

    public boolean remove(String col, String val){
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement("delete from ? where ? = ?;");
            stat.setString(1, destination);
            stat.setString(2, col);
            stat.setString(3, val);
            return stat.execute();
        } catch (SQLException e) {
            Main.logger.error("Error with data base. Element not saved. " + e.getMessage());
            return false;
        }
    }
}
