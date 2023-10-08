package com.codegym.casestudy.service.account;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.model.account.Account;
import com.codegym.casestudy.repository.account.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class AccountService implements IAccountService {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public Page<IAccountDto> searchByEmail(Pageable pageable, String keyword) {
        return accountRepository.findAccountByEmailContaining(pageable, "%" + keyword + "%");
    }

    @Override
    public Account findById(int deleteId) {
        return accountRepository.findById(deleteId).orElse(null);
    }

    @Override
    public void deleteAccount(Account account) {
        accountRepository.deleteAccount(account);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public void addAccount(Account account) {
        String newPass = passwordEncoder.encode(account.getPassword());
        accountRepository.addAccount(account.getEmail(), newPass,account.getCreateDate());
    }

    @Override
    public boolean testPass(String email, String currentPassword) {
        Account account = accountRepository.findAccountByEmail(email);
        System.out.println(email);
        String oldPassEncoder = account.getPassword();
        boolean isMatch = passwordEncoder.matches(currentPassword, oldPassEncoder);
        System.out.println(isMatch);
        return isMatch;
    }

    @Override
    public void changePass(String email, String newPassword) {
        Account account = accountRepository.findAccountByEmail(email);
        System.out.println(passwordEncoder.encode(newPassword));
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }
    @Override
    public void sendEmail(String to, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (javax.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String sendEmailAndReturnCode(String to) {
        // Sinh mã ngẫu nhiên
        String code = generateRandomCode(4); // Mã có độ dài 4
        // Tạo nội dung email
        String body = "<fieldset style=\"border: 2px solid #17a2b8 ;color: #00394f;background-image:url('https://i.pinimg.com/originals/1d/63/94/1d639410cc3e6be49c4dcbab4f0f06d0.png');height:430px;width:600px\">\n" +
                "    <legend style=\"text-align: center\">\n" +
                "        <h1 style=\"color: #00394f\">Da Nang Drift</h1>\n" +
                "    </legend>\n" +
                "    <legend>\n" +
                "        <h4>\n" +
                "            Chào bạn,\n" +
                "            <br/>\n" +
                "            <br/>\n" +
                "            Chúng tôi rất vui được gửi đến bạn mã xác nhận tài khoản tại Da Nang Drift. <br/><br/>\n" +
                "\n" +
                "            <span style=\"font-weight: bold; color: #008000\"> Đây là mã xác nhận đặc biệt dành riêng cho bạn: " + code + "</span>\n" +
                "        </h4>\n" +
                "        <h4>\n" +
                "            Xin lưu ý không chia sẻ mã này với bất kỳ ai khác để đảm bảo an toàn \n" +
                "        <h4>và bảo mật cho tài khoản của bạn!\n" +
                "        </h4>\n" +
                "        <h4>Mọi thắc mắc hoặc yêu cầu hỗ trợ, hãy liên hệ với chúng tôi.\n" +
                "        <h4>Chúng tôi sẽ sẵn lòng giúp bạn.\n" +
                "        <h4>Xin chân thành cảm ơn và trân trọng!\n" +
                "        <h4>Da Nang Drift\n" +
                "    </legend>\n" +
                "</fieldset>";
        // Cấu hình subject
        String subject = "DA NANG DRIFT - Thay đổi mật khẩu!";
        sendEmail(to, subject, body);
        return code;
    }

    @Override
    public void forgotPassword(Account account) {
    account.setPassword(passwordEncoder.encode(account.getPassword()));
    accountRepository.save(account);
    }

    @Override
    public void createAccount(Account newAccount, int role) {
    String newPass = passwordEncoder.encode(newAccount.getPassword());
    accountRepository.createAccount(newAccount.getEmail(),newPass,role);
    }

    private String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }


}
