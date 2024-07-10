import java.io.*;

public class IOController
{
    private String sfilePath;
    public IOController(String _sFilePath)
    {
        this.sfilePath = _sFilePath;
    }

    public boolean readTaskFile()
    {
        try (FileReader fr = new FileReader(sfilePath);
             BufferedReader br = new BufferedReader(fr))
        {
            String sTaskLine;
            while ((sTaskLine = br.readLine()) != null)
            {
                String[] sTaskField = sTaskLine.split(",");
                todoTask newTask = new todoTask();
                newTask.setsTaskTitle(sTaskField[0]);
                newTask.setBIsCompleted(Boolean.parseBoolean(sTaskField[1]));
                if (!newTask.setsEndDate(sTaskField[2])) throw new RuntimeException();
                todoTask.todoTaskList.add(newTask);
            }
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean writeTaskFile()
    {
        try (FileWriter fw = new FileWriter(sfilePath);
             BufferedWriter bw = new BufferedWriter(fw))
        {
            for (todoTask _todoTask : todoTask.todoTaskList)
            {
                
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
