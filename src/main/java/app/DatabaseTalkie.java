package app;

import redis.clients.jedis.Jedis;

import java.security.MessageDigest;

/**
 * Created by netwave on 18/05/15.
 */

public class DatabaseTalkie {
    private Jedis m_jedis;

    public DatabaseTalkie()
    {
        m_jedis = new Jedis("localhost");
    }

    public DatabaseTalkie(Jedis jedis)
    {
        m_jedis = jedis;
    }

    public boolean store(String data_name, String data, String pwd )
    {
        Long ret = m_jedis.setnx(data_name+"_key", data);
        if (ret > 0) {
            try {
                MessageDigest pwd_code = MessageDigest.getInstance("MD5");
                pwd_code.update(pwd.getBytes());
                String set_ret = m_jedis.set(data_name + "_pwd", pwd_code.digest().toString());

                return set_ret.equals("OK")? true: false;
            }
            catch (Exception e)
            {
                m_jedis.del(data_name+"_key");
                return false;
            }
        }
        return false;

    }

    public String retrieve(String data_name, String pwd)
    {
        return "";
    }


}
