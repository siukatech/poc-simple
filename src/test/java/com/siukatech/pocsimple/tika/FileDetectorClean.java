package com.siukatech.pocsimple.tika;


import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.CompositeDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class FileDetectorClean {
    public static void main(String... args) {
        Tika tika = new Tika();
        TikaConfig tikaConfig = TikaConfig.getDefaultConfig();
        MediaTypeRegistry mediaTypeRegistry = tikaConfig.getMediaTypeRegistry();
        MimeTypes mimeTypes = tikaConfig.getMimeRepository();
        //
        List<String> filePathList = Arrays.asList(
                "/Users/karl.hk.yeung/Documents/gt/project/AIA/JIRA/file-detection/test-upload-doc/7z2201-x64.exe"
                , "/Users/karl.hk.yeung/Documents/gt/project/AIA/JIRA/file-detection/test-upload-doc/7z2201-x64.jpg"
                , "/Users/karl.hk.yeung/Documents/gt/project/AIA/JIRA/file-detection/test-upload-doc/aia-doc-jpeg.jpeg"
                , "/Users/karl.hk.yeung/Documents/gt/project/AIA/JIRA/file-detection/test-upload-doc/aia-doc.png"
                , "/Users/karl.hk.yeung/Documents/gt/project/AIA/JIRA/file-detection/test-upload-doc/Temp doc copy.docx"
                , "/Users/karl.hk.yeung/Documents/gt/project/AIA/JIRA/file-detection/test-upload-doc/Temp doc.doc"
                , "/Users/karl.hk.yeung/Documents/gt/project/AIA/JIRA/file-detection/test-upload-doc/Temp docx.docx"
                , "/Users/karl.hk.yeung/Documents/gt/project/AIA/JIRA/file-detection/test-upload-doc/Temp pdf testing.pdf"
        );
        filePathList.forEach(filePath -> {
            File file = null;
            //
            String fileType1 = null;
            MimeType mimeType1 = null;
            //
            MediaType mediaType2 = null;
            Metadata metadata2 = null;
            Set<MediaType> aliasSet2 = null;
            FileInputStream fis2 = null;
            BufferedInputStream bis2 = null;
            //
            MimeType mimeType2 = null;
            //
            TikaInputStream tis3 = null;
            MediaType mediaType3 = null;
            MimeType mimeType3 = null;
            try {
                file = new File(filePath);
                //
                fileType1 = tika.detect(file);
                mimeType1 = mimeTypes.forName(fileType1);
                //
                metadata2 = new Metadata();
                metadata2.add(TikaCoreProperties.RESOURCE_NAME_KEY, filePath);
                fis2 = new FileInputStream(file);
                bis2 = new BufferedInputStream(fis2);
                mediaType2 = tika.getDetector().detect(bis2, metadata2);
                aliasSet2 = mediaTypeRegistry.getAliases(mediaType2);
                mimeType2 = mimeTypes.forName(mediaType2.toString());
                //
                tis3 = TikaInputStream.get(bis2);
                Detector detector3 = new CompositeDetector(Arrays.asList(mimeTypes));
                mediaType3 = detector3.detect(tis3, metadata2);
                mimeType3 = mimeTypes.forName(mediaType3.toString());
                //
                System.out.println("fileType: [" + fileType1
                        + "], mediaType.toString: [" + (mediaType2.toString())
                        + "], mimeType1.getExtensions: [" + (mimeType1==null?"NULL":StringUtils.join(mimeType1.getExtensions(), ","))
                        + "], mimeType2.getExtensions: [" + (mimeType2==null?"NULL":StringUtils.join(mimeType2.getExtensions(), ","))
                        + "], mediaType3.toString: [" + (mediaType3.toString())
                        + "], mimeType3.getExtensions: [" + (mimeType3==null?"NULL":StringUtils.join(mimeType3.getExtensions(), ","))
                        //+ "], aliasSet: [" + StringUtils.join(aliasSet, ",")
                        //+ "], filePath: [" + filePath
                        + "]");
            }
            catch (Exception e) {
                System.err.println(e.fillInStackTrace());
            }
            finally {
                if (fis2 != null) {
                    try { fis2.close(); } catch (IOException e) { System.err.println(e.fillInStackTrace()); }
                }
                if (bis2 != null) {
                    try { bis2.close(); } catch (IOException e) { System.err.println(e.fillInStackTrace()); }
                }
            }
        });

    }
}
