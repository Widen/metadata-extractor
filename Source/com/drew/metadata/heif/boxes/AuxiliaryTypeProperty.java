package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Payton Garland
 */
public class AuxiliaryTypeProperty extends FullBox
{
    String auxType;

    public AuxiliaryTypeProperty(SequentialReader reader, Box box) throws IOException
    {
        super(reader, box);

        auxType = reader.getNullTerminatedString((int)box.size - 12, Charset.defaultCharset());
    }
}
