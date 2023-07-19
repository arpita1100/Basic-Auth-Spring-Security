package umunchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import umunchat.user.CustomUser;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailService userDetailService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("authentication /..............");
        String username= authentication.getName();
        String password=authentication.getCredentials().toString();
        System.out.println("username "+username);
        System.out.println("password "+password);
        CustomUser userDetails=userDetailService.loadUserByUsername(username);
        String validatingToken=tokenProvider.createBasicAuthToken(username,password);
        if(validatingToken.equals(userDetails.getToken()))
            return new UsernamePasswordAuthenticationToken(userDetails,password);
        else
            throw new BadCredentialsException("Not a valid user");
//        String validatingToken=tokenProvider.createBasicAuthToken(userDetails.getUsername(),userDetails.getPassword());
//        System.out.println("token  = "+validatingToken);
//        System.out.println("authentication executed");
        //        if(password.equals(tokenProvider.createBasicAuthToken(username,password))
//        return new UsernamePasswordAuthenticationToken(username,null,);
//        if(password.equals(validatingToken))
//            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userDetails,password);
//            System.out.println(token);
//            return token;
//        return null;
//        else {
//            System.out.println("something went wrong !");
//            throw new RuntimeException("User is not valid");
//        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
