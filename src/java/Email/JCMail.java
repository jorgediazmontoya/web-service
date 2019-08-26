package Email;

import java.io.File;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Session;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;

public class JCMail {

    private String from = "";//tu_correo@gmail.com
    private String password = "";//tu password: 123456 :)
    private String host = "";
    private String port = "";
    // destinatario1@hotmail.com,destinatario2@hotmail.com, destinatario_n@hotmail.com
    private InternetAddress[] addressTo;
    private InternetAddress[] BBC;
    private InternetAddress[] CC;
    private String Subject = "";//titulo del mensaje
    private String MessageMail = "";//contenido del mensaje

    public JCMail() {
    }

    public void SEND(String nombreFactura, String dirFacturaAutorizada, boolean sslHabilitado) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", getHost());
        props.put("mail.smtp.port", getPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        if (sslHabilitado) {
            props.put("mail.smtp.ssl.enable", "true");
        }
        props.put("mail.smtp.ssl.trust", getHost());
        //

        //
        SMTPAuthenticator auth = new SMTPAuthenticator(getFrom(), getPassword());
        //si no se le pone getInstance el sistema envia los correos por el primer servidor de correo
        //que utilizó
        Session session = Session.getInstance(props, auth);
        session.setDebug(false);
        //Se crea destino y origen del mensaje
        MimeMessage mimemessage = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(getFrom());
        mimemessage.setFrom(addressFrom);
        mimemessage.setRecipients(Message.RecipientType.TO, getAddressTo());

        mimemessage.setSubject(getSubject());
        // Se crea el contenido del mensaje
        MimeBodyPart mimebodypart = new MimeBodyPart();
        mimebodypart.setText(getMessage());
        mimebodypart.setContent(getMessage(), "text/html");

        File documento = new File(dirFacturaAutorizada);
        boolean existeDoc = false;
        BodyPart adjunto = new MimeBodyPart();
        if (documento.exists()) {
            existeDoc = true;

            adjunto.setDataHandler(
                    new DataHandler(new FileDataSource(dirFacturaAutorizada)));
            adjunto.setFileName(nombreFactura);
        }
        Multipart multipart = new MimeMultipart();

        dirFacturaAutorizada = dirFacturaAutorizada.replace(".xml", ".pdf");
        nombreFactura = nombreFactura.replace(".xml", ".pdf");
        BodyPart adjunto2 = new MimeBodyPart();
        adjunto2.setDataHandler(
                new DataHandler(new FileDataSource(dirFacturaAutorizada)));
        adjunto2.setFileName(nombreFactura);
        multipart.addBodyPart(adjunto2);

        multipart.addBodyPart(mimebodypart);
        if (existeDoc) {
            multipart.addBodyPart(adjunto);
        }

        mimemessage.setContent(multipart);
        mimemessage.setSentDate(new Date());
        if (BBC != null && BBC.length > 0) {
            mimemessage.addRecipients(Message.RecipientType.BCC, BBC);
        }
        if (CC != null && CC.length > 0) {
            mimemessage.addRecipients(Message.RecipientType.CC, CC);
        }
        Transport.send(mimemessage);

    }

    //remitente
    public void setFrom(String mail) {
        this.from = mail;
    }

    public String getFrom() {
        return this.from;
    }

    //Contraseña
    public void setPassword(char[] value) {
        this.setPassword(new String(value));
    }

    public String getPassword() {
        return this.password;
    }

    //destinatarios
    public void setTo(String mails) {
        String[] tmp = mails.split(",");
        setAddressTo(new InternetAddress[tmp.length]);
        for (int i = 0; i < tmp.length; i++) {
            try {
                getAddressTo()[i] = new InternetAddress(tmp[i]);
            } catch (AddressException ex) {
                System.out.println(ex);
            }
        }
    }

    public InternetAddress[] getTo() {
        return this.getAddressTo();
    }

    //titulo correo
    public void setSubject(String value) {
        this.Subject = value;
    }

    public String getSubject() {
        return this.Subject;
    }

    //contenido del mensaje
    public void setMessage(String value) {
        this.setMessageMail(value);
    }

    public String getMessage() {
        return this.getMessageMail();
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the addressTo
     */
    public InternetAddress[] getAddressTo() {
        return addressTo;
    }

    /**
     * @param addressTo the addressTo to set
     */
    public void setAddressTo(InternetAddress[] addressTo) {
        this.addressTo = addressTo;
    }

    /**
     * @return the BBC
     */
    public InternetAddress[] getBBC() {
        return BBC;
    }

    /**
     * @param BBC the BBC to set
     */
    public void setBBC(InternetAddress[] BBC) {
        this.BBC = BBC;
    }

    /**
     * @return the CC
     */
    public InternetAddress[] getCC() {
        return CC;
    }

    /**
     * @param CC the CC to set
     */
    public void setCC(InternetAddress[] CC) {
        this.CC = CC;
    }

    /**
     * @return the MessageMail
     */
    public String getMessageMail() {
        return MessageMail;
    }

    /**
     * @param MessageMail the MessageMail to set
     */
    public void setMessageMail(String MessageMail) {
        this.MessageMail = MessageMail;
    }

    public void setToCC(String mails) {
        if (mails != null) {
            String[] tmp = mails.split(",");
            setCC(new InternetAddress[tmp.length]);
            for (int i = 0; i < tmp.length; i++) {
                try {
                    getCC()[i] = new InternetAddress(tmp[i].trim());
                } catch (AddressException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public void setToBBC(String mails) {
        if (mails != null) {
            String[] tmp = mails.split(",");
            setBBC(new InternetAddress[tmp.length]);
            for (int i = 0; i < tmp.length; i++) {
                try {
                    getBBC()[i] = new InternetAddress(tmp[i].trim());
                } catch (AddressException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

}
