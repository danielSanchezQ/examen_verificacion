package app;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by netwave on 18/05/15.
 */
@Component
public class DatabaseTalkie {
    static private String   KEY = "_key";
    static private String   PWD = "_pwd";
    static private int      h24 = 86400;

    private Jedis m_jedis;

    public DatabaseTalkie()
    {
        try {
            m_jedis = new Jedis("10.93.1.69 ", 6379);
            m_jedis.auth("WkS8Bkm9aj");
        }
        catch (Exception e)
        {
            m_jedis = new Jedis("localhost");
        }
        m_jedis.flushAll();
    }

    public DatabaseTalkie(Jedis jedis)
    {
        m_jedis = jedis;
    }

    public boolean store(String data_name, String data, String pwd )
    {
        Long ret = m_jedis.setnx(data_name+KEY, data);
        if (ret > 0) {
            try {
                m_jedis.expire(data_name + KEY, h24);
                String set_ret = m_jedis.set(data_name+PWD, DigestUtils.shaHex(pwd));
                m_jedis.expire(data_name+PWD, h24);
                return set_ret.equals("OK")? true : false;
            }
            catch (Exception e)
            {
                m_jedis.del(data_name+KEY);
                return false;
            }
        }
        return false;

    }

    public String retrieve(String data_name, String pwd)
    {
        try {
            String data_pwd = m_jedis.get(data_name + PWD);
            String pwd_md5 = DigestUtils.shaHex(pwd);
            if (data_pwd.equals(pwd_md5))
            {
                String ret = m_jedis.get(data_name+KEY);
                return ret;
            }
            return "";
        }
        catch (Exception e) {return "";}
    }
}
