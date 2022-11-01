package com.zerobase.luffy.Util.file;

import org.springframework.web.multipart.MultipartFile;

public interface fileUtil {

    public String[] imageMaker(MultipartFile file);

    public String[] getNewSaveFile(String baseUrlPath, String baseLocalPath, String OriginalName);
}