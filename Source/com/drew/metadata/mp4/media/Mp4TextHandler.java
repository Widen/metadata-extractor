package com.drew.metadata.mp4.media;

import com.drew.lang.ByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4ContainerTypes;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.boxes.Box;

import java.io.IOException;

public class Mp4TextHandler extends Mp4MediaHandler<Mp4TextDirectory>
{
    public Mp4TextHandler(Metadata metadata)
    {
        super(metadata);
    }

    @Override
    protected Mp4TextDirectory getDirectory()
    {
        return directory;
    }

    @Override
    protected String getMediaInformation()
    {
        return Mp4ContainerTypes.BOX_MEDIA_TEXT;
    }

    @Override
    protected void processSampleDescription(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {

    }

    @Override
    protected void processMediaInformation(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {

    }

    @Override
    protected void processTimeToSample(@NotNull SequentialReader reader, @NotNull Box box) throws IOException
    {

    }
}
