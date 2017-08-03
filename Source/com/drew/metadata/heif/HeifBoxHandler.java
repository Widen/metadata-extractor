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
    private HeifHandlerFactory handlerFactory = new HeifHandlerFactory(this);

    public HeifBoxHandler(Metadata metadata)
    {
        super(metadata);
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
            HeifBoxTypes.BOX_ITEM_INFO);

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
                ItemProtectionBox itemProtectionBox = new ItemProtectionBox(reader, box);
                System.out.println("here");
            } else if (box.type.equals(HeifBoxTypes.BOX_PRIMARY_ITEM)) {
                PrimaryItemBox primaryItemBox = new PrimaryItemBox(reader, box);
                System.out.println("here");
            } else if (box.type.equals(HeifBoxTypes.BOX_ITEM_INFO)) {
                ItemInfoBox itemInfoBox = new ItemInfoBox(reader, box);
                System.out.println("here");
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
