package app;


import junit.framework.TestCase;
import org.mockito.Mockito.*;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.security.MessageDigest;
/**
 * Created by netwave on 18/05/15.
 */
public class DatabaseTalkieTest extends TestCase {


    Jedis           jedis;
    DatabaseTalkie  dbt;

    public void setUp() throws Exception {
        super.setUp();
        jedis   = mock(Jedis.class);
        dbt     = new DatabaseTalkie(jedis);
        when(jedis.setnx(anyString(), anyString()))  .thenReturn(new Long(1));
        when(jedis.set(anyString(), anyString()))   .thenReturn("OK");
        when(jedis.get("test_key")).thenReturn("test_body");
        when(jedis.get(anyString())).thenReturn("");
        MessageDigest ret = MessageDigest.getInstance("MD5");
        ret.update("test".getBytes());
        when(jedis.get("test_pwd")).thenReturn(ret.digest().toString());
    }

    public void tearDown() throws Exception {

    }

    public void testStore() throws Exception {
        assertEquals(dbt.store("test", "test_body", "test"), true);
    }

    public void testRetrieve() throws Exception {
        assertEquals(dbt.retrieve("test", "test"), "test_body");
    }

    public void testRetrievefails() throws Exception {
        assertEquals(dbt.retrieve("test", "asddd"), "");
    }
}