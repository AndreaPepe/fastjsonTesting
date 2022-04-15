import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPatch;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/*

Old test class

public class JSONPatchTest_0 extends TestCase {

    public void test_for_multi_0() throws Exception {
        String original = "{\n" +
                "  \"baz\": \"qux\",\n" +
                "  \"foo\": \"bar\"\n" +
                "}";

        String patch = "[\n" +
                "  { \"op\": \"replace\", \"path\": \"/baz\", \"value\": \"boo\" },\n" +
                "  { \"op\": \"add\", \"path\": \"/hello\", \"value\": [\"world\"] },\n" +
                "  { \"op\": \"remove\", \"path\": \"/foo\" }\n" +
                "]";

        String result = JSONPatch.apply(original, patch);
        assertEquals("{\"baz\":\"boo\",\"hello\":[\"world\"]}", result);
    }

    public void test_for_add_1() throws Exception {
        String original = "{}";

        String patch = "{ \"op\": \"add\", \"path\": \"/a/b/c\", \"value\": [ \"foo\", \"bar\" ] }";

        String result = JSONPatch.apply(original, patch);
        assertEquals("{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", result);
    }

    public void test_for_remove_0() throws Exception {
        String original = "{}";

        String patch = "{ \"op\": \"remove\", \"path\": \"/a/b/c\" }\n";

        String result = JSONPatch.apply(original, patch);
        assertEquals("{}", result);
    }

    public void test_for_remove_1() throws Exception {
        String original = "{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}";

        String patch = "{ \"op\": \"remove\", \"path\": \"/a/b/c\" }\n";

        String result = JSONPatch.apply(original, patch);
        assertEquals("{\"a\":{\"b\":{}}}", result);
    }

    public void test_for_replace_1() throws Exception {
        String original = "{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}";

        String patch = "{ \"op\": \"replace\", \"path\": \"/a/b/c\", \"value\": 42 }";

        String result = JSONPatch.apply(original, patch);
        assertEquals("{\"a\":{\"b\":{\"c\":42}}}", result);
    }

    public void test_for_move_0() throws Exception {
        String original = "{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}";

        String patch = "{ \"op\": \"move\", \"from\": \"/a/b/c\", \"path\": \"/a/b/d\" }";

        String result = JSONPatch.apply(original, patch);
        assertEquals("{\"a\":{\"b\":{\"d\":[\"foo\",\"bar\"]}}}", result);
    }

    public void test_for_copy_0() throws Exception {
        String original = "{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}";

        String patch = "{ \"op\": \"copy\", \"from\": \"/a/b/c\", \"path\": \"/a/b/e\" }";

        String result = JSONPatch.apply(original, patch);
        assertEquals("{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"],\"e\":[\"foo\",\"bar\"]}}}", result);
    }


    public void test_for_test_0() throws Exception {
        String original = "{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}";

        String patch = "{ \"op\": \"test\", \"path\": \"/a/b/c\", \"value\": \"foo\" }";

        String result = JSONPatch.apply(original, patch);
        assertEquals("false", result);
    }
}
*/

@RunWith(Parameterized.class)
public class JSONPatchTest_0 extends TestCase{

    /* Instance under test is not needed, because the tested method is static*/
    private String original;
    private String patch;
    private String expected;

    public JSONPatchTest_0 (String original, String patch, String expected){
        configureTestClass(original, patch, expected);
    }

    private void configureTestClass(String original, String patch, String expected){
        this.original = original;
        this.patch = patch;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters(){
        return Arrays.asList(new Object[][]{
                // original, patch, expected
                {"{\n" +
                        "  \"baz\": \"qux\",\n" +
                        "  \"foo\": \"bar\"\n" +
                        "}", "[\n" +
                        "  { \"op\": \"replace\", \"path\": \"/baz\", \"value\": \"boo\" },\n" +
                        "  { \"op\": \"add\", \"path\": \"/hello\", \"value\": [\"world\"] },\n" +
                        "  { \"op\": \"remove\", \"path\": \"/foo\" }\n" +
                        "]", "{\"baz\":\"boo\",\"hello\":[\"world\"]}"},
                {"{}", "{ \"op\": \"add\", \"path\": \"/a/b/c\", \"value\": [ \"foo\", \"bar\" ] }", "{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}"},
                {"{}", "{ \"op\": \"remove\", \"path\": \"/a/b/c\" }\n", "{}"},
                {"{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", "{ \"op\": \"remove\", \"path\": \"/a/b/c\" }\n", "{\"a\":{\"b\":{}}}"},
                {"{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", "{ \"op\": \"replace\", \"path\": \"/a/b/c\", \"value\": 42 }", "{\"a\":{\"b\":{\"c\":42}}}"},
                {"{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", "{ \"op\": \"move\", \"from\": \"/a/b/c\", \"path\": \"/a/b/d\" }", "{\"a\":{\"b\":{\"d\":[\"foo\",\"bar\"]}}}"},
                {"{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", "{ \"op\": \"copy\", \"from\": \"/a/b/c\", \"path\": \"/a/b/e\" }", "{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"],\"e\":[\"foo\",\"bar\"]}}}"},
                {"{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", "{ \"op\": \"test\", \"path\": \"/a/b/c\", \"value\": \"foo\" }", "false"}
        });
    }

    @Test
    public void genericTest() throws Exception {
        String result = JSONPatch.apply(original, patch);
        assertEquals(expected, result);
    }
}