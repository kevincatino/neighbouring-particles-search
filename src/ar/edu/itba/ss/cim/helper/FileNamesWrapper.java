package ar.edu.itba.ss.cim.helper;

import java.time.Instant;

public class FileNamesWrapper {
    public final String StaticFileName;
    public final String DynamicFileName;
    public final String TimeStamp;

    public FileNamesWrapper(String staticFileName, String dynamicFileName) {
        TimeStamp = Instant.now().toString();
        StaticFileName = staticFileName + TimeStamp + ".txt";
        DynamicFileName = dynamicFileName + TimeStamp + ".txt";
    }
}
