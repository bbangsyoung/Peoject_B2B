package com.shop.service;

import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import com.shop.vo.ItemFormVo;
import com.shop.vo.ItemImgVo;
import com.shop.vo.ItemSearchVo;
<<<<<<< Updated upstream
=======
import com.shop.vo.MainItemVo;
>>>>>>> Stashed changes
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;


    //상품조회조건과 페이지 정보를 파라미터로 받아 상품데이터 조회
    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchVo itemSearchVo, Pageable pageable){
        return itemRepository.getAdminItemPage(itemSearchVo, pageable);
    }

<<<<<<< Updated upstream
=======
    @Transactional(readOnly = true)
    public Page<MainItemVo> getMainItemPage(ItemSearchVo itemSearchVo, Pageable pageable){
        return itemRepository.getMainItemPage(itemSearchVo, pageable);
    }

>>>>>>> Stashed changes

    public Long saveItem(ItemFormVo itemFormVo, List<MultipartFile> itemImgFileList) throws Exception{


        //상품 등록
        Item item = itemFormVo.createItem();
        itemRepository.save(item);

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }



    //등록된 상품 불러오기
    @Transactional(readOnly = true) //데이터를 읽어오는 트랜잭션을 읽기전용으로 설정.
    public ItemFormVo getItemDtl(Long itemId) {

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        //해당 상품의 이미지 조회. 등록순으로 가지고 오기 위해 상품이미지 아이디 오름차순으로 가져옴

        List<ItemImgVo> itemImgVoList = new ArrayList<>();

        //조회한 Iteming 객체를 ItemImgVo 객체로 만들어 리스트에 추가
        for(ItemImg itemImg : itemImgList) {
            ItemImgVo itemImgVo = ItemImgVo.of(itemImg);
            itemImgVoList.add(itemImgVo);
        }

        //상품아이디로 상품객체를 조회. 존재하지 않을땐 EntityNotFoundException 발생.
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormVo itemFormVo = ItemFormVo.of(item);
        itemFormVo.setItemImgVoList(itemImgVoList);
        return itemFormVo;
    }



    public Long updateItem(ItemFormVo itemFormVo, List<MultipartFile> itemImgFileList) throws Exception {
        //상품 수정
        Item item = itemRepository.findById(itemFormVo.getId()) //상품등록화면으로부터 전달받은 Id로 상품 객체 조회
                .orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormVo); //상품등록화면으로부터 전달받은 ItemFormVo를 통해 상품 객체 업데이트

        List<Long> itemImgIds = itemFormVo.getItemImgIds(); //상품이미지 아이디 리스트 조회

        //이미지등록
        for(int i=0; i<itemImgFileList.size(); i++) {
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
            //상품 이미지를 업데이트하기위해 updateItemImg() 메소드에 상품 이미지아이디와, 상품 이미지 파일정보를 파라미터로 전달
        }
        return item.getId();
    }


}
