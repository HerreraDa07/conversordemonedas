public record Moneda(String base_code, String target_code, Double conversion_rate, Double conversion_result) {

    @Override
    public String toString() {
        return "\nDe: " + base_code +
                "\nA: " + target_code +
                "\nResultado de la conversión de " + base_code + " a " + target_code + ": " + conversion_result + " " + target_code +
                "\nEl valor de: 1 " + base_code + " equivale a: " + conversion_rate + " " + target_code +
                "\n";
    }
}