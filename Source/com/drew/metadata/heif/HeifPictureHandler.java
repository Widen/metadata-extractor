//package com.drew.metadata.heif;
//
//import com.drew.imaging.heif.HeifHandler;
//import com.drew.lang.SequentialByteArrayReader;
//import com.drew.lang.SequentialReader;
//import com.drew.metadata.Metadata;
//import com.drew.metadata.heif.boxes.*;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author Payton Garland
// */
//public class HeifPictureHandler extends HeifHandler<HeifDirectory>
//{
//    ItemProtectionBox itemProtectionBox;
//    PrimaryItemBox primaryItemBox;
//    ItemInfoBox itemInfoBox;
//    ItemLocationBox itemLocationBox;
//
//    public HeifPictureHandler(Metadata metadata)
//    {
//        super(metadata);
//
//        itemProtectionBox = null;
//        primaryItemBox = null;
//        itemInfoBox = null;
//        itemLocationBox = null;
//    }
//
//    @Override
//    protected boolean shouldAcceptBox(Box box)
//    {
//        List<String> boxes = Arrays.asList(HeifBoxTypes.BOX_ITEM_PROTECTION,
//            HeifBoxTypes.BOX_PRIMARY_ITEM,
//            HeifBoxTypes.BOX_ITEM_INFO,
//            HeifBoxTypes.BOX_ITEM_LOCATION,
//            HeifBoxTypes.BOX_IMAGE_SPATIAL_EXTENTS,
//            HeifBoxTypes.BOX_AUXILIARY_TYPE_PROPERTY,
//            HeifBoxTypes.BOX_HEVC_CONFIGURATION);
//
//        return boxes.contains(box.type);
//    }
//
//    @Override
//    protected boolean shouldAcceptContainer(Box box)
//    {
//        return box.type.equals(HeifContainerTypes.BOX_IMAGE_PROPERTY)
//            || box.type.equals(HeifContainerTypes.BOX_ITEM_PROPERTY);
//    }
//
//    @Override
//    protected HeifHandler processBox(Box box, byte[] payload) throws IOException
//    {
//        SequentialReader reader = new SequentialByteArrayReader(payload);
//        if (box.type.equals(HeifBoxTypes.BOX_ITEM_PROTECTION)) {
//            itemProtectionBox = new ItemProtectionBox(reader, box);
//        } else if (box.type.equals(HeifBoxTypes.BOX_PRIMARY_ITEM)) {
//            primaryItemBox = new PrimaryItemBox(reader, box);
//        } else if (box.type.equals(HeifBoxTypes.BOX_ITEM_INFO)) {
//            itemInfoBox = new ItemInfoBox(reader, box);
//            itemInfoBox.addMetadata(directory);
//        } else if (box.type.equals(HeifBoxTypes.BOX_ITEM_LOCATION)) {
//            itemLocationBox = new ItemLocationBox(reader, box);
//        } else if (box.type.equals(HeifBoxTypes.BOX_IMAGE_SPATIAL_EXTENTS)) {
//            ImageSpatialExtentsProperty imageSpatialExtentsProperty = new ImageSpatialExtentsProperty(reader, box);
//            imageSpatialExtentsProperty.addMetadata(directory);
//        } else if (box.type.equals(HeifBoxTypes.BOX_AUXILIARY_TYPE_PROPERTY)) {
//            AuxiliaryTypeProperty auxiliaryTypeProperty = new AuxiliaryTypeProperty(reader, box);
//        } else if (box.type.equals(HeifBoxTypes.BOX_HEVC_CONFIGURATION)) {
//            HEVCDecoderConfigurationRecord HEVCDecoderConfigurationRecord = new HEVCDecoderConfigurationRecord(reader, box);
//        }
//        return this;
//    }
//
//    @Override
//    protected void processContainer(Box box, SequentialReader reader) throws IOException
//    {
//
//    }
//
//    @Override
//    protected HeifDirectory getDirectory()
//    {
//        return new HeifDirectory();
//    }
//}
