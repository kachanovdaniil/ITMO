package common;

import java.io.Serial;
import java.io.Serializable;

public enum LocalizationKeys implements Serializable {
    WELCOME,
    EXIT,
    SUCCESS,
    FAIL,
    ERROR_EXECUTION,
    UNKNOWN_COMMAND,
    UNKNOWN_LANGUAGE,
    HELP_COMMAND,
    HISTORY_COMMAND,
    INFO_COMMAND,
    SHOW_COMMAND,
    ADD_COMMAND,
    REMOVE_COMMAND,
    CLEAR_COMMAND,
    ERROR_WRONG_ENUM_VALUE,
    TRY_AGAIN,
    EXECUTE_SCRIPT_COMMAND,
    EXIT_COMMAND,
    TIMEOUT_ERROR,
    FILE_NOT_FOUND,
    FILE_NOT_READABLE,
    FILE_NOT_EXIST,
    ADD_IF_MAX_COMMAND,
    COUNT_BY_BEST_ALBUM,
    UPDATE_COMMAND,
    FILTER_BY_BEST_ALBUM_COMMAND,
    REMOVE_GREATER_COMMAND,
    SAVE_COMMAND,
    REGISTER_SUCCESS,
    REGISTER_ERROR,
    AUTH_SUCCESS,
    AUTH_ERROR,
    COUNT_BY_BEST_ALBUM_COMMAND,
    ERROR_ALREADY_USED_ID,
    ERROR_WRONG_INPUT,
    ERROR_WRONG_ID,
    ERROR_SERVER_CONNECTION,
    SERIALIZATION_ERROR,
    INCORRECT_SERVER_ANSWER,
    ENTER_LOGIN,
    ENTER_PASSWORD,
    ERROR_INCORRECT_LOGIN_OR_PASSWORD,
    ERROR_CLOSING_CHANNEL,
    AVAILABLE_COMMANDS,
    LOGIN_COMMAND,
    REGISTER_COMMAND,
    ERROR_WRONG_NUMBER_OF_ARGUMENTS,
    ERROR_EMPTY_COMMAND,
    ERROR_OPENING_FILE,
    ENTERING_ARGUMENTS_FOR_COMMAND,
    EXECUTING_COMMAND,
    REQUEST_SENDING,
    REQUEST_RECEIVING,
    ENTER_ARGUMENTS_FROM_DESCRIPTION,
    RECURSION_DETECTED,
    BAD_SCRIPT_FILE,
    ERROR_IMPOSSIBLE_PARSE_COMPOSITE_FROM_CONSOLE,
    ERROR_OPENING_CHANNEL,
    ERROR_PARSING_PORT,
    ERROR_WITH_SOCKET_CONNECTION,
    ERROR_CREATING_REQUEST_HANDLER,
    ERROR_WITH_GETTING_DATAGRAM,
    ERROR_CREATING_OBJECT_SENDER,
    TYPE_OF_COLLECTION,
    NUMBER_OF_ELEMENTS,
    DATE_OF_INITIALIZATION,
    DATE_ERROR,
    ERROR_ADDING_ELEMENT,
    ERROR_CLEARING_COLLECTION,
    OWNER_LOGIN,
    ID,
    ID_FIELD,
    ERROR_WRONG_TYPE_OF_FIELD,
    ERROR_EMPTY_COLLECTION,
    ERROR_REMOVE_GREATER,
    ERROR_REMOVING_ELEMENT,
    ERROR_SAVING_COLLECTION,
    ERROR_PARSING_DATABASE_FORMAT, ERROR_PARSING_CSV_FORMAT, BEST_ALBUM_NAME, LENGTH_OF_ALBUM_NAME,
    BEST_ALBUM_NAME_FIELD,
    LENGTH_OF_ALBUM_NAME_FIELD,
    NUMBER_OF_TRACKS_NAME,
    NUMBER_OF_TRACKS_FIELD,
    SALES_NAME,
    SALES_FIELD,
    LENGTH_OF_ALBUM,
    FIELD_X_CANNOT_BE_NULL,
    FIELD_X_MUST_BE_GREATER_THAN_MINUS_129,
    FIELD_Y_CANNOT_BE_NULL,
    FIELD_Y_MUST_BE_GREATER_THAN_MINUS_420,
    FIELD_Y_IS_INTEGER,
    THE_NAME_OF_MUSICBAND_CANNOT_BE_NULL,
    LENGTH_OF_ALBUM_FIELD,
    NUMBER_OF_TRACKS,
    SALES,
    BEST_ALBUM,
    X,
    Y,
    X_CORD,
    Y_CORD,
    COORDINATES_OF_MUSICBAND,
    NAME_OF_MUSICBAND,
    NAME_OF_MUSICBAND_FIELD,
    CREATION_DATE,
    CREATION_DATE_FIELD,
    NUMBER_OF_PARTICIPANTS,
    NUMBER_OF_PARTICIPANTS_FIELD,
    GENRE_FIELD,
    GENRE_LIST,
    INTERACTIVE_START,
    MUSIC_BAND,
    MAX_BY_BEST_ALBUM_COMMAND,
    YOU_ARE_NOT_LOGGED_IN,
    ERROR_NO_SUCH_ELEMENTS,
    NOT_THE_GREATEST_ELEMENT,
    LOGIN_IS_TAKEN,
    ERROR_INFO_COMMAND,
    ERROR_UPDATE_COMMAND,
    ERROR_FILTER_BY_BEST_ALBUM_COMMAND,
    ERROR_REMOVE_GREATER_COMMAND,
    ERROR_EXECUTE_SCRIPT_COMMAND,
    ERROR_COUNT_BY_BEST_ALBUM_COMMAND,
    ERROR_SAVING_COLLECTION_COMMAND,
    ERROR_SHOW_COMMAND,
    ERROR_ADD_IF_MAX_COMMAND,
    ERROR_CLEAR_COMMAND,
    ERROR_HELP_COMMAND,
    ERROR_REMOVE_COMMAND,
    ERROR_MAX_BY_BEST_ALBUM_COMMAND,
    LOGIN,
    PASSWORD,
    SALT,
    USER,
    ERROR_DATABASE_ANSWER,
    ERROR_REQUEST,
    FILE_NOT_FOUND_DATABASE,
    NOT_PERMISSION_FOR_FILE_FOR_DATABASE,
    ERROR_WITH_FILE_FOR_DATABASE,
    ERROR_WITH_DATABASE_ENUM,
    YOU_HAVE_NOT_PERMISSION_TO_SUCH_ELEMENTS,
    SUBMIT,
    LOGGING_IN,
    REGISTRATION,
    LANGUAGE,
    REMOVE_ELEMENT,
    UPDATE_ELEMENT,
    EXECUTE_COMMAND,
    THE_NAME_CANT_BE_NULL,
    NUMBER_OF_PARTICIPANTS_CANNOT_BE_NEGATIVE,
    LENGTH_CANNOT_BE_NEGATIVE,
    NUMBER_OF_TRACKS_CANNOT_BE_NEGATIVE,
    SALES_CANNOT_BE_NEGATIVE,
    PATH,
    YOU_CANNOT_UPDATE_UNTIL,
    WRONG_VALUE,
    DIFFERENT_PASSWORDS;


    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
