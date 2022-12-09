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

public class FileDetector {
    public static void main(String... args) {
        Tika tika = new Tika();
        TikaConfig tikaConfig = TikaConfig.getDefaultConfig();
        //MediaTypeRegistry mediaTypeRegistry = MediaTypeRegistry.getDefaultRegistry();
        MediaTypeRegistry mediaTypeRegistry = tikaConfig.getMediaTypeRegistry();
        //MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
        MimeTypes mimeTypes = tikaConfig.getMimeRepository();
        //
//        List<Detector> detectors = new ArrayList<>();
//        Detector detector = null;
////        // zip compressed container types
////        detectors.add(new ZipContainerDetector());
////        // Microsoft stuff
////        detectors.add(new POIFSContainerDetector());
//        // mime magic detection as fallback
//        detectors.add(MimeTypes.getDefaultMimeTypes());
//        detector = new CompositeDetector(detectors);
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
            String fileType = null;
            MediaType mediaType = null;
            Metadata metadata = null;
            Set<MediaType> aliasSet = null;
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            MimeType mimeType0 = null;
            MimeType mimeType = null;
            TikaInputStream tis = null;
            MediaType mediaType2 = null;
            MimeType mimeType2 = null;
            try {
                file = new File(filePath);
                fileType = tika.detect(file);
                metadata = new Metadata();
                metadata.add(TikaCoreProperties.RESOURCE_NAME_KEY, filePath);
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                mediaType = tika.getDetector().detect(bis, metadata);
                aliasSet = mediaTypeRegistry.getAliases(mediaType);
                //mimeType = new MimeType(mediaType);
                mimeType0 = mimeTypes.forName(fileType);
                mimeType = mimeTypes.forName(mediaType.toString());
                tis = TikaInputStream.get(bis);
                Detector detector = new CompositeDetector(Arrays.asList(mimeTypes));
                mediaType2 = detector.detect(tis, metadata);
                mimeType2 = mimeTypes.forName(mediaType2.toString());
                System.out.println("fileType: [" + fileType
                        + "], mediaType.toString: [" + (mediaType.toString())
//                        + "], mediaType.getBaseType: [" + (mediaType.getBaseType())
//                        + "], mediaType.getType: [" + mediaType.getType()
//                        + "], mediaType.getSubtype: [" + mediaType.getSubtype()
                        + "], mimeType0.getExtensions: [" + (mimeType0==null?"NULL":StringUtils.join(mimeType0.getExtensions(), ","))
//                        + "], mimeType.toString: [" + (mimeType==null?"NULL":mimeType.toString())
                        + "], mimeType.getExtensions: [" + (mimeType==null?"NULL":StringUtils.join(mimeType.getExtensions(), ","))
                        + "], mediaType2.toString: [" + (mediaType2.toString())
//                        + "], mimeType2.toString: [" + (mimeType2==null?"NULL":mimeType2.toString())
                        + "], mimeType2.getExtensions: [" + (mimeType2==null?"NULL":StringUtils.join(mimeType2.getExtensions(), ","))
                        //+ "], aliasSet: [" + StringUtils.join(aliasSet, ",")
                        //+ "], filePath: [" + filePath
                        + "]");
            }
            catch (Exception e) {
                System.err.println(e.fillInStackTrace());
            }
            finally {
                if (fis != null) {
                    try { fis.close(); } catch (IOException e) { System.err.println(e.fillInStackTrace()); }
                }
                if (bis != null) {
                    try { bis.close(); } catch (IOException e) { System.err.println(e.fillInStackTrace()); }
                }
            }
        });

    }
}
