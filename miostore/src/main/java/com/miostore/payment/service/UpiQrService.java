package com.miostore.payment.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class UpiQrService {

    @Value("${mio.upi.id}")
    private String upiId;

    @Value("${mio.upi.payee-name:MIO Foods}")
    private String payeeName;

    public String generateUpiQr(String amount, String orderId, String note) {
        try {
            String upiUri = buildUpiUri(amount, orderId, note);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(upiUri, BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "png", baos);
            byte[] qrBytes = baos.toByteArray();

            // Encode to Base64 for frontend
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(qrBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error generating UPI QR", e);
        }
    }

    private String buildUpiUri(String amount, String orderId, String note) throws WriterException {
        String encodedPayeeName = payeeName.replace(" ", "%20");
        String encodedNote = note != null ? note.replace(" ", "%20") : "Order%20" + orderId;

        return String.format(
                "upi://pay?pa=%s&pn=%s&am=%s&cu=INR&tn=%s",
                upiId, encodedPayeeName, amount, encodedNote
        );
    }
}
