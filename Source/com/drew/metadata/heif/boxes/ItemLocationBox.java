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
    long itemCount;
    long itemID;
    int constructionMethod;
    int dataReferenceIndex;
    byte[] baseOffset;
    int extentCount;
    int extentIndex;

    public ItemLocationBox(SequentialReader reader, Box box) throws IOException
    {
        super(reader, box);

        int holder = reader.getUInt8();
        offsetSize = (holder & 0xF0) >> 4;
        lengthSize = (holder & 0x0F);

        holder = reader.getUInt8();
        baseOffsetSize = (holder & 0xF0) >> 4;
        if ((version == 1) || (version == 2)) {
            indexSize = (holder & 0x0F);
        } else {
            // Reserved
        }
        if (version < 2) {
            itemCount = reader.getUInt16();
        } else if (version == 2) {
            itemCount = reader.getUInt32();
        }
        for (int i = 0; i < itemCount; i++) {
            if (version < 2) {
                itemID = reader.getUInt16();
            } else if (version == 2) {
                itemID = reader.getUInt32();
            }
            if ((version == 1) || (version == 2)) {
                holder = reader.getUInt16();
                constructionMethod = (holder & 0x000F);
            }
            dataReferenceIndex = reader.getUInt16();
            baseOffset = reader.getBytes(baseOffsetSize);
            extentCount = reader.getUInt16();

            long extentIndex;
            long extentOffset;
            long extentLength;
            for (int j = 0; j < extentCount; j++) {
                if ((version == 1) || (version == 2) && (indexSize > 0)) {
                    extentIndex = getIntFromUnknownByte(indexSize, reader);
                }
                extentOffset = getIntFromUnknownByte(offsetSize, reader);
                extentLength = getIntFromUnknownByte(lengthSize, reader);
            }
        }
    }

    public Long getIntFromUnknownByte(int variable, SequentialReader reader) throws IOException
    {
        switch(variable) {
            case (1):
                return (long)reader.getUInt8();
            case (2):
                return (long)reader.getUInt16();
            case (4):
                return (long)reader.getUInt32();
            case (8):
                return reader.getInt64();
            default:
                return null;
        }
    }

    class Extent
    {
        int index;
        long offset;
        long length;

        public Extent(int index, long offset, long length) {
            this.index = index;
            this.offset = offset;
            this.length = length;
        }
    }
}
