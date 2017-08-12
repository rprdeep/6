package exceldata;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App {
    private static XSSFWorkbook myWorkBook;
	public static void main( String [] args ) {
    String f1="/src/file/test.xlsx";	
    String workingDirectory = System.getProperty("user.dir");
	System.out.println("workingDirectory is: "+workingDirectory);
		String  fileName=workingDirectory+f1;
		 System.out.println("absolute path of file is: "+fileName);

        Vector<List<XSSFCell>> dataHolder=read(fileName);
        saveToDatabase(dataHolder);
    }
    public static Vector<List<XSSFCell>> read(String fileName)    {
        Vector<List<XSSFCell>> cellVectorHolder = new Vector<List<XSSFCell>>();
        try{
            FileInputStream myInput = new FileInputStream(fileName);
            myWorkBook = new XSSFWorkbook(myInput);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIter = mySheet.rowIterator();
            while(rowIter.hasNext()){
                XSSFRow myRow = (XSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                //Vector cellStoreVector=new Vector();
                List list = new ArrayList();
                while(cellIter.hasNext()){
                    XSSFCell myCell = (XSSFCell) cellIter.next();
                    list.add(myCell);
                }
                cellVectorHolder.addElement(list);
            }
        }catch (Exception e){e.printStackTrace(); }
        return cellVectorHolder;
    }
    private static void saveToDatabase(Vector dataHolder) {
        String ClientAdd="";
        String Page="";
        String AccessDate="";
        String   ProcessTime="";
        String Bytes="";
        System.out.println(dataHolder);

        for(Iterator iterator = dataHolder.iterator();iterator.hasNext();) {
            List list = (List) iterator.next();
            System.out.println(list);

            ClientAdd = list.get(0).toString();
            Page = list.get(1).toString();
            AccessDate = list.get(2).toString();
            ProcessTime = list.get(3).toString(); 
        }



        }
    }