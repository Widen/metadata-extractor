package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;

import java.io.IOException;

/**
 * @author Payton Garland
 */
public class ItemLocationBox extends FullBox
{
    int indexSize;

    int offsetSize;
    int lengthSize;
    int baseOffsetSize;

    public ItemLocationBox(SequentialReader reader, Box box) throws IOException
    {
        super(reader, box);

        if ((version == 1) || (version == 2)) {

        }
    }
}
