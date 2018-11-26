package com.project_management.shoppingweb.service.Impl;

import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.model.Card;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import com.project_management.shoppingweb.repository.CardRepository;
import com.project_management.shoppingweb.service.CardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardServiceImpl implements CardService {
    @Resource
    private CardRepository cardRepository;

    @Override
    public List<Card> findByUserId(Integer userId) {
        return cardRepository.findByUserId(userId);
    }

    @Override
    public RequestResultVO insert(Card card) {
        cardRepository.save(card);

        Map map = new HashMap<>();
        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_200);
        map.put("cardId",card.getCardId());
        requestResultVO.setData(map);

        return requestResultVO;
    }

    @Override
    public RequestResultVO update(Card card) {
        Card oldCard = cardRepository.findByCardId(card.getCardId());
        if(card.getCardNum()!=null){
            oldCard.setCardNum(card.getCardNum());
        }
        if(card.getUserId()!=null){
            oldCard.setUserId(card.getUserId());
        }

        cardRepository.save(oldCard);

        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_300);
        return requestResultVO;
    }

    @Override
    public RequestResultVO delete(Integer cardId) {
        Card oldCard = cardRepository.findByCardId(cardId);
        cardRepository.delete(oldCard);

        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_400);
        return requestResultVO;
    }
}
