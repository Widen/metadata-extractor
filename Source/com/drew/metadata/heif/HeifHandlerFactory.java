package com.drew.metadata.heif;

import com.drew.imaging.heif.HeifHandler;
import com.drew.imaging.mp4.Mp4Handler;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.boxes.HandlerBox;
import com.drew.metadata.mp4.media.*;

public class HeifHandlerFactory
{
    private HeifHandler caller;

    public HeifHandlerFactory(HeifHandler caller)
    {
        this.caller = caller;
    }

    public HeifHandler getHandler(HandlerBox box, Metadata metadata)
    {
        String type = box.getHandlerType();
        return caller;
    }
}
