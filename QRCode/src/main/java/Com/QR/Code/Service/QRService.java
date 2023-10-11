package Com.QR.Code.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRService {

    public  byte[] getQrCode(String text,int width,int height) throws WriterException, IOException
    {
        QRCodeWriter qr=new QRCodeWriter();
        BitMatrix bit=qr.encode(text, BarcodeFormat.QR_CODE,width,height);
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        MatrixToImageConfig config=new MatrixToImageConfig(0xFF000000,0xFFFFFFFF);

        MatrixToImageWriter.writeToStream(bit,"PNG",byteArrayOutputStream,config);

        byte[] pngData=byteArrayOutputStream.toByteArray();

        return pngData;
    }
}
