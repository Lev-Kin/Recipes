package recipes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void save(User user) {
        userRepo.save(user);
    }
}

