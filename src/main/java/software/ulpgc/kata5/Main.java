package software.ulpgc.kata5;

import spark.Request;
import spark.Response;
import spark.Spark;

public class Main {
    public static void main(String[] args) {
        Spark.port(8080);
        Spark.get("/factorial", (req,res)->new CommandExecutor(req,res).execute(new FactorialCommand()));
    }

    private record CommandExecutor(Request req, Response res) {
        public String execute(Command command) {
            Command.Output output = command.execute(input());
            res.status(output.code());
            return output.result();
        }

        private Command.Input input() {
            return new Command.Input() {
                @Override
                public String get(String key) {
                    return oneOf(req.params(key), req.queryParams(key));
                }

                private String oneOf(String a, String b) {
                    return a != null ? a : b;
                }
            };
        }
    }
}
