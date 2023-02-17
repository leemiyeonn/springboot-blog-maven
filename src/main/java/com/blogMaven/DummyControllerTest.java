package com.blogMaven;

import com.blogMaven.model.RoleType;
import com.blogMaven.model.User;
import com.blogMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 존재하지 않습니다";
        }

        return "삭제가 완료되었습니다. id : " + id;
    }


    @Transactional // jpa 더티 체킹 - 변경 감지
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        System.out.println(" id : " + requestUser.getId());
        System.out.println(" password : " + requestUser.getPassword());
        System.out.println(" email : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //userRepository.save(user);
        return user;
    }
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList (
            @PageableDefault(
                    size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<User> pagingUser = userRepository.findAll(pageable);

        return pagingUser.getContent();
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다. id : " + id));
    }

    @PostMapping("/dummy/join")
    public String join(@RequestBody User user){
        System.out.println("id : " + user.getId() + "\n");
        System.out.println("username : " + user.getUsername() + "\n");
        System.out.println("password : " + user.getPassword() + "\n");
        System.out.println("email : " + user.getEmail() + "\n");
        System.out.println("role : " + user.getRole() + "\n");
        System.out.println("createDate : " + user.getCreateDate() + "\n");

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
