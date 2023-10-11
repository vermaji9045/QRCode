package Com.QR.Code.Controller;

import Com.QR.Code.Service.QRService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Base64;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class QRCodeCorntroller {



    @Autowired
    QRService qrService;
    @GetMapping("/")
    public String home()
    {
        return "index.html";
    }


    @RequestMapping(value = "/generate",method=POST)
    public String getQRCode(Model model, @RequestParam(value = "text") String text)
    {
        byte[] image=new byte[0];

        try{
            image=qrService.getQrCode(text,250,250);
        }catch (WriterException| IOException exception)
        {
          exception.printStackTrace();
        }

        String qrcode= Base64.getEncoder().encodeToString(image);

        model.addAttribute("qrcode",qrcode);

        return  "index.html";
    }
}
