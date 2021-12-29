package xingming;

import java.io.*;


public class FileIO implements Serializable {

    
    public static String[] readAllLines(String file) throws IOException {
        String[] stu = new String[1000];
        BufferedReader read = new BufferedReader(new FileReader(file));
        for(int i=0;(stu[i]=read.readLine())!=null;i++){ }
        read.close();
        return stu;
    }

    
    public static void writeLine(String file,String write) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        writer.append(write);
        writer.close();
    }

  
    public static void writeArray(String file,String[] write) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        for(int i=0;i<write.length&&write[i]!=null;i++) {
            writer.append(write[i]);
        }
        writer.close();
    }

  
    public static <Stirng> Object[] readAllObject(Stirng file) throws IOException, ClassNotFoundException {
        Object[] obj = new Object[1000];
        Object temp;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream((File) file));
        for(int i=0;(temp=in.readObject())!=null;i++) {
            obj[i]=temp;
        }
        in.close();
        return obj;
    }

 
    public static void writeObject(String file,Object wirte) throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file,true));
        if(new FileInputStream(file).available()!=0){
            Object[] obj = readAllObject(file);
            Object[] obj2 = new Object[obj.length+1];
            for(int i=0;i<obj.length;i++){
                obj2[i]=obj[i];
            }
            for(int i=0;i<obj2.length;i++){
                out.writeObject(obj2[i]);
            }
        }
        out.close();
    }
}
