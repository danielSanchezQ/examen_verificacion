package app;

import redis.clients.jedis.Jedis;

/**
 * Created by netwave on 18/05/15.
 */

public class DatabaseTalkie {
    private Jedis m_jedis;

    public DatabaseTalkie()
    {
        m_jedis = new Jedis("localhost");
    }

    public boolean store(String data_name,String data, String pwd )
    {
        return true;

    }

    public String retrieve(String data_name, String pwd)
    {
        return "";
    }


}
