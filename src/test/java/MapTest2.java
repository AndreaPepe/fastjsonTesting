import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapTest2 {
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

    public MapTest2 (){
        configureTestClass("{1:\"2\",\"3\":4,'5':6}", new Object[]{1, "3", "5"});

        // this has been added by me
//        configureTestClass("{\"abc\":\"hello\",\"3\":4,5:\"null\"}", new Object[]{"abc", "3", 5});
    }

    private void configureTestClass(String jsonObject, Object[] keys){
        this.jsonObject = jsonObject;
        this.typeReference = new TypeReference<>() {};
        this.keys = keys;
    }

    /**
     * This test method invokes the <i>parseObject</i> static method of the JSON class.
     * In particular, it checks that the conversion from the string representation of the jsonObject to a Map
     * between keys and values is well done.
     * The test is passed only if each value is associated to the correct key.
     */
    @Test
    public void test_map () {
        Map<Object, Object> map = JSON.parseObject(this.jsonObject, this.typeReference);
        Oracle oracle = new Oracle();
        Object[] expected = oracle.getExpectedResults(jsonObject, keys);
        boolean passed = true;
        for (int i = 0; i < this.keys.length; i++){
            if (!map.get(keys[i]).equals(expected[i])){
                passed = false;
                break;
            }
        }
        Assert.assertTrue(passed);
    }



    private static class Oracle{
        /**
         * This private method is used as a Software Testing oracle to know the
         * expected values of the JSON Object referred by the key in the input array of keys.
         * It's an oracle because it uses the library <b>org.json</b> as a trusted entity.
         *
         * @param jsonObject The string representing the JSONObject of the FastJSON project
         * @param keys The array of the objects representing the keys of the JSON Object
         * @return an array of Object, containing the values expected to be contained in the jsonObject, in the same order of their keys
         */
        private Object[] getExpectedResults(String jsonObject, Object[] keys){
            JSONObject obj = new JSONObject(jsonObject);
            List<Object> expected = new LinkedList<>();
            for (int i = 0; i < keys.length; i++){
                expected.add(i, obj.get(String.valueOf(keys[i])));
            }
            return expected.toArray();
        }
    }
}