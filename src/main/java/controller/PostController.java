package controller;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.impl.PostServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostServiceImpl postService;

//- Dùng SDR, Thymeleaf
//1. Làm giao diện cho chức năng vừa tìm theo title, vừa tìm theo khoảng thời gian. Nếu thiếu 1 trong 2 thì tìm theo cái kia
//2. Tìm theo khoảng thời gian và sắp xếp từ cũ đến mới
//3. Làm giao diện cho chắc năng vừa tìm theo title, vừa tìm theo khoảng thời gian và sắp xếp từ cũ đến mới
//4. Phân trang 5 sản phẩm 1 trang
//5. Phân trang 5 sản phẩm 1 trang, sắp xếp từ cũ đến mới
//6. Tìm kiếm theo title và phân trang 5 sản phẩm 1 trang


    @GetMapping
    public ModelAndView showList(@PageableDefault(value = 5) Pageable pageable) {
        return new ModelAndView("/post/list", "posts", postService.findAll(pageable));
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
    public ModelAndView search(@RequestParam String title, @PageableDefault(value = 5) Pageable pageable) {
        return new ModelAndView("/post/search", "posts", postService.findByTitle(title, pageable));
    }

    @GetMapping("/title-create-at")
    public ModelAndView searchTitleAndCreateAt(@RequestParam String title, @RequestParam String begin ,@RequestParam String end, @PageableDefault(value = 5) Pageable pageable) {
        return new ModelAndView("/post/search", "posts", postService.findByTitleAndCreateAt(title, begin,end, pageable));
    }

    @GetMapping("/order")
    public ModelAndView orderByLikes(@PageableDefault(value = 5) Pageable pageable) {
        return new ModelAndView("/post/search", "posts", postService.orderByLikes(pageable));
    }

    @GetMapping("/top-4-new")
    public ModelAndView top4OrderByCreateAt() {
        return new ModelAndView("/post/search", "posts", postService.top4OrderByCreateAt());
    }

    @GetMapping("/create-at")
    public ModelAndView orderByCreateAt() {
        return new ModelAndView("/post/search", "posts", postService.orderByCreateAt());
    }
}
