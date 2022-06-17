package service.impl;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Iterable<Post> findAll() {
        return postRepository.findAll();
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

    public List<Post> findByTitle(String title) {
        return postRepository.findAllByTitleContaining(title);
    }

    public List<Post> orderByLikes() {
        return postRepository.findAllByOrderByLikes();
    }

    public List<Post> orderByCreateAt() {
        return postRepository.findFourOfNewPost();
    }
}
