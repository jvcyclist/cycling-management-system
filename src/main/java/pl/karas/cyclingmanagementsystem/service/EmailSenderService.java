package pl.karas.cyclingmanagementsystem.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String content);
}
