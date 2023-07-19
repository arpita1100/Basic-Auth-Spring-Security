package umunchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umunchat.user.CustomUser;
import umunchat.user.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser userDetails=userRepo.findByUsername(username);
        if(userDetails==null)
            throw new UsernameNotFoundException("user does not exist with this username");
        return userDetails;
    }
}
