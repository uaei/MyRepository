package com.opentext.utils.uuid;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

/**
 * define UUIDUtils
 * version: 1.0
 */
public class UUIDUtils {

    /**
     * create a unique UUID
     *
     * @return String
     */
    public static String getUUID() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        return uuid.toString().replaceAll("-", "").toLowerCase();
    }

/*    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getUUID());
        }
    }*/

}
