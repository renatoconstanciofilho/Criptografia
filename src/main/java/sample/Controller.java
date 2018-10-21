package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Controller {

    @FXML
    private Button btnEncrypt;
    @FXML
    private Button btnDecrypt;
    @FXML
    private TextArea taPlainText;
    @FXML
    private TextArea taEncryptedText;
    @FXML
    private TextField tfEncryptionKey;
    @FXML
    private ComboBox<String> cbEncryptionMethod;

    public static String generateString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @FXML
    public void initialize() {
        cbEncryptionMethod.getItems().removeAll(cbEncryptionMethod.getItems());
        cbEncryptionMethod.getItems().addAll("AES", "OTP", "RC4", "3DES", "BlowFish");
        cbEncryptionMethod.getSelectionModel().select("AES");
    }

    @FXML
    private void handleBtnEncryptAction(ActionEvent ae) {
        String selectedEncryptionMethod = cbEncryptionMethod.getValue();
        switch (selectedEncryptionMethod) {
            case "OTP":
                EncriptaDecriptaOTP otp = new EncriptaDecriptaOTP();
                tfEncryptionKey.setText(otp.genKey(taPlainText.getLength()));
                taEncryptedText.setText(otp.criptografa(taPlainText.getText(), tfEncryptionKey.getText()));
                break;
            case "RC4":

                RC4 rc4 = new RC4();
                tfEncryptionKey.setText(generateString());
                try {
                    taEncryptedText.setText(rc4.encrypt(taPlainText.getText(), tfEncryptionKey.getText()).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                tfEncryptionKey.setText(generateString());
//                EncriptaDecriptaRC4 rc4 = new EncriptaDecriptaRC4(tfEncryptionKey.getText());
//                taEncryptedText.setText(rc4.criptografa(taPlainText.getText()));
                break;
            case "AES":
                AES aes = new AES();
                tfEncryptionKey.setText(generateString());
                try {
                    taEncryptedText.setText(aes.aesEncryptString(taPlainText.getText(), tfEncryptionKey.getText()));
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case "3DES":
                tfEncryptionKey.setText(generateString());
                TripleDES tripleDES = new TripleDES(tfEncryptionKey.getText());
                try {
                    taEncryptedText.setText(tripleDES.harden(taPlainText.getText()));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                }
                break;
            case "BlowFish":
                tfEncryptionKey.setText(generateString());
                BlowFish blowFish = new BlowFish(tfEncryptionKey.getText());
                taEncryptedText.setText(blowFish.Encrypt(taPlainText.getText()));
                break;
            default:
                taEncryptedText.setText("Nenhum tipo de criptografia selecionado");

        }
    }

    @FXML
    private void handleBtnDecryptAction(ActionEvent ae) throws Exception {
        String selectecEncryptionMethod = cbEncryptionMethod.getValue();
        switch (selectecEncryptionMethod) {
            case "OTP":
                EncriptaDecriptaOTP otp = new EncriptaDecriptaOTP();
                taPlainText.setText(otp.decriptografa(taEncryptedText.getText(), tfEncryptionKey.getText()));
                break;
            case "RC4":
//                TODO https://stackoverflow.com/questions/18571223/how-to-convert-java-string-into-byte
                RC4 rc4 = new RC4();
                try {
                    taPlainText.setText(rc4.decrypt(taEncryptedText.getText().getBytes(), tfEncryptionKey.getText()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                EncriptaDecriptaRC4 rc4 = new EncriptaDecriptaRC4(tfEncryptionKey.getText());
//                taPlainText.setText(rc4.descriptografa(taEncryptedText.getText()));
                break;
            case "AES":
                AES aes = new AES();
                try {
                    taPlainText.setText(aes.aesDecryptString(taEncryptedText.getText(), tfEncryptionKey.getText()));
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case "3DES":
                TripleDES tripleDES = new TripleDES(tfEncryptionKey.getText());
                taPlainText.setText(tripleDES.soften(taEncryptedText.getText()));
                break;
            case "BlowFish":
                BlowFish blowFish = new BlowFish(tfEncryptionKey.getText());
                taPlainText.setText(blowFish.Decrypt(taEncryptedText.getText()));
                break;
            default:
                taPlainText.setText("Nenhum tipo de criptografia selecionado");

        }
    }
}
