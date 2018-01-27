package by.loiko.library.receiver;

import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public interface AbstractReceiver {

    default ArrayList<Long> validateArrayOfId(String[] idArray) {

        ArrayList<Long> idList = new ArrayList<>();

        for (String id : idArray) {
            try {
                long bookId = Long.parseLong(id);
                idList.add(bookId);
            } catch (NumberFormatException e) {
                // log and just pass this value
            }
        }

        return idList;
    }

}
