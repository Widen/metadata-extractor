package com.drew.metadata.aiff;

import com.drew.metadata.TagDescriptor;

public class AiffDescriptor extends TagDescriptor<AiffDirectory>
{
    public AiffDescriptor(AiffDirectory directory)
    {
        super(directory);
    }

    @Override
    public String getDescription(int tagType)
    {
        switch (tagType) {
            default:
                return super.getDescription(tagType);
        }
    }
}
