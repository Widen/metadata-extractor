package com.drew.metadata.aiff;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;

import java.util.HashMap;

public class AiffDirectory extends Directory
{


    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<Integer, String>();

    static {

    }

    public AiffDirectory()
    {
        this.setDescriptor(new AiffDescriptor(this));
    }

    @Override
    public String getName()
    {
        return "AIFF";
    }

    @Override
    protected HashMap<Integer, String> getTagNameMap()
    {
        return null;
    }
}
