package software.ulpgc.kata6.spark;

import software.ulpgc.kata6.architecture.io.Input;
import spark.Request;

public class SparkRequestAdapter implements Input {
    private final Request request;

    public SparkRequestAdapter(Request request) {
        this.request = request;
    }

    @Override
    public String getInput(String parameter) {
        return this.request.queryParams(parameter);
    }
}