package threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Producer implements Runnable{
    BlockingQueue<String>blockingQueue = new LinkedBlockingQueue<>();
    String path = "./src/main/resources/voyna.txt";
    public Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        ArrayList<String>list = parseFile(path);
        try {
            for(String match: list) {
                blockingQueue.put(match);
                Thread.sleep(125);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
        }
    }

    public ArrayList<String> parseFile(String path){
        ArrayList<String>list = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\s?([Сс]трада[а-я]*)\\s?");
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "start";
            while (bufferedReader.ready()){
                line = bufferedReader.readLine();
                Matcher matcher = pattern.matcher(line);
                while(matcher.find()){
                    System.out.println(matcher.group(1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
