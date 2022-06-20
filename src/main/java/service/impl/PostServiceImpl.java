package service.impl;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.PostRepository;
import service.PostService;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    public Page<Post> findByTitle(String title, Pageable pageable) {
        return postRepository.findAllByTitleContaining(title, pageable);
    }

    public Page<Post> orderByLikes(Pageable pageable) {
        return postRepository.findAllByOrderByLikes(pageable);
    }

    public List<Post> top4OrderByCreateAt() {
        return postRepository.findFourOfNewPost();
    }

    public List<Post> orderByCreateAt() {
        return postRepository.findFourOfNewPost();
    }

    public Page<Post> findByTitleAndCreateAt(String title, String begin, String end, Pageable pageable) {
        return postRepository.findAllByTitleContainingAndCreateAtBetween("%" + title + "%", begin, end, pageable);
    }

}
