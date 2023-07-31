package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
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

    @Transactional
    public void updateItem(UpdateItemDto itemDto) {
        Item findItem = itemRepository.findOne(itemDto.getItemId());
        // findItem.change(파라미터); -> setter 대신에 따라 메서드를 생성하여 값을 변경하는 것을 권장
        findItem.setName(itemDto.getName());
        findItem.setPrice(itemDto.getPrice());
        findItem.setStockQuantity(itemDto.getStockQuantity());
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
