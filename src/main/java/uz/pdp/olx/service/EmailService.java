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
import uz.pdp.olx.enitiy.Authentication;
import uz.pdp.olx.enitiy.User;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final Configuration configuration;
    private final AuthService authService;

    @Value("${frontend.url}")
    private String verificationUrl;

    @Value("${frontend.support-email}")
    private String supportEmail;

    @SneakyThrows
    @Async
    public void sendEmailVerificationMessage(final User user) {
        TimeUnit.SECONDS.sleep(10);
        var helper = new MimeMessageHelper(mailSender.createMimeMessage());
        helper.setFrom("pdp@gmail.com");
        helper.setTo(user.getEmail());
        Template template = configuration.getTemplate("mail/verification_email.ftl");
        Authentication authentication = authService.createAuthentication(user);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(
                template,
                Map.of("verification_link", verificationUrl + authentication.getToken(),
                        "support_email", supportEmail)
        );
        helper.setText(html, true);
        mailSender.send(helper.getMimeMessage());
        log.info("[{}] email sent", Thread.currentThread().getName());
    }
}
