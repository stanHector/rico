package service.ricotunes.giftcards.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.WalletTransactions;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class EmailsService {

    private final JavaMailSender javaMailSender;

    public void sendEmailWithAttachment(WalletTransactions walletTransactions) throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(walletTransactions.getEmail());
//        helper.addBcc("OOloniyo@fhi360.org");
//        helper.addCc("NAdhekoyibo@fhi360.org");
//        helper.addCc("schikeluba@epicact2.org");
//        helper.addBcc("facility@fhi360web.onmicrosoft.com");
//        helper.addBcc("ABU.IT@epicact2.org");
        helper.setSubject("Confirm Withdraw " + walletTransactions.getStatus());
        helper.setText(
                "<br><br>\n" +
                        "\t\t\t\t\t\t\tHi \n" + walletTransactions.getAccountName() + "," + "\n" +
                        "<br>\n" +
                        "\t\t\t\t\t\t\tYou have has been " + walletTransactions.getStatus().toLowerCase() + ", details are below:\n" +
                        "\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t</div>\n" +
                        "\t<table rules=\"all\" style=\"border-color: #666;\" cellpadding=\"10\">\n" +
                        "\t\t<tr style=\"background: #eee;\">\n" +
                        "\t\t\t<td>Amount</td>\n" +
                        "\t\t\t<td>Status</td>\n" +
                        "\t\t\t<td>Date</td>\n" +
                        "\t\t</tr>\n" +
                        "\t<tr style=\"border-color: #666;\"background: #eee;\" cellpadding=\"10\">" +
                        "<td>" + walletTransactions.getAmount() +
                        "<td>" + walletTransactions.getStatus() +

                        "<td>" + LocalDate.now() +
                        "<td style=color:green>" + walletTransactions.getStatus() + "</tr>\n" +
                        "</tr>\n" +
                        "\t\t</table>\n" +
//                        "\t\t<br><br>" +
//                        "\t\t\t\t\t\t\t<img src=\"https://media-exp1.licdn.com/dms/image/C4D1BAQFl07u_40yfYw/company-background_10000/0/1624546064903?e=2159024400&v=beta&t=KtIapO9HHvIQg6SfBqaLkOtCHfEi8Cqq7gVR4T_o7Mw\" border=\"0\" alt=\"fhi\" style=\"margin:0;margin-left: 0px; max-width:388px;max-height:182px;\">\n" +
                        "<br>Thank you,<br>" +
//                        "\t\t\t\t\t\t\tFor Support Contact: globalservicedesk@fhi360.org" + "\n" +
                        "\t\t\t</tbody>", true);
        javaMailSender.send(msg);

    }
}
