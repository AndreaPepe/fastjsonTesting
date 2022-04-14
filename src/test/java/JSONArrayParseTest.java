import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class JSONArrayParseTest extends TestCase {

    /**
    Old Test

    public void test_array() throws Exception {
        String text = "[{id:123}]";
        List<Map<String, Integer>> array = JSON.parseObject(text, new TypeReference<List<Map<String, Integer>>>() {});
        Assert.assertEquals(1, array.size());
        Map<String, Integer> map  = array.get(0);
        Assert.assertEquals(123, map.get("id").intValue());
    }*/

    /* The instance to be tested is not needed because the tested method is static*/
    private String jsonArray;
    private TypeReference<List<Map<String, Integer>>> typeReference;
    private int expectedOne;
    private int expectedTwo;

    public JSONArrayParseTest(String jsonArray, TypeReference<List<Map<String, Integer>>> typeReference, int expectedOne, int expectedTwo){
        configureTestClass(jsonArray, typeReference, expectedOne, expectedTwo);
    }

    private void configureTestClass(String jsonArray, TypeReference<List<Map<String, Integer>>> typeReference, int expectedOne, int expectedTwo){
        this.jsonArray = jsonArray;
        this.typeReference = typeReference;
        this.expectedOne = expectedOne;
        this.expectedTwo = expectedTwo;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList( new Object[][]{
                // jsonArray, typeReference, expectedOne, expectedTwo
                {"[{id:123}]", new TypeReference<List<Map<String, Integer>>>() {}, 1, 123}
        });
    }

    @Test
    public void test_array() throws Exception {
        List<Map<String, Integer>> array = JSON.parseObject(this.jsonArray, this.typeReference);
        Assert.assertEquals(this.expectedOne, array.size());
        Map<String, Integer> map  = array.get(0);
        Assert.assertEquals(this.expectedTwo, map.get(map.keySet().iterator().next()).intValue());
    }
}
