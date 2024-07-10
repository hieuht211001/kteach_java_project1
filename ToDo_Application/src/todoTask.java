import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class todoTask
{
    private String sTaskTitle;
    private String sEndDate;
    private boolean bIsCompleted;

    public boolean getBIsCompleted()
    {
        return bIsCompleted;
    }

    public void setBIsCompleted(boolean bIsCompleted)
    {
        this.bIsCompleted = bIsCompleted;
    }

    public String getsTaskTitle()
    {
        return sTaskTitle;
    }

    public void setsTaskTitle(String sTaskTitle) { this.sTaskTitle = sTaskTitle; }

    public String getsEndDate()
    {
        return sEndDate;
    }

    public boolean setsEndDate(String sEndDate)
    {
        if (!checkDateValid(sEndDate)) return false;
        this.sEndDate = sEndDate;
        return true;
    }

    public boolean checkDateValid(String sDate)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try
        {
            LocalDate endDate = LocalDate.parse(sDate, formatter);
            if (endDate.isBefore(LocalDate.now())) { return false;}
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public String convertToString()
    {
        return String.format("%s,%s,%s", sTaskTitle, sEndDate, bIsCompleted);
    }
}
