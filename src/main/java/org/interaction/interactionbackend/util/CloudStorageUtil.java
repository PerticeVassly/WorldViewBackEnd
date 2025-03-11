package org.interaction.interactionbackend.util;

import java.io.InputStream;

public interface CloudStorageUtil {

    public String upload(String objectName, InputStream inputStream);
}
