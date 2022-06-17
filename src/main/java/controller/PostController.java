package controller;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.PostService;
import service.impl.PostServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @GetMapping
    public ModelAndView showList() {
        return new ModelAndView("/post/list", "posts", postService.findAll());
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(Post post) {
        return new ModelAndView("/post/create", "post", post);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        return new ModelAndView("/post/edit", "post", postService.findById(id));
    }

    @PostMapping("/save")
    public ModelAndView create(Post post) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        String createAt = localDateTime.format(dateFormatter);
        post.setCreateAt(createAt);
        postService.save(post);
        return new ModelAndView("redirect:/posts");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        return new ModelAndView("/post/delete", "post", postService.findById(id));
    }

    @PostMapping("/delete")
    public ModelAndView delete(Post post) {
        postService.remove(post.getId());
        return new ModelAndView("redirect:/posts");
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String title) {
        return new ModelAndView("/post/search", "posts", postService.findByTitle(title));
    }

    @GetMapping("/order")
    public ModelAndView orderByLikes() {
        return new ModelAndView("/post/search", "posts", postService.orderByLikes());
    }

    @GetMapping("/new-post")
    public ModelAndView orderByCreateAt() {
        return new ModelAndView("/post/search", "posts", postService.orderByCreateAt());
    }

}
