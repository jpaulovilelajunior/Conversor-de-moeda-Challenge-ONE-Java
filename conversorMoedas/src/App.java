/*  private static final String dolarComercial = "USD";
    private static final String EuroComercial = "EUR";
    private static final String RealComercial = "BRL";
    private static final String PesoArgComercial = "ARS";
    private static final String PesoChilComercial = "CLP";
    private static final String LibrasEsterComercial = "GBP"; */

public class App {
    public static void main(String[] args) throws Exception {
        String valorConvertido = alphaVantageAPI.ConectaAPI("GBP","CLP");

        System.out.println("Preço atual do dólar comercial: " + valorConvertido);
        MainFrame myFrame = new MainFrame();
        myFrame.initialize();
    }
}

