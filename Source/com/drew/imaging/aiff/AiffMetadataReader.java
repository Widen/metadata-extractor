package com.drew.imaging.aiff;

import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.aiff.AiffReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Obtains metadata from AIFF files.
 *
 * @author Payton Garland
 */
public class AiffMetadataReader
{
    @NotNull
    public static Metadata readMetadata(@NotNull File file) throws IOException
    {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(file);
            return readMetadata(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    @NotNull
    public static Metadata readMetadata(@NotNull InputStream inputStream)
    {
        Metadata metadata = new Metadata();
        new AiffReader().extract(new StreamReader(inputStream), metadata);
        return metadata;
    }
}
