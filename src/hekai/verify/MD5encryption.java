package hekai.verify;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：MD5encryption.java
 * 文件标识：无
 * 内容摘要：该类的作用是对传进来的字符串进行MD5加密操作
 * 其它说明：MD5属于哈希算法的一种,为不可逆的加密运算
 * 因此无法通过哈希值逆运算得到密码明文
 * 只能通过MD5值的比较来判断密码是否正确
 * 确保了密码的安全性
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class MD5encryption {
    public String MD5encrypt(String s)
    {
        if(s.isEmpty())
        {
            return "";
        }
        MessageDigest md5;
        try {
            md5=MessageDigest.getInstance("MD5");
            byte[] bytes=md5.digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuilder res= new StringBuilder();
            for(byte b:bytes)
            {
                String temp=Integer.toHexString(b&0xff);
                if(temp.length()==1)
                {
                    temp="0"+temp;
                }
                res.append(temp);
            }
            return res.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
