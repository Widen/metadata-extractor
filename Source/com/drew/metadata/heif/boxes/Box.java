package com.drew.metadata.heif.boxes;

import com.drew.imaging.quicktime.QtAtom;
import com.drew.lang.SequentialReader;
import com.drew.metadata.Directory;
import lombok.Getter;

import java.io.IOException;

/**
 * ISO/IED 14496-12:2015 pg.6
 */
@Getter
public class Box implements QtAtom
{
    public long size;
    public String type;
    String usertype;

    public Box(SequentialReader reader) throws IOException
    {
        this.size = reader.getUInt32();
        this.type = reader.getString(4);
        if (size == 1) {
            size = reader.getInt64();
        } else if (size == 0) {
            size = -1;
        }
        if (type.equals("uuid")) {
            usertype = reader.getString(16);
        }
    }

    public Box(Box box)
    {
        this.size = box.size;
        this.type = box.type;
        this.usertype = box.usertype;
    }

    @Override
    public void addMetadata(Directory directory)
    {

    }
}
