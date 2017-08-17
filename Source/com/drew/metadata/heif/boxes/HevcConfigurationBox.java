package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;

import java.io.IOException;

/**
 * @author Payton Garland
 */
public class HevcConfigurationBox extends Box
{
    public HevcConfigurationBox(SequentialReader reader, Box box) throws IOException
    {
        super(box);

        int mConfigurationVersion = reader.getUInt8();
        int mHolder = reader.getUInt8();
        int mGeneralProfileSpace = (mHolder & 0xC0) >> 6;
        int mGeneralTierFlag = (mHolder & 0x20) >> 5;
        int mGeneralProfileIdc = (mHolder & 0x1F);

//        reader.skip(4);
        int temp = reader.getInt32();
        reader.skip(6);
//        reader.skip(4);
        int temp2 = reader.getInt32();
        reader.skip(3);

        int mAvgFrameRate = reader.getUInt16();
        mHolder = reader.getUInt8();

        int mConstantFrameRate = (mHolder & 0xC0) >> 6;
        int mNumTemporalLayers = (mHolder & 0x38) >> 3;
        int mTemporalIdNested = (mHolder & 0x04) >> 2;
        int mLengthSizeMinus1 = (mHolder & 0x03);

        int numOfArrays = reader.getUInt8();

        System.out.println("here");
    }
}
