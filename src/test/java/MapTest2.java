import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import org.junit.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MapTest2 extends TestCase {
    /*
    Old test case

    public void test_map () throws Exception {
        Map<Object, Object> map = JSON.parseObject("{1:\"2\",\"3\":4,'5':6}", new TypeReference<Map<Object, Object>>() {});
        Assert.assertEquals("2", map.get(1));
        Assert.assertEquals(4, map.get("3"));
        Assert.assertEquals(6, map.get("5"));
    }
     */

    /* The instance to be tested is not needed because the tested method is static*/

    private String jsonObject;
    private TypeReference<Map<Object,Object>> typeReference;
    private Object[] keys;
    private Object[] expectedValues;

    public MapTest2 (String jsonObject, TypeReference<Map<Object,Object>> typeReference, Object[] keys, Object[] expectedValues){
        configureTestClass(jsonObject, typeReference, keys, expectedValues);
    }

    private void configureTestClass(String jsonObject, TypeReference<Map<Object,Object>> typeReference, Object[] keys, Object[] expectedValues){
        this.jsonObject = jsonObject;
        this.typeReference = typeReference;
        this.keys = keys;
        this.expectedValues = expectedValues;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters(){
        return Arrays.asList(new Object[][] {
                // jsonObject (string), typeReference, expected (array)
                {"{1:\"2\",\"3\":4,'5':6}", new TypeReference<Map<Object, Object>>() {}, new Object[]{1, "3", "5"}, new Object[]{"2", 4, 6} }
        });
    }

    @Test
    public void test_map () throws Exception {
        Map<Object, Object> map = JSON.parseObject(this.jsonObject, this.typeReference);
        for (int i = 0; i < this.keys.length; i++){
            Assert.assertEquals(this.expectedValues[i], map.get(this.keys[i]));
        }
    }
}