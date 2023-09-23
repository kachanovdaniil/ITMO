package common.descriptions;

import common.LocalizationKeys;
import common.builders.Buildable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LoadDescription<T> implements Serializable {

    protected T value;
    protected LocalizationKeys description;
    protected LocalizationKeys FieldName;
    protected Class<T> type;
    protected Buildable<T> builder;
    protected SerialFunction<T, ?> fieldOfDescriptionSetter;
    protected ArrayList<LoadDescription<?>> fields = new ArrayList<>();

    public LoadDescription(LocalizationKeys description, LocalizationKeys FieldName, SerialFunction<T, ?> fieldSetter, Buildable<T> builder, Class<T> type) {
        this.description = description;
        this.FieldName = FieldName;
        this.fieldOfDescriptionSetter = fieldSetter;
        this.builder = builder;
        this.type = type;
    }

    public LoadDescription(LocalizationKeys description, SerialFunction<T, ?> fieldSetter, Class<T> type) {
        this.description = description;
        this.fieldOfDescriptionSetter = fieldSetter;
        this.type = type;
    }


    public LoadDescription(Class<T> type){
        this(null, null, null, null, type);
    }

    public LoadDescription<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public T getValue() {
        return value;
    }

    public LocalizationKeys getDescription() {
        return description;
    }

    public LocalizationKeys getFieldName() {
        return FieldName;
    }

    public Class<T> getType() {
        return type;
    }

    public Buildable<T> getBuilder() {
        return builder;
    }

    public void setFieldOfDescriptionSetter(SerialFunction<T, ?> fieldOfDescriptionSetter) {
        this.fieldOfDescriptionSetter = fieldOfDescriptionSetter;
    }

    public Function<T, ?> getFieldOfDescriptionSetter() {
        return fieldOfDescriptionSetter;
    }

    public void setFieldsOfObject(ArrayList<LoadDescription<?>> fields) {
        this.fields = fields;
    }

    public ArrayList<LoadDescription<?>> getFields() {
        return fields;
    }


    @SuppressWarnings("unchecked")
    public void setField(Object object){
        if(object.getClass() == type) {
            fieldOfDescriptionSetter.apply((T) object);
        }
        else
            throw new IllegalArgumentException("Wrong type of field");
    }

    public void build() {
        value = builder.build();
    }


    @Override
    public String toString() {
        return "LoadDescription{" +
                "value=" + value +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", builder=" + builder +
                ", fieldOfDescriptionSetter=" + fieldOfDescriptionSetter +
                ", fields=" + fields +
                '}';
    }
}