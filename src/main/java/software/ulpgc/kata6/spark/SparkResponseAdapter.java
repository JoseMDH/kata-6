package software.ulpgc.kata6.spark;

import software.ulpgc.kata6.architecture.io.Output;
import spark.Response;

public class SparkResponseAdapter implements Output {
    private final Response response;

    public SparkResponseAdapter(Response response) {
        this.response = response;
    }


    @Override
    public void setOutput(String output) {
        this.response.body(output);
    }
}
