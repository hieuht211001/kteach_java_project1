import java.util.*;

public class AppFunc implements IAppFunc
{
    public static List<todoTask> todoTaskList;
    public IOController ioController;
    Scanner scanner = new Scanner(System.in);
    public enum APP_STATE
    {
        RUNNING,
        EXIT
    }
    public AppFunc()
    {
        todoTaskList = new ArrayList<>();
        ioController = new IOController("C:\\Users\\HLC_2021\\Desktop\\todoAppData.csv");
    }
    @Override
    public void appStart()
    {
        System.out.println("Welcome! \n");
        System.out.println(showTasksInfo());
        while (true)
        {
            System.out.println(showFunctionList());
            System.out.print("Input: ");
            if (taskSelectionManager() == APP_STATE.EXIT) break;
        }
    }

    @Override
    public void createTask()
    {
        try
        {
            todoTask createdTask = taskDataCreate();
            if (createdTask == null) throw new Exception();
            todoTaskList.add(createdTask);
        }
        catch (Exception e)
        {
        }
        finally
        {
            System.out.println(showTaskList());
        }
    }

    @Override
    public void editTask()
    {
        try
        {
            int iTaskNo = selectTaskNo() - 1;
            if (iTaskNo > todoTaskList.size()) throw new Exception();
            todoTask changedTask = taskDataCreate();
            todoTaskList.set(iTaskNo,changedTask);
        }
        catch (Exception e)
        {
            System.out.println("Error Occured");
        }
        finally
        {
            System.out.println(showTaskList());
        }
    }

    public String showTaskList()
    {
        String sTaskNotify = "";
        String sRemainTaskNumNotify = "";
        int iRemainTaskNum = 0;
        int iTaskNo = 1;
        for (todoTask seperateTask : todoTaskList)
        {
            sTaskNotify += (String.format("%d. %s", iTaskNo, seperateTask.getsTaskTitle()));
            if (seperateTask.getBIsCompleted())
            {
                sTaskNotify += " (Done) \n";
            }
            else
            {
                sTaskNotify += "\n";
                iRemainTaskNum++;
            }
            iTaskNo++;
        }
        if (iRemainTaskNum != 0)
        {
            sRemainTaskNumNotify = "You have " + iRemainTaskNum + " TODO Tasks" + "\n";
        }
        else
        {
            sRemainTaskNumNotify = "You have no more TODOs left!!!" + "\n";
        }
        return sRemainTaskNumNotify + sTaskNotify;
    }

    @Override
    public void finishTask()
    {
        int iTaskNo = selectTaskNo() - 1;
        try
        {
            todoTask selectedTask = new todoTask();
            selectedTask = todoTaskList.get(iTaskNo);
            if (!selectedTask.getBIsCompleted())
            {
                selectedTask.setBIsCompleted(true);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error Occured");
        }
        finally
        {
            System.out.println(showTaskList());
        }
    }

    @Override
    public void deleteTask()
    {
        int iTaskNo = selectTaskNo() - 1;
        try
        {
            todoTaskList.remove(iTaskNo);
            System.out.println("Task Deleted Successfully \n");
            System.out.println(showTaskList());
        }
        catch (Exception e)
        {
            System.out.println("Error Occured");
            System.out.println(showTaskList());
        }
    }

    @Override
    public void exitApp()
    {
        ioController.writeTaskFile();
        System.out.println("Byebye!");
    }

    public APP_STATE taskSelectionManager()
    {
        String strSelection = scanner.nextLine();
        switch (strSelection)
        {
            case "1": createTask() ; return APP_STATE.RUNNING;
            case "2": editTask(); return APP_STATE.RUNNING;
            case "3": finishTask(); return APP_STATE.RUNNING;
            case "4": deleteTask(); return APP_STATE.RUNNING;
            case "5": exitApp(); return APP_STATE.EXIT;
            default: return APP_STATE.RUNNING;
        }
    }

    private String showFunctionList()
    {
        String strFunctionList = "";
        strFunctionList += "1. Create TODO \n";
        strFunctionList += "2. Edit TODO \n";
        strFunctionList += "3. Finish TODO \n";
        strFunctionList += "4. Delete TODO \n";
        strFunctionList += "5. Exit \n";

        return strFunctionList;
    }

    private String showTasksInfo()
    {
        String sTaskNotify;
        if (ioController.readTaskFile())
        {
            if (!todoTaskList.isEmpty())
            {
                sTaskNotify = showTaskList();
            }
            else sTaskNotify = "You have no more TODOs left!!!" + "\n";
        }
        else sTaskNotify = "You have no more TODOs left!!!" + "\n";
        return sTaskNotify;
    }

    private todoTask taskDataCreate()
    {
        System.out.print("Title: ");
        String strTitle = scanner.nextLine();
        System.out.print("Until: ");
        String strEndDate = scanner.nextLine();

        todoTask newCreatedTask = new todoTask();
        newCreatedTask.setsTaskTitle(strTitle);
        if (!newCreatedTask.setsEndDate(strEndDate))
        {
            System.out.println("Invalid Task Data");
            return null;
        }
        else
        {
            System.out.println("Saved!!!");
            return newCreatedTask;
        }
    }

    private int selectTaskNo()
    {
        try
        {
            System.out.print("Edit Todo Number: ");
            String sEditTaskNo = scanner.nextLine();
            return Integer.parseInt(sEditTaskNo);
        }
        catch (Exception e)
        {
            return -1;
        }
    }
}
