package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item) {
        validateDuplicateItem(item); // 중복 상품 검증
        itemRepository.save(item);
        return item.getId();
    }

    private void validateDuplicateItem(Item item) {
        List<Item> findItems = itemRepository.findByName(item.getName());
        if(!findItems.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 상품입니다.");
        }
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
