package com.shop.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName,byte[] fileDate) throws Exception {
        UUID uuid = UUID.randomUUID(); //서로 다른 개체들을 구별하는 이름을 부여할 때 사용. 중복문제 해결가능.

        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String saveFileName = uuid.toString() + extension; //UUID로 받은 값 + 파일이름(+확장자) = 저장될 파일이름
        String fileUploadFullUrl = uploadPath + "/" + saveFileName;

        //바이트단위의 출력을 내보냄. 생성자로 파일이 저장될 위치와 파일의 이름을 넘겨 파일에 쓸 파일 출력 스트림을 만듬
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileDate); //fileDate를 파일 출력 스트립에 입력
        fos.close();
        return saveFileName; //업로드된 파일 이름을 반환
    }

    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath); //파일 저장경로를 이용해 파일객체를 생성

        if(deleteFile.exists()) { //해당 파일이 존재하면 파일삭제
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }





}
