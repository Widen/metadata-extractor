package com.drew.metadata.heif;

import com.drew.imaging.heif.HeifHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.heif.boxes.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Payton Garland
 */
public class HeifBoxHandler extends HeifHandler<HeifDirectory>
{
    ItemProtectionBox itemProtectionBox;
    PrimaryItemBox primaryItemBox;
    ItemInfoBox itemInfoBox;
    ItemLocationBox itemLocationBox;

    private HeifHandlerFactory handlerFactory = new HeifHandlerFactory(this);

    public HeifBoxHandler(Metadata metadata)
    {
        super(metadata);

        itemProtectionBox = null;
        primaryItemBox = null;
        itemInfoBox = null;
        itemLocationBox = null;
    }

    @Override
    protected HeifDirectory getDirectory()
    {
        return new HeifDirectory();
    }

    @Override
    public boolean shouldAcceptBox(@NotNull Box box)
    {
        List<String> boxes = Arrays.asList(HeifBoxTypes.BOX_FILE_TYPE,
            HeifBoxTypes.BOX_ITEM_PROTECTION,
            HeifBoxTypes.BOX_PRIMARY_ITEM,
            HeifBoxTypes.BOX_ITEM_INFO,
            HeifBoxTypes.BOX_ITEM_LOCATION);

        return boxes.contains(box.type);
    }

    @Override
    public boolean shouldAcceptContainer(Box box)
    {
        return box.type.equals(HeifContainerTypes.BOX_METADATA);
    }

    @Override
    public HeifHandler processBox(@NotNull Box box, @NotNull byte[] payload) throws IOException
    {
        if (payload != null) {
            SequentialReader reader = new SequentialByteArrayReader(payload);
            if (box.type.equals(HeifBoxTypes.BOX_FILE_TYPE)) {
                processFileType(reader, box);
            } else if (box.type.equals(HeifBoxTypes.BOX_ITEM_PROTECTION)) {
                itemProtectionBox = new ItemProtectionBox(reader, box);
            } else if (box.type.equals(HeifBoxTypes.BOX_PRIMARY_ITEM)) {
                primaryItemBox = new PrimaryItemBox(reader, box);
            } else if (box.type.equals(HeifBoxTypes.BOX_ITEM_INFO)) {
                itemInfoBox = new ItemInfoBox(reader, box);
            } else if (box.type.equals(HeifBoxTypes.BOX_ITEM_LOCATION)) {
                itemLocationBox = new ItemLocationBox(reader, box);
            }
        }
        return this;
    }

    @Override
    public void processContainer(@NotNull Box box, @NotNull SequentialReader reader) throws IOException
    {
        if (box.type.equals(HeifContainerTypes.BOX_METADATA)) {
            new FullBox(reader, box);
        }
    }

    private void processFileType(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {
        FileTypeBox fileTypeBox = new FileTypeBox(reader, box);
        fileTypeBox.addMetadata(directory);
        if (!fileTypeBox.compatibleBrands.contains("mif1")) {
            directory.addError("File Type Box does not contain required brand, mif1");
        }
    }
}
