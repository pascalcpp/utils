package com.github.chenlijia1111.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.github.chenlijia1111.utils.common.constant.ContentTypeConstant;
import com.github.chenlijia1111.utils.core.RandomUtil;
import com.github.chenlijia1111.utils.encrypt.MD5EncryptUtil;
import com.github.chenlijia1111.utils.http.HttpClientUtils;
import com.github.chenlijia1111.utils.http.HttpUtils;
import com.github.chenlijia1111.utils.pay.ali.ALiPayUtil;
import com.github.chenlijia1111.utils.pay.wx.PayType;
import com.github.chenlijia1111.utils.pay.wx.WXPayUtil;
import org.junit.Test;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

/**
 * @author 陈礼佳
 * @since 2019/9/15 16:02
 */
public class TestPay {

    @Test
    public void test1() {
        String orderNo = RandomUtil.createUUID();
        Map preOrder = WXPayUtil.createPreOrder("wxd3fdb22474bdd195",
                "1553017211", "商品1",
                "XfenDefeEYdtv7jHrWx0es2wJMTI7T7i", 100,
                "https://www.shixianly.cn/system", PayType.APP, "sa", orderNo,null);
        System.out.println(preOrder);
    }


    @Test
    public void test3() {
        Map map = HttpClientUtils.getInstance().putParams("userNum", "admin").putParams("password", "123456").
                doPost("http://58.250.17.31:8334/expertise/api/user/login").toMap();
        System.out.println(map);


        Map map1 = HttpClientUtils.getInstance().putParams("page", "1").putParams("limit", "10").
                putParams("userName", "管理员").
                putHeader("authentication", "e639514a859144fb814752063036577b").
                doGet("http://58.250.17.31:8334/expertise/api/user/listAll").toMap();
        System.out.println(map1);
    }


    @Test
    public void test4() {

        String APP_ID = "2019082666435508";
        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8md5bd1lowiY1fs2YLhrFWxliUp2dhmjnIozJIRAjIq6X3SFBbyKvD2pgFQeZqsCyb8H4OuvSZFjImZJu7EPWb/mDyrm1JUyQwt70AWSlL9YAA3aeffNWrCM4M7EfOmHSNkJkE4SF2uU92eXXyieBfcP8SAS/EvUIK938i7PGgDAHo6xUa1kB9GnR1LFtEWM6IxW40SYM1pWXRxyjR6Zy9xAieItFE10nUm54nRNq6kR0O7vd/wO7oiJewbOwDGdUJy+y/BtAk6W5IudcZHh0xx7xiX1btLvpgCswEcEgNaMHQkIfgPhZ25IgP6oI+HeLtKfrq5EiNFN4Y0dFRTpFAgMBAAECggEAXYQuLUxQmRErwPrPFtZFj/ZL7+TnImSzP2hyRoiOZ92alNKg2sEwgX0zUdEbo6Z3RgMEOwF8TSZUn6MGaNneCUYcgqzSyG9mN6Foy7SlIwNFKVyORKhPMHrKO+Plv4ZDWuhsoxLKc1TGtpSE0yr5y8uAkmdjcxsDRLn7qG3dBl9O2IjfKFhSq9J0MuotMa01Kx9Ud5Z9pCMYJGz7LcClHFsl9RkO6jMm1hAOEBsLmL47bLezyDFgs2f9UR8vApb5AXVBEDRPGiI8qVXbwyKApTOxb3xKg60/PGobMGxv8vD1W3qXPiK5IyO7iqzkQjiqpbiLG2DeEWlur7egwDsBQQKBgQDt7XVeRNBN1f8ManoADzyKAmA82SrB1PoBSkMr784KEtCKua4D8j7z939hZFZGmuLFlTM2c0jhfGZP08xrBZgbvgD5tWXi5BOSaVlHe04dC2Aq38KzO8AQ7ODnKm9gFo9x5e6XU8IJVyfwSJVic1kgsObtiKbRdQCUECIdpesDVwKBgQDK7T9i+Z1aC0Wn+xfjL/6kVruqbyFfF7bLcyrD/wJ5fAExdehetgMKM40wXvND5YF5vjyTB9m8HKDn5h3+6i/CwogAwYvjy+onIsTrsDHucnF9Lsj6x2j8VirddqNCKUeXggE2ImMdWXI5fRj057Po1DlddbSCRinKDJGz1VVpwwKBgQCFSLWsFm5+e/fBk9A+QkrOoSAaVimepdlbaaeRcGgCuqF6ZSP3b/gWITqJa3TtXozU1Iz43sIgFtkVtPdZqKuaNulC+XxXCx9vsHZkFc4fdSTtP6ZJU5p3oMsolFwJ/vlie8UZnsEfDGUCNRcCLM3sA02KGTgiNCIbz6Hc6/poRwKBgBmEZdEbVFcLTdIGuAVAdsPRiD2xRcAAamMA82K8AWKnXjsK+ZJXTuCMhPeNKQfjpzlY26MdfJqz+uPOQv1YE3NpJNL7g4y8ipArRhnhCRZT28ODC5DFXIVGjJNWpMbgC/mYRnC5OD9rjPp/qZC6RhxD+4auE2nCSuT2WagEbn4jAoGADlVyKfRoQgAIXb98s3sobsjQFsB3ySsQ+okSf7GFW2hCANRV/vxSUwNOuQ7fhuIcWq+IST3mWuwaAhOjpDv+oG/idQSblx2NPtgVNyse9xluVGV+gPwaiVanYfGnmS9xEEL7b0mfWQO6QeJkLQ69qU892Vk8BctYPyjetZ5o0e8=";
        String CHARSET = "UTF-8";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAntWGCd9E7dayalOUP2+48AqMEODJRFRV17OKQQ7bO9yoTu6+iyXvp0dqd6FFJycLTc9VTRoOon3Kp7oLnKe/Umb+zrX5o/xv06uQqoNfiUAuFPuRv6kK8w188q3koHoIB549r2G8a74wJuvBaczw4OOsarEuGgWPtICULpw3+u5XvYKIm/htQgLLA7aHpcpboGD1l+2aRo/ZFVt673INEuB/G124BDdZzG6wo2RRecjY0uXhBbF9WVCqkW/lqS9KRPoCt8lJ95MR1BhcWORuUHJNRBxMC+2yHPd+g4pVgIjUz5X1Fx8JKSSI428F9bQxsXPNybiwnFmhQ7DPBs9pMwIDAQAB";

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("sa");
        model.setSubject("sa");
        model.setOutTradeNo("asdasdsdasdsad");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("12");
        model.setPassbackParams("callback params");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("https://www.shixianly.cn/system/alipay/notify_url");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test6() {

        String appId = "wxd3fdb22474bdd195";
        String appSecret = "a3563455b057a976230c0cb5a828659f";
        String mchId = "1553017211";
        String partnerKey = "XfenDefeEYdtv7jHrWx0es2wJMTI7T7i";
        Map refund = WXPayUtil.refund(appId, mchId, partnerKey,
                new File("C:/Users/Administrator/Documents/Tencent Files/571740367/FileRecv/1553017211_20190903_cert/apiclient_cert.p12"),
                mchId, null, "ea35db7357df4a029a5cbe41046fa249", 2, 2);
        System.out.println(refund);
    }

    @Test
    public void test7() {
        String APP_ID = "2019082666435508";
        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8md5bd1lowiY1fs2YLhrFWxliUp2dhmjnIozJIRAjIq6X3SFBbyKvD2pgFQeZqsCyb8H4OuvSZFjImZJu7EPWb/mDyrm1JUyQwt70AWSlL9YAA3aeffNWrCM4M7EfOmHSNkJkE4SF2uU92eXXyieBfcP8SAS/EvUIK938i7PGgDAHo6xUa1kB9GnR1LFtEWM6IxW40SYM1pWXRxyjR6Zy9xAieItFE10nUm54nRNq6kR0O7vd/wO7oiJewbOwDGdUJy+y/BtAk6W5IudcZHh0xx7xiX1btLvpgCswEcEgNaMHQkIfgPhZ25IgP6oI+HeLtKfrq5EiNFN4Y0dFRTpFAgMBAAECggEAXYQuLUxQmRErwPrPFtZFj/ZL7+TnImSzP2hyRoiOZ92alNKg2sEwgX0zUdEbo6Z3RgMEOwF8TSZUn6MGaNneCUYcgqzSyG9mN6Foy7SlIwNFKVyORKhPMHrKO+Plv4ZDWuhsoxLKc1TGtpSE0yr5y8uAkmdjcxsDRLn7qG3dBl9O2IjfKFhSq9J0MuotMa01Kx9Ud5Z9pCMYJGz7LcClHFsl9RkO6jMm1hAOEBsLmL47bLezyDFgs2f9UR8vApb5AXVBEDRPGiI8qVXbwyKApTOxb3xKg60/PGobMGxv8vD1W3qXPiK5IyO7iqzkQjiqpbiLG2DeEWlur7egwDsBQQKBgQDt7XVeRNBN1f8ManoADzyKAmA82SrB1PoBSkMr784KEtCKua4D8j7z939hZFZGmuLFlTM2c0jhfGZP08xrBZgbvgD5tWXi5BOSaVlHe04dC2Aq38KzO8AQ7ODnKm9gFo9x5e6XU8IJVyfwSJVic1kgsObtiKbRdQCUECIdpesDVwKBgQDK7T9i+Z1aC0Wn+xfjL/6kVruqbyFfF7bLcyrD/wJ5fAExdehetgMKM40wXvND5YF5vjyTB9m8HKDn5h3+6i/CwogAwYvjy+onIsTrsDHucnF9Lsj6x2j8VirddqNCKUeXggE2ImMdWXI5fRj057Po1DlddbSCRinKDJGz1VVpwwKBgQCFSLWsFm5+e/fBk9A+QkrOoSAaVimepdlbaaeRcGgCuqF6ZSP3b/gWITqJa3TtXozU1Iz43sIgFtkVtPdZqKuaNulC+XxXCx9vsHZkFc4fdSTtP6ZJU5p3oMsolFwJ/vlie8UZnsEfDGUCNRcCLM3sA02KGTgiNCIbz6Hc6/poRwKBgBmEZdEbVFcLTdIGuAVAdsPRiD2xRcAAamMA82K8AWKnXjsK+ZJXTuCMhPeNKQfjpzlY26MdfJqz+uPOQv1YE3NpJNL7g4y8ipArRhnhCRZT28ODC5DFXIVGjJNWpMbgC/mYRnC5OD9rjPp/qZC6RhxD+4auE2nCSuT2WagEbn4jAoGADlVyKfRoQgAIXb98s3sobsjQFsB3ySsQ+okSf7GFW2hCANRV/vxSUwNOuQ7fhuIcWq+IST3mWuwaAhOjpDv+oG/idQSblx2NPtgVNyse9xluVGV+gPwaiVanYfGnmS9xEEL7b0mfWQO6QeJkLQ69qU892Vk8BctYPyjetZ5o0e8=";
        String CHARSET = "UTF-8";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAntWGCd9E7dayalOUP2+48AqMEODJRFRV17OKQQ7bO9yoTu6+iyXvp0dqd6FFJycLTc9VTRoOon3Kp7oLnKe/Umb+zrX5o/xv06uQqoNfiUAuFPuRv6kK8w188q3koHoIB549r2G8a74wJuvBaczw4OOsarEuGgWPtICULpw3+u5XvYKIm/htQgLLA7aHpcpboGD1l+2aRo/ZFVt673INEuB/G124BDdZzG6wo2RRecjY0uXhBbF9WVCqkW/lqS9KRPoCt8lJ95MR1BhcWORuUHJNRBxMC+2yHPd+g4pVgIjUz5X1Fx8JKSSI428F9bQxsXPNybiwnFmhQ7DPBs9pMwIDAQAB";

        AlipayTradeRefundResponse desc = ALiPayUtil.refund(APP_ID, APP_PRIVATE_KEY, ALIPAY_PUBLIC_KEY,"f3d49702007f40ad83492c5221943d72",
                null, "100.01", "desc");
        System.out.println(desc.getBody());
        System.out.println(desc.getMsg());
    }


    @Test
    public void test8(){
        //1574216248657
        //1574216309861
        System.out.println(System.currentTimeMillis());
    }


    @Test
    public void test9(){
        String APP_ID = "2019082666435508";
        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8md5bd1lowiY1fs2YLhrFWxliUp2dhmjnIozJIRAjIq6X3SFBbyKvD2pgFQeZqsCyb8H4OuvSZFjImZJu7EPWb/mDyrm1JUyQwt70AWSlL9YAA3aeffNWrCM4M7EfOmHSNkJkE4SF2uU92eXXyieBfcP8SAS/EvUIK938i7PGgDAHo6xUa1kB9GnR1LFtEWM6IxW40SYM1pWXRxyjR6Zy9xAieItFE10nUm54nRNq6kR0O7vd/wO7oiJewbOwDGdUJy+y/BtAk6W5IudcZHh0xx7xiX1btLvpgCswEcEgNaMHQkIfgPhZ25IgP6oI+HeLtKfrq5EiNFN4Y0dFRTpFAgMBAAECggEAXYQuLUxQmRErwPrPFtZFj/ZL7+TnImSzP2hyRoiOZ92alNKg2sEwgX0zUdEbo6Z3RgMEOwF8TSZUn6MGaNneCUYcgqzSyG9mN6Foy7SlIwNFKVyORKhPMHrKO+Plv4ZDWuhsoxLKc1TGtpSE0yr5y8uAkmdjcxsDRLn7qG3dBl9O2IjfKFhSq9J0MuotMa01Kx9Ud5Z9pCMYJGz7LcClHFsl9RkO6jMm1hAOEBsLmL47bLezyDFgs2f9UR8vApb5AXVBEDRPGiI8qVXbwyKApTOxb3xKg60/PGobMGxv8vD1W3qXPiK5IyO7iqzkQjiqpbiLG2DeEWlur7egwDsBQQKBgQDt7XVeRNBN1f8ManoADzyKAmA82SrB1PoBSkMr784KEtCKua4D8j7z939hZFZGmuLFlTM2c0jhfGZP08xrBZgbvgD5tWXi5BOSaVlHe04dC2Aq38KzO8AQ7ODnKm9gFo9x5e6XU8IJVyfwSJVic1kgsObtiKbRdQCUECIdpesDVwKBgQDK7T9i+Z1aC0Wn+xfjL/6kVruqbyFfF7bLcyrD/wJ5fAExdehetgMKM40wXvND5YF5vjyTB9m8HKDn5h3+6i/CwogAwYvjy+onIsTrsDHucnF9Lsj6x2j8VirddqNCKUeXggE2ImMdWXI5fRj057Po1DlddbSCRinKDJGz1VVpwwKBgQCFSLWsFm5+e/fBk9A+QkrOoSAaVimepdlbaaeRcGgCuqF6ZSP3b/gWITqJa3TtXozU1Iz43sIgFtkVtPdZqKuaNulC+XxXCx9vsHZkFc4fdSTtP6ZJU5p3oMsolFwJ/vlie8UZnsEfDGUCNRcCLM3sA02KGTgiNCIbz6Hc6/poRwKBgBmEZdEbVFcLTdIGuAVAdsPRiD2xRcAAamMA82K8AWKnXjsK+ZJXTuCMhPeNKQfjpzlY26MdfJqz+uPOQv1YE3NpJNL7g4y8ipArRhnhCRZT28ODC5DFXIVGjJNWpMbgC/mYRnC5OD9rjPp/qZC6RhxD+4auE2nCSuT2WagEbn4jAoGADlVyKfRoQgAIXb98s3sobsjQFsB3ySsQ+okSf7GFW2hCANRV/vxSUwNOuQ7fhuIcWq+IST3mWuwaAhOjpDv+oG/idQSblx2NPtgVNyse9xluVGV+gPwaiVanYfGnmS9xEEL7b0mfWQO6QeJkLQ69qU892Vk8BctYPyjetZ5o0e8=";
        String CHARSET = "UTF-8";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAntWGCd9E7dayalOUP2+48AqMEODJRFRV17OKQQ7bO9yoTu6+iyXvp0dqd6FFJycLTc9VTRoOon3Kp7oLnKe/Umb+zrX5o/xv06uQqoNfiUAuFPuRv6kK8w188q3koHoIB549r2G8a74wJuvBaczw4OOsarEuGgWPtICULpw3+u5XvYKIm/htQgLLA7aHpcpboGD1l+2aRo/ZFVt673INEuB/G124BDdZzG6wo2RRecjY0uXhBbF9WVCqkW/lqS9KRPoCt8lJ95MR1BhcWORuUHJNRBxMC+2yHPd+g4pVgIjUz5X1Fx8JKSSI428F9bQxsXPNybiwnFmhQ7DPBs9pMwIDAQAB";

        String uuid = RandomUtil.createUUID();
        AlipayResponse alipayResponse = ALiPayUtil.transferToBank(APP_ID, APP_PRIVATE_KEY, ALIPAY_PUBLIC_KEY, "123456", "chen", uuid, "1", "remark");
        System.out.println(alipayResponse.isSuccess());
        System.out.println(alipayResponse.getBody());
        System.out.println(alipayResponse.getMsg());
    }


    @Test
    public void test10() {

        String appId = "wx5caed73a638f7765";
        String appSecret = "888d775ffecafd367e8b65f24fb970eb";
        String mchId = "1580913951";
        String partnerKey = "MGzfLBacu3Jfdw3Wf3jqqYPsAortu2ZT";
        /*Map map = WXPayUtil.fieldRSAPublicKey(mchId,
                "D:\\公司资料\\乔迪课堂\\代码\\qiaodiJava\\jody-app\\src\\main\\resources\\wechatFiles\\apiclient_cert.p12",
                partnerKey);
        System.out.println(map);*/

        //应付金额 假定一块钱
        Double payable = 1.0;
        //商家订单号  商户订单号要保存起来，可以存在订单里，到时候回调的时候根据商户订单号去查询支付信息
        String outTradeNo = RandomUtil.createRandomName();
        //转为分为单位
        payable = payable * 100;
        //回调地址
//        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String requestUrlPrefix = "http://127.0.0.1/test";
        //改的时候记得改回调地址，回调地址不要设拦截器，要放开
        String notifyUrl = requestUrlPrefix + "/wxPay/payNotify";
        String body = "乔迪课堂";
        Map preOrder = WXPayUtil.createPreOrder(appId, mchId, body, partnerKey, payable.intValue(), notifyUrl, PayType.APP, null, outTradeNo, null);
        System.out.println(preOrder);

        //appid=wx5caed73a638f7765&body=乔迪课堂&mch_id=1580913951&nonce_str=ae3dfd48644c44c6bae0cf44e4194cec&notify_url=http://127.0.0.1/test/wxPay/payNotify&out_trade_no=440496050314940416&sign_type=MD5&spbill_create_ip=127.0.0.1&total_fee=100&trade_type=APP
        //appid=wx5caed73a638f7765&body=乔迪课堂&mch_id=1580913951&nonce_str=ae3dfd48644c44c6bae0cf44e4194cec&notify_url=http://127.0.0.1/test/wxPay/payNotify&out_trade_no=440496050314940416&sign_type=MD5&spbill_create_ip=127.0.0.1&total_fee=100&trade_type=APP&key=1580913951
    }


    @Test
    public void test11(){
        String str = "appid=wx5caed73a638f7765&body=乔迪课堂&mch_id=1580913951&nonce_str=ae3dfd48644c44c6bae0cf44e4194cec&notify_url=http://127.0.0.1/test/wxPay/payNotify&out_trade_no=440496050314940416&sign_type=MD5&spbill_create_ip=127.0.0.1&total_fee=100&trade_type=APP&key=MGzfLBacu3Jfdw3Wf3jqqYPsAortu2ZT";
        String s = MD5EncryptUtil.MD5StringToHexString(str);
        System.out.println(s);
    }

}
