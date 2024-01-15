
XSSFWorkbook workbook = new XSSFWorkbook();  
XSSFSheet sheet = workbook.createSheet("Daily Attendance Report");  
XSSFCellStyle xssfCellStyle = workbook.createCellStyle();  
xssfCellStyle.setWrapText(true);  
workbook.createFont().setBold(true);
