/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author u17197
 */
public class Client {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Client clienteRest = new Client();
        
        String urlConsultaAluno = "http://localhost:8080/RestfulProject/webresources/resource/consultarAlunos";
        String urlConsultaAlunoRA = "http://localhost:8080/RestfulProject/webresources/resource/consultarAlunoRA/";
        String urlConsultaAlunoNome = "http://localhost:8080/RestfulProject/webresources/resource/consultarAlunoNome/";
        String urlIncluirAluno = "http://localhost:8080/RestfulProject/webresources/resource/incluirAluno";
        String urlAlterarAluno = "http://localhost:8080/RestfulProject/webresources/resource/alterarAluno";
        String urlExcluirAluno = "http://localhost:8080/RestfulProject/webresources/resource/excluirAluno/";
        Boolean exit = false;
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        Gson gson = new Gson();
        
        while(!exit)
        {
            System.out.println("Bem-vindo(a) ao Cliente Rest!");
            System.out.println("O que deseja fazer?");
            System.out.println("1 - Consultar Alunos");
            System.out.println("2 - Consultar Aluno por RA");
            System.out.println("3 - Consultar Aluno por Nome");
            System.out.println("4 - Incluir Aluno");
            System.out.println("5 - Alterar Aluno");
            System.out.println("6 - Excluir Aluno");
            System.out.println("7 - Exit");
            System.out.println("");
            System.out.print("Escolha: ");
            try{
                String resp = leitor.readLine();
                
                switch(resp)
                {
                    case "1": {
                        String consultaTudoAluno =
                            clienteRest.consultaAluno(urlConsultaAluno);
                        ArrayList<Aluno> a = (ArrayList<Aluno>) gson.fromJson(consultaTudoAluno, new TypeToken<List<Aluno>>(){}.getType());
                        System.out.println(a);
                        break;
                    }
                    
                    case "2": {
                        System.out.print("Digite o ra: ");
                        String RA = leitor.readLine();
                        if (RAisValid(RA))
                        {
                            String consultaAlunoRA =
                            clienteRest.consultaAlunoRA(urlConsultaAlunoRA+RA);
                            Aluno a =(Aluno)gson.fromJson(consultaAlunoRA, Aluno.class);
                            System.out.println(a);

                        }
                        else
                        {
                            System.out.println("RA inválido.");
                        }  
                        break;
                    }
                    
                    case "3":{
                        System.out.print("Irmao digita o nome: ");
                        String Nome = leitor.readLine();
                        if (NomeisValid(Nome))
                        {
                            String consultaAlunoNome =
                            clienteRest.consultaAlunoNome(urlConsultaAlunoNome+Nome);
                            Aluno a =(Aluno) gson.fromJson(consultaAlunoNome, Aluno.class);
                            System.out.println(a); 
                        }
                        else
                        {
                            System.out.println("Nome vazio não é válido!");
                        }  
                        break;
                    }
                        
                    case "4":{
                        System.out.print("Digite o RA: ");
                        String RA = leitor.readLine();
                        System.out.println("");
                        System.out.print("Digite o Nome: ");
                        String nome = leitor.readLine();
                        System.out.println("");
                        System.out.print("Digite o Email: ");
                        String email = leitor.readLine();
                        System.out.println("");
                        
                        Aluno a = new Aluno(RA, nome, email);
                        String alunoJson = gson.toJson(a);
                        clienteRest.incluiAluno(urlIncluirAluno, alunoJson);
                        
                        System.out.println("O aluno " + nome + "foi incluído!");
                       break;
                    }
                        
                    case "5":{
                        System.out.print("Digite o RA do aluno a ser alterado: ");
                        String RA = leitor.readLine();
                        System.out.println("");
                        System.out.print("Digite o Nome novo: ");
                        String nome = leitor.readLine();
                        System.out.println("");
                        System.out.print("Digite o Email novo: ");
                        String email = leitor.readLine();
                        System.out.println("");
                        
                        Aluno a = new Aluno(RA, nome, email);
                        String alunoJson = gson.toJson(a);
                        clienteRest.alteraAluno(urlAlterarAluno, alunoJson);
                        
                        System.out.println("O aluno " + nome + " foi alterado."
                                + "!");
                       break;
                    }
                        
                    case "6":{
                        System.out.print("Digite o RA do aluno a ser excluído:");
                        String RA = leitor.readLine();
                        if (RAisValid(RA))
                        {
                            String ExcluiAluno =
                            clienteRest.consultaAlunoRA(urlExcluirAluno+RA);
                            
                            System.out.println("Aluno excluído com sucesso.");
                        }
                        else
                        {
                            System.out.println("RA inválido.");
                        }  
                        break;
                    }
                        
                    case "7": exit = true; break;
                }
                System.out.println("Pressione [ENTER] para continuar...");
                leitor.readLine();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
                    
        }
        try{
         
        
       //String consultaTudoAluno = clienteRest.consultaAluno(urlConsultaAluno);
       //String incluirAluno = clienteRest.incluiAluno(urlIncluirAluno);
       //String alterarAluno = clienteRest.alteraAluno(urlAlterarAluno);
       
       //Mostra no formato json
       //System.out.println(consultaTudoAluno);
       //System.out.println(incluirAluno);
       //System.out.println(alterarAluno);
        }catch(Exception e)
        {
          e.printStackTrace();
        }
    }
    
    public String consultaAlunoRA(String url) throws MalformedURLException, IOException{
        
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
    
    public String consultaAlunoNome(String url) throws MalformedURLException, IOException{
        
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
    
    public String incluiAluno(String url, String output) throws MalformedURLException, IOException{
        
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection)objURL.openConnection();
        
        con.setDoOutput(true);
        
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        
        //Formata em json o item da lista a ser inserido com POST
        
        
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
    
    public String alteraAluno(String url, String output) throws MalformedURLException, IOException{
        
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection)objURL.openConnection();
        
        con.setDoOutput(true);
        
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        
       
        
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
    
    static public boolean RAisValid(String RA)
    {
        if (RA.length() != 5)
            return false;
        if (!RA.matches("[0-9]+"))
            return false;
        
        return true;
    }
    
    static public boolean NomeisValid(String Nome)
    {
        
        if (Nome.isEmpty())
            return false;
        
        return true;
    }
}
