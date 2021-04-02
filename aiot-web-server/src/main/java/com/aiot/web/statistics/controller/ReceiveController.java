package com.aiot.web.statistics.controller;

import com.aiot.web.util.DataPushUtil;
import com.aiot.web.util.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: Frank
 * @Date: 2021/2/16
 */
@RestController
@RequestMapping("push")
public class ReceiveController {
    //OneNet Token
    private static String token ="wWJYbE7NxbAS0ogtejSJWQUU5vDkaiZA";

    //AES key
    private static String aeskey ="i1e64cfU6b7vvi0i0LxSy3Wll5myfftWpO3Yg1TxQ1Q";

    private static Logger logger = LoggerFactory.getLogger(ReceiveController.class);

    @Resource
    private Sender sender;

    /**
     * 功能描述：第三方平台数据接收。<p>
     *           <ul>注:
     *               <li>1.OneNet平台为了保证数据不丢失，有重发机制，如果重复数据对业务有影响，数据接收端需要对重复数据进行排除重复处理。</li>
     *               <li>2.OneNet每一次post数据请求后，等待客户端的响应都设有时限，在规定时限内没有收到响应会认为发送失败。
     *                    接收程序接收到数据时，尽量先缓存起来，再做业务逻辑处理。</li>
     *           </ul>
     * @param body 数据消息
     * @return 任意字符串。OneNet平台接收到http 200的响应，才会认为数据推送成功，否则会重发。
     */
    @PostMapping(value = "/receive")
    public String receive(@RequestBody String body) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        logger.info("data receive:  body String --- " +body);
        /************************************************
         *  解析数据推送请求，非加密模式。
         *  如果是明文模式使用以下代码
         **************************************************/
        /*************明文模式  start****************/
//        DataPushUtil.BodyObj obj = DataPushUtil.resolveBody(body, false);
//        logger.info("data receive:  body Object --- " +obj);
//        if (obj != null){
//            boolean dataRight = DataPushUtil.checkSignature(obj, token);
//            if (dataRight){
//                logger.info("data receive: content" + obj.toString());
//            }else {
//                logger.info("data receive: signature error");
//            }
//
//        }else {
//            logger.info("data receive: body empty error");
//        }
        /*************明文模式  end****************/


        /********************************************************
         *  解析数据推送请求，加密模式
         *
         *  如果是加密模式使用以下代码
         ********************************************************/
        /*************加密模式  start****************/
        DataPushUtil.BodyObj obj1 = DataPushUtil.resolveBody(body, true);
        //logger.info("data receive:  body Object--- " +obj1);
        if (obj1 != null){
            boolean dataRight1 = DataPushUtil.checkSignature(obj1, token);
            if (dataRight1){
                String msg = DataPushUtil.decryptMsg(obj1, aeskey);
                sender.send(msg);
                //logger.info("data receive: content" + msg);
            }else {
                logger.info("data receive:  signature error " );
            }
        }else {
            logger.info("data receive: body empty error" );
        }
        /*************加密模式  end****************/
        return "ok";
    }

    /**
     * 功能说明： URL&Token验证接口。如果验证成功返回msg的值，否则返回其他值。
     * @param msg 验证消息
     * @param nonce 随机串
     * @param signature 签名
     * @return msg值
     */
    @GetMapping(value = "/receive")
    public String check(@RequestParam(value = "msg") String msg,
                        @RequestParam(value = "nonce") String nonce,
                        @RequestParam(value = "signature") String signature) throws UnsupportedEncodingException {

        logger.info("url&token check: msg:{} nonce{} signature:{}",msg,nonce,signature);
        if (DataPushUtil.checkToken(msg,nonce,signature,token)){
            return msg;
        }else {
            return "error";
        }

    }
}
