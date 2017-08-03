package com.drew.metadata.heif;

import com.drew.metadata.TagDescriptor;

public class HeifDescriptor extends TagDescriptor<HeifDirectory>
{

    public HeifDescriptor(HeifDirectory directory)
    {
        super(directory);
    }
}
