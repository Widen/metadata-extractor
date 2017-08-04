package com.drew.metadata.heif;

import java.util.ArrayList;

/**
 * @author Payton Garland
 */
public class HeifBoxTypes
{
    public static final String BOX_FILE_TYPE                        = "ftyp";
    public static final String BOX_PRIMARY_ITEM                     = "pitm";
    public static final String BOX_ITEM_PROTECTION                  = "ipro";
    public static final String BOX_ITEM_INFO                        = "iinf";
    public static final String BOX_ITEM_LOCATION                    = "iloc";

    public static ArrayList<String> _boxList = new ArrayList<String>();

    static {
        _boxList.add(BOX_FILE_TYPE);
        _boxList.add(BOX_ITEM_PROTECTION);
        _boxList.add(BOX_PRIMARY_ITEM);
        _boxList.add(BOX_ITEM_INFO);
        _boxList.add(BOX_ITEM_LOCATION);
    }
}
