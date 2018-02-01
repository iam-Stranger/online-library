package by.loiko.library.validator;

/***
 Author: Aliaksei Loika
 Date: 30.01.2018
 ***/
public enum FieldEnum {

    ID("[^-0]+\\d*", "ID is incorrect"),

    TYPE("[A-ZА-ЯЁ]{1}[A-ZА-ЯЁa-zа-яё\\s]{2,48}", "Genre type is incorrect"),
    NAME("[A-ZА-ЯЁ]{1}[A-ZА-ЯЁa-zа-яё\\s]{2,48}", "Author name is incorrect"),
    STATUS("0|1", "Entity status is incorrect")


    ;

    FieldEnum(String regEx, String message) {
        this.regEx = regEx;
        this.message = message;
    }

    private String regEx;
    private String message;

    public String getRegEx() {
        return regEx;
    }

    public String getMessage() {
        return message;
    }
}
