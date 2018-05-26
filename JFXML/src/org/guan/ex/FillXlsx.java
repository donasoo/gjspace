/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guan.ex;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FillXlsx {

    private StringBuffer unitName;
    private String fileName = "000";

    private FileOutputStream fos;
    private String[] data;

    XSSFSheet sh;

    private OPCPackage pkg;
    private OPCPackage allpkg;
    private XSSFWorkbook allwb;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;

    private ArrayList<TableHeadCell> thlist;

    private int firstData;
    private int nTableHead;
    private int colnum;
    private int rownum;
    private String firstcell;
    private int firstrow;
    private int firstcol;

    private String tablename;
    private boolean pdf = false;
    private boolean first = false;

    private ArrayList<String> namelist;
    private final TaskConfig taskconfig;

    private double realvalue = 0.0;
    private long intvalue = 0;
    private int currRowNum;
    private int currColNum;

    public FillXlsx(TaskConfig taskconfig) {
        this.taskconfig = taskconfig;
        setup();
        readData();
    }

    private void setup() {

        data = new String[750];
        unitName = new StringBuffer();
        namelist = new ArrayList<String>();
        thlist = new ArrayList<TableHeadCell>(5);

        firstrow = taskconfig.startCell.charAt(1) - '1';
        firstcol = taskconfig.startCell.charAt(0) - 'A';
    }

    private void readData() {

        BufferedReader reader = null;
        try {

            InputStreamReader isr = new InputStreamReader(new FileInputStream(taskconfig.dataFile), getEncoding());

            reader = new BufferedReader(isr);
            //reader = new BufferedReader(new FileReader(taskconfig.dataFile));
            String rowString = null;

            rowString = reader.readLine();

            while ((rowString = reader.readLine()) != null) {
                process(rowString);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * process one row data
     *
     * @param tempString
     */
    private void process(String rowString) {

        //split to array
        data = rowString.split(",");

        //get xlsx sheet file name
        buildFileName();

        System.out.println(unitName.toString());

        excel();

    }

    /**
     * fill excel file
     */
    private void excel() {
        try {
            pkg = OPCPackage.open(taskconfig.templateFile, PackageAccess.READ_WRITE);
            workbook = new XSSFWorkbook(pkg);
            sheet = workbook.getSheetAt(0);

            //ÌîÐ´×ÛºÏ»ú¹ØÃû³Æ
            if (taskconfig.nameNeed) {
                fillName();
            }
            if (taskconfig.codeNeed) {
                fillCode();
            }

            if (taskconfig.tailNeed) {
                fillTail();
            }

            currRowNum = firstrow;
            currColNum = firstcol - 1;

            for (int i = 3; i < data.length - 5; i++) {

                cell = getNextCell();

                if (data[i].isEmpty()) {
                } else if (data[i].contains(".")) {

                    realvalue = Double.parseDouble(data[i]);
                    if (realvalue > 0.01) {
                        cell.setCellValue(realvalue);
                    }
                } else {
                    intvalue = Long.parseLong(data[i]);
                    if (intvalue != 0) {
                        cell.setCellValue(intvalue);
                    }
                }

            }

            fos = new FileOutputStream(fileName + ".xlsx");
            workbook.write(fos);
            pkg.revert();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }

    }

    private void buildFileName() {
        fileName = data[0];
    }

    private void fillCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void fillTail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    find return next enable cell
     */
    private XSSFCell getNextCell() {

        if (currColNum - firstcol + 1 == taskconfig.colNum) {
            currColNum = firstcol;
            currRowNum++;
        } else {
            currColNum++;
        }

        row = sheet.getRow(currRowNum);

        if (row == null) {
            row = sheet.createRow(currRowNum);
        }

        cell = row.getCell(currColNum);

        if (cell == null) {
            cell = row.createCell(currColNum);
        } else {
            if (cell.getStringCellValue().endsWith("-")) {
                getNextCell();
            }
        }

        return cell;
    }

    private void fillName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 复制原有sheet的合并单元格到新创建的sheet
     *
     * @param sheetCreat 新创建sheet
     * @param sheet 原有的sheet
     */
    public static void copysheet(XSSFSheet fromSheet, XSSFSheet toSheet) {
        int sheetMergerCount = fromSheet.getNumMergedRegions();

        List<CellRangeAddress> list = fromSheet.getMergedRegions();
        Iterator<CellRangeAddress> tor = list.iterator();

        while (tor.hasNext()) {
            toSheet.addMergedRegion(tor.next());
        }
    }

    private String getEncoding() {

        String code="GBK";
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(taskconfig.dataFile);
            byte[] head = new byte[3];
            inputStream.read(head);
            inputStream.close();
            for(int i =0; i<3; i++){
                System.out.print(head[i]);
            }
            if (head[0] == -17 && head[1] == -69 && head[2] == -65) {
                code="UTF-8";
            } 
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FillXlsx.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FillXlsx.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return code;

        }

    }
