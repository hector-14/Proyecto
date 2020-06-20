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
public class ArchivoCorreo {
    
    private final String CorrArch="Correo.txt";
    List<Atributos> Correo = new ArrayList<>();
    
    public boolean verificaArch2(){
    File archivo = new File(CorrArch);
    if (!archivo.exists()) return false;
    else return true;
    }
    
    public boolean Grabar(List<Atributos> Correo, int accion){
        //0 = anexa informacion
        //1 = reactualiza toda lainformacion
        FileWriter archivo;
        try{
            if(accion == 0){
                archivo = new FileWriter(CorrArch, true);
            }else{
                archivo = new FileWriter(CorrArch);
            }
           
            try(BufferedWriter bw = new BufferedWriter(archivo)){
              for(Atributos correo : Correo){
                  bw.write(conviertegson(correo) + "\n");
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
            FileReader archivoCorreo = new FileReader(CorrArch);
            BufferedReader br = new BufferedReader(archivoCorreo);
            while((cadena = br.readLine()) != null ){
                Correo.add(convierteClase(cadena));
            }
            br.close();
            archivoCorreo.close();
        } catch(Exception ex){ return false; }
    return true;
    }
    
    public void Agregar(Atributos correo){
    Correo.add(correo);
    }
    
    private String conviertegson(Atributos correo){
    Gson gson = new Gson();
    return gson.toJson(correo);
    }
    
    private Atributos convierteClase(String correo){
    Gson gson = new Gson();
    return gson.fromJson(correo, Atributos.class);
    
    }
    
    public List<Atributos>getCorreo(){
    return Correo;
    }
}
