import com.ritaja.xchangerate.api.CurrencyConverter;
import com.ritaja.xchangerate.api.CurrencyConverterBuilder;
import com.ritaja.xchangerate.util.Currency;
import com.ritaja.xchangerate.util.Strategy;
import org.yaml.snakeyaml.Yaml;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;

class CurrencyList{
    public String[] get(){
        return new String[]{"INR","AFN", "ALL", "AMD", "AED", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BOV", "BRL", "BSD", "BTN", "BWP", "BYR", "BZD", "CAD", "CDF", "CHE", "CHF", "CHW", "CLF", "CLP", "CNY", "COP", "COU", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IQD", "IRR", "ISK", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MXV", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "SSP", "STD", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "USN", "USS", "UYI", "UYU", "UZS", "VEF", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XBA", "XBB", "XBC", "XBD", "XBT", "XCD", "XDR", "XFU", "XOF", "XPD", "XPF", "XPT", "XSU", "XTS", "XUA", "XXX", "YER", "ZAR", "ZMW", "ZWD"};
    }
}

public class ApiHandler extends JFrame{
    String answer;
    private String API_KEY;
    Map<String,String> mainData;
    public String getConvertedValues(String amt,String ipVal,String opVal) {
        try{
            InputStream file = new FileInputStream("src/main/resources/config.yml");
            Yaml cfg = new Yaml();
            mainData = (Map<String, String>) cfg.load(file);
            API_KEY = mainData.get("API_KEY");
        }
        catch (FileNotFoundException fe){
            API_KEY = "043fe2d37862c40fe47dd5e2aeed484a";
            System.out.println(fe.getMessage());
        }
        CurrencyConverter cnv = new CurrencyConverterBuilder().strategy(Strategy.CURRENCY_LAYER_FILESTORE).accessKey(API_KEY).buildConverter();
        cnv.setRefreshRateSeconds(3600);
        try{
            answer = cnv.convertCurrency(new BigDecimal(amt), Currency.get(ipVal),Currency.get(opVal)).toString();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Error has been encountered while processing your request.");
            System.out.println(e);
        }
        return answer;
    }
}
