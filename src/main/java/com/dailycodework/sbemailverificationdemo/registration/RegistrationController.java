package com.dailycodework.sbemailverificationdemo.registration;


import com.dailycodework.sbemailverificationdemo.event.RegistrationCompleteEvent;
import com.dailycodework.sbemailverificationdemo.event.listener.RegistrationCompleteEventListener;
import com.dailycodework.sbemailverificationdemo.registration.token.VerificationToken;
import com.dailycodework.sbemailverificationdemo.registration.token.VerificationTokenRepository;
import com.dailycodework.sbemailverificationdemo.user.User;
import com.dailycodework.sbemailverificationdemo.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {


    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;
    private final RegistrationCompleteEventListener eventListener;
    private final HttpServletRequest servletRequest;


    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request) {
        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success! Please, check your email to complete your registration";
    }

    @GetMapping("/verifyEmail")
    public String sendVerificationToken(@RequestParam("token") String token) {
        String url = applicationUrl(servletRequest)+"/register/resend-verification-token?token="+token;

        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()) {
            return "This account has already been verified, please, login.";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification link, <a href=\"" + url + "\"> Get a new verification link </a>";
    }

    @GetMapping("/resend-verification-token")
    public String resendVerificationToken(@RequestParam("token") String oldToken, final HttpServletRequest request)
            throws MessagingException, UnsupportedEncodingException {
        VerificationToken verificationToken =  userService.generateNewVerificationToken(oldToken);
        User theUser = verificationToken.getUser();
        resendVerificationTokenEmail(theUser, applicationUrl(request), verificationToken);
        return "A new verification link hs been sent to your email," +
                " please, check to complete your registration";
    }

    private void resendVerificationTokenEmail(User theUser, String applicationUrl, VerificationToken verificationToken)
            throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl+"/register/verifyEmail?token="+verificationToken.getToken();
        eventListener.sendVerificationEmail(url);
        log.info("Click the link to verify your registration :  {}", url);
    }


    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
