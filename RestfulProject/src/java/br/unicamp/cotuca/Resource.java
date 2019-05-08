/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicamp.cotuca;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import bd.daos.Alunos;
import bd.dbos.Aluno;
import bd.core.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;              
/**
 * REST Web Service
 *
 * @author u17197
 */
@Path("resource")
public class Resource {

    @Context
    private UriInfo context;
    private final String RA = "RA";
    private final String nome = "nome";
    private final String email = "email";
    
    
    private static ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();


    /**
     * Creates a new instance of Resource
     */
    public Resource() {
    }

    /**
     * Retrieves representation of an instance of br.unicamp.cotuca.Resource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/consultarAlunos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aluno> consultarAlunos() throws Exception{
        listaAluno.clear();
         //return listaAluno;
        try{
            MeuResultSet result = Alunos.getAlunos();
               
            result.beforeFirst();
            while(result.next())
            {
                Aluno atual = new Aluno(result.getString(RA), 
                        result.getString(nome), 
                        result.getString(email));     
                listaAluno.add(atual);
            }
        }
        catch(Exception e)
        {
           throw new Exception(e.getMessage());
        }
        
        return listaAluno;
               
    }
    
    
    @GET
    @Path("consultarAlunoRA/{RA}")
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno consultarRA(@PathParam("RA")String RA) throws Exception{
        if(RA == null || RA.equals("")) 
           throw new Exception("Insira um Nome");
        
        this.consultarAlunos();
        Aluno ret = new Aluno();
            for(Aluno a:listaAluno)
            {
                if(a.getRA().equals(RA))
                    return a;
            }
        return ret;       
    }
    
    @GET
    @Path("consultarAlunoNome/{Nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno consultarNome(@PathParam("Nome")String Nome) throws Exception{
       if(Nome == null || Nome.equals("")) 
           throw new Exception("Insira um Nome");
        
       this.consultarAlunos();
        Aluno ret = new Aluno();
            for(Aluno a:listaAluno)
            {
                if(a.getNome().equals(Nome))
                    return a;
            }
        return ret;        
    }
    
    @POST
    @Path("/incluirAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aluno> incluirAluno(Aluno aluno) throws Exception{
        try{
            if (aluno.getRA()==
                    null || aluno.getRA().trim().equals("")) {
                throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity(
                    "O RA do aluno é obrigatório").build());
            }
            else
            {   
                Alunos.incluir(aluno);
                return consultarAlunos();
            }
        }catch(Exception e)
        {
            throw new Exception(e.getMessage());
        }

    }
    /**
     * PUT method for updating or creating an instance of Resource
     * @param content representation for the resource
     */
    
    @PUT
    @Path("/alterarAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aluno> alterarAluno(Aluno aluno)throws Exception {
        try{
            if (aluno.getRA()==
                    null || aluno.getRA().trim().equals("")) {
                throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity(
                    "O RA do aluno é obrigatório").build());
            }
            else
            { 
            Alunos.alterar(aluno);
            return consultarAlunos();
            }
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
    
    @GET
    @Path("/excluirAluno/{RA}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Aluno> excluirAluno(@PathParam("RA") String RA)throws Exception 
    {
        
        try{
            if (RA==null || RA.trim().equals("")) {
                    throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity(
                    "O RA do aluno é obrigatório").build());
            }
            else
            { 
                Alunos.excluir(RA);
                return consultarAlunos();
            }
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
    
    

    
    /**
     * Retrieves representation of an instance of br.unicamp.cotuca.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
}