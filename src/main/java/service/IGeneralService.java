package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService<T> { // interface sử dụng generic mô tả các phương chung mà tất cả các service cần có
    Page<T> findAll(Pageable pageable);

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);
}
