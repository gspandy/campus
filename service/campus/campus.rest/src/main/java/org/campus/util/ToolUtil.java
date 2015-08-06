package org.campus.util;

import java.util.UUID;

public class ToolUtil {

    public static String getUUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
