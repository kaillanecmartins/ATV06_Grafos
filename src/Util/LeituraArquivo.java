package Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class LeituraArquivo {
    File arquivo;

    public LeituraArquivo(String path){
        arquivo = new File(path);
    }

    public void escrever(Object dado){
        try {
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(dado.toString());
            
            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public Object lerEnesima(int line){
        Object result = null;
        try {
            FileReader fr = new FileReader( arquivo );
            BufferedReader br = new BufferedReader(fr);

            for (int i = 1; i < line && br.ready(); i++) {
                br.readLine();
            }
            result = br.readLine();
            
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Object[] lerTudo(){
        ArrayList<Object> result = new ArrayList<Object>();

        try {
            FileReader fr = new FileReader( arquivo );
            BufferedReader br = new BufferedReader(fr);

            while(br.ready()){
                result.add(br.readLine());
            }
            
            
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toArray();
    }
}
