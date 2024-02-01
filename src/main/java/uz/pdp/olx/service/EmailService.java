package uz.pdp.olx.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import uz.pdp.olx.enitiy.User;
import uz.pdp.olx.security.jwt.JwtTokenProvider;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final Configuration configuration;
    private final JwtTokenProvider jwtTokenProvider;
    @Value("${frontend.url}")
    private String verificationUrl;

    @SneakyThrows
    @Async
    public void sendEmailVerificationMessage(String username, String email) {
        TimeUnit.SECONDS.sleep(10);
        var helper = new MimeMessageHelper(mailSender.createMimeMessage());
        helper.setFrom("pdp@gmail.com");
        helper.setTo(email);
        Template template = configuration.getTemplate("mail/verification_email.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(
                template,
                Map.of("link", verificationUrl + jwtTokenProvider.generateTokenForEmail(
                        User.builder()
                                .username(username)
                                .email(email)
                                .build())
                )
        );
        helper.setText(html, true);
        mailSender.send(helper.getMimeMessage());
    }
}
