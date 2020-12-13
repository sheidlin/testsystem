package com.zust.stkdy.testsystem.utils;

import com.zust.stkdy.testsystem.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static void sloveFile(String path){
        File file=new File(path);
        if(!file.exists()){
            try {
                //创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static List<Student>getXlsStudentSnoList(MultipartFile file){
        try {
            List<Student>studentList=new ArrayList<>();
            InputStream inputStream=file.getInputStream();
            POIFSFileSystem poifsFileSystem=new POIFSFileSystem(inputStream);
            HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                System.out.println(physicalNumberOfRows);
                Student student;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row=sheet.getRow(j);
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        student=new Student();
                        HSSFCell cell= row.getCell(k);
                        cell.setCellType(cell.CELL_TYPE_STRING);
                        String sno=cell.getStringCellValue();
                        student.setSno(sno);
                        studentList.add(student);
                        //...
                    }

                }
            }
            return studentList;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static List<Student>getXlsxStudentSnoList(MultipartFile file){
        try {
            List<Student>studentList=new ArrayList<>();
            InputStream inputStream=file.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                System.out.println(physicalNumberOfRows);
                Student student;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    XSSFRow row=sheet.getRow(j);
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        student=new Student();
                        XSSFCell cell= row.getCell(k);
                        cell.setCellType(cell.CELL_TYPE_STRING);
                        String sno=cell.getStringCellValue();
                        student.setSno(sno);
                        studentList.add(student);
                        //...
                    }

                }
            }
            return studentList;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
