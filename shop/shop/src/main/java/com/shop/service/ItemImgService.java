package com.shop.service;

import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;


@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일업로드
        if(!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            //이미지 등록 시 uploadFile 메소드 호출 -> 저장된 파일에 이름을 imgName변수에 저장

            imgUrl = "/imges/item/" + imgName; //저장한 상품 이미지를 불러올 경로를 설정
            //C:/00shop/ 아래 item 폴더에 이미지를 저장하므로 상품이미지를 불러오는 경로로 /images/item/ 를 붙여줌
        }

        //상품 이미지정보 저장 - 입력받은 상품 이미지 정보를 저장
        //oriImgName: 실제 로컬에 저장된 상품 이미지 파일이름
        //imgName: 업로드했던 상품 이미지 파일의 원래이름
        //imgUrl: 업로드 결과 로컬에 저장된 상품 이미지파일을 불러오는 경로
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);

    }




}
