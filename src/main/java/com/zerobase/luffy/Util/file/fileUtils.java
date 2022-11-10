package com.zerobase.luffy.Util.file;

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
public class fileUtils implements fileUtil{

    @Override
    public String[] imageMaker(List<MultipartFile> fileList) {

         String[][] imageList= new String[fileList.size()][2];
        int i = 0;
      for(MultipartFile file : fileList){

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

            imageList[i][0] = saveFileName;
            imageList[i][1] = urlFileName;
            i++;
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int j = 0; j <imageList.length; j++) {
            sb1.append(imageList[j][0]).append("-");
            sb2.append(imageList[j][1]).append("-");
        }


        return new String [] {String.valueOf(sb1), String.valueOf(sb2)};
    }

    @Override
    public String[] getNewSaveFile(String baseUrlPath, String baseLocalPath, String OriginalName) {

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
