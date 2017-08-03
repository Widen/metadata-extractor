package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author Payton Garland
 */
public class ItemInfoBox extends FullBox
{
    long entryCount;
    ArrayList<ItemInfoEntry> entries;

    public ItemInfoBox(SequentialReader reader, Box box) throws IOException
    {
        super(reader, box);

        if (version == 0) {
            entryCount = reader.getUInt16();
        } else {
            entryCount = reader.getUInt32();
        }
        entries = new ArrayList<ItemInfoEntry>();
        for (int i = 1; i <= entryCount; i++)
        {
            entries.add(new ItemInfoEntry(reader, box));
        }
    }

    class ItemInfoEntry extends FullBox
    {
        int itemID;
        int itemProtectionIndex;
        String itemName;
        String contentType;
        String contentEncoding;
        long extensionType;

        public ItemInfoEntry(SequentialReader reader, Box box) throws IOException
        {
            super(reader, box);

            if ((version == 0) || (version == 1)) {
                itemID = reader.getUInt16();
                itemProtectionIndex = reader.getUInt16();
                itemName = reader.getNullTerminatedString((int)size, Charset.defaultCharset());
                contentType = reader.getNullTerminatedString((int)size, Charset.defaultCharset());
                reader.skip(18 + contentType.length() + itemName.length());
            }
        }
    }
}
