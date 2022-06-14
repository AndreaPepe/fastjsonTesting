import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapTest2 {

    /* The instance to be tested is not needed because the tested method is static*/

    private String jsonObject;
    private TypeReference<Map<Object,Object>> typeReference;

    public MapTest2 (){
        configureTestClass("{1:\"2\",\"3\":4,'5':6}");
    }

    private void configureTestClass(String jsonObject){
        this.jsonObject = jsonObject;
        this.typeReference = new TypeReference<>() {};
    }

    /**
     * This test method invokes the <i>parseObject</i> static method of the JSON class.
     * In particular, it checks that the conversion from the string representation
     * of the jsonObject to a Map between keys and values is well done.
     * The test is passed only if each value is associated to the correct key.
     */
    @Test
    public void test_map () {
        Map<Object, Object> map = JSON.parseObject(this.jsonObject, this.typeReference);

        // Use an oracle to compute the keys and the expected values bounded with those keys
        Oracle oracle = new Oracle();
        Object[] keys = oracle.getKeys(jsonObject);
        Object[] expected = oracle.getExpectedResults(jsonObject, keys);

        boolean passed = true;
        for (int i = 0; i < keys.length; i++){
            if (map.get(keys[i]) == null){
                if (! (map.get(keys[i]) == expected[i])){
                    passed = false;
                    break;
                }
            }
            else if (!map.get(keys[i]).equals(expected[i])){
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
         * @return an array of Object, containing the values expected to be contained in the jsonObject,
         * in the same order of their keys
         */
        private Object[] getExpectedResults(String jsonObject, Object[] keys){
            JSONObject obj = new JSONObject(jsonObject);
            List<Object> expected = new LinkedList<>();
            for (int i = 0; i < keys.length; i++){
                expected.add(i, obj.get(String.valueOf(keys[i])));
            }
            return expected.toArray();
        }

        /**
         * This private method is used to retrieve the array of keys
         * of the json object string representation in input.
         * @param jsonObject The string representing the JSONObject of the FastJSON project
         * @return an array of Objects, containing the keys of the JSONObject.
         */
        private Object[] getKeys(String jsonObject){
            String noBrackets = jsonObject.replace("{", "");
            noBrackets = noBrackets.replace("}","");
            noBrackets = noBrackets.replace(" ", "");
            noBrackets = noBrackets.replace("\n", "");
            noBrackets = noBrackets.replace("\r", "");
            noBrackets = noBrackets.replace("\t", "");

            String[] pairs = noBrackets.split(",");

            List<Object> ret = new LinkedList<>();
            for (int i = 0; i < pairs.length; i++){
                String key = pairs[i].split(":")[0];
                if (key.contains("\"") || key.contains("'")){
                    ret.add(i, key.replace("\"", "").replace("'", ""));
                }
                else if (key.contains("."))
                    ret.add(i, Float.valueOf(key));
                else
                    ret.add(i, Integer.valueOf(key));
            }
            return ret.toArray();
        }
    }
}