package com.drew.metadata.aiff;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;

import java.io.IOException;

public class AiffReader
{
    public void extract(@NotNull final SequentialReader reader, final @NotNull Metadata metadata) throws IOException
    {
        reader.setMotorolaByteOrder(true);
        AiffDirectory directory = new AiffDirectory();
        metadata.addDirectory(directory);

        String fileFourCC = reader.getString(4);
        
    }
}
