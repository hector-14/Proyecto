/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finaltesoem;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chelis
 */
public class ArchivoMatricula {
    private final String MatrArch="Matricula.txt";
    List<Atributos> Matricula = new ArrayList<>();
    
    public boolean verificaArch(){
    File archivo = new File(MatrArch);
    if (!archivo.exists()) return false;
    else return true;
    }
    
    public boolean Grabar(List<Atributos> Datos, int accion){
        //0 = anexa informacion
        //1 = reactualiza toda lainformacion
        FileWriter archivo;
        try{
            if(accion == 0){
                archivo = new FileWriter(MatrArch, true);
            }else{
                archivo = new FileWriter(MatrArch);
            }
           
            try(BufferedWriter bw = new BufferedWriter(archivo)){
              for(Atributos matricula : Matricula){
                  bw.write(conviertegson(matricula) + "\n");
              }
              bw.close();
            }
            archivo.close();
        }catch(Exception ex){ return false; }
    return true;
    }
    
    public boolean leer(){
        String cadena="";
        try{
            FileReader archivo = new FileReader(MatrArch);
            BufferedReader br = new BufferedReader(archivo);
            while((cadena = br.readLine()) != null ){
                Matricula.add(convierteClase(cadena));
            }
            br.close();
            archivo.close();
        } catch(Exception ex){ return false; }
    return true;
    }
    
    public void Agregar(Atributos matricula){
    Matricula.add(matricula);
    }
    
    private String conviertegson(Atributos matricula){
    Gson gson = new Gson();
    return gson.toJson(matricula);
    }
    
    private Atributos convierteClase(String matricula){
    Gson gson = new Gson();
    return gson.fromJson(matricula, Atributos.class);
    
    }
    
    public List<Atributos>getMatricula(){
    return Matricula;
    }
}
