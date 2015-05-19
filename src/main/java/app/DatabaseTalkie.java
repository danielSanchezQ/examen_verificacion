package app;

import redis.clients.jedis.Jedis;

import java.security.MessageDigest;

/**
 * Created by netwave on 18/05/15.
 */

public class DatabaseTalkie {
    static private String KEY = "_key";
    static private String PWD = "_pwd";

    private Jedis m_jedis;

    public DatabaseTalkie()
    {
        m_jedis = new Jedis("localhost", 26379);
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
                MessageDigest pwd_code = MessageDigest.getInstance("MD5");
                pwd_code.update(pwd.getBytes());
                String set_ret = m_jedis.set(data_name+PWD, pwd_code.digest().toString());

                return set_ret.equals("OK")? true: false;
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
        String data_pwd = m_jedis.get(data_name+PWD);
        try {
            MessageDigest pwd_code = MessageDigest.getInstance("MD5");
            pwd_code.update(pwd.getBytes());
            if (pwd_code.digest().toString().equals(data_pwd));
            {
                String ret = m_jedis.get(data_name+KEY);
                return ret;
            }
        }
        catch (Exception e) {return "";}
    }
}
