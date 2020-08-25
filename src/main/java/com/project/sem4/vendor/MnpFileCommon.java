package com.project.sem4.vendor;

import com.project.sem4.model.view.FileInfo;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class MnpFileCommon {
    public static FileInfo getFileInfo(File file) {
        if (file == null) return null;

        String rootDirName = file.getParent();
        rootDirName = rootDirName.substring(rootDirName.lastIndexOf('\\') + 1);
        String abc = "src\\main\\resources\\static\\";

        String replaceString = file.toString().replace(abc, "");
        String fileName = replaceString.replace("\\", "/");
        String[] setT = file.getName().split(Pattern.quote("."));
        Long LongDate = Long.parseLong(setT[0]);
        Date d = new Date(LongDate);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String createDate = formatter.format(d);
        return new FileInfo(
                file.getName(),

                file.length() / 1024,
                createDate,
                fileName,
                encodeURI(file.getName()));
    }
    /* JS encodeURI */
    public static String encodeURI(String src) {
        String dest = null;
        try {
            dest = URLEncoder.encode(src, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
            dest = src;
        }
        return dest;
    }
}
