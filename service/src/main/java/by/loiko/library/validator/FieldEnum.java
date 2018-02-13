package by.loiko.library.validator;

/***
 Author: Aliaksei Loika
 Date: 30.01.2018
 ***/
public enum FieldEnum {


    ID("^[1-9]+\\d*$", "id", "ID is incorrect"),

    TITLE("[A-ZА-ЯЁ\\d]{1}[A-ZА-ЯЁa-zа-яё-.,\\s\\d]{2,127}", "title", "Title is incorrect"),

    PUBLISH_YEAR("\\d{4}", "publish_year", "Publish year is incorrect"),

    TOTAL_AMOUNT("[1-9]+\\d*$", "total_amount", "Total amount is incorrect"),

    REAL_AMOUNT("\\d+", "real_amount", "Real amount is incorrect"),

    GENRE("[^-0]+\\d*", "genre", "Genre ID is incorrect"),

    AUTHOR("[^-0]+\\d*", "author", "Author ID is incorrect"),

    TYPE("[A-ZА-ЯЁ]{1}[A-ZА-ЯЁa-zа-яё\\s]{2,48}", "type", "Genre type is incorrect"),

    NAME("[A-ZА-ЯЁ]{1}[A-ZА-ЯЁa-zа-яё\\s.]{2,48}", "name", "Author name is incorrect"),

    STATUS("0|1", "status", "Entity status is incorrect"),

    ORDER_TYPE("0|1", "order_type", "Order type is incorrect"),

    DATE_FROM("\\d{4}-\\d{2}-\\d{2}", "date_from", "DateFrom is incorrect"),

    DATE_TO("\\d{4}-\\d{d}-\\d{2}", "date_to", "DateTo is incorrect"),

    FIRSTNAME("[A-ZА-ЯЁ]{1}[a-zа-яё]{2,20}", "firstname", "First name is incorrect"),

    LASTNAME("[A-ZА-ЯЁ]{1}[a-zа-яё]{3,20}", "lastname", "Last name is incorrect"),

    LOGIN("[a-zA-Z]{1}[a-zA-Z\\d]{3,19}", "login", "Login is incorrect"),

    PASSWORD("[a-zA-Z0-9._*]{3,20}", "password", "Password is incorrect"),

    OLD_PASSWORD("[a-zA-Z0-9._*]{3,20}", "old_password", "Old password is incorrect"),

    EMAIL("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$", "email", "E-mail is incorrect"),

    ROLE_ID("1|2|3", "role_id", "Role ID is incorrect"),


    ;

    /**
     * Instantiates a new field enum.
     *
     * @param regEx the Regular Expression
     * @param fieldName the Name of field
     * @param message the Error message
     */
    FieldEnum(String regEx, String fieldName, String message) {
        this.regEx = regEx;
        this.fieldName = fieldName;
        this.message = message;
    }


    private String regEx;

    private String fieldName;

    private String message;

    /**
     * Gets the Regular expression
     *
     * @return the reg ex
     */
    public String getRegEx() {
        return regEx;
    }

    /**
     * Gets the Error message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the Name of field.
     *
     * @return the field name
     */
    public String getFieldName() {
        return fieldName;
    }
}
