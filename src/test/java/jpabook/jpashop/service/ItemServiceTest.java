package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품등록_앨범() throws Exception {
        //given
        Album album = new Album();
        album.setName("albumName");

        //when
        Long savedName = itemService.saveItem(album);

        //then
        assertEquals(album, itemRepository.findOne(savedName));
    }

    @Test
    public void 상품등록_책() throws Exception {
        //given
        Book book = new Book();
        book.setName("bookName");

        //when
        Long savedName = itemService.saveItem(book);

        //then
        assertEquals(book, itemRepository.findOne(savedName));
    }

    @Test
    public void 상품등록_영화() throws Exception {
        //given
        Movie movie = new Movie();
        movie.setName("movieName");

        //when
        Long savedName = itemService.saveItem(movie);

        //then
        assertEquals(movie, itemRepository.findOne(savedName));
    }

}