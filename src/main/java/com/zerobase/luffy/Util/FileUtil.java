package com.zerobase.luffy.Util;

import com.zerobase.luffy.member.admin.Dto.ProductDto;
import com.zerobase.luffy.member.admin.Dto.ProductFileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
public class FileUtil {

    public void imageMaker(MultipartFile file , ProductDto dto) throws Exception {



            String saveFileName = "";
            String urlFileName = "";

            if (file != null) {

                String originalFileName = file.getOriginalFilename();
                System.out.println(originalFileName);

                String baseLocalPath = "C:\\zero\\Ultimate\\Luffy\\src\\main\\resources\\static\\files";
                String baseUrlPath = "/files";
                String[] arrFileName = getNewSaveFile(baseUrlPath, baseLocalPath, originalFileName);

                saveFileName = arrFileName[0];
                urlFileName = arrFileName[1];

                try {
                    File newFile = new File(saveFileName);
                    FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));

                } catch (IOException e) {
                    log.info("#############################");
                    log.info(e.getMessage());
                }
            }

            dto.setFileName(saveFileName);
            dto.setUrlFileName(urlFileName);

        }





    private String[] getNewSaveFile(String baseUrlPath, String baseLocalPath, String OriginalName) {
        LocalDate now = LocalDate.now();

        String[] dirs = {
                String.format("%s/%d/", baseLocalPath, now.getYear()),
                String.format("%s/%d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};

        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                //디렉토리가 없으면 생성
                file.mkdir();
            }
        }
        String fileExtension = " ";
        if (OriginalName != null) {
            int dotPos = OriginalName.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = OriginalName.substring(dotPos + 1);
            }
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String urlFilename = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFilename += "." + fileExtension;
            urlFilename += "." + fileExtension;
        }

        return new String[]{newFilename, urlFilename};

    }

}
