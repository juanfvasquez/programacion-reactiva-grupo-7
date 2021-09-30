public class Scope {
    double var1;
    static double var2;

    public void probarVariableGlobal() {
        InterfaceScope is = new InterfaceScope() {
            @Override
            public double operacion(double x) {
                double resultado = x * var1;
                var1 = resultado;
                return resultado;
            }
        };
        is = x -> {
            double resultado = x * var1;
            var1 = resultado;
            return resultado;
        };
    }

    public void probarVariableEstatica() {
        InterfaceScope is = new InterfaceScope() {
            @Override
            public double operacion(double x) {
                double resultado = x * var2;
                var2 = resultado;
                return resultado;
            }
        };
        is = x -> {
            double resultado = x * var2;
            var2 = resultado;
            return resultado;
        };
    }

    public void probarVariableLocal() {
        double var3 = 10;
        InterfaceScope is = new InterfaceScope() {
            @Override
            public double operacion(double x) {
                double resultado = x * var3;
                return resultado;
            }
        };
        is = x -> {
          double resultado = x * var3;
//          var3 = resultado;
          return resultado;
        };
    }
}

interface InterfaceScope {
    public double operacion(double x);
}