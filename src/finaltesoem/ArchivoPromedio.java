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
public class ArchivoPromedio {
    private final String PromArch="Promedio.txt";
    List<Atributos> Promedio = new ArrayList<>();
    
    public boolean verificaArch1(){
    File archivo = new File(PromArch);
    if (!archivo.exists()) return false;
    else return true;
    }
    
    public boolean Grabar(List<Atributos> Promedio, int accion){
        //0 = anexa informacion
        //1 = reactualiza toda lainformacion
        FileWriter archivo;
        try{
            if(accion == 0){
                archivo = new FileWriter(PromArch, true);
            }else{
                archivo = new FileWriter(PromArch);
            }
           
            try(BufferedWriter bw = new BufferedWriter(archivo)){
              for(Atributos promedio : Promedio){
                  bw.write(conviertegson(promedio) + "\n");
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
            FileReader archivo = new FileReader(PromArch);
            BufferedReader br = new BufferedReader(archivo);
            while((cadena = br.readLine()) != null ){
                Promedio.add(convierteClase(cadena));
            }
            br.close();
            archivo.close();
        } catch(Exception ex){ return false; }
    return true;
    }
    
    public void Agregar(Atributos promedio){
    Promedio.add(promedio);
    }
    
    private String conviertegson(Atributos promedio){
    Gson gson = new Gson();
    return gson.toJson(promedio);
    }
    
    private Atributos convierteClase(String promedio){
    Gson gson = new Gson();
    return gson.fromJson(promedio, Atributos.class);
    
    }
    
    public List<Atributos>getPromedio(){
    return Promedio;
    }
}
