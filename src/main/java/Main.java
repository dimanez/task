import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static final String PATH = "SPF_SCHEMA";
    private static final String JSON_FILE ="IObjectData.json";
    private IObjectDataLoader dataLoader;

    public static void main(String[] args) {
        new Main().getIObjectData();
    }

    private void getIObjectData(){
        dataLoader = new IObjectDataLoader();
        try {
            processFilesFromFolder(new File(System.getenv(PATH)));
            toJSON(dataLoader);
            System.out.println(dataLoader.getResponse().size() + " Записей.");
        }catch (Exception e){
            System.err.println("Не найдена переменная окружения SPF_SCHEMA!");
        }
    }

    /**
     * Получаем все файлы с расширением xml из переменной окружения SPF_SCHEMA
     * @param folder catalog
     */
    private void processFilesFromFolder(File folder)
    {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries){

            if (entry.isDirectory()){
                processFilesFromFolder(entry);
                continue;
            }
            if (entry.getName().toLowerCase().endsWith(".xml")){
                dataLoader.readFile(entry.getAbsolutePath());
            }
        }
    }

    /**
     * Создаем JSON файл с полученными данными.
     */
    private void toJSON(Object object){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        try (FileWriter fileWriter = new FileWriter(JSON_FILE)){
            fileWriter.write(gson.toJson(object));
            System.out.println("Create json file.");
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(gson.toJson(object));
    }
}
