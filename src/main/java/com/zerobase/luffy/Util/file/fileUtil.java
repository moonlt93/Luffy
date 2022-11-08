package com.zerobase.luffy.Util.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface fileUtil {

    public String[] imageMaker(List<MultipartFile> fileList);

    public String[] getNewSaveFile(String baseUrlPath, String baseLocalPath, String OriginalName);
}