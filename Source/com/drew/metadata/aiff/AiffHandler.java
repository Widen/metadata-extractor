package com.drew.metadata.aiff;

import com.drew.imaging.iff.IffHandler;
import com.drew.lang.ByteArrayReader;
import com.drew.metadata.Metadata;

import java.io.IOException;

/**
 * Implementation of {@link IffHandler} specialising in Aiff support.
 *
 * Extracts data from chunk types:
 *
 * <ul>
 *     <li><code>"comm"</code>: channel count, sample frame count, sample size, sample rate</li>
 * </ul>
 *
 * Sources: http://www-mmsp.ece.mcgill.ca/Documents/AudioFormats/AIFF/AIFF.html
 *
 * @author Payton Garland
 */
public class AiffHandler implements IffHandler
{
    AiffDirectory _directory;

    public AiffHandler(Metadata metadata)
    {
        _directory = new AiffDirectory();
        metadata.addDirectory(_directory);
    }

    @Override
    public boolean shouldAcceptIffIdentifier(String identifier)
    {
        return identifier.equals(AiffDirectory.FORMAT);
    }

    @Override
    public boolean shouldAcceptChunk(String fourCC)
    {
        return fourCC.equals(AiffDirectory.CHUNK_COMMON);
    }

    @Override
    public boolean shouldAcceptList(String fourCC)
    {
        return false;
    }

    @Override
    public void processChunk(String fourCC, byte[] payload)
    {
        try {
            ByteArrayReader reader = new ByteArrayReader(payload);
            if (fourCC.equals(AiffDirectory.CHUNK_COMMON)) {
                _directory.setInt(AiffDirectory.TAG_NUMBER_CHANNELS, reader.getInt16(0));
                _directory.setLong(AiffDirectory.TAG_NUMBER_SAMPLE_FRAMES, reader.getUInt32(2));
                _directory.setInt(AiffDirectory.TAG_SAMPLE_SIZE, reader.getInt16(6));
                byte[] sampleRate = reader.getBytes(8, 10);
                _directory.setByteArray(AiffDirectory.TAG_SAMPLE_RATE, sampleRate);
            }
        } catch (IOException ex) {
            _directory.addError("Error processing " + fourCC + " chunk: " + ex.getMessage());
        }
    }
}
