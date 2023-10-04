package peaksoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
//        System.out.println("Сиздин кодунуз ийгиликтуу иштеди!");


        final String from = "abukubanychbekov53@gmail.com";
        String to = "gafurov.daniel28@gmail.com";
        String host = "smtp.gmail.com";
        String smtpPort = "465";

        Properties properties = new Properties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",smtpPort);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(
                properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,"fkzdsrrfppczapkq");
                    }
                }
        );

        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Message from BOSS");
//            // Текст сообщения
//            MimeBodyPart textPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart();
            String messageText = "<h1 style='font-size: 24px;'>Just Donnie че ты там делаешь бро!</h1>"
                    + "<p style='font-size: 18px;'>Кысталак.  Мен жава код менен gmail га сообщение жонотуп уйрондум!</p>";
            textPart.setContent(messageText, "text/html; charset=utf-8");//
//            // Скачиваем фотографию с URL и сохраняем как локальный файл
//            String imageUrl = "https://scontent.ffru1-3.fna.fbcdn.net/v/t39.30808-6/310989196_1093018041351635_3270122294714027634_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=5614bc&_nc_ohc=_DPJgk9CRdMAX8lPPJW&_nc_ht=scontent.ffru1-3.fna&oh=00_AfBn6Jg4hW-HBpfoqOhqU7wx_8CA-ObjARXanjbWBb3tcw&oe=6520DE6F.jpg";
//            String localImagePath = "local_image.jpg"; // Путь к сохранению локального файла
//
//            try {
//                URL url = new URL(imageUrl);
//                URLConnection connection = url.openConnection();
//                try (InputStream in = connection.getInputStream()) {
//                    Files.copy(in, Path.of(localImagePath), StandardCopyOption.REPLACE_EXISTING);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

//            // Вложение (фото)
//            MimeBodyPart attachmentPart = new MimeBodyPart();
//            FileDataSource fileDataSource = new FileDataSource(localImagePath);
//            attachmentPart.setDataHandler(new DataHandler(fileDataSource));
//            attachmentPart.setFileName("Boss.jpg"); // Замените название на свое

            // Создаем Multipart и добавляем текст и вложение
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
//            multipart.addBodyPart(attachmentPart);
//
//            // Устанавливаем Multipart как контент сообщения

            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

}
