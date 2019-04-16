/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienterest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author simone
 */
public class ClienteRest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       ClienteRest clienteRest = new ClienteRest();
        
       //String urlConsultaAluno = "http://localhost:8080/WSRestServidor/webresources/generic/consulta";
       //String urlConsultaId = "http://localhost:8080/WSRestServidor/webresources/generic/consultaId/2";
       String urlConsultaCurso = "http://localhost:8080/WSRestServidor/webresources/generic/consultaCurso/Engenharia%20da%20Pesca";
      // String urlIncluirAluno = "http://localhost:8080/WSRestServidor/webresources/generic/incluirAluno";
      // String urlAlterarAluno = "http://localhost:8080/WSRestServidor/webresources/generic/alterarAluno";
      // String urlExcluirAluno = "http://localhost:8080/WSRestServidor/webresources/generic/excluirAluno/{id}";
       
       //String consultaTudoAluno = clienteRest.consultaAluno(urlConsultaAluno);
       //String consultaIdAluno = clienteRest.consultaAlunoId(urlConsultaId);
       String consultaCursoAluno = clienteRest.consultaAlunoCurso(urlConsultaCurso);
       //String incluirAluno = clienteRest.incluiAluno(urlIncluirAluno);
       //String alterarAluno = clienteRest.alteraAluno(urlAlterarAluno);
       
       //Mostra no formato json
       // System.out.println(consultaTudoAluno);
        //System.out.println(consultaIdAluno);
         System.out.println(consultaCursoAluno);
       // System.out.println(incluirAluno);
       // System.out.println(alterarAluno);
       
        
    }
    
    public String consultaAluno(String url) throws MalformedURLException, IOException{
        
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
        
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        
        System.out.println("\nEnviando requisição 'GET' para URL: " + url);
        
        System.out.println("Response Code: "+ responseCode);
        
        BufferedReader br = 
             new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while((inputLine = br.readLine()) != null){
            response.append(inputLine);
        }
        
        br.close();
        con.disconnect();
        
        return response.toString();
    }
    
    public String consultaAlunoId(String url) throws MalformedURLException, IOException{
        
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
        
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        
        System.out.println("\nEnviando requisição 'GET' para URL: " + url);
        
        System.out.println("Response Code: "+ responseCode);
        
        BufferedReader br = 
             new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while((inputLine = br.readLine()) != null){
            response.append(inputLine);
        }
        
        br.close();
        con.disconnect();
        
        return response.toString();
    }
    
    public String consultaAlunoCurso(String url) throws MalformedURLException, IOException{
        
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
        
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        
        System.out.println("\nEnviando requisição 'GET' para URL: " + url);
        
        System.out.println("Response Code: "+ responseCode);
        
        BufferedReader br = 
             new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while((inputLine = br.readLine()) != null){
            response.append(inputLine);
        }
        
        br.close();
        con.disconnect();
        
        return response.toString();
    }
    
    public String incluiAluno(String url) throws MalformedURLException, IOException{
        
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection)objURL.openConnection();
        
        con.setDoOutput(true);
        
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        
        //Formata em json o item da lista a ser inserido com POST
        String output = 
         "{\"curso\":\"Artes Plástica Litorânea\",\"id\":6,\"nome\":\"Bianca\"}";
        
        System.out.println("\nEnviando requisição 'POST' para URL: " + url);
        
        //Pega a conexão aberta em con (getOutputStream()) e faz OutputStream, ouseja, faz o fluxo de dados do cliente para o servidor
        OutputStream os = con.getOutputStream();
        
        //Escreve o output tranformado em Bytes        
        os.write(output.getBytes());
        
        
        int responseCode = con.getResponseCode();
        //Se retornar 200 significa que deu certo
        
        System.out.println("Response Code: "+ responseCode);
        
        //Armazena o retorno do método POST do servidor        
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
        StringBuffer response = new StringBuffer();        
        String inputLine;
        
        while((inputLine = br.readLine())!=null){
            response.append(inputLine);
        
        }
        
        br.close();
        con.disconnect();
        return response.toString();
    }
    
    
    public String alteraAluno(String url) throws MalformedURLException, IOException{
        
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection)objURL.openConnection();
        
        con.setDoOutput(true);
        
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        
        //Formata em json o item da lista a ser inserido com POST
        String output = "{\"curso\":\"Eletroeletronica\",\"id\":2,\"nome\":\"Prato\"}";
         
        
        System.out.println("\nEnviando requisição 'POST' para URL: " + url);
        
        //Pega a conexão aberta em con (getOutputStream()) e faz OutputStream, ouseja, faz o fluxo de dados do cliente para o servidor
        OutputStream os = con.getOutputStream();
        
        //Escreve o output tranformado em Bytes        
        os.write(output.getBytes());
        
        
        int responseCode = con.getResponseCode();
        //Se retornar 200 significa que deu certo
        
        System.out.println("Response Code: "+ responseCode);
        
        //Armazena o retorno do método POST do servidor        
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
        StringBuffer response = new StringBuffer();        
        String inputLine;
        
        while((inputLine = br.readLine())!=null){
            response.append(inputLine);
        
        }
        
        br.close();
        con.disconnect();
        return response.toString();
    }
}
