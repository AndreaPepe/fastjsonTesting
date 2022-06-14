import com.alibaba.fastjson.JSONPatch;
import org.junit.Assert;
import org.junit.Test;

public class JSONPatchTest_0 {

    /* Instance under test is not needed, because the tested method is static*/
    private String original;
    private String patch;
    private String expected;

    public JSONPatchTest_0() {
        configureTestClass("{\n" + "  \"baz\": \"qux\",\n" + "  \"foo\": \"bar\"\n" + "}",
                  "[\n" +
                          "  { \"op\": \"replace\", \"path\": \"/baz\", \"value\": \"boo\" },\n" +
                        "  { \"op\": \"add\", \"path\": \"/hello\", \"value\": [\"world\"] },\n" +
                        "  { \"op\": \"remove\", \"path\": \"/foo\" }\n" + "]",
                "{\"baz\":\"boo\",\"hello\":[\"world\"]}");

//        configureTestClass("{}", "{ \"op\": \"add\", \"path\": \"/a/b/c\", \"value\": [ \"foo\", \"bar\" ] }",
//                "{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}");
//
//        configureTestClass("{}", "{ \"op\": \"remove\", \"path\": \"/a/b/c\" }\n",
//                "{}");
//        configureTestClass("{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", "{ \"op\": \"remove\", \"path\": \"/a/b/c\" }\n",
//                "{\"a\":{\"b\":{}}}");
//        configureTestClass("{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", "{ \"op\": \"replace\", \"path\": \"/a/b/c\", \"value\": 42 }",
//                "{\"a\":{\"b\":{\"c\":42}}}");
//        configureTestClass("{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", "{ \"op\": \"move\", \"from\": \"/a/b/c\", \"path\": \"/a/b/d\" }",
//                "{\"a\":{\"b\":{\"d\":[\"foo\",\"bar\"]}}}");
//        configureTestClass("{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", "{ \"op\": \"copy\", \"from\": \"/a/b/c\", \"path\": \"/a/b/e\" }",
//                "{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"],\"e\":[\"foo\",\"bar\"]}}}");
//        configureTestClass("{\"a\":{\"b\":{\"c\":[\"foo\",\"bar\"]}}}", "{ \"op\": \"test\", \"path\": \"/a/b/c\", \"value\": \"foo\" }",
//                "false");
    }

    private void configureTestClass(String original, String patch, String expected) {
        this.original = original;
        this.patch = patch;
        this.expected = expected;
    }

    @Test
    public void testJSONPatch() {
        String result = JSONPatch.apply(original, patch);
        Assert.assertEquals(expected, result);
    }
}