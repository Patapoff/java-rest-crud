/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicamp.cotuca;

import java.util.ArrayList;
import javax.validation.constraints.Pattern.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author simone
 */
@Path("generic")
public class Service {
    
    private static ArrayList<Aluno> listaAluno = 
                           new ArrayList<Aluno>();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Service
     */
    public Service() {
        
        listaAluno.clear();
        
        Aluno a1 = new Aluno();
        a1.setId(1);
        a1.setNome("João");
        a1.setCurso("Ciência da Computação");
        
        Aluno a2 = new Aluno();
        a2.setId(2);
        a2.setNome("Prato");
        a2.setCurso("Ciência do Esporte");
        
        Aluno a3 = new Aluno();
        a3.setId(3);
        a3.setNome("Fábio");
        a3.setCurso("Engenharia da Pesca");
        
        Aluno a4 = new Aluno();
        a4.setId(4);
        a4.setNome("Kaio");
        a4.setCurso("Artes Plástica Litorânea");
        
        Aluno a5 = new Aluno();
        a5.setId(5);
        a5.setNome("Terra");
        a5.setCurso("Artes Plástica Litorânea");
        
        listaAluno.add(a1);
        listaAluno.add(a2);
        listaAluno.add(a3);
        listaAluno.add(a4);
        listaAluno.add(a5);
    }

    /**
     * Retrieves representation of an instance of br.unicamp.cotuca.Service
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of Service
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    /**
     *
     * @return
     */
    @GET
    @Path("/consulta")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> consultaAluno(){
        
        return listaAluno;
        
    }
    
    @GET
    @Path("consultaId/{Id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno consultaIdAluno(@PathParam("Id")int id){
        
        ArrayList<Aluno> listaTudo = consultaAluno();
        
        Aluno resultado = null;
        
        for (Aluno a: listaTudo){
            if(a.getId()== id)
            {
                resultado = a;
                break;
            }
        }
        
        return resultado;        
    }
    
    @GET
    @Path("consultaCurso/{Curso}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> consultaCursoAluno(@PathParam("Curso")String curso){
        
         ArrayList<Aluno> listaTudo = consultaAluno();
         
         ArrayList<Aluno> resultado = new ArrayList<Aluno>();   
         
         
         
         for(Aluno a : listaTudo){
            if(a.getCurso().equals(curso))
            {
               resultado.add(a);                
            }
         }        
         
         return resultado;
        
    } 
    
    
    
    @POST
    @Path("/incluirAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> incluiAluno(Aluno aluno)
    {        
        listaAluno.add(aluno);
        return listaAluno;        
    }
    
    @PUT
    @Path("/alterarAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> alterarAluno (Aluno aluno){        
       // ArrayList<Aluno> listaTudo = listaAluno;        
        for(Aluno a: listaAluno ){
            if(a.getId() == aluno.getId()){
                a.setNome(aluno.getNome());
                a.setCurso(aluno.getCurso());
               
            }              
        }
        return listaAluno;        
    }   
    
    @GET
    @Path("/excluirAluno/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public ArrayList<Aluno> excluirAluno(@PathParam("id") Integer id) throws Exception{        
        
        ArrayList<Aluno> listaTudo = consultaAluno(); 
        Aluno resultado = null;
        for(Aluno a: listaTudo){
            if(a.getId()== id){
                resultado = a;
                listaTudo.remove(resultado);
                break;
            }
        }        
        return listaTudo;
    }
}
