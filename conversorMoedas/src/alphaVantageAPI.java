import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import org.json.JSONObject;

public class alphaVantageAPI {
    private static final String API_KEY = "9A9RYAPQQPR0V32N";    
    private static final DecimalFormat df = new DecimalFormat("0.00");  
    
    public static String ConectaAPI(String moedaDE, String moedaPARA) {
 
        String URL_ALPHA_VANTAGE = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency="
         + moedaDE + "&to_currency=" 
         + moedaPARA +"&apikey=" 
         + API_KEY;


        try {
            /*Faz a conexão da URL utilizando o método GET */
            URL url = new URL(URL_ALPHA_VANTAGE);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            
            /*Checka se a conexão foi bem sucedida através do parâmetro 200 (Rest API formato) */
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            /*Salva o que foi puxado pela conexão em um buffer (memória) */
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder sb = new StringBuilder();
            /*Aguarda toda a informação da linha vim, (espera o fim da linha) */
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            /*Cria o objeto em Jason da string que foi criada, pegando o valor de Em tempo real da cotação (Realtime Currency...) Taxa de cambio (Exchange Rate) */
            JSONObject jsonObj = new JSONObject(sb.toString());
            double exchangeRate = jsonObj.getJSONObject("Realtime Currency Exchange Rate").getDouble("5. Exchange Rate");

            /*Desconecta da API */
            conn.disconnect();
            return df.format(exchangeRate);
        } 
        /*Caso haja erro na conexão, ele retorna qual o erro */
        catch (Exception e) {
            e.printStackTrace();
            return "Erro na nos valores, verifique conexão ou moeda desejada";
        }
    }

}